package jeu2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author lkemmler
 * The type Jeu, used to create a game
 */
public class Jeu implements java.io.Serializable {
    /**
     * The Liste observers
     */
    List<Observateur> listeObservateur;
    /**
     * The Grille list.
     */
    List<Grille> grilleList;
    /**
     * The boolean telling you if you can do a rollback on the game
     */
    private transient boolean peutRetournerArrierre;
    /**
     * The boolean used only when you are in a rollback state
     */

    private transient boolean estEnTrainDeRetournerEnArrierre;
    /**
     * An int used to increase/set the number of rollback that you can do
     */
    private int retourArrierre;
    /**
     * An int used to see when you are in a rollback state
     */
    private int increment;
    /**
     * The score of the game
     */
    private int score;
    /**
     * The max score "doable" in the game
     */
    private int scoreMax;
    /**
     * The number of grid, use 3 for the normal game, 2 and 4 aren't working
     */
    private int tailleGrille;
    /**
     * The previous list of game
     */
    private List<Jeu> jeuPrecedent;
    private Style style = new StyleClassique();

    /**
     * Instantiates a new Jeu.
     *
     * @param grilleList   The grid list in the game
     * @param score        The score, which is 0 at the beginning and goes up when you do a move and 2 tiles merge
     * @param tailleGrille The number of grid in the game
     */
    Jeu(List<Grille> grilleList, int score, int tailleGrille) {
        this.grilleList = grilleList;
        this.peutRetournerArrierre = true;
        this.estEnTrainDeRetournerEnArrierre = false;
        this.retourArrierre = 5;
        this.increment = 0;
        this.score = score;
        this.scoreMax = 2048;
        this.tailleGrille = tailleGrille;
        this.jeuPrecedent = new ArrayList<>();
    }

    /**
     * Instantiates a new Jeu.
     *
     * @param score        The score, which is 0 at the beginning and goes up when you do a move and 2 tiles merge
     * @param tailleGrille The number of grid in the game
     * @param TAILLEMAX    The max size of the game, used to create the randomness of the game
     */
    Jeu(int score, int tailleGrille, int TAILLEMAX) {
        this.grilleList = null;
        this.peutRetournerArrierre = true;
        this.estEnTrainDeRetournerEnArrierre = false;
        this.retourArrierre = 5;
        this.increment = 0;
        this.score = score;
        this.scoreMax = 2048;
        this.tailleGrille = tailleGrille;
        this.createRandomGame(TAILLEMAX);
        this.jeuPrecedent = new ArrayList<>();
    }

    /**
     * Instantiates a new Jeu.
     *
     * @param grilleList   The grid list
     * @param retourArr    Boolean telling you if you can do a rollback on the game
     * @param score        The score, which is 0 at the beginning and goes up when you do a move and 2 tiles merge
     * @param tailleGrille The number of grid in the game
     */
    Jeu(List<Grille> grilleList, Boolean retourArr, int score, int tailleGrille) {
        this.grilleList = grilleList;
        this.peutRetournerArrierre = retourArr;
        this.estEnTrainDeRetournerEnArrierre = false;
        this.retourArrierre = 5;
        this.increment = 0;
        this.score = score;
        this.scoreMax = 2048;
        this.tailleGrille = tailleGrille;
        this.jeuPrecedent = new ArrayList<>();
        this.jeuPrecedent.add(this);
    }

