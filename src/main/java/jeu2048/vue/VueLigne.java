package jeu2048.vue;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import jeu2048.GUI;

import java.io.IOException;
import java.net.URL;

public class VueLigne extends GUI {

    @Override
    public void actualise() {
    }

    public GridPane dessinerLigne() throws IOException {
        VueCase dessinerCasse = new VueCase();
        URL vueLigne = VueLigne.class.getResource("affichage/vueLigne.fxml");
        GridPane grid = new FXMLLoader(vueLigne).load();
        grid.add(dessinerCasse.dessinerTuile(100,100,100),0,0 );
        grid.add(dessinerCasse.dessinerTuile(100,100,100),1,0 );
        grid.add(dessinerCasse.dessinerTuile(100,100,100),2,0 );
        return grid;

    }
}
