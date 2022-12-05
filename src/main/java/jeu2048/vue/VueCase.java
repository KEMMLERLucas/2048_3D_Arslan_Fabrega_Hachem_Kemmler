package jeu2048.vue;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;

public class VueCase {

    public Node dessinerTuile(double a, double b, double c){
        Canvas tuile = new Canvas();
        tuile.getGraphicsContext2D().fillRect(a,b,b,c);
        return tuile;
    }
}
