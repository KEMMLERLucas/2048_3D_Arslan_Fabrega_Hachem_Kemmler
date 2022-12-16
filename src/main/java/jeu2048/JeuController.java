package jeu2048;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jeu2048.vue.VueGrille;

import java.io.IOException;
import java.util.List;

public class JeuController {

    public void afficherJeu() {
        Jeu jeu = getJeu();
        String res = "";
        if(jeu.contient2048()){
            res = " : VICTOIRE";
        }
        if(jeu.perdu()){
            res = " : PERDU";
        }
        try {
            //preparation du stage
            Stage stage = Main.getStagePrincipal();
            stage.setMinHeight(480);
            stage.setMinWidth(720);
            //preparation de la grille
            GridPane grid = new GridPane();
            List<Grille> Grilles = jeu.getGrille();
            Style style = jeu.getStyle();
            grid.add(VueGrille.dessinerGrille(Grilles.get(0),style), 0, 0);
            grid.add(VueGrille.dessinerGrille(Grilles.get(1),style), 1, 0);
            grid.add(VueGrille.dessinerGrille(Grilles.get(2),style), 2, 0);
            //preparation des controle
            FXMLLoader fxmlLoaderControle = new FXMLLoader(Main.class.getResource("controle.fxml"));
            Node controle = fxmlLoaderControle.load();
            //preparation du score
            Label score = new Label("Score : "+jeu.getScore()+res);
            score.setFont(Font.font ("Arial",36));
            //mise en commun du tous
            AnchorPane root = new AnchorPane();
            root.setMinSize(720,480);
            root.setStyle(style.getCouleurFond());
            AnchorPane.setTopAnchor(score,30.0);
            AnchorPane.setTopAnchor(grid, 150.0);
            AnchorPane.setBottomAnchor(controle, 0.0);
            stage.setTitle("2048-3D!");
            root.getChildren().add(grid);
            root.getChildren().add(controle);
            root.getChildren().add(score);
            Scene scene = preparerScene(root, jeu);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chargerJeu(){
        Jeu j1 = new Jeu(0, 3, 3);
        InstanceJeu intjeu = InstanceJeu.getInstance();
        intjeu.setJeu(j1);
        afficherJeu();
    }
    @FXML
    public void chargerDernierePartie(){
        Jeu j1 = new Jeu();
        InstanceJeu intjeu = InstanceJeu.getInstance();
        intjeu.setJeu(j1);
        afficherJeu();
    }

    public Scene preparerScene(Pane root, Jeu jeu) {
        Scene scene = new Scene(root);
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                boolean ok = false;
                switch (event.getCode()) {

                    case Z:
                        ok = jeu.deplacerHaut();
                        if (ok) ajouterCase(jeu);
                        break;
                    case S:
                        ok = jeu.deplacerBas();
                        if (ok) ajouterCase(jeu);
                        break;
                    case D:
                        ok = jeu.deplacerDroite();
                        if (ok) ajouterCase(jeu);
                        break;
                    case Q:
                        ok = jeu.deplacerGauche();
                        if (ok) ajouterCase(jeu);
                        break;
                    case F:
                        ok = jeu.deplacerArrierre();
                        if (ok) ajouterCase(jeu);
                        break;
                    case R:
                        ok = jeu.deplacerAvant();
                        if (ok) ajouterCase(jeu);
                        break;
                }
                afficherJeu();
            }
        });
        return scene;
    }

    public void ajouterCase(Jeu jeu) {
        int i = (int) Math.floor(Math.random() * 2);
        if (i == 2) {
            jeu.addRandomTile();
            jeu.addRandomTile();
        } else {
            jeu.addRandomTile();
        }
    }

    public Jeu getJeu(){
        InstanceJeu intjeu = InstanceJeu.getInstance();
        return intjeu.getJeu();
    }
    @FXML
    public void changerThemeClassique(){
        Jeu j1 = getJeu();
        j1.setStyle(new StyleClassique());
        afficherJeu();
    }
    @FXML
    public void changerThemeNoel(){
        Jeu j1 = getJeu();
        j1.setStyle(new StyleNoel());
        afficherJeu();
    }

    @FXML
    public void rollBack(){
        Jeu j1 = getJeu();
        j1.retourArriere();
        afficherJeu();
    }
    @FXML
    public void coupAleatoire(){
        Jeu j = getJeu();
        j.coupAleatoire();
        ajouterCase(j);
        afficherJeu();
    }
}