    /**
     * Instantiates a new Jeu.
     *
     * @param jeu The game you want to copy
     */
    Jeu(Jeu jeu){
        List<Grille> copyGrilles=new ArrayList<>();
        jeu.getGrilleList().forEach(grille ->
                copyGrilles.add(grille.copy()));
        this.grilleList=copyGrilles;
        this.retourArrierre=jeu.retourArrierre;
        this.listeObservateur=jeu.listeObservateur;
        this.estEnTrainDeRetournerEnArrierre = jeu.estEnTrainDeRetournerEnArrierre;
        this.score=jeu.score;
        this.scoreMax=jeu.scoreMax;
        this.tailleGrille=jeu.tailleGrille;
        this.jeuPrecedent = jeu.jeuPrecedent;
    }
    Jeu(){
        Jeu jeuRecup=this.chargerJeu();
        this.grilleList=jeuRecup.grilleList;
        this.retourArrierre=jeuRecup.retourArrierre;
        this.listeObservateur=jeuRecup.listeObservateur;
        this.estEnTrainDeRetournerEnArrierre = jeuRecup.estEnTrainDeRetournerEnArrierre;
        this.score=jeuRecup.score;
        this.scoreMax=jeuRecup.scoreMax;
        this.tailleGrille=jeuRecup.tailleGrille;
        this.jeuPrecedent = jeuRecup.jeuPrecedent;

    }

    /**
     * Get grille list.
     *
     * @return grilleList
     */
    public List<Grille> getGrille(){
        return grilleList;
    }


    /**
     * Move tile to the upper grid
     *
     * @return the boolean telling you if tiles moved
     */
    boolean deplacerAvant() {
        if(estEnTrainDeRetournerEnArrierre)peutRetournerArrierre=false;
        if(increment<retourArrierre) increment++;

        Jeu copy=this.copy();
        boolean merge = false;
        ///Décaler la grille 2 dans la 1, puis la grille 3 dans la 1
        Grille grille2 = grilleList.get(1);
        for (int i = 0; i < tailleGrille; i++) { ///On déplace de la grille 2 vers la grille 1
            Ligne ligne = grille2.getLigne(i);
            for (int j = 0; j < tailleGrille; j++) {
                Tuile tuile = ligne.getTuile(j);
                boolean changer = changerTuileGrille(tuile, grilleList.get(0), i, j);
                if (changer) {
                    merge=true;
                    grilleList.get(1).getLigne(i).getTuile(j).setValeur(0);
                    grilleList.get(1).getLigne(i).getTuile(j).setEstVide(true);
                }
            }
        }
        ///Maintenant, on essaie de déplacer la grille 3 vers la grille 1
        Grille grille3 = grilleList.get(2);
        for (int i = 0; i < tailleGrille; i++) { ///On déplace de la grille 3 vers la grille 1
            Ligne ligne = grille3.getLigne(i);
            for (int j = 0; j < tailleGrille; j++) {
                Tuile tuile = ligne.getTuile(j);
                boolean changer = changerTuileGrille(tuile, grilleList.get(0), i, j);
                if (changer) {
                    merge=true;
                    grilleList.get(2).getLigne(i).getTuile(j).setValeur(0);
                    grilleList.get(2).getLigne(i).getTuile(j).setEstVide(true);
                } else {
                    changer = changerTuileGrille(tuile, grilleList.get(1), i, j);
                    if (changer) {
                        merge=true;
                        grilleList.get(2).getLigne(i).getTuile(j).setValeur(0);
                        grilleList.get(2).getLigne(i).getTuile(j).setEstVide(true);
                    }
                }
            }
        }
        if(merge){
            if(increment==retourArrierre){
                jeuPrecedent.remove(0);
                jeuPrecedent.add(copy);
            }

        }
        return merge;
    }

