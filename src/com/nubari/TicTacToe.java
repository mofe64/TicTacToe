package com.nubari;

public class TicTacToe {
    private Board gameBoard;
    private boolean lastValuePlayedWasX;

    public TicTacToe(Board gameBoard) {
        this.gameBoard = gameBoard;

    }

    public Board getBoard() {
        return gameBoard;
    }

    public void setBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void makeMove(int rowNumber) {
        rowNumber -= 1;
        int row = rowNumber / 3;
        int column = rowNumber % 3;
        System.out.println("row: " + row + " column: " + column);
    }

    private void placeValue(int rowPosition, int columnPosition) {
    }
}
