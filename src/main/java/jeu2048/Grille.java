package jeu2048;

import java.util.ArrayList;
import java.util.List;

public class Grille implements java.io.Serializable {
    List<Ligne> listLignes;
    int tailleMaxGrille;

    Grille(List<Ligne> listLignes, int TailleMaxGrille) {
        this.listLignes = listLignes;
        this.tailleMaxGrille = TailleMaxGrille;
    }

    public Ligne getLigne(int index) {
        return listLignes.get(index);
    }

    public List<Tuile> getColonne(int indexColonne) {
        List<Tuile> listTuile = new ArrayList<Tuile>();
        for (int i = 0; i < tailleMaxGrille; i++) {
            listTuile.add(listLignes.get(i).getTuile(indexColonne));
        }
        return listTuile;
    }

    public List<Tuile> getToutesLesTuiles() {
        List<Tuile> listTuiles = new ArrayList<Tuile>();
        for (Ligne ligne : listLignes) {
            listTuiles.addAll(ligne.getListTuiles());
        }
        return listTuiles;
    }
    public void afficherGrille() {
        for (int i = 0; i < tailleMaxGrille; i++) {
            if (i == tailleMaxGrille - 1) {
                listLignes.get(i).afficherDerniereLigne();
            } else {
                listLignes.get(i).afficherLigne();
            }
        }
    }
}