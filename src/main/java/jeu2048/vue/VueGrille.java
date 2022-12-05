package jeu2048.vue;

import javafx.scene.layout.GridPane;
import jeu2048.GUI;
import jeu2048.Grille;

import java.io.IOException;

public class VueGrille extends GUI {

    @Override
    public void actualise() {
    }

    public static GridPane dessinerGrille(Grille grille) throws IOException {
        VueLigne dessinerLigne = new VueLigne();
        GridPane grid = new GridPane();
        grid.add(dessinerLigne.dessinerLigne(grille.getLigne(0)),0,0 );
        grid.add(dessinerLigne.dessinerLigne(grille.getLigne(1)),0,1 );
        grid.add(dessinerLigne.dessinerLigne(grille.getLigne(2)),0,2 );
        return grid;
    }
}
