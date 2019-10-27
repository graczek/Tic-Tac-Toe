package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Field extends StackPane {

    public static final int  BOARD_SIZE = 450;

    private Text sign = new Text();

    private boolean turnX = true;

    private Field() {

        Rectangle field = new Rectangle(BOARD_SIZE/3, BOARD_SIZE/3);
        field.setFill(null);
        field.setStroke(Color.BLACK);

        sign.setFont(Font.font(BOARD_SIZE/4));

        setAlignment(Pos.CENTER);
        getChildren().addAll(field, sign);

        addMouseListener();
    }

    private void addMouseListener() {
        setOnMouseClicked(e -> {
            if(turnX){
                drawX();
                turnX = false;
                System.out.println(turnX);

            } else {
                drawO();
                turnX = true;
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
