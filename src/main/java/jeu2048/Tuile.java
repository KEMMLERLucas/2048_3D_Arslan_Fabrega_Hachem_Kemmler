package jeu2048;

import java.util.Random;

public class Tuile implements java.io.Serializable {
    int valeur;
    boolean estVide;

    public Tuile(int valeur, boolean estVide) {
        this.valeur = valeur;
        this.estVide = estVide;
    }

    void etreVidee() {
        this.estVide = true;
        this.valeur = 0;
    }

    void increment() {
        this.valeur = this.valeur * 2;
    }

    void fusionner(Tuile t) {
        this.valeur = this.valeur + t.valeur;
        this.estVide = false;
        t.etreVidee();
    }

    void setValeur(int valeur) {
        this.valeur = valeur;
    }

    void setEstVide(boolean estVide) {
        this.estVide = estVide;
    }

    int getValeur() {
        return this.valeur;
    }

    boolean getEstVide() {
        return this.estVide;
    }

    boolean randomize() {
        boolean randomized = false;
        if (!this.estVide || this.valeur != 0) return false;
        int i = 1 + (int) Math.floor(Math.random() * 5);
        if (i != 1) {
            this.valeur = 0;
            this.estVide = true;
        } else {
            if ((int) Math.floor(Math.random() * 2) == 1) {
                this.valeur = 4;
            } else {
                this.valeur = 2;
            }
            this.estVide = false;
            randomized = true;
        }
        return randomized;
    }

    void randomizeStart() {
        int min = 1;
        int max = 10;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        if (value == 1) {
            this.valeur = 0;
            this.estVide = true;
        } else {
            this.valeur = 2;
            this.estVide = false;
        }
    }

    void afficherTuile() {
        if (this.estVide) {
            System.out.print("[    ]");
        } else {
            if (this.valeur < 10) {
                System.out.print("[  " + this.valeur + " ]");
            } else if (this.valeur < 100) {
                System.out.print("[ " + this.valeur + " ]");
            } else if (this.valeur < 1000) {
                System.out.print("[ " + this.valeur + "]");
            } else if (this.valeur < 10000) {
                System.out.print("[" + this.valeur + "]");
            }
        }
    }
}
