package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuScreenController {

    private MainController mainController;

    @FXML
    Button buttonBoisko;

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void openBoiskoScreen(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/BoiskoScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BoiskoScreenController boiskoScreenController = loader.getController();
        boiskoScreenController.setMainController(this.getMainController());
        mainController.setPane(pane);
    }
}
