package gh.nijd.pong;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class GameController {
    @FXML
    Pane gamePane;
    @FXML
    Line leftEnd, rightEnd;
    @FXML
    Circle ball;
    @FXML
    Label p1Score;
    @FXML
    Label p2Score;
    @FXML
    Rectangle p1;
    @FXML
    Rectangle p2;
    double ballx;
    double bally;
    double ballxVelocity;
    double ballyVelocity;
    double xVelocityChange;

    private Scene scene;
    private Stage stage;

    boolean p1up = false, p1down = false, p2up = false, p2down = false, pause = false;

    int p1Sens, p2Sens;

    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) { // When a key is pressed, its corresponding bool var becomes true, indicating it has been pressed, to be dealt with in the game loop
            case S:
                p1down = true;
                break;
            case W:
                p1up = true;
                break;
            case DOWN:
                p2down = true;
                break;
            case UP:
                p2up = true;
                break;
            case P:
                pause = true;
                break;
        }
    }

    @FXML
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) { // When a key is released, its corresponding bool var becomes false, indicating it is no longer pressed
            case S:
                p1down = false;
                break;
            case W:
                p1up = false;
                break;
            case DOWN:
                p2down = false;
                break;
            case UP:
                p2up = false;
                break;
            case P:
                pause = false;
                break;
        }
    }

    public void setSens(int p1Sens, int p2Sens) {
        this.p1Sens = p1Sens;
        this.p2Sens = p2Sens;
    }

    public void setColor(Color p1Col, Color p2Col) {
        p1.setFill(p1Col);
        p2.setFill(p2Col);
    }

    public Alert gamePausedAlert() {
        Alert gamePaused = new Alert(Alert.AlertType.CONFIRMATION);
        gamePaused.setTitle("Game Paused");
        gamePaused.setHeaderText("Game Paused");
        gamePaused.getButtonTypes().clear(); // Clears current buttons on alert

        // Creates new button options for the alert
        ButtonType quitButton = new ButtonType("Quit");
        ButtonType playButton = new ButtonType("Continue");

        // Adds button options to the alert
        gamePaused.getButtonTypes().addAll(quitButton, playButton);
        return gamePaused;
    }

    public Alert gameFinishedAlert(String winner) {
        Alert gameFinished = new Alert(Alert.AlertType.CONFIRMATION);
        gameFinished.setTitle("Game Finished");
        gameFinished.setHeaderText(winner + " won!");
        gameFinished.getButtonTypes().clear(); // Clears current buttons on alert

        // Creates new button options for the alert
        ButtonType quitButton = new ButtonType("Quit to main menu");
        ButtonType playButton = new ButtonType("Replay");

        // Adds button options to the alert
        gameFinished.getButtonTypes().addAll(quitButton, playButton);
        return gameFinished;
    }

    public void playerMovement(boolean keyOne, boolean keyTwo, Rectangle p, int sens) {
        if (keyOne) {
            if (p.getY() + p.getHeight() / 2 + sens > gamePane.getHeight() / 2) { // If the player's sensitivity will take them below the screen it will set the player to the lowest they can go, no matter their sensitivity they will always end at the same yPos
                p.setY(gamePane.getHeight() / 2 - p.getHeight() / 2);
            }
            else{ // Moves the player down by their sensitivity
                p.setY(p.getY() + sens);
            }
        }
        if (keyTwo) {
            if (p.getY() - p.getHeight() / 2 - sens < (gamePane.getHeight() / 2) * -1) { // Same as above, except for going up
                p.setY((gamePane.getHeight() / 2) * -1 + p.getHeight() / 2);
            }
            else {
                p.setY(p.getY() - sens);
            }
        }
    }

    private boolean isBallCollidingPlayer() {
        return (ball.getBoundsInParent().intersects(p1.getBoundsInParent())
                ||
                (ball.getBoundsInParent().intersects(p2.getBoundsInParent())));
    }

    private boolean isBallCollidingTopOrBot() {
        double ballyCoord = ball.getCenterY() + gamePane.getHeight() / 2;
        return (ballyCoord - ball.getRadius() <= 0) || ((ballyCoord + ball.getRadius()) >= gamePane.getHeight());
    }

    private boolean isBallCollidingSide() {
        double ballxCoord = ball.getCenterX() + gamePane.getWidth() / 2;
        return (ballxCoord - ball.getRadius() <= 0) || ((ballxCoord + ball.getRadius()) >= gamePane.getWidth());
    }

    private void initBall() {
        Random rand = new Random();
        int randInt = rand.nextInt(2);
        if (randInt == 0) { // ball will go left at start
            ballxVelocity = -2;
        } else { // ball will go right at start
            ballxVelocity = 2;
        }
        ballyVelocity = 0;
        xVelocityChange = 1;
        ballx = 0;
        bally = 0;
    }

    public void initialize() {
        Alert gamePaused = gamePausedAlert(); // Creates alert to be used when the game is paused

        // --- Inspiration from https://stackoverflow.com/questions/13796595/return-result-from-javafx-platform-runlater and https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx ---
        AnimationTimer gameLoop = new AnimationTimer() { // Creates the game loop
            @Override
            public void handle(long l) {
                playerMovement(p1down, p1up, p1, p1Sens);
                playerMovement(p2down, p2up, p2, p2Sens);

                if (isBallCollidingPlayer()) {
                    if (ballxVelocity < 0) {
                        ballxVelocity = (ballxVelocity - xVelocityChange) * -1;
                    } else if (ballxVelocity > 0) {
                        ballxVelocity = (ballxVelocity + xVelocityChange) * -1;
                    }
                    xVelocityChange = xVelocityChange * 0.88;
                    Random rand = new Random();
                    ballyVelocity = rand.nextInt(3) + 3;
                    int randInt = rand.nextInt(2);
                    if (randInt == 0) {
                        ballyVelocity = ballyVelocity * -1;
                    }
                }

                if (isBallCollidingTopOrBot()) {
                    ballyVelocity = ballyVelocity * -1;
                }

                if (isBallCollidingSide()) {
                    //TODO: update score
                    double ballxCoord = ball.getCenterX() + gamePane.getWidth() / 2;
                    if (ballxCoord - ball.getRadius() <= 0) {
                        int score = Integer.valueOf(p2Score.getText());
                        p2Score.setText(String.valueOf(score + 1));
                    } else if ((ballxCoord + ball.getRadius()) >= gamePane.getWidth()) {
                        int score = Integer.valueOf(p1Score.getText());
                        p1Score.setText(String.valueOf(score + 1));
                    }
                    if (p1Score.getText().equals("11") || p2Score.getText().equals("11")) {
                        stop();
                        String winner = "";
                        if (p1Score.getText().equals("11")) {
                            winner = "Player 1";
                        } else {
                            winner = "Player 2";
                        }
                        Alert gameFinished = gameFinishedAlert(winner);
                        final Optional<ButtonType>[] bt = new Optional[1];
                        // Using Alert.showAndWait() causes a runtime error due to the current loop being performed,
                        // so the action must be performed after the current loop is finished.
                        Platform.runLater(() -> {
                            bt[0] = gameFinished.showAndWait();
                            if (bt[0].get().getText().equals("Quit to main menu")) { // If the player quits, they get taken to the menu page
                                //new Console().selectNewScene("menu.fxml", (Stage) gamePane.getScene().getWindow(), ((Stage) gamePane.getScene().getWindow()).isFullScreen());
                                //new Console().selectNewScene("menu.fxml", (Stage) gamePane.getScene().getWindow(), ((Stage) gamePane.getScene().getWindow()).isFullScreen());
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml")); // TODO: make use of console class
                                //stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                                stage = (Stage) gamePane.getScene().getWindow();
                                try {
                                    scene = new Scene(loader.load());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                scene.getRoot().requestFocus();
                                stage.setScene(scene);
                                stage.show();

                                ApplicationStart applicationStart = loader.getController();
                                applicationStart.setSens(p1Sens, p2Sens);
                                applicationStart.setColor((Color) p1.getFill(), (Color) p2.getFill());
                            }
                            else {
                                p1Score.setText("0");
                                p2Score.setText("0");
                                initBall();
                                p1.setY((gamePane.getHeight() / 2) - (gamePane.getHeight() / 2));
                                p2.setY((gamePane.getHeight() / 2) - (gamePane.getHeight() / 2));
                                start(); // If they want to continue, the game loop starts
                                //TODO: possible to make use of initialise
                            }
                            pause = p1up = p1down = p2up = p2down = false; // These bool vars need to be reset otherwise the player will move constantly until user input after the game restarts
                        });
                    }
                    initBall();
                }

                ball.setCenterX(ballx = ballx + ballxVelocity);
                ball.setCenterY(bally = bally + ballyVelocity);

                if (pause) {
                    stop(); // When escape is pressed the game loop stops
                    final Optional<ButtonType>[] bt = new Optional[1]; // Inits arr to store result from alert
                    Platform.runLater(new Runnable() { // Using Alert.showAndWait() causes a runtime error due to the current loop being performed so the action must be performed after the current loop is finished
                        @Override
                        public void run() {
                            bt[0] = gamePaused.showAndWait();
                            if (bt[0].get().getText().equals("Quit")) { // If the player quits, they get taken to the menu page
                                //new Console().selectNewScene("menu.fxml", (Stage) gamePane.getScene().getWindow(), ((Stage) gamePane.getScene().getWindow()).isFullScreen());
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml")); // TODO: make use of console class
                                //stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                                stage = (Stage) gamePane.getScene().getWindow();
                                try {
                                    scene = new Scene(loader.load());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                scene.getRoot().requestFocus();
                                stage.setScene(scene);
                                stage.show();

                                ApplicationStart applicationStart = loader.getController();
                                applicationStart.setSens(p1Sens, p2Sens);
                                applicationStart.setColor((Color) p1.getFill(), (Color) p2.getFill());
                            }
                            else {
                                start(); // If they want to continue, the game loop starts
                            }
                            pause = p1up = p1down = p2up = p2down = false; // These bool vars need to be reset otherwise the player will move constantly until user input after the game restarts
                        }
                    });
                }

                // In-game music

            }
        };

        initBall();

        gameLoop.start(); // Starts the gameloop for the first time
    }
}
