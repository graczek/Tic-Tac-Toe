package sample;

import enums.FieldState;

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
