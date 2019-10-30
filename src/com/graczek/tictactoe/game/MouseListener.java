package com.graczek.tictactoe.game;

import com.graczek.tictactoe.enums.FieldState;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import com.graczek.tictactoe.view.FieldPane;

public class MouseListener implements EventHandler<MouseEvent> {

    private final Field field;
    private final FieldStateHolder fieldStateHolder;
    private final GameStateListener gameStateListener;

    public MouseListener(Field field, FieldStateHolder fieldStateHolder, GameStateListener gameStateListener) {
        this.field = field;
        this.fieldStateHolder = fieldStateHolder;
        this.gameStateListener = gameStateListener;
    }

    @Override
    public void handle(MouseEvent event) {

        if(field.getFieldState() != null) {
            return;
        }

        FieldState stateToBeSet = fieldStateHolder.getCurrentFieldStateAndSetNext();
        field.setFieldState(stateToBeSet);
        Platform.runLater(() -> ((FieldPane) event.getSource()).drawSign(stateToBeSet));

        gameStateListener.gameStateChanged();
    }
}
