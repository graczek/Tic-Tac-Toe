package com.graczek.tictactoe.game;

import com.graczek.tictactoe.enums.FieldState;

class Field {

    private FieldState fieldState;

    FieldState getFieldState() {
        return fieldState;
    }

    void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

}
