package jeu2048.vue;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jeu2048.Tuile;

public class VueCase {

    public Node dessinerTuile(Tuile t){
        Canvas tuile = new Canvas();
        tuile.setHeight(50.0);
        tuile.setWidth(50.0);
        GraphicsContext gc = tuile.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, 50, 50);
        gc.strokeText(String.valueOf(t.getValeur()), 5, 25);
        return tuile;
    }
}
