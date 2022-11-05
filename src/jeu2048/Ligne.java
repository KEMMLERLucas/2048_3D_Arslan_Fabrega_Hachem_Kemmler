package jeu2048;

import java.util.List;

public class Ligne implements java.io.Serializable  {
    List<Tuile> listTuiles;

    Ligne(List<Tuile> listTuiles) {
        this.listTuiles = listTuiles;
    }

    public List<Tuile> getListTuiles() {
        return listTuiles;
    }

    public Tuile getTuile(int index) {
        return listTuiles.get(index);
    }
    public void afficherLigne(){
        System.out.println("---------");
        listTuiles.forEach(tuile -> {
           tuile.afficherTuile();
        });
        System.out.println();
    }
    public void afficherDerniereLigne(){
        System.out.println("---------");
        listTuiles.forEach(tuile -> {
            tuile.afficherTuile();
        });
        System.out.println();
        System.out.println("---------");
    }
}
