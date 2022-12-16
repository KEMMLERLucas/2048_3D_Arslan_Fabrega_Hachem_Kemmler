package jeu2048.vue;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import jeu2048.Style;
import jeu2048.Tuile;

public class VueCase {

    public Node dessinerTuile(Tuile t,Style style){
        Canvas tuile = new Canvas();
        tuile.setHeight(75.0);
        tuile.setWidth(75.0);
        GraphicsContext gc = tuile.getGraphicsContext2D();
        gc.setFill(style.recupererCouleur(t.getValeur()));
        gc.fillRect(5, 5, 75, 75);
        if(t.getValeur()!=0){
            gc.strokeText(String.valueOf(t.getValeur()), 30, 40);
        }
        return tuile;
    }
}
