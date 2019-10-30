package com.graczek.tictactoe.game;

import com.graczek.tictactoe.enums.FieldState;

public class FieldStateHolder {

    private FieldState fieldState = FieldState.X;

    public FieldState getCurrentFieldStateAndSetNext() {
        FieldState fieldStateToReturn = fieldState;
        fieldState = this.fieldState == FieldState.X ? FieldState.O : FieldState.X;
        return fieldStateToReturn;
    }

    public void resetFieldToInitialState() {
        fieldState = FieldState.X;
    }


}
