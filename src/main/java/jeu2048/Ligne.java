package jeu2048;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lkemmler
 * The type Ligne, used to create a row.
 */
public class Ligne implements java.io.Serializable  {
    /**
     * The List of tiles in the row
     */
    List<Tuile> listTuiles;
    /**
     * The maximum size of the row
     */
    int tailleMaxLigne;

    /**
     * Instantiates a new Ligne.
     *
     * @param listTuiles the tile list in the row
     */
    Ligne(List<Tuile> listTuiles) {
        this.listTuiles = listTuiles;
        this.tailleMaxLigne = listTuiles.size();
    }

    /**
     * Instantiates a new Ligne.
     *
     * @param listTuiles the tile list in the row
     */
    Ligne(List<Tuile> listTuiles, int tailleMaxLigne) {
        this.listTuiles = listTuiles;
        this.tailleMaxLigne = tailleMaxLigne;
    }
    /**
     * Instantiates a new Ligne.
     *
     * @param ligne the row to copy
     */
    Ligne(Ligne ligne){
        List<Tuile> copyLigne=new ArrayList<>();
        ligne.getListTuiles().forEach(tuile ->
                copyLigne.add(tuile.copy())  );
        this.listTuiles=copyLigne;
        this.tailleMaxLigne=ligne.tailleMaxLigne;
    }


    /**
     * Gets list tuiles.
     *
     * @return the list of tiles in the row
     */
    public List<Tuile> getListTuiles() {
        return listTuiles;
    }

    /**
     * Gets tuile.
     *
     * @param index the index of the tile to get
     * @return the tile at the index
     */
    public Tuile getTuile(int index) {
        return listTuiles.get(index);
    }

    /**
     * Afficher ligne.
     */
    public void afficherLigne(){
        System.out.println("------------------");
        listTuiles.forEach(tuile -> {
            tuile.afficherTuile();
        });
        System.out.println();
    }

    /**
     * Afficher derniere ligne.
     */
    public void afficherDerniereLigne(){
        System.out.println("------------------");
        listTuiles.forEach(tuile -> {
            tuile.afficherTuile();
        });
        System.out.println();
        System.out.println("------------------");
    }

    /**
     * Moving tile to the left
     *
     * @return An array containing the score to add, and the boolean if the row has changed
     */
    public Object[] deplacerGauche() {
        boolean merge = false;
        Object[] tabToReturn = new Object[2];
        int scoreToAdd=0;
        boolean estFusionne = false;
        for (int i = 0; i < tailleMaxLigne; i++) {
            if (i == 0 && listTuiles.get(i).getEstVide()) {
                ///Nothing happens
            }
            for (int j = i; j >= 0; j--) {
                if (j != 0 && !listTuiles.get(j).getEstVide()) {
                    Tuile caseGauche = listTuiles.get(j - 1);
                    Tuile caseActuelle = listTuiles.get(j);
                    System.out.println(caseGauche.getEstVide());
                    if (caseGauche.getEstVide()) { //Si c'est vide, on la décale vers la gauche
                        caseGauche.setValeur(caseActuelle.getValeur());
                        caseGauche.setEstVide(false);
                        caseActuelle.setValeur(0);
                        caseActuelle.setEstVide(true);
                        merge=true;
                        System.out.println(merge + " déplacement");
                    } else if (caseActuelle.getValeur() == caseGauche.getValeur() && !estFusionne) { //Si celle a gauche a la même valeur que l'actuelle, on décale l'actuelle a gauche et on incrémente
                        scoreToAdd+=caseGauche.getValeur()*2;
                        System.out.println(merge + " fusion");
                        merge=true;
                        caseGauche.increment();
                        caseActuelle.etreVidee();
                        estFusionne = true;
                    } else { //Si c'est pas vide, et qu'elle n'a pas la même valeur, on fait rien
                        break;
                    }
                }
            }
        }
        tabToReturn[0]=merge;
        tabToReturn[1]=scoreToAdd;
        return tabToReturn;
    }

    /**
     * Moving tile to the right
     *
     * @return an array containing the score to add, and the boolean if the row has changed
     */
    public Object[] deplacerDroite(){
        boolean merge = false;
        Object[] tabToReturn = new Object[2];
        int scoreToAdd=0;

        boolean estFusionnee=false;
        for (int i = tailleMaxLigne-1; i >= 0; i--) {
            if (i == tailleMaxLigne-1 && listTuiles.get(i).getEstVide()) {
                ///Nothing happens
            }
            for (int j = i; j < tailleMaxLigne; j++) {
                if (j != tailleMaxLigne-1 && !listTuiles.get(j).getEstVide()) {
                    Tuile caseDroite = listTuiles.get(j + 1);
                    Tuile caseActuelle = listTuiles.get(j);
                    if (caseDroite.getEstVide()) { //Si c'est vide, on la décale vers la gauche
                        caseDroite.setValeur(caseActuelle.getValeur());
                        caseDroite.setEstVide(false);
                        caseActuelle.setValeur(0);
                        caseActuelle.setEstVide(true);
                        merge=true;
                    } else if (caseActuelle.getValeur() == caseDroite.getValeur() && !estFusionnee) {//Si celle a gauche a la même valeur que l'actuelle, on décale l'actuelle a gauche et on incrémente
                        scoreToAdd+=caseDroite.getValeur()*2;
                        merge=true;
                        caseDroite.increment();
                        caseActuelle.etreVidee();
                        estFusionnee=true;
                    } else { //Si c'est pas vide, et qu'elle n'a pas la même valeur, on fait rien
                        break;
                    }

                }
            }
        }
        tabToReturn[0]=merge;
        tabToReturn[1]=scoreToAdd;
        return tabToReturn;
    }

    /**
     * Copy ligne.
     *
     * @return the ligne
     */
    public Ligne copy(){
        return new Ligne(this);
    }
}
