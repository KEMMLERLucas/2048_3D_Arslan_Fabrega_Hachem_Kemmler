package jeu2048.vue;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import jeu2048.Grille;
import jeu2048.Style;

import java.io.IOException;

public class VueGrille{
    public static GridPane dessinerGrille(Grille grille, Style style) throws IOException {
        VueLigne dessinerLigne = new VueLigne();
        GridPane grid = new GridPane();
        grid.setStyle(style.getCouleurFond());
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(dessinerLigne.dessinerLigne(grille.getLigne(0),style),0,0 );
        grid.add(dessinerLigne.dessinerLigne(grille.getLigne(1),style),0,2 );
        grid.add(dessinerLigne.dessinerLigne(grille.getLigne(2),style),0,4 );
        return grid;
    }
}
