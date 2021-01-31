package com.nubari;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {
    TicTacToe game;
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(3);
        game = new TicTacToe(board);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testBoardHasAGridWhenInitialized() {
        assertNotNull(board.getGrid());
    }

    @Test
    void testBoardGridLimitEqualToValuePassedIntoConstructor() {
        assertEquals(3, board.getGrid().length);
        assertEquals(3, board.getGrid()[0].length);
    }

    @Test
    void testGameHasAGameBoard() {
        assertNotNull(game.getBoard());
    }

    @Test
    void testGameBoardIsEmptyWhenInitialized() {
        Board gameBoard = game.getBoard();
        GameValue[][] grid = gameBoard.getGrid();
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                assertEquals(GameValue.EMPTY, grid[row][column]);
            }
        }
    }

    @Test
    void testGamePlayerCanPlaceAValueOnTheBoard() {
        game.makeMove(3);
        // assertEquals(GameValue.X, game.getBoard().getGrid()[0][2]);
        game.makeMove(5);
        //
        // assertEquals(GameValue.O, game.getBoard().getGrid()[1][1]);
    }

    @Test
    void testGamePlayersCannotPlayTwoConsecutiveXValues() {
        game.makeMove(3);
        assertEquals(GameValue.X, game.getBoard().getGrid()[0][2]);
        game.makeMove(5);
        assertNotEquals(GameValue.X, game.getBoard().getGrid()[1][1]);
    }

    @Test
    void testGamePlayerCanOnlyPlayOnAnEmptySquare() {

    }
}