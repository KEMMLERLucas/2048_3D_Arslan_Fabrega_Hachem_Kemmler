package jeu2048;

import java.io.Serializable;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author lkemmler
 * The main class of the console version of the game.
 */
public class MainConsole {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws CloneNotSupportedException the clone not supported exception
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        boolean debugMode = true;

        boolean aFaitRetour=false;



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
        Jeu j1 = new Jeu(lg, 0, 3);
        List<Jeu> enCours = new ArrayList<>();
        Jeu copyJ1=j1.copy();

        enCours.add(copyJ1);
        SerializerJeu sj = new SerializerJeu(copyJ1);
        sj.serialize();

        System.out.println("Bienvenue dans le 2048 3D");
        Scanner input = new Scanner(System.in);
        AtomicBoolean playing = new AtomicBoolean(true);

        if (!debugMode) {
            boolean changementGrille = false;
            j1.afficherJeuConsole();
            System.out.print("Pour se déplacer, utilisez les touches:" +
                    " Z pour monter, S pour descendre, Q pour aller à gauche et D pour aller à droite, F pour l'étage supérieur et R pour l'étage inférieur. \nPour faire un mouvement aléatoire, appuyez sur P, et faire un retour arrière, L. \n Pour quitter, appuyez sur C");
            while (playing.get()) {
                // takes input from the keyboard
                String direction = input.nextLine().toUpperCase();

                switch (direction) {
                    case "Z":
                        System.out.println("Vous avez choisi de monter");
                        changementGrille=j1.deplacerHaut();
                        break;
                    case "S":
                        System.out.println("Vous avez choisi de descendre");
                        changementGrille=j1.deplacerBas();
                        break;
                    case "Q":
                        System.out.println("Vous avez choisi d'aller à gauche");
                        changementGrille=j1.deplacerGauche();
                        break;
                    case "D":
                        System.out.println("Vous avez choisi d'aller à droite");
                        changementGrille=j1.deplacerDroite();
                        break;
                    case "F":
                        System.out.println("Vous avez choisi d'aller à l'étage supérieur");
                        changementGrille=j1.deplacerAvant();
                        break;
                    case "R":
                        System.out.println("Vous avez choisi d'aller à l'étage inférieur");
                        changementGrille=j1.deplacerArrierre();
                        break;
                    case "L":
                        aFaitRetour=j1.retourArriere();
                        if(!aFaitRetour)
                            System.out.println("Vous ne pouvez pas faire de retour arrière");
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
                sj.setJeu(j1);
                sj.serialize();
                if(changementGrille) {
                    int i = (int) Math.floor(Math.random() * 2);
                    if (i == 2) {
                        j1.addRandomTile();
                        j1.addRandomTile();
                    }else{
                        j1.addRandomTile();
                    }
                }




                //j1.afficherJeuConsole();
                j1.getGrilleList().forEach(grille -> {
                    grille.getToutesLesTuiles().forEach(tuile -> {
                        if (tuile.getValeur() == 2048) {
                            System.out.println("Vous avez gagné");
                            playing.set(false);
                        }else if(j1.arretJeu()){
                            System.out.println("Vous avez perdu, aucun autre mouvement n'est possible");
                            playing.set(false);
                        }
                    });
                });
            }

        } else {
            j1.afficherJeuConsole();
            j1.deplacerBas();
            j1.afficherJeuConsole();
            SerializerJeu sj2 = new SerializerJeu(j1);
            sj2.serialize();
            Jeu test=new Jeu();
            test.afficherJeuConsole();
        }
    }
}

