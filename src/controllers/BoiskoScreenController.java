package controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import silnik.MozliweKierunkiRuchu;
import silnik.Punkt;
import silnik.StanGry;


public class BoiskoScreenController {
    private StanGry stanGry = new StanGry(true);
    private MainController mainController;
    @FXML
    private Canvas canvas; //wymiary boiska to 600x700 jednostek
    @FXML
    private Button goraLewo;
    @FXML
    private Button gora;
    @FXML
    private Button goraPrawo;
    @FXML
    private Button prawo;
    @FXML
    private Button dolPrawo;
    @FXML
    private Button dol;
    @FXML
    private Button dolLewo;
    @FXML
    private Button lewo;
    //grafika i wymiary
    private GraphicsContext boisko;
    final int jednostkaWymiaruBoiska = 50;
    final int szerokoscStarejLini = 1;
    final int szerokoscNowejLini = 3;

    final int promienKropkiBoiska =2;
    final int szerokoscObwoduBoiska = 2;
    //yZero xZero to poczatki ukladow wspolrzednych, d
    final int xZero = 2 * jednostkaWymiaruBoiska;
    final int yZero = 1 * jednostkaWymiaruBoiska;

    final int promienKolaPozycji = 10;








    public MainController getMainController() {
        return mainController;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void  BoiskoScreenController(){

    }

    public void initialize(){
        boisko = canvas.getGraphicsContext2D();
        rysujPusteBoisko();


        rysujAktualnaPozycje();
        odswiezPrzyciski();
    }

    /*  POCZATEK UKLADU WSPOLRZEDNYCH W PRAWYM GORNYM ROGU os x rosnie w PRAWO, os y rosnie w DOL*/
    public void rysujPusteBoisko(){
        int jednostka = jednostkaWymiaruBoiska;
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

    public void rysujStaraLinie(int xPoczatek, int yPoczatek, int xKoniec, int yKoniec){
        boisko.setLineWidth(szerokoscStarejLini);
        boisko.setStroke(Color.WHITE);
        boisko.strokeLine(xZero + xPoczatek * jednostkaWymiaruBoiska,yZero + yPoczatek * jednostkaWymiaruBoiska,
                xZero + xKoniec * jednostkaWymiaruBoiska, yZero + yKoniec * jednostkaWymiaruBoiska);
    }

    public void rysujNowaLinie(int xPoczatek, int yPoczatek, int xKoniec, int yKoniec){
        boisko.setLineWidth(szerokoscNowejLini);
        boisko.setStroke(Color.WHITE);
        boisko.strokeLine(xZero + xPoczatek * jednostkaWymiaruBoiska,yZero + yPoczatek * jednostkaWymiaruBoiska,
                xZero + xKoniec * jednostkaWymiaruBoiska, yZero + yKoniec * jednostkaWymiaruBoiska);
    }

    public void rysujAktualnaPozycje(){
        boisko.setFill(Color.WHITE);
        boisko.fillOval(stanGry.aktualnaPozycja.getX() * jednostkaWymiaruBoiska + xZero - promienKolaPozycji/2 , stanGry.aktualnaPozycja.getY() * jednostkaWymiaruBoiska + yZero - promienKolaPozycji/2,promienKolaPozycji,promienKolaPozycji );
    }

    public void rysujStareRuchy(){
        if(stanGry.stareRuchy.size() >=2){
            for(int i =1; i < stanGry.stareRuchy.size(); i++){
                Punkt poczatek = stanGry.stareRuchy.get(i);
                Punkt koniec = stanGry.stareRuchy.get((i-1));
                rysujStaraLinie(poczatek.getX(),poczatek.getY(), koniec.getX(), koniec.getY());
            }
        }

    }

    public void rysujNoweRuchy(){
        if (!stanGry.noweRuchy.isEmpty()){
            Punkt poczatek = stanGry.stareRuchy.get(stanGry.stareRuchy.size()-1);
            Punkt koniec = stanGry.noweRuchy.get(0);
            rysujNowaLinie(poczatek.getX(),poczatek.getY(),koniec.getX(),koniec.getY());
            if (stanGry.noweRuchy.size()>1)
            for (int p = 1; p<stanGry.noweRuchy.size(); p++){
                poczatek = stanGry.noweRuchy.get(p-1);
                koniec = stanGry.noweRuchy.get(p);
                rysujNowaLinie(poczatek.getX(),poczatek.getY(),koniec.getX(),koniec.getY());
            }
        }
    }

    public void odswiezPrzyciski(){
        MozliweKierunkiRuchu mozliweKierunkiRuchu = stanGry.pokazMozliweKierunkiRuchu();
        mozliweKierunkiRuchu.wypiszMozliweKierunki();
        goraLewo.setDisable(true);
        gora.setDisable(true);
        goraPrawo.setDisable(true);
        prawo.setDisable(true);
        dolPrawo.setDisable(true);
        dol.setDisable(true);
        dolLewo.setDisable(true);
        lewo.setDisable(true);

            if (mozliweKierunkiRuchu.kierunek[0]){
                goraLewo.setDisable(false);
            }
            if (mozliweKierunkiRuchu.kierunek[1])  {gora.setDisable(false);}
            if (mozliweKierunkiRuchu.kierunek[2]) { goraPrawo.setDisable(false); }
            if (mozliweKierunkiRuchu.kierunek[3]) { prawo.setDisable(false); }
            if (mozliweKierunkiRuchu.kierunek[4]) { dolPrawo.setDisable(false); }
            if (mozliweKierunkiRuchu.kierunek[5]) { dol.setDisable(false); }
            if (mozliweKierunkiRuchu.kierunek[6]) { dolLewo.setDisable(false); }
            if (mozliweKierunkiRuchu.kierunek[7]) { lewo.setDisable(false); }

    }

    public void rysujPoNacisnieciu(int kierunek){
        Punkt aktualnaPozycja = stanGry.getAktualnaPozycja();

        /*
        switch (kierunek){
            case 0 : stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()-1,aktualnaPozycja.getY()-1)); break;
            case 1 : stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX(),aktualnaPozycja.getY()-1));break;
            case 2 : stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()+1,aktualnaPozycja.getY()-1));break;
            case 3 : stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()+1,aktualnaPozycja.getY()));break;
            case 4 : stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()+1,aktualnaPozycja.getY()+1));break;
            case 5 : stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX(),aktualnaPozycja.getY()+1));break;
            case 6 : stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()-1,aktualnaPozycja.getY()+1));break;
            case 7 : stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()-1,aktualnaPozycja.getY()));break;
        }
        */
        if (kierunek==0){
            stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()-1,aktualnaPozycja.getY()-1));
        }
        if (kierunek==1){
            stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX(),aktualnaPozycja.getY()-1));
        }
        if (kierunek==2){
            stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()+1,aktualnaPozycja.getY()-1));
        }
        if (kierunek==3){
            stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()+1,aktualnaPozycja.getY()));
        }
        if (kierunek==4){
            stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()+1,aktualnaPozycja.getY()+1));
        }
        if (kierunek==5){
            stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX(),aktualnaPozycja.getY()+1));
        }
        if (kierunek==6){
            stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()-1,aktualnaPozycja.getY()+1));
        }
        if (kierunek==7){
            stanGry.dodajNowyRuch(new Punkt(aktualnaPozycja.getX()-1,aktualnaPozycja.getY()));
        }


        rysujPusteBoisko();
        rysujStareRuchy();
        rysujNoweRuchy();
        rysujAktualnaPozycje();
        odswiezPrzyciski();
    }

    public void rysujGoraLewo(){ rysujPoNacisnieciu(0); }

    public void rysujGora(){ rysujPoNacisnieciu(1);}

    public void rysujGoraPrawo(){rysujPoNacisnieciu(2);}

    public void rysujPrawo(){rysujPoNacisnieciu(3);}

    public void rysujDolPrawo(){rysujPoNacisnieciu(4);}

    public void rysujDol(){rysujPoNacisnieciu(5);}

    public void rysujDolLewo(){rysujPoNacisnieciu(6);}

    public void rysujLewo(){rysujPoNacisnieciu(7);}


}
