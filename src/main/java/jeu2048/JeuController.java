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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class JeuController {
    public void afficherJeu(Jeu jeu) {
        try {
            //preparation du stage
            Stage stage = Main.getStagePrincipal();
            stage.setMinHeight(480);
            stage.setMinWidth(720);
            //preparation de la grille
            GridPane grid = new GridPane();
            List<Grille> Grilles = jeu.getGrille();
            grid.add(VueGrille.dessinerGrille(Grilles.get(0)), 0, 0);
            grid.add(VueGrille.dessinerGrille(Grilles.get(1)), 1, 0);
            grid.add(VueGrille.dessinerGrille(Grilles.get(2)), 2, 0);
            //preparation des controle
            FXMLLoader fxmlLoaderControle = new FXMLLoader(Main.class.getResource("controle.fxml"));
            Node controle = fxmlLoaderControle.load();
            //preparation du score
            Label score = new Label("Score : "+jeu.getScore());
            score.setFont(new Font("Arial",50));
            System.out.println(jeu.getScore());
            //mise en commun du tous
            AnchorPane root = new AnchorPane();
            root.setMinSize(720,480);
            root.setStyle("-fx-background-color: #555151;");
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
        boolean debugMode = false;
        Jeu jeu;
        int TAILLEMAX = 3;
        int nbTuileDebut = 0;
        int nbTuileDebutMax = 2;
        int nbTuile = TAILLEMAX * TAILLEMAX * TAILLEMAX;
        int nbLigne = TAILLEMAX * TAILLEMAX;
        List<Tuile> listTuiles = new ArrayList<Tuile>();
        for (int i = 0; i < nbTuile; i++) {
            Tuile t = new Tuile(0, true);
            if (nbTuileDebut != nbTuileDebutMax) {
                t.randomizeStart();
                if (!t.getEstVide()) {
                    nbTuileDebut++;
                }
            }
            if (i == nbTuile - 2 && nbTuileDebut == 0) {
                t.setValeur(2);
                t.setEstVide(false);
            }
            if (i == nbTuile - 1 && nbTuileDebut != nbTuileDebutMax) {
                t.setValeur(2);
                t.setEstVide(false);
            }
            listTuiles.add(t);
        }


        List<Ligne> listLignes = new ArrayList<Ligne>();
        for (int i = 0; i < nbLigne; i++) {
            List<Tuile> listTuile = new ArrayList<Tuile>();
            Ligne ligne = new Ligne(listTuile);
            if (!listTuiles.isEmpty()) {
                for (int j = 0; j < TAILLEMAX; j++) {
                    ligne.getListTuiles().add(listTuiles.get(0));
                    listTuiles.remove(0);
                }
                listLignes.add(ligne);
            }
        }

        List<Grille> listGrilles = new ArrayList<Grille>();
        for (int i = 0; i < TAILLEMAX; i++) {
            List<Ligne> listLigne = new ArrayList<Ligne>();
            Grille g = new Grille(listLigne, TAILLEMAX);

            if (!listLignes.isEmpty()) {
                for (int j = 0; j < TAILLEMAX; j++) {
                    g.getListLignes().add(listLignes.get(0));
                    listLignes.remove(0);
                }
                listGrilles.add(g);
            }
        }
        jeu = new Jeu(listGrilles, 0, TAILLEMAX);

        Tuile t1 = new Tuile(0, true);
        Tuile t2 = new Tuile(0, true);
        Tuile t3 = new Tuile(4, false);

        Tuile t4 = new Tuile(0, true);
        Tuile t5 = new Tuile(0, true);
        Tuile t6 = new Tuile(2, false);

        Tuile t7 = new Tuile(0, true);
        Tuile t8 = new Tuile(0, true);
        Tuile t9 = new Tuile(2, false);
        Tuile t10 = new Tuile(0, true);
        Tuile t11 = new Tuile(0, true);
        Tuile t12 = new Tuile(0, true);
        Tuile t13 = new Tuile(0, true);
        Tuile t14 = new Tuile(0, true);
        Tuile t15 = new Tuile(0, true);
        Tuile t16 = new Tuile(0, true);
        Tuile t17 = new Tuile(0, true);
        Tuile t18 = new Tuile(0, true);
        Tuile t19 = new Tuile(0, true);
        Tuile t20 = new Tuile(0, true);
        Tuile t21 = new Tuile(0, true);
        Tuile t22 = new Tuile(0, true);
        Tuile t23 = new Tuile(0, true);
        Tuile t24 = new Tuile(0, true);
        Tuile t25 = new Tuile(0, true);
        Tuile t26 = new Tuile(0, true);
        Tuile t27 = new Tuile(0, true);
        Ligne l1 = new Ligne(List.of(t1, t2, t3));
        Ligne l2 = new Ligne(List.of(t4, t5, t6));
        Ligne l3 = new Ligne(List.of(t7, t8, t9));
        Ligne l4 = new Ligne(List.of(t10, t11, t12));
        Ligne l5 = new Ligne(List.of(t13, t14, t15));
        Ligne l6 = new Ligne(List.of(t16, t17, t18));
        Ligne l7 = new Ligne(List.of(t19, t20, t21));
        Ligne l8 = new Ligne(List.of(t22, t23, t24));
        Ligne l9 = new Ligne(List.of(t25, t26, t27));

        Grille g1 = new Grille(List.of(l1, l2, l3), 3);
        Grille g2 = new Grille(List.of(l4, l5, l6), 3);
        Grille g3 = new Grille(List.of(l7, l8, l9), 3);
        List<Grille> lg = List.of(g1, g2, g3);
        //System.out.println("Grille 1");
        //g1.afficherGrille();
        //System.out.println();
        //System.out.println("Grille 2");
        //g2.afficherGrille();
        //System.out.println();
        //System.out.println("Grille 3");
        //g3.afficherGrille();
        Jeu j1 = new Jeu(lg, 0, 3);
        //j1.afficherJeuConsole();
        /*j2.randomize();
        j3.randomize();
        j4.randomize();
        j5.randomize();*/
        List<Jeu> enCours = List.of(j1);
        SerializerJeu sj = new SerializerJeu(enCours);
        sj.serialize();
        DeserializerJeu dj = new DeserializerJeu();
        List<Jeu> lj = dj.deserialize();
        /// A voir si il ne faut pas tout faire dans la même classe (serialisation et deserilisation)
        //lj.get(0).afficherJeuConsole();

        System.out.println("Bienvenue dans le 2048 3D");
        Scanner input = new Scanner(System.in);
        AtomicBoolean playing = new AtomicBoolean(true);

        afficherJeu(j1);

        /**if (!debugMode) {

         j1.afficherJeuConsole();
         System.out.print("Pour se déplacer, utilisez les touches:" +
         " Z pour monter, S pour descendre, Q pour aller à gauche et D pour aller à droite, F pour l'étage supérieur et R pour l'étage inférieur. Pour faire un mouvement aléatoire, appuyez sur P \n pour quitter, appuyez sur C");
         while (playing.get()) {
         // takes input from the keyboard
         String direction = input.nextLine().toUpperCase();

         switch (direction) {
         case "Z":
         System.out.println("Vous avez choisi de monter");
         j1.deplacerHaut();
         break;
         case "S":
         System.out.println("Vous avez choisi de descendre");
         j1.deplacerBas();
         break;
         case "Q":
         System.out.println("Vous avez choisi d'aller à gauche");
         j1.deplacerGauche();
         break;
         case "D":
         System.out.println("Vous avez choisi d'aller à droite");
         j1.deplacerDroite();
         break;
         case "F":
         System.out.println("Vous avez choisi d'aller à l'étage supérieur");
         j1.deplacerAvant();
         break;
         case "R":
         System.out.println("Vous avez choisi d'aller à l'étage inférieur");
         j1.deplacerArrierre();
         break;
         case "C":
         System.out.println("Vous avez choisi de quitter");
         input.close();
         playing.set(false);
         break;
         case "P":
         System.out.println("Vous avez choisi de faire un mouvement aléatoire");
         int i = 1 + (int) Math.floor(Math.random() * 6);
         switch (i){
         case 1:
         System.out.println("Vous êtes allés vers le haut");
         j1.deplacerHaut();
         break;
         case 2:
         System.out.println("Vous êtes allés vers le bas");
         j1.deplacerBas();
         break;
         case 3:
         System.out.println("Vous êtes allés vers la gauche");
         j1.deplacerGauche();
         break;
         case 4:
         System.out.println("Vous êtes allés vers la droite");
         j1.deplacerDroite();
         break;
         case 5:
         System.out.println("Vous êtes allés vers l'avant");
         j1.deplacerAvant();
         break;
         case 6:
         System.out.println("Vous êtes allés vers l'arrière");
         j1.deplacerArrierre();
         break;
         }
         break;
         }
         int i = (int) Math.floor(Math.random() * 2);
         if (i == 2) {
         j1.addRandomTile();
         j1.addRandomTile();
         }else{
         j1.addRandomTile();

         }
         j1.afficherJeuConsole();
         j1.getGrilleList().forEach(grille -> {
         grille.getToutesLesTuiles().forEach(tuile -> {
         if (tuile.getValeur() == 2048) {
         System.out.println("Vous avez gagné");
         playing.set(false);
         }
         });
         });
         }
         } else {
         j1.afficherJeuConsole();
         System.out.println("Test de la fonction de déplacement vers le haut");
         j1.deplacerHaut();
         j1.afficherJeuConsole();
         }*/
    }

    public Scene preparerScene(Pane root, Jeu jeu) {
        Scene scene = new Scene(root);
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        jeu.deplacerHaut();
                        ajouterCase(jeu);
                        afficherJeu(jeu);
                        break;
                    case S:
                        jeu.deplacerBas();
                        ajouterCase(jeu);
                        afficherJeu(jeu);
                        break;
                    case D:
                        jeu.deplacerDroite();
                        ajouterCase(jeu);
                        afficherJeu(jeu);
                        break;
                    case Q:
                        jeu.deplacerGauche();
                        ajouterCase(jeu);
                        afficherJeu(jeu);
                        break;
                    case F:
                        jeu.deplacerArrierre();
                        ajouterCase(jeu);
                        afficherJeu(jeu);
                        break;
                    case R:
                        jeu.deplacerAvant();
                        ajouterCase(jeu);
                        afficherJeu(jeu);
                        break;
                }
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
}