    /**
     * Move tile to the lower grid
     *
     * @return the boolean telling you if tiles moved
     */
    boolean deplacerArrierre() {
        if(estEnTrainDeRetournerEnArrierre)peutRetournerArrierre=false;
        if(increment<retourArrierre) increment++;
        boolean merge = false;
        Jeu copy=this.copy();
        ///Décaler la grille 2 dans la 3, puis la grille 1 dans la 3
        Grille grille2 = grilleList.get(1);
        grille2.afficherGrille();
        for (int i = 0; i < tailleGrille; i++) { ///On déplace de la grille 2 vers la grille 3
            Ligne ligne = grille2.getLigne(i);
            ligne.afficherLigne();
            for (int j = 0; j < tailleGrille; j++) {
                Tuile tuile = ligne.getTuile(j);
                if (tuile.getEstVide() == false) {
                    boolean changer = changerTuileGrille(tuile, grilleList.get(2), i, j);
                    if (changer) {
                        merge=true;
                        grilleList.get(1).getLigne(i).getTuile(j).setValeur(0);
                        grilleList.get(1).getLigne(i).getTuile(j).setEstVide(true);
                    }
                }
            }
        }

        ///Maintenant, on essaie de déplacer la grille 1 vers la grille 2
        Grille grille1 = grilleList.get(0);
        for (int i = 0; i < tailleGrille; i++) { ///On déplace de la grille 1 vers la grille 3
            Ligne ligne = grille1.getLigne(i);
            for (int j = 0; j < tailleGrille; j++) {
                Tuile tuile = ligne.getTuile(j);
                boolean changer = changerTuileGrille(tuile, grilleList.get(2), i, j);
                if (changer) {
                    merge=true;
                    grilleList.get(0).getLigne(i).getTuile(j).setValeur(0);
                    grilleList.get(0).getLigne(i).getTuile(j).setEstVide(true);
                } else {
                    changer = changerTuileGrille(tuile, grilleList.get(1), i, j);
                    if (changer) {
                        merge=true;
                        grilleList.get(0).getLigne(i).getTuile(j).setValeur(0);
                        grilleList.get(0).getLigne(i).getTuile(j).setEstVide(true);
                    }
                }
            }
        }
        if(merge)jeuPrecedent.add(copy);
        return merge;
    }

    /**
     * Moving tile to the right
     *
     * @return the boolean telling you if tiles moved
     */
    boolean deplacerDroite() {
        if(estEnTrainDeRetournerEnArrierre)peutRetournerArrierre=false;
        if(increment<retourArrierre) increment++;
        Jeu copy=this.copy();
        Object[] tab;
        boolean merge = false;
        for (int i = 0; i < tailleGrille; i++) { //on choisis la grille
            Grille g = grilleList.get(i);
            for (int j = 0; j < tailleGrille; j++) { //on choisis la ligne
                Ligne ligne = g.getLigne(j);
                tab =ligne.deplacerDroite();
                score=score+(int)tab[1];
                if((boolean)tab[0]){
                    merge=true;
                }
            }
        }
        if (merge)jeuPrecedent.add(copy);
        return merge;
    }

    /**
     * Moving tile to the left
     *
     * @return the boolean telling you if tiles moved
     */
    boolean deplacerGauche() {
        if(estEnTrainDeRetournerEnArrierre)peutRetournerArrierre=false;
        if(increment<retourArrierre) increment++;
        Jeu copy=this.copy();
        Object[] tab;
        boolean merge = false;
        for (int i = 0; i < tailleGrille; i++) { //on choisis la grille
            Grille g = grilleList.get(i);
            for (int j = 0; j < tailleGrille; j++) { //on choisis la ligne
                Ligne ligne = g.getLigne(j);
                tab=ligne.deplacerGauche();
                score=score+(int)tab[1];
                if((boolean)tab[0]){
                    merge=true;
                }
            }
        }
        if(merge)jeuPrecedent.add(copy);
        return merge;
    }

