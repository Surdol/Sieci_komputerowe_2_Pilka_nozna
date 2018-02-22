package silnik;

import java.util.ArrayList;
import java.util.List;

public class MozliweKierunkiRuchu {
    public boolean[] kierunek = new boolean[8];

    public MozliweKierunkiRuchu(){
        for (int i =0 ; i<8 ; i++){
            kierunek[i]=false;
        }
    }

    public void dodajKierunek(int kierunek){
        this.kierunek[kierunek] = true;
    }

    public void wypiszMozliweKierunki(){
        System.out.print("mozliwe kierunki ruchu w tym punkcie: ");
        for (int p =0 ; p<8 ; p++){
            if(kierunek[p]){
                System.out.print(Integer.toString(p) + " ");
            }
        }
        System.out.print("\n");
    }

}
