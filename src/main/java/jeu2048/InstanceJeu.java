package jeu2048;

/**
 * Classe permetant de récuprer la meme instance de jeu pour le controleur
 */
public class InstanceJeu {
    private Jeu jeu;
    private static InstanceJeu intjeu;

    private InstanceJeu(){
    }

    public static InstanceJeu getInstance(){
        if(intjeu == null){
            intjeu = new InstanceJeu();
        }
        return intjeu;
    }
    public void setJeu(Jeu j){
        jeu = j;
    }
    public Jeu getJeu(){
        return jeu;
    }

}
