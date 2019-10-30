package com.graczek.tictactoe.view;

import com.graczek.tictactoe.enums.FieldState;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FieldPane extends StackPane {

    private Text sign = new Text();

    FieldPane(int size) {
        Rectangle rectangle = new Rectangle(size/3, size/3);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);

        getChildren().addAll(rectangle, sign);

        sign.setFont(Font.font(100));
        sign.setFill(Color.GREEN);
    }

    private void drawX() {
        sign.setText("X");
    }

    private void drawO() {
        sign.setText("O");
    }

    private void drawNone() {
        sign.setText(null);
    }

    public void drawSign(FieldState stateToBeSet) {
        if(stateToBeSet == FieldState.X) {
            drawX();
        } else if (stateToBeSet == FieldState.O) {
            drawO();
        } else {
            drawNone();
        }
    }
}
