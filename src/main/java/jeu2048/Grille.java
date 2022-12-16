package jeu2048;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lkemmler

 * The type Grille, used to create a grid of a 2048 game
 */
public class Grille implements java.io.Serializable {
    /**
     * The List of row that's contained in the grid. The number of row depend of the size of the grid (tailleMaxGrille .
     */
    List<Ligne> listLignes;
    /**
     * The Taille max grille.
     */
    int tailleMaxGrille;

    /**
     * Instantiates a new Grille.
     *
     * @param listLignes      the list of lignes
     * @param TailleMaxGrille the taille max grille
     */
    public Grille(List<Ligne> listLignes, int TailleMaxGrille) {
        this.listLignes = listLignes;
        this.tailleMaxGrille = TailleMaxGrille;
    }

    /**
     * Instantiates a new Grille.
     *
     * @param grille the grille
     */
    Grille(Grille grille){
        List<Ligne> copyGrille=new ArrayList<>();
        grille.getListLignes().forEach(ligne -> {
            copyGrille.add(ligne.copy());
        });
        this.listLignes=copyGrille;
        this.tailleMaxGrille=grille.tailleMaxGrille;

    }

    /**
     * Gets ligne.
     *
     * @param index the index
     * @return the ligne
     */
    public Ligne getLigne(int index) {
        return listLignes.get(index);
    }

    /**
     * Gets colonne.
     *
     * @param indexColonne the index colonne
     * @return A list of the values ordered as a column
     */
    public List<Tuile> getColonne(int indexColonne) {
        List<Tuile> listTuile = new ArrayList<Tuile>();
        for (int i = 0; i < tailleMaxGrille; i++) {
            listTuile.add(listLignes.get(i).getTuile(indexColonne));
        }
        return listTuile;
    }

    /**
     * Gets all the tile of the grid.
     *
     * @return Every tile of the grid
     */
    public List<Tuile> getToutesLesTuiles() {
        List<Tuile> listTuiles = new ArrayList<Tuile>();
        for (Ligne ligne : listLignes) {
            listTuiles.addAll(ligne.getListTuiles());
        }
        return listTuiles;
    }

    /**
     * Used to show the grid in the console.
     */
    public void afficherGrille() {
        for (int i = 0; i < tailleMaxGrille; i++) {
            if (i == tailleMaxGrille - 1) {
                listLignes.get(i).afficherDerniereLigne();
            } else {
                listLignes.get(i).afficherLigne();
            }
        }
    }

    /**
     * Get the list of empty tile.
     *
     * @return the list of empty tile contained in this specific grid
     */
    public List<Tuile> getTuilesVide(){
        List<Tuile> listTuilesVide = new ArrayList<Tuile>();
        for (Ligne ligne : listLignes) {
            for (Tuile tuile : ligne.getListTuiles()) {
                if (tuile.getEstVide()) {
                    listTuilesVide.add(tuile);
                }
            }
        }
        return listTuilesVide;
    }

    /**
     * Gets list of row present in the grid.
     *
     * @return the listLignes
     */
    public List<Ligne> getListLignes() {
        return listLignes;
    }

    /**
     * Copy grid.
     *
     * @return the grid that has been copied
     */
    public Grille copy(){
        return new Grille(this);
    }
}