    /**
     * Moving tile to the bottom
     *
     * @return the boolean telling you if tiles moved
     */
    boolean deplacerBas(){
        if(estEnTrainDeRetournerEnArrierre)peutRetournerArrierre=false;
        if(increment<retourArrierre) increment++;
        Jeu copy=this.copy();
        boolean merge=false;
        for (int i = 0; i < tailleGrille; i++) { //on choisis la grille
            Grille g = grilleList.get(i);
            for (int j = 0; j < tailleGrille; j++) { //on choisis la colonne
                List<Tuile> colonne=g.getColonne(j); //on choisis la colonne j
                boolean estFusionne=false;
                for(int k = tailleGrille-1; k >= 0; k--){ //pour chaque élément de la colonne
                    //On récupère la tuille à la place k de la colonne
                    for(int l=k; l<tailleGrille; l++){ //on commence à la position K
                        if(!colonne.get(l).getEstVide()&& l!=tailleGrille-1){
                            Tuile tuileActuelle=colonne.get(l); // on vérifie qu'on est pas à droite et que la tuile n'est pas vide
                            Tuile tuileDessous = colonne.get(l+1);
                            if(tuileDessous.getEstVide()){
                                tuileDessous.setValeur(tuileActuelle.getValeur());
                                tuileDessous.setEstVide(false);
                                tuileActuelle.etreVidee();
                                merge=true;
                            }else if(tuileActuelle.getValeur()==tuileDessous.getValeur() && !estFusionne){ /// Si les deux cases ont la même valeurs
                                score+=tuileActuelle.getValeur()*2;
                                tuileActuelle.etreVidee();
                                tuileDessous.increment();
                                estFusionne=true;
                                merge=true;
                            }
                        }else{
                            ///Nothing happens
                        }
                    }
                }
            }
        }
        if(merge)jeuPrecedent.add(copy);
        return merge;
    }

    /**
     * Moving tile to the top
     *
     * @return the boolean telling you if tiles moved
     */
    boolean deplacerHaut(){
        if(estEnTrainDeRetournerEnArrierre)peutRetournerArrierre=false;
        if(increment<retourArrierre) increment++;
        Jeu copy=this.copy();
        boolean merge=false;
        for (int i = 0; i < tailleGrille; i++) { //on choisis la grille
            Grille g = grilleList.get(i);
            for (int j = 0; j < tailleGrille; j++) { //on choisis la colonne
                List<Tuile> colonne=g.getColonne(j); //on choisis la colonne j
                boolean estFusionnee=false;
                for(int k = 0; k < tailleGrille; k++){ //pour chaque élément de la colonne
                    //On récupère la tuille à la place k de la colonne
                    for(int l=k; l>=0; l--){ //on commence à la position K
                        if(!colonne.get(l).getEstVide()&& l!=0){
                            Tuile tuileActuelle=colonne.get(l); // on vérifie qu'on est pas à droite et que la tuile n'est pas vide
                            Tuile tuileDessus = colonne.get(l-1);
                            if(tuileDessus.getEstVide()){
                                tuileDessus.setValeur(tuileActuelle.getValeur());
                                tuileDessus.setEstVide(false);
                                tuileActuelle.etreVidee();
                                merge=true;
                            }else if(tuileActuelle.getValeur()==tuileDessus.getValeur() && !estFusionnee){ /// Si les deux cases ont la même valeurs
                                score+=tuileActuelle.getValeur()*2;
                                tuileActuelle.etreVidee();
                                tuileDessus.increment();
                                estFusionnee=true;
                                merge=true;
                            }
                        }else{
                            ///Nothing happens
                        }
                    }
                }
            }
        }
        if(merge)jeuPrecedent.add(copy);
        return merge;
    }
    /**
     * Loading the game from a serialized file
     * @return the unserialized game
     */
    public Jeu chargerJeu(){
        DeserializerJeu dj = new DeserializerJeu();
        return dj.deserialize();
    }

    boolean changerTuileGrille(Tuile tuile, Grille grille, int posI, int posJ) {
        boolean changement = false;
        Ligne ligne = grille.getLigne(posI);
        if (ligne.getTuile(posJ).getValeur() == 0 && tuile.getEstVide() == false) {
            ligne.getTuile(posJ).setValeur(tuile.getValeur());
            ligne.getTuile(posJ).setEstVide(false);
            changement = true;
        } else if (ligne.getTuile(posJ).getValeur() == tuile.getValeur()) {
            score+=tuile.getValeur()*2;
            ligne.getTuile(posJ).setValeur(tuile.getValeur() * 2);
            changement = true;

        }
        return changement;

    }


