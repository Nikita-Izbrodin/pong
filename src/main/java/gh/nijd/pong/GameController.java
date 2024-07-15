package gh.nijd.pong;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class GameController {
    @FXML
    Pane gamePane;
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
    boolean fullscreen;

    public void initialize() {
        // Inspiration from https://stackoverflow.com/questions/13796595/return-result-from-javafx-platform-runlater
        // and https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
        AnimationTimer gameLoop = new AnimationTimer() {
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
                    if (ballyVelocity > 0) {
                        ballyVelocity = (rand.nextInt(3) + 3);
                    } else if (ballyVelocity < 0){
                        ballyVelocity = (rand.nextInt(3) + 3) * -1;
                    } else {
                        int randInt = rand.nextInt(2);
                        if (randInt == 0) {
                            ballyVelocity = (rand.nextInt(3) + 3);
                        } else {
                            ballyVelocity = (rand.nextInt(3) + 3) * -1;
                        }
                    }
                }

                if (isBallCollidingTopOrBot()) {
                    ballyVelocity = ballyVelocity * -1;
                }

                if (isBallCollidingSide()) {
                    updateScore();
                    initBall();
                }

                ball.setCenterX(ballx = ballx + ballxVelocity);
                ball.setCenterY(bally = bally + ballyVelocity);

                if (pause) {
                    stop();
                    Platform.runLater(new Runnable() {
                        // Using Alert.showAndWait() causes a runtime error due to the current loop being performed,
                        // so the action must be performed after the current loop is finished.
                        @Override
                        public void run() {
                            final Optional<ButtonType>[] bt = new Optional[1]; // Inits arr to store result from alert
                            bt[0] = createAlert(
                                    "Game Paused",
                                    "Game Paused",
                                    "Quit",
                                    "Continue"
                            ).showAndWait();
                            if (bt[0].get().getText().equals("Quit")) {
                                Stage stage = (Stage) gamePane.getScene().getWindow();
                                FXMLLoader loader;
                                try {
                                    loader = new Console().loadScene("menu.fxml", stage, fullscreen);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Main main = loader.getController();
                                main.setValues(p1Sens, p2Sens, (Color) p1.getFill(), (Color) p2.getFill());
                            }
                            else {
                                start();
                            }
                            pause = p1up = p1down = p2up = p2down = false;
                            // These bool vars need to be reset otherwise the player will
                            // move constantly until user input after the game restarts.
                        }
                    });
                }

                // In-game music

            }

            private void updateScore() { //TODO: clean method
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
                    Alert gameFinished = createAlert(
                            "Game Finished",
                            winner + " won!",
                            "Quit to main menu",
                            "Replay"
                    );
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

                            SettingsController settingsController = loader.getController();
                            settingsController.setValues(p1Sens, p2Sens, (Color) p1.getFill(), (Color) p2.getFill());

                            /*Main main = loader.getController();
                            main.setValues(p1Sens, p2Sens,(Color) p1.getFill(), (Color) p2.getFill());*/
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
            }
        };

        initBall();

        gameLoop.start(); // Starts the gameloop for the first time
    }

    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
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
        switch (keyEvent.getCode()) {
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

    public void playerMovement(boolean up, boolean down, Rectangle p, int sens) {
        if (up) {
            if (p.getY() + p.getHeight() / 2 + sens > gamePane.getHeight() / 2) {
                // If the player's sensitivity will take them below the screen
                // it will set the player to the lowest they can go.
                p.setY(gamePane.getHeight() / 2 - p.getHeight() / 2);
            }
            else{
                p.setY(p.getY() + sens);
            }
        }
        if (down) {
            if (p.getY() - p.getHeight() / 2 - sens < (gamePane.getHeight() / 2) * -1) {
                // Same as above, except for going above the screen.
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

    public Alert createAlert(String title, String header, String button1Text, String button2Text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.getButtonTypes().clear(); // clears current buttons on alert
        ButtonType button1 = new ButtonType(button1Text);
        ButtonType button2 = new ButtonType(button2Text);
        alert.getButtonTypes().addAll(button1, button2);
        return alert;
    }

    public void setValues (int p1Sens, int p2Sens, Color p1Col, Color p2Col) {
        this.p1Sens = p1Sens;
        this.p2Sens = p2Sens;
        p1.setFill(p1Col);
        p2.setFill(p2Col);
    }
}
