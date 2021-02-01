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
        board = null;
        game = null;
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
    void testGamePlayerCanPlaceAValueOnTheBoard() throws GameOverException {
        game.makeMove(3);
        assertEquals(GameValue.X, game.getBoard().getGrid()[0][2]);
        game.makeMove(5);

        assertEquals(GameValue.O, game.getBoard().getGrid()[1][1]);
    }

    @Test
    void testGamePlayersCannotPlayTwoConsecutiveXValues() throws GameOverException {
        game.makeMove(3);
        assertEquals(GameValue.X, game.getBoard().getGrid()[0][2]);
        game.makeMove(5);
        assertNotEquals(GameValue.X, game.getBoard().getGrid()[1][1]);
        game.makeMove(1);
        assertEquals(GameValue.X, game.getBoard().getGrid()[0][0]);
        game.makeMove(2);
        assertEquals(GameValue.O, game.getBoard().getGrid()[0][1]);
    }

    @Test
    void testGamePlayerCanOnlyPlayOnAnEmptySquare() throws GameOverException {
        assertEquals(1, game.makeMove(3));
        assertEquals(-1, game.makeMove(3));
        assertEquals(1, game.makeMove(6));
        assertEquals(-1, game.makeMove(6));
        assertEquals(-1, game.makeMove(3));
        assertEquals(1, game.makeMove(7));
    }

    @Test
    void testGameThrowsIllegalArgumentExceptionWhenUserPassesInOutOfRangeGridPosition() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.makeMove(17);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            game.makeMove(10);
        });
    }

    @Test
    void testGameThrowsGameOverExceptionWhenAllSlotsOnGridAreFull() throws GameOverException {
        game.makeMove(1);
        game.makeMove(2);
        game.makeMove(3);
        game.makeMove(4);
        game.makeMove(5);
        game.makeMove(6);
        game.makeMove(7);
        game.makeMove(8);
        game.makeMove(9);
        assertThrows(GameOverException.class, () -> {
            game.makeMove(9);
        });
    }

    @Test
    void testGameResetsGameValues() throws GameOverException {
        Board oldBoard = game.getBoard();
        game.makeMove(1);
        game.makeMove(4);
        game.makeMove(2);
        game.makeMove(5);
        game.makeMove(3);
        assertTrue(game.checkIfGameWon());
        game.resetGame();
        assertFalse(game.checkIfGameWon());
        assertNotEquals(oldBoard, game.getBoard());
        assertEquals(oldBoard.getGrid().length, game.getBoard().getGrid().length);
    }

    @Test
    void testGameWonIfPlayerMakesHorizontalLineXValues() throws GameOverException {
        game.makeMove(1);
        game.makeMove(4);
        game.makeMove(2);
        game.makeMove(5);
        game.makeMove(3);
        game.displayBoard();
        assertTrue(game.checkIfGameWon());
        game.resetGame();
        game.makeMove(4);
        game.makeMove(2);
        game.makeMove(5);
        game.makeMove(3);
        game.makeMove(6);
        assertTrue(game.checkIfGameWon());
        game.resetGame();
        game.makeMove(7);
        game.makeMove(2);
        game.makeMove(8);
        game.makeMove(1);
        game.makeMove(9);
        assertTrue(game.checkIfGameWon());
    }

    @Test
    void testGameWonIfPlayerMakesVerticalLineValuesXValues() throws GameOverException {
        game.makeMove(1);
        game.makeMove(5);
        game.makeMove(4);
        game.makeMove(9);
        game.makeMove(7);
        assertTrue(game.checkIfGameWon());
        game.resetGame();
        assertFalse(game.checkIfGameWon());
        game.makeMove(2);
        game.makeMove(3);
        game.makeMove(5);
        game.makeMove(4);
        game.makeMove(8);
        assertTrue(game.checkIfGameWon());
        game.resetGame();
        assertFalse(game.checkIfGameWon());
        game.makeMove(3);
        game.makeMove(1);
        game.makeMove(6);
        game.makeMove(2);
        game.makeMove(9);
        assertTrue(game.checkIfGameWon());
    }

    @Test
    void testGameCanDisplayBoard() throws GameOverException {
        game.makeMove(1);
        game.makeMove(2);
        game.makeMove(3);
        game.makeMove(4);
        game.makeMove(5);
        game.makeMove(6);
        game.makeMove(7);
        game.makeMove(8);
        game.makeMove(9);
        game.displayBoard();
    }
    @Test
    void testGameDiagonal() throws GameOverException {
        game.makeMove(7);
        game.makeMove(1);
        game.makeMove(5);
        game.makeMove(2);
        game.makeMove(3);
        assertTrue(game.checkIfGameWon());
    }
}