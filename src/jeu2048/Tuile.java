package jeu2048;

public class Tuile {
    int valeur;
    boolean estVide;

    public Tuile(int valeur, boolean estVide) {
        this.valeur = valeur;
        this.estVide = estVide;
    }
    void etreVidee(){
        this.estVide = true;
        this.valeur=0;
    }
    void increment(){
        this.valeur = this.valeur*2;
    }
    void fusionner(Tuile t){
        this.valeur = this.valeur + t.valeur;
        this.estVide = false;
        t.etreVidee();
    }
    void setValeur(int valeur){
        this.valeur = valeur;
    }
    void setEstVide(boolean estVide){
        this.estVide = estVide;
    }
    int getValeur(){
        return this.valeur;
    }
    boolean getEstVide(){
        return this.estVide;
    }
}
