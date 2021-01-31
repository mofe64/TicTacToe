package com.nubari;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicTacToe {
    private Board gameBoard;
    private boolean lastValuePlayedWasX;

    public TicTacToe(Board gameBoard) {
        this.gameBoard = gameBoard;
        lastValuePlayedWasX = false;

    }

    public Board getBoard() {
        return gameBoard;
    }

    public void setBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int makeMove(int gridPosition) {
        if (gridPosition > 9 || gridPosition < 1) {
            throw new IllegalArgumentException("Grid position must be between 1 and 9");
        }
        gridPosition -= 1;
        int row = gridPosition / 3;
        int column = gridPosition % 3;
        System.out.println("row: " + row + " column: " + column);
        return placeValue(row, column);
    }

    private int placeValue(int rowPosition, int columnPosition) {

        GameValue[][] grid = gameBoard.getGrid();
        if (grid[rowPosition][columnPosition].equals(GameValue.EMPTY)) {
            if (lastValuePlayedWasX) {
                grid[rowPosition][columnPosition] = GameValue.O;
                lastValuePlayedWasX = false;
            } else {
                grid[rowPosition][columnPosition] = GameValue.X;
                lastValuePlayedWasX = true;
            }
            return 1;
        }
        return -1;
    }
}
