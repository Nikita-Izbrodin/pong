package gh.nijd.pong;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    @FXML
    private Pane settingsPane;
    @FXML
    private ColorPicker p1ColPick, p2ColPick;
    @FXML
    private Spinner<Integer> p1SensSpin, p2SensSpin;
    @FXML
    private CheckBox fullscreenOption;

    //TODO: Fix fullscreen.
    public void generalSaveSettings(ActionEvent event) { // Annoying, many features need fixing, cba rn --- NEEDS ATTENTION
        ((Stage)(((Node) event.getSource()).getScene().getWindow())).setFullScreen(fullscreenOption.isSelected());
        createSavedPU("General");
    }

    //TODO: Remove after fixing fullscreen.
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

    public void backToMenu() throws IOException {
        FXMLLoader loader = new Console().loadScene("menu.fxml", (Stage) settingsPane.getScene().getWindow(), fullscreenOption.isSelected());
        Main main = loader.getController();
        main.setValues(
                p1SensSpin.getValue(), p2SensSpin.getValue(),
                p1ColPick.getValue(), p2ColPick.getValue()
        );
    }

    public void setValues (int p1Sens, int p2Sens, Color p1Col, Color p2Col) {
        SpinnerValueFactory<Integer> p1SensSpinFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50, p1Sens);
        SpinnerValueFactory<Integer> p2SensSpinFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50, p2Sens);
        p1SensSpin.setValueFactory(p1SensSpinFactory);
        p2SensSpin.setValueFactory(p2SensSpinFactory);
        p1ColPick.setValue(p1Col);
        p2ColPick.setValue(p2Col);
    }
}