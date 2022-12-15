package jeu2048;

import java.io.*;
import java.util.List;

public class DeserializerJeu {
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public Jeu deserialize() {
        Jeu jeu = null;
        try {
            final FileInputStream fichier = new FileInputStream("grilles.ser");
            ois = new ObjectInputStream(fichier);
            jeu = (Jeu) ois.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return jeu;
    }
}
