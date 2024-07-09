package gh.nijd.pong;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Pane settingsPane;
    @FXML
    private ColorPicker p1ColPick, p2ColPick;
    @FXML
    private Spinner<Integer> p1SensSpin, p2SensSpin;
    @FXML
    private CheckBox fullscreenOption;

    int p1Sens, p2Sens;
    Color p1Col, p2Col;

    public void p1SaveSettings(ActionEvent event) {
        p1Col = p1ColPick.getValue(); // Gets values of player settings
        p1Sens = p1SensSpin.getValue();
        createSavedPU("Player 1");
    }

    public void p2SaveSettings(ActionEvent event) {
        p2Col = p2ColPick.getValue();
        p2Sens = p2SensSpin.getValue();
        createSavedPU("Player 2");
    }

    public void generalSaveSettings(ActionEvent event) { // Annoying, many features need fixing, cba rn --- NEEDS ATTENTION
        ((Stage)(((Node) event.getSource()).getScene().getWindow())).setFullScreen(fullscreenOption.isSelected());
        createSavedPU("General");
    }

    public void createSavedPU(String playerType) { // Creates popup telling the player their data is saved
        Popup savedPU = new Popup();
        Label savedLabel = new Label(playerType + " settings saved!");
        savedLabel.setTextFill(Color.RED);
        savedLabel.setStyle(" -fx-background-color: white;");
        savedLabel.setPadding(new Insets(10,10,10,10));
        savedPU.getContent().add(savedLabel);
        savedPU.setAutoHide(true); // The popup disappears when focus lost
        savedPU.show((settingsPane.getScene().getWindow()));
    }

    public void backToMenu(ActionEvent event) {
        //Player[] playerArr = {p1, p2};

        ApplicationStart AS = new ApplicationStart();
        //AS.getPlayers(playerArr);
        AS.setSens(p1Sens, p2Sens);
        AS.setColor(p1Col, p2Col);
        new Console().selectNewScene("menu.fxml", (Stage)((Node) event.getSource()).getScene().getWindow(), ((Stage)((Node) event.getSource()).getScene().getWindow()).isFullScreen());
    }

    public void setSens(int p1Sens, int p2Sens) {
        this.p1Sens = p1Sens;
        this.p2Sens = p2Sens;
    }

    public void setColor(Color p1Col, Color p2Col) {
        this.p1Col = p1Col;
        this.p2Col = p2Col;
    }

    public void initialize() {
        // Inits the spinners for sensitivity
        SpinnerValueFactory<Integer> p1SPFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50, p1Sens);
        SpinnerValueFactory<Integer> p2SPFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50, p2Sens);
        p1SensSpin.setValueFactory(p1SPFactory);
        p2SensSpin.setValueFactory(p2SPFactory);

        // Inits the colour pickers
        p1ColPick.setValue(p1Col);
        p2ColPick.setValue(p2Col);
    }
}
