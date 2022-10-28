package jeu2048;

import java.util.ArrayList;
import java.util.List;

public class Grille {
    List<Ligne> listLignes;
    int TailleMaxGrille;
    Grille(List<Ligne> listLignes,int TailleMaxGrille) {
        this.listLignes = listLignes;
        this.TailleMaxGrille = TailleMaxGrille;
    }
    public Ligne getLigne(int index) {
        return listLignes.get(index);
    }
    public List<Tuile> getColonne(int indexColonne){
        List<Tuile> listTuile=new ArrayList<Tuile>();
        for (int i = 0; i < TailleMaxGrille; i++) {
            listTuile.add(listLignes.get(i).getTuile(indexColonne));
        }
        return  listTuile;
    }
    public List<Tuile>getToutesLesTuiles() {
        List<Tuile> listTuiles = new ArrayList<Tuile>();
        for (Ligne ligne : listLignes) {
            listTuiles.addAll(ligne.getListTuiles());
        }
        return listTuiles;
    }
}
