package silnik;

import a.d.S;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public class StanGry {
    /**
     kierunki ruchów
     0 - gora lewa
     1 - gora
     2 - gora prawa
     3 - prawo
     4 - dol prawo
     5 - dol
     6 - dol lewo
     7 - lewo

     mozliwosc ruchu - true / false
     */

    public boolean[][][] mozliweRuchy = new boolean[9][13][8];
    private boolean[][] mozliweOdbicia = new boolean[9][13];
    public List<Punkt> stareRuchy = new LinkedList<>();
    public List<Punkt> noweRuchy = new LinkedList<>();
    public Punkt aktualnaPozycja = new Punkt(4,6);
    public boolean mozliwoscRuchu;

    //funkcje metody
    public StanGry(boolean czyZaczynasz){
        ustawPoczatkoweBoisko(czyZaczynasz);
    }

    public void ustawMozliwyRuch(Punkt punkt, int kierunek, boolean wartosc){
        this.mozliweRuchy[punkt.getX()][punkt.getY()][kierunek] = wartosc;
    }

    public void  ustawPoczatkoweBoisko( boolean czyZaczynasz){
        //zeruje wszystkie pkt
        for (int x = 0; x < 9; x++){
            for(int y =0; y < 13; y++){
                for (int p = 0; p< 8; p++){
                    this.mozliweRuchy[x][y][p] = false;
                }
            }
        }

        //w srodek boiska z ktorego mozna isc w kazda strone
        for( int x = 1 ; x <= 7 ; x++){
            for (int y = 2 ; y <= 10 ; y++){
                for (int p = 0; p <8 ; p++){
                    mozliweRuchy[x][y][p] = true;
                }
            }
        }

        //lewa sciana bez katow
        for (int y = 2 ; y <= 10 ; y++ ){
            for (int p = 2; p <= 4 ; p++){
                mozliweRuchy[0][y][p] = true;
            }
        }

        //prawa sciana bez katow
        for (int y = 2 ; y <= 10 ; y++ ){
            mozliweRuchy[8][y][6] = true;
            mozliweRuchy[8][y][7] = true;
            mozliweRuchy[8][y][0] = true;
        }

        //katy boiska
        mozliweRuchy[0][1][4] = true;
        mozliweRuchy[0][11][2] = true;
        mozliweRuchy[8][1][6] = true;
        mozliweRuchy[8][11][7] = true;

        //pozioma gorna sciana
        for ( int p = 4 ; p <= 6 ; p++){
            mozliweRuchy[1][1][p] = true;
            mozliweRuchy[2][1][p] = true;
            mozliweRuchy[6][1][p] = true;
            mozliweRuchy[7][1][p] = true;
        }

        //pozioma dolna sciana
        for (int p = 0 ; p <=2 ; p++){
            mozliweRuchy[1][11][p] = true;
            mozliweRuchy[2][11][p] = true;
            mozliweRuchy[6][11][p] = true;
            mozliweRuchy[7][11][p] = true;
        }

        //lewa gora zalamanie do bramki
        for (int p =2 ; p <= 6 ; p++){
            mozliweRuchy[3][1][p] = true;
        }

        //prawa gora zalamanie do bramki
        for (int p =4 ; p < 7 ; p++){
            mozliweRuchy[5][1][p] = true;
        }
        mozliweRuchy[5][1][0] = true;

        //lewa dol zalamanie do bramki
        for (int p =0 ; p <= 4 ; p++){
            mozliweRuchy[3][11][p] = true;
        }

        //prawy dol zalamanie do bramki
        for (int p =0 ; p <= 2 ; p++){
            mozliweRuchy[5][11][p] = true;
        }
        mozliweRuchy[5][11][6] = true;
        mozliweRuchy[5][11][7] = true;

        //srodki przed bramkami
        for (int p = 0 ; p <=7 ; p++){
            mozliweRuchy[4][1][p] = true;
            mozliweRuchy[4][11][p] = true;
        }

        //gorna bramka
        //w sumie nie uzupelniam


        //mozliwe odbicia po ruchu. poczatkowo jest mozliwe tylko na liniach bocznych i na poczatku

        //srodek
        mozliweOdbicia[4][6] = true;

        //lewa linia i prawa linia
        for (int y = 1 ; y <=11  ;y++){
            mozliweOdbicia[0][y] = true;
            mozliweOdbicia[8][y] = true;
        }

        //gorna i dolna linia
        for(int x = 0; x <=8 ; x++){
            if( x != 4){
                mozliweOdbicia[x][1] = true;
                mozliweOdbicia[x][11] = true;
            }
        }
        //aktualnaPozycja = new Punkt(4,6);

        //ustawienie czy ty zaczynasz
        mozliwoscRuchu = czyZaczynasz;

        //dodanie pierwszego punktu
        stareRuchy.add(new Punkt(4,6));
    }

    public MozliweKierunkiRuchu pokazMozliweKierunkiRuchu(){
      MozliweKierunkiRuchu mozliweKierunkiRuchu =  new MozliweKierunkiRuchu();
      int x = aktualnaPozycja.getX();
      int y = aktualnaPozycja.getY();
      for ( int p=0 ; p< 8 ; p++){
              mozliweKierunkiRuchu.kierunek[p]= mozliweRuchy[x][y][p];
              if(mozliweRuchy[x][y][p]){

                  System.out.println("mozliwy kierunek ruchu: " + Integer.toString(p));
              }
      }
      return mozliweKierunkiRuchu;
    }

    public void dodajNowyRuch(Punkt punkt){

        int kierunekRuchu;
        int odwrotnyKierunekRuchu;
        Punkt poprzedniPunkt;
        if(noweRuchy.size()<1){
             poprzedniPunkt = stareRuchy.get(stareRuchy.size()-1);
        }else{
            poprzedniPunkt = noweRuchy.get(noweRuchy.size()-1);
        }
        noweRuchy.add(punkt);
        uaktualnijAktualnaPozycje();
        mozliweOdbicia[punkt.getX()][punkt.getY()] = true;
        kierunekRuchu = pokazKierunekMiedzyPunktami(poprzedniPunkt,punkt);
        odwrotnyKierunekRuchu = (kierunekRuchu + 4) % 8;



        ustawMozliwyRuch(poprzedniPunkt,kierunekRuchu,false);
        ustawMozliwyRuch(punkt,odwrotnyKierunekRuchu,false);

        //mozliweRuchy[poprzedniPunkt.getX()][poprzedniPunkt.getY()][kierunekRuchu] = false;
        //mozliweRuchy[punkt.getX()][punkt.getY()][odwrotnyKierunekRuchu] = false;
        System.out.println("kasuje mozliwosci kierunkow : tam-" + Integer.toString(kierunekRuchu) + ", powrot-" + Integer.toString(odwrotnyKierunekRuchu) );
        if (mozliweRuchy[poprzedniPunkt.getX()][poprzedniPunkt.getY()][kierunekRuchu]){
            System.out.println("nie skasowano kierunku z poprzendiego punktu");
        }
        System.out.print("Punkt: " + Integer.toString(poprzedniPunkt.getX()) + "," + Integer.toString(poprzedniPunkt.getY()) + " kierunki: ");
        for (int p =0; p<8 ; p++){
            if(mozliweRuchy[poprzedniPunkt.getX()][poprzedniPunkt.getY()][p]){
                System.out.print(Integer.toString(p) + " ");
            }
        }
        System.out.print("\n");
        System.out.print("Punkt: " + Integer.toString(punkt.getX()) + "," + Integer.toString(punkt.getY()) + " kierunki: ");
        for (int p =0; p<8 ; p++){
            if(mozliweRuchy[punkt.getX()][punkt.getY()][p]){
                System.out.print(Integer.toString(p) + " ");
            }
        }
        System.out.print("\n");
        System.out.println(Integer.toString(poprzedniPunkt.getX()) + "," + Integer.toString(poprzedniPunkt.getY()) + " kierunek: " + Integer.toString(kierunekRuchu));
        System.out.print("aktualna pozycja : " + Integer.toString(aktualnaPozycja.getX()) + "," + Integer.toString(aktualnaPozycja.getY()));

    }

    public int pokazKierunekMiedzyPunktami(Punkt a, Punkt b) {
        int deltaX = b.getX() - a.getX();
        int deltaY = b.getY() - a.getY();
        int wynik = -1;
        if ((abs(deltaX) > 1) || (abs(deltaY)) > 1 || (deltaX == 0 && deltaY == 0)) {
            wynik = -1;
        } else {
            if (deltaX == -1 && deltaY == -1){ wynik = 0;}
            if (deltaX == 0 && deltaY == -1) {wynik = 1;}
            if (deltaX == 1 && deltaY == -1) {wynik = 2;}
            if (deltaX == 1 && deltaY == 0) {wynik = 3;}
            if (deltaX == 1 && deltaY == 1) {wynik = 4;}
            if (deltaX == 0 && deltaY == 1) {wynik = 5;}
            if (deltaX == -1 && deltaY == 1) {wynik = 6;}
            if (deltaX == -1 && deltaY == 0) {wynik = 7;}
        }
        return wynik;
    }

    public void dodajStaryRuch(Punkt punkt){
        stareRuchy.add(punkt);
        mozliweOdbicia[punkt.getX()][punkt.getY()] = true;
        if(stareRuchy.size()>1){
            Punkt poprzedniPunkt = stareRuchy.get(stareRuchy.size()-2);
            int kierunekRuchu = pokazKierunekMiedzyPunktami(poprzedniPunkt,punkt);
            int odwrotnyKierunekRuchu = (kierunekRuchu + 4)%8;

            ustawMozliwyRuch(poprzedniPunkt,kierunekRuchu,false);
            ustawMozliwyRuch(punkt,odwrotnyKierunekRuchu,false);
        }
        setAktualnaPozycja(punkt);
    }

    public void setAktualnaPozycja(Punkt punkt){
        aktualnaPozycja = punkt;
    }

    public Punkt getAktualnaPozycja() {
        return aktualnaPozycja;
    }

    public void uaktualnijAktualnaPozycje(){
        if (noweRuchy.isEmpty()){
            aktualnaPozycja = stareRuchy.get(stareRuchy.size()-1);
        }else{
            aktualnaPozycja = noweRuchy.get(noweRuchy.size()-1);
        }
    }
}
