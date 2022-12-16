package jeu2048;

import javafx.scene.paint.Color;

public abstract class Style {

    public abstract String getCouleurFond();

    public Color recupererCouleur(int val) {
        switch (val) {
            case 0:
                return Color.WHITE;
            case 2:
                return Color.WHITESMOKE;
            case 4:
                return Color.BEIGE;
            case 8:
                return Color.LIGHTYELLOW;
            case 16:
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
            case 2048:
                return Color.GOLD;
            default:
                return Color.ROYALBLUE;
        }
    }
}
