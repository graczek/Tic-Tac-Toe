package com.graczek.tictactoe.game;

import com.graczek.tictactoe.view.EndGamePopUp;
import javafx.application.Platform;
import com.graczek.tictactoe.view.ViewMain;

public class Game implements GameStateListener {


    private final FieldStateHolder fieldStateHolder = new FieldStateHolder();
    private final ViewMain viewMain;
    private final Board board;
    private final WinnerChecker winnerChecker;

    private EndGamePopUp endGamePopUp = new EndGamePopUp();

    public Game(ViewMain viewMain) {
        this.viewMain = viewMain;
        this.board = new Board();
        this.winnerChecker = new WinnerChecker(board);

        initListeners(viewMain);
    }

    private void initListeners(ViewMain viewMain) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3 ; y++) {
                Field field = board.getField(x, y);
                MouseListener listener = new MouseListener(field, fieldStateHolder, this);
                viewMain.addFieldPaneListener(listener, x, y);
            }

        }
    }

    @Override
    public void gameStateChanged() {
        if(winnerChecker.playerWon()) {
            Platform.runLater(() -> endGamePopUp.displayEndGamePopUpWindow(true, this::resetGame));
        } else if (allFieldsFilled()) {
            Platform.runLater(() -> endGamePopUp.displayEndGamePopUpWindow(false, this::resetGame));
        }

    }

    private boolean allFieldsFilled() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3 ; y++) {
                if(board.getField(x, y).getFieldState() == null) {
                    return false;
                }

            }

        }
        return true;
    }

    private void resetGame() {
        fieldStateHolder.resetFieldToInitialState();
        board.resetBoard();
        Platform.runLater(viewMain::reset);
    }
}
