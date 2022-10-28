package jeu2048;

import java.util.List;

public class Ligne {
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
}
