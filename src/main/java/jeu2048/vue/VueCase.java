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
        gc.setFill(recupererCouleur(t.getValeur()));
        gc.fillRect(5, 5, 75, 75);
        if(t.getValeur()!=0){
            gc.strokeText(String.valueOf(t.getValeur()), 30, 40);
        }
        return tuile;
    }
    public Color recupererCouleur(int val){
        switch (val){
            case 0:
                return Color.WHITE;
            case 2:
                return Color.WHITESMOKE;
            case 4:
                return Color.BEIGE;
            case 8 :
                return Color.LIGHTYELLOW;
            case 16 :
                return Color.ORANGE;
            case 32:
                return Color.FIREBRICK;
            case 64:
                return Color.RED;
            case 128:
                return Color.DARKRED;
            case 256:
                return Color.DARKGOLDENROD;
            case 512:
                return Color.YELLOW;
            case 1024:
                return Color.LIGHTGOLDENRODYELLOW;
            case 2048 :
                return Color.GOLD;
            default:
                return Color.ROYALBLUE;
        }

    }
}
