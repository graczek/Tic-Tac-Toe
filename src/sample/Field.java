package sample;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field extends StackPane {

    private Text sign = new Text();

    private Field() {

        Rectangle field = new Rectangle(150, 150);
        field.setFill(null);
        field.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);
        getChildren().addAll(field, sign);

        setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.PRIMARY) {
                drawX();
            } else if(e.getButton() == MouseButton.SECONDARY) {
                drawO();
            }
        });



    }

    private void drawX() {
        sign.setText("X");
    }

    private void drawO() {
        sign.setText("O");
    }

    public static void drawBoard(){

        for (int x = 0; x < 3 ; x++) {
            for (int y = 0; y < 3 ; y++) {
                Field field = new Field();
                field.setTranslateX(x * 150);
                field.setTranslateY(y * 150);
                Main.grid.getChildren().add(field);
            }

        }
    }
}
