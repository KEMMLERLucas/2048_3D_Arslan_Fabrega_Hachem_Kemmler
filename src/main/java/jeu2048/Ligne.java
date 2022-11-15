package jeu2048;

import java.util.List;

public class Ligne implements java.io.Serializable  {
    List<Tuile> listTuiles;
    int tailleMaxLigne;

    Ligne(List<Tuile> listTuiles) {
        this.listTuiles = listTuiles;
        this.tailleMaxLigne = listTuiles.size();
    }

    public List<Tuile> getListTuiles() {
        return listTuiles;
    }

    public Tuile getTuile(int index) {
        return listTuiles.get(index);
    }
    public void afficherLigne(){
        System.out.println("------------------");
        listTuiles.forEach(tuile -> {
            tuile.afficherTuile();
        });
        System.out.println();
    }
    public void afficherDerniereLigne(){
        System.out.println("------------------");
        listTuiles.forEach(tuile -> {
            tuile.afficherTuile();
        });
        System.out.println();
        System.out.println("------------------");
    }
    public void deplacerGauche() {
        for (int i = 0; i < tailleMaxLigne; i++) {
            if (i == 0 && listTuiles.get(i).getEstVide()) {
                ///Nothing happens
            }
            for (int j = i; j >= 0; j--) {
                if (j != 0 && !listTuiles.get(j).getEstVide()) {
                    Tuile caseGauche = listTuiles.get(j - 1);
                    Tuile caseActuelle = listTuiles.get(j);
                    if (caseGauche.getEstVide()) { //Si c'est vide, on la décale vers la gauche
                        caseGauche.setValeur(caseActuelle.getValeur());
                        caseGauche.setEstVide(false);
                        caseActuelle.setValeur(0);
                        caseActuelle.setEstVide(true);
                    } else if (caseActuelle.getValeur() == caseGauche.getValeur()) { //Si celle a gauche a la même valeur que l'actuelle, on décale l'actuelle a gauche et on incrémente
                        caseGauche.increment();
                        caseActuelle.etreVidee();
                    } else { //Si c'est pas vide, et qu'elle n'a pas la même valeur, on fait rien
                        break;
                    }

                }
            }
        }
    }
    public void deplacerDroite(){
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
                    } else if (caseActuelle.getValeur() == caseDroite.getValeur()) { //Si celle a gauche a la même valeur que l'actuelle, on décale l'actuelle a gauche et on incrémente
                        caseDroite.increment();
                        caseActuelle.etreVidee();
                    } else { //Si c'est pas vide, et qu'elle n'a pas la même valeur, on fait rien
                        break;
                    }

                }
            }
        }
    }
}