    @Override
    public String toString() {
        return "Jeu{" +
                "grilleList=" + grilleList +
                ", peutRetournerArrierre=" + peutRetournerArrierre +
                ", retourArrierre=" + retourArrierre +
                ", score=" + score +
                ", scoreMax=" + scoreMax +
                ", tailleGrille=" + tailleGrille +
                '}';
    }

    public void afficherJeuConsole() {
        System.out.print("Score actuel :" + score + "       " + "Score max :" + scoreMax);
        System.out.println();
        grilleList.forEach(grille -> {
            grille.afficherGrille();
        });
    }

    void randomize() {
        grilleList.forEach(grille -> {
            grille.getToutesLesTuiles().forEach(tuile -> {
                tuile.randomize();
            });
        });
    }

    void enregisterObservateur(Observateur o) {

    }

    void suprimerObservateur(Observateur o) {

    }

    void notifierObservateur(Observateur o) {

    }
    public List<Tuile> getTuileVide(){
        List<Tuile> tuileVide = new ArrayList<>();
        grilleList.forEach(grille -> {
            grille.getToutesLesTuiles().forEach(tuile -> {
                if(tuile.getEstVide()){
                    tuileVide.add(tuile);
                }
            });
        });
        return tuileVide;
    }
    public List<Grille> getGrilleList() {
        return grilleList;
    }

    public void addRandomTile() {
        while (true) {
            AtomicBoolean randomized = new AtomicBoolean(false);
            int i = (int) Math.floor(Math.random() * 3);
            Grille grille = this.getGrilleList().get(i);
            List<Tuile> tuilesVides=grille.getTuilesVide();
            if(tuilesVides.isEmpty()) break;
            tuilesVides.forEach(tuile -> {
                if (!randomized.get()) {
                    randomized.set(tuile.randomize());
                }
            });
            if (randomized.get()) {
                break;
            }
        }
    }

    public  boolean arretJeu(){
        Jeu j1=this.copy();
        Jeu j2=this.copy();
        Jeu j3=this.copy();
        Jeu j4=this.copy();
        Jeu j5=this.copy();
        Jeu j6=this.copy();

        if(j1.deplacerBas()) return false;
        if(j2.deplacerHaut()) return false;
        if(j3.deplacerDroite()) return false;
        if(j4.deplacerGauche()) return false;
        if(j5.deplacerAvant()) return false;
        if(j6.deplacerArrierre()) return false;

        return true;
    }
    public boolean isPeutRetournerArrierre() {
        return peutRetournerArrierre;
    }

    public int getRetourArrierre() {
        return retourArrierre;
    }

    public int getTailleGrille() {
        return tailleGrille;
    }

    public int getScore() {
        return score;
    }
    public void remplacerJeu(List<Grille> grilleList,int score){
        this.grilleList=grilleList;
        this.score=score;
        this.afficherJeuConsole();
    }
    public  void remplacerJeuEntier(List<Grille> grilleList,int score, int increment,int scoreMax, int tailleGrille, boolean peutRetournerArrierre,boolean estEnTrainDeRetournerEnArrierre) {
        this.grilleList=grilleList;
        this.score=score;
        this.increment=increment;
        this.scoreMax=scoreMax;
        this.tailleGrille=tailleGrille;
        this.peutRetournerArrierre=peutRetournerArrierre;
        this.estEnTrainDeRetournerEnArrierre= estEnTrainDeRetournerEnArrierre;


    }
    public boolean retourArriere(){
        boolean retour=false;
        if(!jeuPrecedent.isEmpty() && peutRetournerArrierre && increment>0){
            retour=true;
            Jeu jeu=jeuPrecedent.get(this.increment-1);
            jeuPrecedent.remove(this.increment-1);
            this.remplacerJeuEntier(jeu.getGrilleList(),jeu.getScore(),increment-1,jeu.getScoreMax(),jeu.getTailleGrille(), this.retourArrierre != 4,true);
        }
        return retour;
    }

