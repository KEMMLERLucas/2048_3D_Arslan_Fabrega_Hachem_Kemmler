package jeu2048.vue;

import javafx.scene.layout.GridPane;
import jeu2048.GUI;
import jeu2048.Ligne;

import java.io.IOException;

public class VueLigne extends GUI {

    @Override
    public void actualise() {
    }

    public GridPane dessinerLigne(Ligne ligne) throws IOException {
        VueCase dessinerCasse = new VueCase();
        GridPane grid = new GridPane();
        grid.add(dessinerCasse.dessinerTuile(ligne.getTuile(0)),0,0 );
        grid.add(dessinerCasse.dessinerTuile(ligne.getTuile(1)),1,0 );
        grid.add(dessinerCasse.dessinerTuile(ligne.getTuile(2)),2,0 );
        return grid;

    }
}
