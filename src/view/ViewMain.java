package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.MouseListener;

public class ViewMain {

    private static final Image BACKGROUND = new Image("file:/resources/backg.jpg");
    private static final int WINDOW_SIZE = 450;

    private final Pane grid;
    private final FieldPane[][] fieldPanes = new FieldPane[3][3];

    public Pane getGrid() {
        return grid;
    }

    public ViewMain() {

        this.grid = new Pane();
        grid.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);
        grid.setBackground(createBackground());

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3 ; x++) {
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

    public void displayEndGamePopUpWindow(boolean isWinner, Runnable playAgainRunnable) {

        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setResizable(false);
        popup.setMinHeight(WINDOW_SIZE/3);
        popup.setMinWidth(WINDOW_SIZE/3);

        Label label = new Label();
        label.setText(isWinner ? "We got the winner!" : "Draw!");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.close());
        Button playAgainButton = new Button("Play again?");
        playAgainButton.setOnAction(e -> {
            playAgainRunnable.run();
            popup.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, playAgainButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        popup.setScene(scene);
        popup.showAndWait();
    }

    public void addFieldPaneListener(MouseListener mouseListener, int x, int y) {
        fieldPanes[x][y].setOnMouseClicked(mouseListener);
    }

    private Background createBackground() {
        BackgroundSize backgroundSize = new BackgroundSize(800, 800, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }


}