    private int getScoreMax() {
        return scoreMax;
    }

    public void createRandomGame(int TAILLEMAX){
        int nbTuileDebutMax = 2;
        int nbTuile = TAILLEMAX * TAILLEMAX * TAILLEMAX;
        int nbLigne = TAILLEMAX * TAILLEMAX;
        int nbTuileDebut = 0;
        List<Tuile> listTuiles = new ArrayList<Tuile>();
        for (int i = 0; i < nbTuile; i++) {
            Tuile t = new Tuile(0, true);
            if (nbTuileDebut != nbTuileDebutMax) {
                t.randomizeStart();
                if (!t.getEstVide()) {
                    nbTuileDebut++;
                }
            }
            if (i == nbTuile-2 && nbTuileDebut == 0) {
                t.setValeur(2);
                t.setEstVide(false);
            }
            if (i == nbTuile-1 && nbTuileDebut != nbTuileDebutMax) {
                t.setValeur(2);
                t.setEstVide(false);
            }
            listTuiles.add(t);
        }


        List<Ligne> listLignes = new ArrayList<Ligne>();
        for (int i = 0; i < nbLigne; i++) {
            List<Tuile> listTuile = new ArrayList<Tuile>();
            Ligne ligne = new Ligne(listTuile,TAILLEMAX);
            if (!listTuiles.isEmpty()) {
                for (int j = 0; j < TAILLEMAX; j++) {
                    ligne.getListTuiles().add(listTuiles.get(0));
                    listTuiles.remove(0);
                }
                listLignes.add(ligne);
            }
        }

        List<Grille> listGrilles = new ArrayList<Grille>();
        for (int i = 0; i < TAILLEMAX; i++) {
            List<Ligne> listLigne = new ArrayList<Ligne>();
            Grille g = new Grille(listLigne, TAILLEMAX);

            if (!listLignes.isEmpty()) {
                for (int j = 0; j < TAILLEMAX; j++) {
                    g.getListLignes().add(listLignes.get(0));
                    listLignes.remove(0);
                }
                listGrilles.add(g);
            }
        }
        this.grilleList=listGrilles;
    }
    public Jeu copy(){
        return new Jeu(this);
    }

    /**
     * Methode pour changer le style du jeu
     * @param s
     */
    public void setStyle(Style s){
        style=s;
    }

    /**
     * methode pour recuperer le style
     * @return
     */
    public Style getStyle(){
        return style;
    }

    /**
     * methode qui permet de savoir si jeu contient un 2048
     * @return true si 2048
     */
    public boolean contient2048(){
        boolean a2048 = false;
        for (Grille g:grilleList){
            for(Ligne l:g.getListLignes()){
                for (Tuile t : l.getListTuiles()){
                    if(t.getValeur()==2048){
                        a2048 = true;
                    }
                }
            }
        }
        return a2048;
    }

    /**
     * permet de savoir si le jeu est perdu
     * @return true si perdu
     */
    public boolean perdu(){
        if(arretJeu() && !contient2048()){
            return true;
        }
        return false;
    }

    public void coupAleatoire(){
        boolean coupJouer = false;
        while(!coupJouer){
            Random random = new Random();
            int nb;
            nb = random.nextInt(6);
            switch (nb){
                case 0:
                    deplacerBas();
                    break;
                case 1:
                    deplacerHaut();
                    break;
                case 2:
                    deplacerDroite();
                    break;
                case 3:
                    deplacerGauche();
                    break;
                case 4:
                    deplacerAvant();
                    break;
                case 5:
                    deplacerArrierre();
                    break;
            }
        }
    }
}
