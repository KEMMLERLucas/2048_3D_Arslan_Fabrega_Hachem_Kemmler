package jeu2048.vue;

import javafx.scene.layout.GridPane;
import jeu2048.Ligne;
import jeu2048.Style;

public class VueLigne {
    public GridPane dessinerLigne(Ligne ligne, Style style){
        VueCase dessinerCasse = new VueCase();
        GridPane grid = new GridPane();
        grid.setStyle(style.getCouleurFond());
        grid.add(dessinerCasse.dessinerTuile(ligne.getTuile(0),style),0,0 );
        grid.add(dessinerCasse.dessinerTuile(ligne.getTuile(1),style),1,0 );
        grid.add(dessinerCasse.dessinerTuile(ligne.getTuile(2),style),2,0 );
        return grid;

    }
}
