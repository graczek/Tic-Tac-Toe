package com.graczek.tictactoe.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.graczek.tictactoe.view.ViewMain;


public class Main extends Application {

    private final ViewMain viewMain = new ViewMain();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(viewMain.getGrid()));
        primaryStage.show();
        new Game(viewMain);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
