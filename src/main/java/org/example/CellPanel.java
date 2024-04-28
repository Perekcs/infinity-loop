package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CellPanel extends JPanel {
    private final Grid grid;
    int width;
    int height;
    JButton[][] buttons;
    LevelBuilder levelBuilder;
    String level4 = "Levels/level4.txt";

    public CellPanel(int i, int j)  {
        this.grid = new Grid(i, j);
        width = i;
        height = j;
        buttons = new JButton[height][width];

        levelBuilder = new LevelBuilder();
        levelBuilder.buildLevel(level4, grid.getCells());
        //setPreferredSize(new Dimension(grid.getCellWidth() * 50, grid.getCellHeight() * 50));
        setLayout(new GridLayout(width, height));
        initializeButtons();
    }

    void initializeButtons() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.white);
                int finalI = i;
                int finalJ = j;
                //buttons[i][j].setText(String.valueOf(grid.getCellOrientation(finalI, finalJ)));
                if (grid.getCell(finalI, finalJ) != null) {
                    buttons[i][j].setText(String.valueOf(grid.getCell(finalI, finalJ).getPossibleConnectionsSides()));
                }
                buttons[i][j].setIcon(grid.getCellImageIcon(i, j));
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton buttonClicked = (JButton) e.getSource();
                        //buttonClicked.setText(String.valueOf(grid.nextCellOrientation(finalI, finalJ)));
                        buttonClicked.setIcon(grid.rotateCellImageIcon(finalI, finalJ, 90));
                        grid.rotateCellRight(finalI, finalJ);
                        buttonClicked.setText(String.valueOf(grid.getCell(finalI, finalJ).getPossibleConnectionsSides()));
                        if(grid.isSolved()){
                            JOptionPane.showMessageDialog(null, "Level Solved");
                        }
                        buttonClicked.setHorizontalTextPosition(JButton.CENTER);
                        buttonClicked.setVerticalTextPosition(JButton.CENTER);
                        buttonClicked.setMargin(new Insets(0, 0, 0, 0));
                    }
                });
                add(buttons[i][j]);
            }
        }
    }
}