package org.example;

import javax.swing.*;

public class Grid {

    private Cell[][] cells; // 2D array of cells
    private int cellWidth;
    private int cellHeight;

    public Grid() {

    }

    public Cell getCell(int i, int j){
        return cells[i][j];
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }



    public Grid(int height, int width) {
        cellHeight = height;
        cellWidth = width;
//        Random rnd = new Random();
//        Direction dr = null;
        this.cells = new Cell[cellHeight][cellWidth];
        for (int i = 0; i < cellHeight; i++) {
            for (int j = 0; j < cellWidth; j++) {
                cells[i][j] = new Cell();
                /*int myRandom = rnd.nextInt(0, 5);
                switch (myRandom) {
                    case 0:
                        dr = Direction.NORTH;
                        break;
                    case 1:
                        dr = Direction.EAST;
                        break;
                    case 2:
                        dr = Direction.SOUTH;
                        break;
                    case 3:
                        dr = Direction.WEST;
                        break;
                    case 4:
                        dr = Direction.ZERO;
                        break;
                }
//                System.out.print(i);
//                System.out.print(j);
//                System.out.print(dr);
//                System.out.println(myRandom);

                 */
            }
        }
//        cells[2][1].addPossibleConnectionSide(Direction.NORTH);
//        cells[2][2].addPossibleConnectionSide(Direction.WEST);

        // Initialize connections between cells (basic horizontal and vertical)
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                if (i > 0) {
//                    cells[i][j].addNeighbors(cells[i - 1][j]);
//                }
//                if (j > 0) {
//                    cells[i][j].addNeighbors(cells[i][j - 1]);
//                }
//            }
//        }
    }

    public Grid(Cell[][] cells) {
        this.cells = cells;
    }

    public ImageIcon getCellImageIcon(int i, int j){
        return cells[i][j].getImageIcon();
    }
    public void setCellImageIcon(int i, int j, ImageIcon imageIcon){
        cells[i][j].setImageIcon(imageIcon);
    }
    public void rotateCellRight(int i, int j){
        cells[i][j].rotateRight();
    }

    public Direction getCellOrientation(int i, int j){
        return cells[i][j].getOrientation();
    }

    public void setCellOrientation(int i, int j, Direction orientation){
        cells[i][j].setOrientation(orientation);
    }

    Direction nextCellOrientation(int i, int j){
        return cells[i][j].nextPosition();
    }

    ImageIcon rotateCellImageIcon(int i, int j, double degrees){
        return cells[i][j].rotateImageIcon(degrees);
    }

    public void printGrid() {
        for (int i = 0; i < cellHeight; i++) {
            for (int j = 0; j < cellWidth; j++) {
                if (!cells[i][j].getPossibleConnectionsSides().isEmpty()) {
                    System.out.print(cells[i][j].getPossibleConnectionsSides());
                } else {
                    System.out.print("[ZERO]");
                }
            }
            System.out.println();
        }
    }

    public boolean isSolved() {
        for (int i = 0; i < cellHeight; i++) {
            for (int j = 0; j < cellWidth; j++) {
                for (Direction direction : cells[i][j].getPossibleConnectionsSides()) {
                    if (!cells[i][j].isConnectable(getNeighbor(i, j, direction), direction)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private Cell getNeighbor(int i, int j, Direction direction) {
        switch (direction) {
            case NORTH:
                if (i > 0) {
                    return cells[i - 1][j];
                }
                break;
            case SOUTH:
                if (i < cellHeight - 1) {
                    return cells[i + 1][j];
                }
                break;
            case EAST:
                if (j < cellWidth - 1) {
                    return cells[i][j + 1];
                }
                break;
            case WEST:
                if (j > 0) {
                    return cells[i][j - 1];
                }
                break;
        }
        return null; // Return null if neighbor is out of bounds
    }


    public boolean solver(int i, int j) {
//        if (i == 2 && j == 2) {
//            System.out.println("checkpoint");
//        }
        //System.out.println("[" + x + ";" + y + "]");
        if (cells[i][j].getPossibleConnectionsSides().isEmpty()) {
            System.out.println("[" + i + ";" + j + "] ZERO");
            if (j + 1 < cellWidth) {
                solver(i, j + 1);
            } else if (i + 1 < cellHeight) {
                solver(i + 1, 0);
            }
            return isSolved();
        }
        else {
            for (int count = 0; count < 4; count++) {
//            System.out.println();
                System.out.print("[" + i + ";" + j + "]");
                cells[i][j].rotateRight();
                System.out.println(cells[i][j].getPossibleConnectionsSides());
                if (isSolved()) {
                    return true;
                }
                if (j + 1 < cellWidth) {
                    solver(i, j + 1);
                } else if (i + 1 < cellHeight) {
                    solver(i + 1, 0);
                }
            }
        }
        return false;
    }
}