package jeu2048;

import java.util.List;

public class Jeu {
    List<Grille> grilleList;
    boolean peutRetournerArrierre;
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

    void retourArrierreTout(){
        if (peutRetournerArrierre){
            ///TODO Gérer le retour arrière avec la sauvegarde de grille (HTML / JSON)
            retourArrierre--;
            if (retourArrierre == 0){
                peutRetournerArrierre = false;
            }
        }
    }
    void retourArrierre(int nbRetour){
        if (peutRetournerArrierre){
            ///TODO Gérer le retour arrière avec la sauvegarde de grille (HTML / JSON)
            if (retourArrierre == 0){
                peutRetournerArrierre = false;
            }
        }
    }
}
