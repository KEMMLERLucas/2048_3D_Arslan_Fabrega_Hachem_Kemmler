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
    void deplacerCasesLignes(Ligne l){

    }
    void deplacerCasesColonnes(List<Tuile> listTuiles){

    }
    void recupererHaut(){
        ///TODO Récupère la liste de ligne dont le déplacement va être vers le haut
    }
    void recupererBas(){
        ///TODO Récupère la liste de ligne dont le déplacement va être vers le bas
    }
    void recupererGauche(){
        ///TODO Récupère la liste de ligne dont le déplacement va être vers la gauche
    }
    void recupererDroite(){
        ///TODO Récupère la liste de ligne dont le déplacement va être vers la droite
    }
    void deplacerAvant(){
        ///TODO Déplace les tuiles vers l'avant
    }
    void deplacerArrierre(){
        ///TODO Déplace les tuiles vers l'arrière
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
