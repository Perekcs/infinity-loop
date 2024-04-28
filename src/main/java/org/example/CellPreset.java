package org.example;

import javax.swing.*;

public class CellPreset {

    public void crossCell(Cell cell){
        cell.addPossibleConnectionSide(Direction.NORTH);
        cell.addPossibleConnectionSide(Direction.EAST);
        cell.addPossibleConnectionSide(Direction.SOUTH);
        cell.addPossibleConnectionSide(Direction.WEST);
        cell.setImageIcon(new ImageIcon("Connections/Cross.png"));
    }

    public void emptyCell(Cell cell){
        cell.setImageIcon(new ImageIcon("Connections/Empty.png"));
    }

    public void endCell(Cell cell){
        cell.addPossibleConnectionSide(Direction.EAST);
        cell.setImageIcon(new ImageIcon("Connections/End.png"));
    }

    public void junctionCell(Cell cell){
        cell.addPossibleConnectionSide(Direction.WEST);
        cell.addPossibleConnectionSide(Direction.EAST);
        cell.addPossibleConnectionSide(Direction.SOUTH);
        cell.setImageIcon(new ImageIcon("Connections/Junction.png"));
    }

    public void lineCell(Cell cell){
        cell.addPossibleConnectionSide(Direction.WEST);
        cell.addPossibleConnectionSide(Direction.EAST);
        cell.setImageIcon(new ImageIcon("Connections/Line.png"));
    }

    public void turnCell(Cell cell){
        cell.addPossibleConnectionSide(Direction.EAST);
        cell.addPossibleConnectionSide(Direction.SOUTH);
        cell.setImageIcon(new ImageIcon("Connections/Turn.png"));
    }
}
