package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    private boolean isMoveX = true;
    private boolean keepPlaying = true;

    private Field[][] board = new Field[3][3];

    private List<WinningCombination> winningCombinationsList = new ArrayList<>();

    private Image backImage = new Image("file:resources/backg.jpg");

    private static final int WINDOW_SIZE = 450;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private Parent createContent() {

        BackgroundSize backgroundSize = new BackgroundSize(800, 800, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        Pane grid = new Pane();
        grid.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);
        grid.setBackground(background);

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3 ; x++) {
                Field field = new Field();
                field.setTranslateX(x * WINDOW_SIZE/3);
                field.setTranslateY(y * WINDOW_SIZE/3);

                grid.getChildren().add(field);

                board[x][y] = field;
            }

        }

        checkIfWinHorizontally();
        checkIfWinVertically();
        checkIfWinDiagonally();

        return grid;
    }

    private void checkIfWinDiagonally() {
        winningCombinationsList.add(new WinningCombination(board[0][0], board[1][1], board[2][2]));
        winningCombinationsList.add(new WinningCombination(board[2][0], board[1][1], board[0][2]));
    }

    private void checkIfWinVertically() {
        for (int x = 0; x < 3 ; x++) {
            winningCombinationsList.add(new WinningCombination(board[x][0], board[x][1], board[x][2]));
        }
    }

    private void checkIfWinHorizontally() {
        for (int y = 0; y < 3 ; y++) {
            winningCombinationsList.add(new WinningCombination(board[0][y], board[1][y], board[2][y]));
        }
    }

    private void checkIfWinner(){
        for (WinningCombination winningCombination : winningCombinationsList) {
            if(winningCombination.isWinningCombinationComplete()) {
                keepPlaying = false;
                displayWinner();
                break;
            }

        }

    }

    private void displayWinner() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setResizable(false);
        popup.setMinHeight(150);
        popup.setMinWidth(150);

        Label label = new Label();
        label.setText("We got the winner!");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.close());
        Button playAgainButton = new Button("Play again?");
        playAgainButton.setOnAction(e -> resetBoard(popup));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, playAgainButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        popup.setScene(scene);
        popup.showAndWait();
    }

    private void resetBoard(Stage window) {
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                board[y][x].text.setText(null);
            }
        }

        keepPlaying = true;
        isMoveX = true;
        window.close();
    }

    private class WinningCombination {
        private Field[] fields;

        public WinningCombination(Field... fields) {
            this.fields = fields;
        }

        public boolean isWinningCombinationComplete(){
            if(fields[0].getSign().isEmpty()){
                return false;
            }

            return fields[0].getSign().equals(fields[1].getSign()) && fields[1].getSign().equals(fields[2].getSign());
        }
    }

    private class Field extends StackPane {

        private Text text = new Text();
        private boolean occupied = false;

        public Field() {
            Rectangle rectangle = new Rectangle(WINDOW_SIZE/3, WINDOW_SIZE/3);
            rectangle.setFill(null);
            rectangle.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().addAll(rectangle, text);

            text.setFont(Font.font(100));
            text.setFill(Color.GREEN);

            addMouseListener();
        }

        private void addMouseListener() {
            setOnMouseClicked(e -> {
                if (!keepPlaying) return;
                if (occupied) return;

                if (isMoveX) {
                    drawX();
                    occupied = true;
                    isMoveX = false;
                } else {
                    drawO();
                    occupied = true;
                    isMoveX = true;
                }
                occupied = false;
                checkIfWinner();
            });
        }

        public String getSign(){
            return text.getText();
        }

        private void drawX(){
            text.setText("X");
        }

        private void drawO(){
            text.setText("O");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
