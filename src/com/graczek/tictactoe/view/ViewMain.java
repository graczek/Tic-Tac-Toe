package com.graczek.tictactoe.view;

import com.graczek.tictactoe.game.MouseListener;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ViewMain {

    private static final Image BACKGROUND = new Image("backg.jpg");
    public static final int WINDOW_SIZE = 450;

    private final Pane grid;
    private final FieldPane[][] fieldPanes = new FieldPane[3][3];

    public Pane getGrid() {
        return grid;
    }

    public ViewMain() {

        this.grid = new Pane();
        grid.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);
        grid.setBackground(createBackground());

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3 ; y++) {
                FieldPane fieldPane = new FieldPane(WINDOW_SIZE);
                fieldPane.setTranslateX(x * WINDOW_SIZE/3);
                fieldPane.setTranslateY(y * WINDOW_SIZE/3);

                grid.getChildren().add(fieldPane);

                fieldPanes[x][y] = fieldPane;
            }

        }
    }

    public void reset() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                fieldPanes[x][y].drawSign(null);
            }

        }
    }

    public void addFieldPaneListener(MouseListener mouseListener, int x, int y) {
        fieldPanes[x][y].setOnMouseClicked(mouseListener);
    }

    private Background createBackground() {
        BackgroundSize backgroundSize = new BackgroundSize(800, 800, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        return background;
    }


}
