package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;



public class Cell {
    private Direction orientation;
    private List<Direction> possibleConnectionsSides;
    ImageIcon imageIcon;
    //private List<Cell> neighbors; // List of neighboring cells


    public Cell() {
        this.possibleConnectionsSides = new ArrayList<>();
        orientation = Direction.WEST;
        imageIcon = new ImageIcon();
    }

    public List<Direction> getPossibleConnectionsSides() {
        return possibleConnectionsSides;
    }



    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }

    public void rotateRight() {
        List<Direction> newPossible = new ArrayList<>();
        for (var connectionsSide: possibleConnectionsSides){
            newPossible.add(connectionsSide.nextPosition());
        }
        possibleConnectionsSides = newPossible;
    }

    public void rotateLeft() {
        for (var connectionsSide: possibleConnectionsSides){
            List<Direction> newPossible = new ArrayList<>();
            newPossible.add(connectionsSide.previousPosition());
            possibleConnectionsSides = newPossible;
        }
    }
    public void addPossibleConnectionSide(Direction dr){
        possibleConnectionsSides.add(dr);
    }

    public boolean isConnectable(Cell otherCell, Direction direction) {
        if(otherCell == null){
            return false;
        }
        return possibleConnectionsSides.contains(direction)
                && otherCell.possibleConnectionsSides.contains(direction.getOpposite());
    }

    public Direction nextPosition(){
        orientation = orientation.nextPosition();
        return orientation;
    }

    BufferedImage rotate(BufferedImage image, Double degrees) {
        double radians = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
        int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);

        BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotate.createGraphics();
        int x = (newWidth - image.getWidth()) / 2;
        int y = (newHeight - image.getHeight()) / 2;
        AffineTransform at = new AffineTransform();
        at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
        at.translate(x, y);
        g2d.setTransform(at);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return rotate;
    }

    BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    public ImageIcon rotateImageIcon(double degrees){
        try {
            imageIcon = new ImageIcon(rotate(toBufferedImage(imageIcon.getImage()), degrees));
        }
        finally {
            return imageIcon;
        }

    }
}
