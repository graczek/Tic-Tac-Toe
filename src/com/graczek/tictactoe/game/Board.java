package com.graczek.tictactoe.game;

public class Board {

    private final Field[][] board = new Field[3][3];
    private final int boardSize = board.length;

    public Board() {
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize ; y++) {
                board[x][y] = new Field();
            }
        }
    }

    public Field getField(int x, int y) {
        return board[x][y];
    }

    public void resetBoard() {
        for (Field[] field : board) {
            for(Field fieldState : field) {
                fieldState.setFieldState(null);
            }
        }
    }
}
