package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Grid grd = new Grid(3,3);
        grd.printGrid();
        if(grd.solver(0,0)){
            System.out.println("Solution:");
            grd.printGrid();
        }
        else{
            System.out.println("No solution");
        }
        SwingUtilities.invokeLater(() -> {
            GameUI frame = new GameUI(4,4);
            frame.setVisible(true);
        });
    }
}