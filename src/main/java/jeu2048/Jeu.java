package jeu2048;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Jeu implements java.io.Serializable {
    List<Observateur> listeObservateur;
    List<Grille> grilleList;
    transient boolean peutRetournerArrierre;
    int retourArrierre;
    int score;
    int scoreMax;
    int tailleGrille;

    Jeu(List<Grille> grilleList, int score, int tailleGrille) {
        this.grilleList = grilleList;
        this.peutRetournerArrierre = true;
        this.retourArrierre = 5;
        this.score = score;
        this.scoreMax = 2048;
        this.tailleGrille = tailleGrille;
    }


    void recupererHaut() {
        ///TODO Récupère la liste de ligne dont le déplacement va être vers le haut
    }

    void recupererBas() {
        ///TODO Récupère la liste de ligne dont le déplacement va être vers le bas
    }

    void recupererGauche() {
        ///TODO Récupère la liste de ligne dont le déplacement va être vers la gauche
    }

    void recupererDroite() {
        ///TODO Récupère la liste de ligne dont le déplacement va être vers la droite
    }

    void deplacerAvant() {
        ///Décaler la grille 2 dans la 1, puis la grille 3 dans la 1
        Grille grille2 = grilleList.get(1);
        for (int i = 0; i < tailleGrille; i++) { ///On déplace de la grille 2 vers la grille 1
            Ligne ligne = grille2.getLigne(i);
            for (int j = 0; j < tailleGrille; j++) {
                Tuile tuile = ligne.getTuile(j);
                boolean changer = changerTuileGrille(tuile, grilleList.get(0), i, j);
                if (changer) {
                    grilleList.get(1).getLigne(i).getTuile(j).setValeur(0);
                    grilleList.get(1).getLigne(i).getTuile(j).setEstVide(true);
                }
            }
        }
        ///Maintenant, on essaie de déplacer la grille 3 vers la grille 1
        Grille grille3 = grilleList.get(2);
        for (int i = 0; i < tailleGrille; i++) { ///On déplace de la grille 3 vers la grille 1
            Ligne ligne = grille3.getLigne(i);
            for (int j = 0; j < tailleGrille; j++) {
                Tuile tuile = ligne.getTuile(j);
                boolean changer = changerTuileGrille(tuile, grilleList.get(0), i, j);
                if (changer) {
                    grilleList.get(2).getLigne(i).getTuile(j).setValeur(0);
                    grilleList.get(2).getLigne(i).getTuile(j).setEstVide(true);
                } else {
                    changer = changerTuileGrille(tuile, grilleList.get(1), i, j);
                    if (changer) {
                        grilleList.get(2).getLigne(i).getTuile(j).setValeur(0);
                        grilleList.get(2).getLigne(i).getTuile(j).setEstVide(true);
                    }
                }
            }
        }
    }

    void deplacerArrierre() {
        ///Décaler la grille 2 dans la 3, puis la grille 1 dans la 3
        Grille grille2 = grilleList.get(1);
        grille2.afficherGrille();
        for (int i = 0; i < tailleGrille; i++) { ///On déplace de la grille 2 vers la grille 3
            Ligne ligne = grille2.getLigne(i);
            ligne.afficherLigne();
            for (int j = 0; j < tailleGrille; j++) {
                Tuile tuile = ligne.getTuile(j);
                if (tuile.getEstVide() == false) {
                    boolean changer = changerTuileGrille(tuile, grilleList.get(2), i, j);
                    if (changer) {
                        grilleList.get(1).getLigne(i).getTuile(j).setValeur(0);
                        grilleList.get(1).getLigne(i).getTuile(j).setEstVide(true);
                    }
                }
            }
        }

        ///Maintenant, on essaie de déplacer la grille 1 vers la grille 2
        Grille grille1 = grilleList.get(0);
        for (int i = 0; i < tailleGrille; i++) { ///On déplace de la grille 1 vers la grille 3
            Ligne ligne = grille1.getLigne(i);
            for (int j = 0; j < tailleGrille; j++) {
                Tuile tuile = ligne.getTuile(j);
                boolean changer = changerTuileGrille(tuile, grilleList.get(2), i, j);
                if (changer) {
                    grilleList.get(0).getLigne(i).getTuile(j).setValeur(0);
                    grilleList.get(0).getLigne(i).getTuile(j).setEstVide(true);
                } else {
                    changer = changerTuileGrille(tuile, grilleList.get(1), i, j);
                    if (changer) {
                        grilleList.get(0).getLigne(i).getTuile(j).setValeur(0);
                        grilleList.get(0).getLigne(i).getTuile(j).setEstVide(true);
                    }
                }
            }
        }
    }
    void deplacerDroite() {
        for (int i = 0; i < tailleGrille; i++) { //on choisis la grille
            Grille g = grilleList.get(i);
            for (int j = 0; j < tailleGrille; j++) { //on choisis la ligne
                Ligne ligne = g.getLigne(j);
                ligne.deplacerDroite();
            }
        }
    }
    void deplacerGauche() {
        for (int i = 0; i < tailleGrille; i++) { //on choisis la grille
            Grille g = grilleList.get(i);
            for (int j = 0; j < tailleGrille; j++) { //on choisis la ligne
                Ligne ligne = g.getLigne(j);
                ligne.deplacerGauche();
            }
        }
    }

    void deplacerBas(){
        for (int i = 0; i < tailleGrille; i++) { //on choisis la grille
            Grille g = grilleList.get(i);
            for (int j = 0; j < tailleGrille; j++) { //on choisis la colonne
                List<Tuile> colonne=g.getColonne(j); //on choisis la colonne j
                boolean estFusionne=false;
                for(int k = tailleGrille-1; k >= 0; k--){ //pour chaque élément de la colonne
                    //On récupère la tuille à la place k de la colonne
                    for(int l=k; l<tailleGrille; l++){ //on commence à la position K
                        if(!colonne.get(l).getEstVide()&& l!=tailleGrille-1){
                            Tuile tuileActuelle=colonne.get(l); // on vérifie qu'on est pas à droite et que la tuile n'est pas vide
                            Tuile tuileDessous = colonne.get(l+1);
                            if(tuileDessous.getEstVide()){
                                tuileDessous.setValeur(tuileActuelle.getValeur());
                                tuileDessous.setEstVide(false);
                                tuileActuelle.etreVidee();
                            }else if(tuileActuelle.getValeur()==tuileDessous.getValeur() && !estFusionne){ /// Si les deux cases ont la même valeurs
                                tuileActuelle.etreVidee();
                                tuileDessous.increment();
                                estFusionne=true;
                            }
                        }else{
                            ///Nothing happens
                        }
                    }
                }
            }
        }
    }
    void deplacerHaut(){
for (int i = 0; i < tailleGrille; i++) { //on choisis la grille
            Grille g = grilleList.get(i);
            for (int j = 0; j < tailleGrille; j++) { //on choisis la colonne
                List<Tuile> colonne=g.getColonne(j); //on choisis la colonne j
                boolean estFusionnee=false;
                for(int k = 0; k < tailleGrille; k++){ //pour chaque élément de la colonne
                    //On récupère la tuille à la place k de la colonne
                    for(int l=k; l>=0; l--){ //on commence à la position K
                        if(!colonne.get(l).getEstVide()&& l!=0){
                            Tuile tuileActuelle=colonne.get(l); // on vérifie qu'on est pas à droite et que la tuile n'est pas vide
                            Tuile tuileDessus = colonne.get(l-1);
                            if(tuileDessus.getEstVide()){
                                tuileDessus.setValeur(tuileActuelle.getValeur());
                                tuileDessus.setEstVide(false);
                                tuileActuelle.etreVidee();
                            }else if(tuileActuelle.getValeur()==tuileDessus.getValeur() && !estFusionnee){ /// Si les deux cases ont la même valeurs
                                tuileActuelle.etreVidee();
                                tuileDessus.increment();
                                estFusionnee=true;
                            }
                        }else{
                            ///Nothing happens
                        }
                    }
                }
            }
        }
    }

    boolean changerTuileGrille(Tuile tuile, Grille grille, int posI, int posJ) {
        boolean changement = false;
        Ligne ligne = grille.getLigne(posI);
        if (ligne.getTuile(posJ).getValeur() == 0 && tuile.getEstVide() == false) {
            ligne.getTuile(posJ).setValeur(tuile.getValeur());
            ligne.getTuile(posJ).setEstVide(false);
            changement = true;
        } else if (ligne.getTuile(posJ).getValeur() == tuile.getValeur()) {
            ligne.getTuile(posJ).setValeur(tuile.getValeur() * 2);
            changement = true;

        }
        return changement;

    }

    void retourArrierreTout() {
        if (peutRetournerArrierre) {
            ///TODO Gérer le retour arrière avec la sauvegarde de grille (HTML / JSON)
            retourArrierre--;
            if (retourArrierre == 0) {
                peutRetournerArrierre = false;
            }
        }
    }

    void retourArrierre(int nbRetour) {
        if (peutRetournerArrierre) {
            ///TODO Gérer le retour arrière avec la sauvegarde de grille (HTML / JSON)
            if (retourArrierre == 0) {
                peutRetournerArrierre = false;
            }
        }
    }

    @Override
    public String toString() {
        return "Jeu{" +
                "grilleList=" + grilleList +
                ", peutRetournerArrierre=" + peutRetournerArrierre +
                ", retourArrierre=" + retourArrierre +
                ", score=" + score +
                ", scoreMax=" + scoreMax +
                ", tailleGrille=" + tailleGrille +
                '}';
    }

    public void afficherJeuConsole() {
        System.out.print("Score actuel :" + score + "       " + "Score max :" + scoreMax);
        System.out.println();
        grilleList.forEach(grille -> {
            grille.afficherGrille();
        });
    }

    void randomize() {
        grilleList.forEach(grille -> {
            grille.getToutesLesTuiles().forEach(tuile -> {
                tuile.randomize();
            });
        });
    }

    void enregisterObservateur(Observateur o) {

    }

    void suprimerObservateur(Observateur o) {

    }

    void notifierObservateur(Observateur o) {

    }
    public List<Tuile> getTuileVide(){
        List<Tuile> tuileVide = new ArrayList<>();
        grilleList.forEach(grille -> {
            grille.getToutesLesTuiles().forEach(tuile -> {
                if(tuile.getEstVide()){
                    tuileVide.add(tuile);
                }
            });
        });
        return tuileVide;
    }
    public List<Grille> getGrilleList() {
        return grilleList;
    }

    public void addRandomTile() {
        while (true) {
            AtomicBoolean randomized = new AtomicBoolean(false);
            int i = (int) Math.floor(Math.random() * 3);
            Grille grille = this.getGrilleList().get(i);
            List<Tuile> tuilesVides=grille.getTuilesVide();
            if(tuilesVides.isEmpty()) break;
            tuilesVides.forEach(tuile -> {
                if (!randomized.get()) {
                    randomized.set(tuile.randomize());
                }
            });
            if (randomized.get()) {
                break;
            }
        }
    }
}
