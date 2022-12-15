package jeu2048;

import java.io.*;
import java.util.List;


/**
 * @author lkemmler
 * The type Deserializer jeu. Used to deserialize a game from a file.
 */
public class DeserializerJeu {
    /**
     * The Oos.
     */
    ObjectOutputStream oos;
    /**
     * The Ois.
     */
    ObjectInputStream ois;

    /**
     * Deserialize Jeu. Deserializes a game from a file.
     *
     * @return Jeu, the game that has been deserialized.
     */
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
