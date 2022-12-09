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

    Grille(Grille grille){
        List<Ligne> copyGrille=new ArrayList<>();
        grille.getListLignes().forEach(ligne -> {
            copyGrille.add(ligne.copy());
        });
        this.listLignes=copyGrille;
        this.tailleMaxGrille=grille.tailleMaxGrille;

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
    public List<Ligne> getListLignes() {
        return listLignes;
    }
    public Grille copy(){
        return new Grille(this);
    }
}
