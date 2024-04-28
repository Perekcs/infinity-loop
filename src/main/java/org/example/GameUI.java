package org.example;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameUI extends JFrame {
    CellPanel cellPanel;

    public GameUI(int i, int j) {
        setTitle("Infinity Loop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //cellPanel = new CellPanel(i,j);
        getContentPane().add(new CellPanel(i,j));
        setBounds(800,800,800,800);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
