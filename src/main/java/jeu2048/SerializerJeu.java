package jeu2048;

import java.io.*;
import java.util.List;

public class SerializerJeu {
    List<Jeu> jeux;
    ObjectOutputStream out = null;

    SerializerJeu(List<Jeu> jeu) {
        this.jeux = jeu;
    }

    public void serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream("grilles.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(jeux);
            out.flush();
        } catch (IOException i) {
            i.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void addJeu(Jeu jeu) {
        if(jeux.size()<5){
            jeux.add(jeu);
        }else{
            jeux.remove(0);
            jeux.add(jeu);
        }
    }
    public void setJeu(List<Jeu> jeux) {
        this.jeux = jeux;
    }

    public List<Jeu> getJeu() {
        return jeux;
    }
}
