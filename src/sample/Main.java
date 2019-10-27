package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Pane grid = new Pane();

    private Parent createSetup() {
        grid.setPrefSize(450, 450);
        Field.drawBoard();
        return grid;
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(new Scene(createSetup()));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
