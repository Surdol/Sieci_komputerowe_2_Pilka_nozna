package controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import silnik.StanGry;

public class BoiskoScreenController {
    private MainController mainController;
    @FXML
    private Canvas canvas; //wymiary boiska to 600x700 jednostek
    private GraphicsContext boisko;
    private final int jednostkaWymiaruBoiska = 50;
    private final int szerokoscStarejLini = 1;
    private final int szerokoscNowejLini = 3;
    private final int promienKolaPozycji = 4;
    private final int promienKropkiBoiska =2;
    private final int szerokoscObwoduBoiska = 2;
    //yZero xZero to poczatki ukladow wspolrzednych, d
    private final int xZero = 2 * jednostkaWymiaruBoiska;
    private final int yZero = 1 * jednostkaWymiaruBoiska;

    public void  BoiskoScreenController(){

    }

    public void initialize(){
        boisko = canvas.getGraphicsContext2D();
        rysujPusteBoisko(jednostkaWymiaruBoiska);
        rysujStaraLinie(4,6,4,5);
        rysujNowaLinie(4,5,4,4);
        rysujKoloPozycji(4,4);
    }


    public MainController getMainController() {
        return mainController;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }



    /*  POCZATEK UKLADU WSPOLRZEDNYCH W PRAWYM GORNYM ROGU os x rosnie w PRAWO, os y rosnie w DOL*/
    public void rysujPusteBoisko(int jednostka){
        boisko.setFill(Color.GREEN);
        boisko.fillRect(0,0,12 * jednostka, 14 * jednostka);
        boisko.setFill(Color.WHITE);
        boisko.setStroke(Color.WHITE);
        boisko.setLineWidth(szerokoscObwoduBoiska);

        //rysowanie lini

        //gora lini
        boisko.strokeLine(2 * jednostka, 2 * jednostka, 5 * jednostka, 2 * jednostka);
        boisko.strokeLine(5 * jednostka, 2 * jednostka, 5 * jednostka, 1 * jednostka);
        boisko.strokeLine(5 * jednostka, 1 * jednostka, 7 * jednostka, 1 * jednostka);
        boisko.strokeLine(7 * jednostka, 1 * jednostka, 7 * jednostka, 2 * jednostka);
        boisko.strokeLine(7 * jednostka, 2 * jednostka, 10 * jednostka, 2 * jednostka);
        //pionowa linia prawa
        boisko.strokeLine(10 * jednostka, 2 * jednostka, 10 * jednostka, 12 * jednostka);
        //dolna linia
        boisko.strokeLine(10 * jednostka, 12 * jednostka, 7 * jednostka, 12 * jednostka);
        boisko.strokeLine(7 * jednostka, 12 * jednostka, 7 * jednostka, 13 * jednostka);
        boisko.strokeLine(7 * jednostka, 13 * jednostka, 5 * jednostka, 13 * jednostka);
        boisko.strokeLine(5 * jednostka, 13 * jednostka, 5 * jednostka,12 * jednostka);
        boisko.strokeLine(5 * jednostka,12 * jednostka, 2 * jednostka,12 * jednostka);
        boisko.strokeLine(2 * jednostka,12 * jednostka,2 * jednostka,2 * jednostka);



        for (int x = 0; x < 7; x++){
            for (int y = 0; y< 9; y++){
                boisko.fillOval(3 * jednostka +x * jednostka,3 * jednostka + y * jednostka,promienKropkiBoiska,promienKropkiBoiska);
            }
        }
        boisko.fillOval(6*jednostka, 2 * jednostka, promienKropkiBoiska, promienKropkiBoiska);
        boisko.fillOval(6*jednostka, 12 * jednostka, promienKropkiBoiska, promienKropkiBoiska);
    }

    //cienki linie poprzendich ruchów
    public void rysujStaraLinie(int xPoczatek, int yPoczatek, int xKoniec, int yKoniec){


        boisko.setLineWidth(szerokoscStarejLini);
        boisko.setStroke(Color.WHITE);
        boisko.strokeLine(xZero + xPoczatek * jednostkaWymiaruBoiska,yZero + yPoczatek * jednostkaWymiaruBoiska,
                xZero + xKoniec * jednostkaWymiaruBoiska, yZero + yKoniec * jednostkaWymiaruBoiska);
    }

    //grube linie aktualnego ruchu
    public void rysujNowaLinie(int xPoczatek, int yPoczatek, int xKoniec, int yKoniec){
        boisko.setLineWidth(szerokoscNowejLini);
        boisko.setStroke(Color.WHITE);
        boisko.strokeLine(xZero + xPoczatek * jednostkaWymiaruBoiska,yZero + yPoczatek * jednostkaWymiaruBoiska,
                xZero + xKoniec * jednostkaWymiaruBoiska, yZero + yKoniec * jednostkaWymiaruBoiska);
    }

    //kolko aktualnej pozycji
    public void rysujKoloPozycji(int x, int y){
        boisko.setFill(Color.WHITE);
        boisko.strokeOval(xZero + x * jednostkaWymiaruBoiska, yZero + y * jednostkaWymiaruBoiska,promienKolaPozycji, promienKolaPozycji);

    }
}
