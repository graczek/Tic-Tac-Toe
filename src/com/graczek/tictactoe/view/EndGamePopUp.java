package com.graczek.tictactoe.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.graczek.tictactoe.view.ViewMain.WINDOW_SIZE;

public class EndGamePopUp {

    public void displayEndGamePopUpWindow(boolean isWinner, Runnable playAgainRunnable) {

        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setResizable(false);
        popup.setMinHeight(WINDOW_SIZE/3);
        popup.setMinWidth(WINDOW_SIZE/3);

        Label label = new Label();
        label.setText(isWinner ? "We got the winner!" : "Draw!");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> System.exit(0));
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
}
