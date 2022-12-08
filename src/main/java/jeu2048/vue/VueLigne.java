package jeu2048.vue;

import javafx.scene.layout.GridPane;
import jeu2048.Ligne;

public class VueLigne {
    public GridPane dessinerLigne(Ligne ligne){
        VueCase dessinerCasse = new VueCase();
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #555151;");
        grid.add(dessinerCasse.dessinerTuile(ligne.getTuile(0)),0,0 );
        grid.add(dessinerCasse.dessinerTuile(ligne.getTuile(1)),1,0 );
        grid.add(dessinerCasse.dessinerTuile(ligne.getTuile(2)),2,0 );
        return grid;

    }
}
