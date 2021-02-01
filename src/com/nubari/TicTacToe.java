package com.nubari;


import java.util.HashSet;
import java.util.Set;

public class TicTacToe {
    private Board gameBoard;
    private boolean lastValuePlayedWasX;
    private boolean gameWon;

    public TicTacToe(Board gameBoard) {
        this.gameBoard = gameBoard;
        lastValuePlayedWasX = false;
        gameWon = false;
    }

    public Board getBoard() {
        return gameBoard;
    }


    public boolean checkIfGameWon() {
        checkHorizontalValuesForWinningPlay();
        checkVerticalValuesForWinningPlay();
        checkRightDiagonal();
        checkLeftDiagonal();
        System.out.println(gameWon);
        return gameWon;
    }

    public int makeMove(int gridPosition) throws GameOverException {
        if (isBoardFull()) {
            throw new GameOverException();
        }
        if (gridPosition > 9 || gridPosition < 1) {
            throw new IllegalArgumentException("Grid position must be between 1 and 9");
        }
        gridPosition -= 1;
        int row = gridPosition / 3;
        int column = gridPosition % 3;
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

    private boolean isBoardFull() {
        GameValue[][] grid = gameBoard.getGrid();
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == GameValue.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkHorizontalValuesForWinningPlay() {
        //System.out.println("GameWon " + gameWon);
        GameValue[][] grid = gameBoard.getGrid();
//        int columnNumber = 0;
        for (int row = 0; row < grid.length; row++) {
            Set<GameValue> values = new HashSet<>();
//            GameValue firstValue = grid[row][columnNumber];
//            values.add(firstValue);
            for (int column = 0; column < grid[row].length; column++) {
                values.add(grid[row][column]);
            }
            if (!values.contains(GameValue.EMPTY)) {
                if (values.size() == 1) {
                    this.gameWon = true;
                    return;
                }
            }

        }
    }

    private void checkVerticalValuesForWinningPlay() {
        GameValue[][] grid = gameBoard.getGrid();
//        boolean flag = true;
//        int rowNumber = 0;
//        int columnNumber = 0;
//        GameValue firstValue = grid[rowNumber][columnNumber];
//        for (int row = 0; row < grid.length; row++) {
//            rowNumber = 0;
//            for (int column = 0; column < grid[row].length; column++) {
//                if (!firstValue.equals(grid[rowNumber][columnNumber]) || grid[rowNumber][columnNumber] == GameValue.EMPTY) {
//                    flag = false;
//                }
//                if (rowNumber < 2) {
//                    rowNumber++;
//                }
//            }
//            columnNumber += 1;
//        }
//        if (flag) {
//            gameWon = true;
//        }
        int columnNumber = 0;
        for (int counter = 0; counter < grid.length; counter++) {
            Set<GameValue> values = new HashSet<>();
            for (int row = 0; row < grid.length; row++) {
                values.add(grid[row][columnNumber]);
            }
            if (columnNumber < 2) {
                columnNumber++;
            }
            if (!values.contains(GameValue.EMPTY)) {
                if (values.size() == 1) {
                    gameWon = true;
                    return;
                }
            }
        }
    }

    private void checkLeftDiagonal() {
        GameValue[][] grid = gameBoard.getGrid();
        boolean flag = true;
        int columnNumber = 0;
        int rowNumber = 0;
        GameValue firstValue = grid[rowNumber][columnNumber];
        for (int row = 0; row < grid.length; row++) {
            if (!grid[rowNumber][columnNumber].equals(firstValue) || grid[rowNumber][columnNumber] == GameValue.EMPTY) {
                flag = false;
                break;
            }
            columnNumber += 1;
            rowNumber += 1;
        }
        if (flag) {
            gameWon = flag;

        }
    }

    private void checkRightDiagonal() {
        GameValue[][] grid = gameBoard.getGrid();
        boolean flag = true;
        int rowNumber = 2;
        int columnNumber = 0;
        GameValue firstValue = grid[rowNumber][columnNumber];
        for (int row = 0; row < grid.length; row++) {
            if (!grid[rowNumber][columnNumber].equals(firstValue) || grid[rowNumber][columnNumber].equals(GameValue.EMPTY)) {
                flag = false;
                break;
            }
            rowNumber -= 1;
            columnNumber += 1;
        }
        if (flag) {
            gameWon = flag;
        }
    }

    public void displayBoard() {
        checkIfGameWon();
        gameBoard.displayBoard();
    }

    public void resetGame() {
        int previousGameBoardSize = gameBoard.getGrid().length;
        gameBoard = new Board(previousGameBoardSize);
        lastValuePlayedWasX = false;
        gameWon = false;
    }
}
