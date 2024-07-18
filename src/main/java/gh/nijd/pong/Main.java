package gh.nijd.pong;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    int p1Sens = 10;
    int p2Sens = 10;
    Color p1Col = Color.WHITE;
    Color p2Col = Color.WHITE;
    boolean fullscreen = false;

    //TODO: Separate controller of menu.fxml from Main
    public static void main(String[] args) {
        launch(); // calls start method
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.setTitle("Pong Game");
        stage.getIcons().add(new Image((
                Objects.requireNonNull(getClass().getResourceAsStream("images/pong.png"))
        )));
        new Console().loadScene("menu.fxml", stage, fullscreen);
        /// new Console().playMusic("game.wav"); // TODO: play audio without pausing application
    }

    public void startGame(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new Console().loadScene("game.fxml", stage, fullscreen);
        GameController gameController = loader.getController();
        gameController.setValues(p1Sens, p2Sens, p1Col, p2Col);
    }

    public void settings(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new Console().loadScene("settings.fxml", stage, fullscreen);
        SettingsController settingsController = loader.getController();
        settingsController.setValues(p1Sens, p2Sens, p1Col, p2Col);
    }

    public void exitGame() {
        System.exit(0);
    }

    public void setValues (int p1Sens, int p2Sens, Color p1Col, Color p2Col) {
        this.p1Sens = p1Sens;
        this.p2Sens = p2Sens;
        this.p1Col = p1Col;
        this.p2Col = p2Col;
    }
}