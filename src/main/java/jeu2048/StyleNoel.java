package jeu2048;

import javafx.scene.paint.Color;

public class StyleNoel extends Style implements java.io.Serializable {
    @Override
    public String getCouleurFond() {
        return "-fx-background-color: #900C3F ;";
    }

    @Override
    public Color recupererCouleur(int val) {
        switch (val) {
            case 0:
                return Color.web("#f0f4ee");
            case 2:
                return Color.web("#e6ece1");
            case 4:
                return Color.web("#cbdbc0");
            case 8:
                return Color.web("#acc49b");
            case 16:
                return Color.web("#769b5c");
            case 32:
                return Color.web("#d1b435");
            case 64:
                return Color.web("#e1ab2d");
            case 128:
                return Color.web("#e19b2d");
            case 256:
                return Color.web("#c96614");
            case 512:
                return Color.web("#e14d1e");
            case 1024:
                return Color.web("#f22c14");
            case 2048:
                return Color.web("#900C3F");
            default:
                return Color.web("#972012");
        }
    }
}
