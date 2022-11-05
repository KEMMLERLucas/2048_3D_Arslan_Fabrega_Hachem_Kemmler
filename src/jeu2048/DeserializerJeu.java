package jeu2048;

import java.io.*;
import java.util.List;

public class DeserializerJeu {
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public List<Jeu> deserialize() {
        List<Jeu> listJeu = null;
        try {
            final FileInputStream fichier = new FileInputStream("grilles.ser");
            ois = new ObjectInputStream(fichier);
            listJeu = (List<Jeu>) ois.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return listJeu;
    }
}
