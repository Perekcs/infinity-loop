package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LevelBuilder {
    CellPreset preset = new CellPreset();

    int[][] levelReader(int width, int height,String path) {
        int[][] result = new int[width][height];
        try{
            FileReader reader = new FileReader(path);
            Scanner scanner = new Scanner(reader);

            int count = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++) {
                    result[count][i] = Integer.parseInt(values[i]);
                }
                count++;
            }
            reader.close();
        }catch (IOException e){
            System.out.println("Error reading file");
        }
        return result;
    }

    public void buildLevel(String path, Cell[][] cells) {
        int[][] levelValues = levelReader(cells.length, cells[0].length, path);
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                numberToCell(levelValues[row][col],cells[row][col]);
            }
        }
    }

    void numberToCell(int number, Cell cell){
        switch(number){
            case 0:
                preset.emptyCell(cell);
                break;
            case 1:
                preset.endCell(cell);
                break;
            case 2:
                preset.lineCell(cell);
                break;
            case 3:
                preset.turnCell(cell);
                break;
            case 4:
                preset.junctionCell(cell);
                break;
            case 5:
                preset.crossCell(cell);
                break;
        }
    }
    //switch case of cells +
    //add logic of setting cells +
    //rework Grid(-remove useless)
    //rework CellPanel 
}
