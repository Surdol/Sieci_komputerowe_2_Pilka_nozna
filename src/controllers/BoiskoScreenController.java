package controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import silnik.Boisko;


public class BoiskoScreenController {
    private MainController mainController;

    @FXML
    private Canvas canvas; //wymiary boiska to 600x700 jednostek
    private Boisko boisko;

    public MainController getMainController() {
        return mainController;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize(){
        int jednostka =50;
        boisko.setObraz(canvas.getGraphicsContext2D());
        boisko.setJednostka(jednostka);
        boisko.rysujBoisko();
    }
}
