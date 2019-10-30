package com.graczek.tictactoe.game;

import com.graczek.tictactoe.enums.FieldState;

import java.util.ArrayList;
import java.util.List;

public class WinnerChecker {

    private List<WinningCombination> winningCombinationsList = new ArrayList<>();

    public WinnerChecker(Board board) {
        addDiagonalWinCombinations(board);
        addVerticalWinCombinations(board);
        addHorizontalWinCombinations(board);
    }

    public boolean playerWon() {
        return winningCombinationsList.stream().anyMatch(WinningCombination::isWinningCombinationComplete);
    }

    private void addHorizontalWinCombinations(Board board) {
        for (int y = 0; y < 3; y++) {
            winningCombinationsList.add(new WinningCombination(board.getField(0, y), board.getField(1, y), board.getField(2, y)));
        }
    }

    private void addVerticalWinCombinations(Board board) {
        for (int x = 0; x < 3; x++) {
            winningCombinationsList.add(new WinningCombination(board.getField(x,0), board.getField(x,1), board.getField(x,2)));
        }
    }

    private void addDiagonalWinCombinations(Board board) {
        winningCombinationsList.add(new WinningCombination(board.getField(0,0), board.getField(1,1), board.getField(2,2)));
        winningCombinationsList.add(new WinningCombination(board.getField(2,0), board.getField(1,1), board.getField(0,2)));
    }

    private static class WinningCombination {
        private Field[] fields;

        private WinningCombination(Field... fields) {
            this.fields = fields;
        }

        private boolean isWinningCombinationComplete() {
            if(fields[0].getFieldState() == null) {
                return false;
            }
            FieldState stateToCheck = fields[0].getFieldState();
            return isStateSame(stateToCheck, fields[1]) && isStateSame(stateToCheck, fields[2]);
        }

        private boolean isStateSame(FieldState stateToCheck, Field field) {
            return stateToCheck == field.getFieldState();
        }
    }
}
