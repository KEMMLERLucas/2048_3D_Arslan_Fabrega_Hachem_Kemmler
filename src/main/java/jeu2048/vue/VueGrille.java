package jeu2048.vue;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import jeu2048.GUI;
import jeu2048.Grille;

import java.io.IOException;
import java.net.URL;

public class VueGrille extends GUI {

    @Override
    public void actualise() {
    }

    public void dessinerGrille(GridPane emplacement, double taille, Grille grille) throws IOException {
        VueLigne dessinerLigne = new VueLigne();
        URL vueGrille = VueLigne.class.getResource("affichage/vueGrille.fxml");
        GridPane grid = new FXMLLoader(vueGrille).load();
        grid.add(dessinerLigne.dessinerLigne(),0,0 );
        grid.add(dessinerLigne.dessinerLigne(),1,0 );
        grid.add(dessinerLigne.dessinerLigne(),2,0 );
    }
}
