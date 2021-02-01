package com.nubari;

import java.util.Arrays;

public class Board {
    private GameValue[][] grid;

    public Board(int gridNumber) {
        grid = new GameValue[gridNumber][gridNumber];
        for (int row = 0; row < grid.length; row++) {
            Arrays.fill(grid[row], GameValue.EMPTY);
        }

    }

    public GameValue[][] getGrid() {
        return grid;
    }

    public void displayBoard() {
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                System.out.print(grid[row][column] + " ");
            }
            System.out.println();
        }
    }
}
