package silnik;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Boisko {
    private GraphicsContext obraz;
    private int jednostka;

    public void rysujBoisko(){

    }

    public GraphicsContext getObraz() {
        return obraz;
    }

    public void setObraz(GraphicsContext obraz) {
        this.obraz = obraz;
    }

    public int getJednostka() {
        return jednostka;
    }

    public void setJednostka(int jednostka) {
        this.jednostka = jednostka;
    }
}
