package gh.nijd.pong;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationStart extends Application {

    int p1Sens = 10;
    int p2Sens = 10;
    Color p1Col = Color.WHITE;
    Color p2Col = Color.WHITE;

    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(ApplicationStart.class.getResource("menu.fxml")); // Gets the FXML file
        Scene scene = new Scene(loader.load(), 1500, 750); // Loads FXML file as a scene
        stage.setResizable(false); // Prevents player from changing window size
        stage.setTitle("Pong Game"); // Sets the window title
        stage.getIcons().add(new Image((getClass().getResourceAsStream("images/pong.png")))); // Adds the application icon to the window
        stage.setScene(scene); // Adds the scene to the window
        stage.show();

        /// new Console().playMusic("game.wav"); // TODO: play audio without pausing fxml
    }

    public void exitGame(ActionEvent event) {
        System.exit(0); // Exits the program
    }

    public void startGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml")); // TODO: make use of console class
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.show();

        //new Console().selectNewScene("game.fxml", (Stage)((Node) event.getSource()).getScene().getWindow(), ((Stage)((Node) event.getSource()).getScene().getWindow()).isFullScreen());
        GameController gameController = loader.getController();
        gameController.setSens(p1Sens, p2Sens);
        gameController.setColor(p1Col, p2Col);
    }

    public void settings(ActionEvent event) {
        SettingsController SC = new SettingsController();
        SC.setSens(p1Sens, p2Sens);
        SC.setColor(p1Col, p2Col);
        new Console().selectNewScene("settings.fxml", (Stage)((Node) event.getSource()).getScene().getWindow(), ((Stage)((Node) event.getSource()).getScene().getWindow()).isFullScreen());
    }

    public void setSens(int p1Sens, int p2Sens) {
        this.p1Sens = p1Sens;
        this.p2Sens = p2Sens;
    }

    public void setColor(Color p1Col, Color p2Col) {
        this.p1Col = p1Col;
        this.p2Col = p2Col;
    }

    public static void main(String[] args) {
        launch();
    }
}