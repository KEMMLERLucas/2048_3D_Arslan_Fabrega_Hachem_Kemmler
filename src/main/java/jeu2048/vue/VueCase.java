package jeu2048.vue;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jeu2048.Tuile;

public class VueCase {

    public Node dessinerTuile(Tuile t){
        Canvas tuile = new Canvas();
        tuile.setHeight(75.0);
        tuile.setWidth(75.0);
        GraphicsContext gc = tuile.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(5, 5, 75, 75);
        gc.strokeText(String.valueOf(t.getValeur()), 30, 40);
        return tuile;
    }
}
