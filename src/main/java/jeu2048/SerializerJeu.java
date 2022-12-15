package jeu2048;

import java.io.*;
import java.util.List;

public class SerializerJeu {
    private Jeu jeu;
    ObjectOutputStream out = null;

    SerializerJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public void serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream("grilles.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(jeu);
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

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Jeu getJeu() {
        return jeu;
    }
}
