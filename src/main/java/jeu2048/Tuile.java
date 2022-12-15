package jeu2048;

import java.util.Random;

/**
 * @author lkemmler
 * The type Tuile, creating a tile of a game.
 */
public class Tuile implements java.io.Serializable {
    private int valeur;
    private boolean estVide;

    /**
     * Instantiates a new Tuile.
     *
     * @param valeur  the value of the tile
     * @param estVide the emptiness of the tile
     */
    public Tuile(int valeur, boolean estVide) {
        this.valeur = valeur;
        this.estVide = estVide;
    }

    /**
     * Instantiates a new Tuile.
     *
     * @param tuile the tile to copy
     */
    public Tuile(Tuile tuile){
        this.valeur=tuile.valeur;
        this.estVide=tuile.estVide;
    }

    /**
     * Empty the tile
     */
    void etreVidee() {
        this.estVide = true;
        this.valeur = 0;
    }

    /**
     * Increment the tile.
     */
    void increment() {
        this.valeur = this.valeur * 2;
    }

    /**
     * Merge two tiles.
     *
     * @param t the tile to merge
     */
    void fusionner(Tuile t) {
        this.valeur = this.valeur + t.valeur;
        this.estVide = false;
        t.etreVidee();
    }

    /**
     * Sets valeur.
     *
     * @param valeur the valeur
     */
    void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Sets est vide.
     *
     * @param estVide the est vide
     */
    void setEstVide(boolean estVide) {
        this.estVide = estVide;
    }

    /**
     * Gets valeur.
     *
     * @return the valeur
     */
    public int getValeur() {
        return this.valeur;
    }

    /**
     * Gets est vide.
     *
     * @return the est vide
     */
    boolean getEstVide() {
        return this.estVide;
    }

    /**
     * Randomize the value of the tile
     *
     * @return the boolean telling you if the tile is randomized
     */
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

    /**
     * Randomize the tile at the start of the game
     */
    void randomizeStart() {
        int min = 1;
        int max = 10;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        if (value == 1) {
            this.valeur = 2;
            this.estVide = false;
        } else {
            this.valeur = 0;
            this.estVide = true;
        }
    }

    /**
     * Afficher tuile.
     */
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

    /**
     * Copy tuile.
     *
     * @return the copy of the tile
     */
    public Tuile copy(){
        return new Tuile(this);
    }
}
