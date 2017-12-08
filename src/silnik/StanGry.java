package silnik;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class StanGry {
   private List<Punkt> przebytaDrogaPilki = new LinkedList<>(); //przechowuje wierzcholki po ktorych po kolei przechodzila pilka


   public StanGry(){
       przebytaDrogaPilki.add(new Punkt(4,6));
   }

    public void nowePolozeniePilki(Punkt p){
       przebytaDrogaPilki.add(p);
    }

}
