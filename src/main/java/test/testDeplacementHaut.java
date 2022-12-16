package test;

import jeu2048.Grille;
import jeu2048.Jeu;
import jeu2048.Ligne;
import jeu2048.Tuile;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

class testDeplacementHaut {

    Tuile t1 = new Tuile(0, true);
    Tuile t2 = new Tuile(0, true);
    Tuile t3 = new Tuile(4, false);
    Ligne l1 = new Ligne(List.of(t1, t2, t3));

    Tuile t4 = new Tuile(0, true);
    Tuile t5 = new Tuile(4, false);
    Tuile t6 = new Tuile(0, true);
    Ligne l2 = new Ligne(List.of(t4, t5, t6));

    Tuile t7 = new Tuile(4, false);
    Tuile t8 = new Tuile(0, true);
    Tuile t9 = new Tuile(0, true);
    Ligne l3 = new Ligne(List.of(t7, t8, t9));

    Tuile t10 = new Tuile(0, true);
    Tuile t11 = new Tuile(2, true);
    Tuile t12 = new Tuile(2, true);
    Ligne l4 = new Ligne(List.of(t10, t11, t12));

    Tuile t13 = new Tuile(2, true);
    Tuile t14 = new Tuile(2, true);
    Tuile t15 = new Tuile(2, true);
    Ligne l5 = new Ligne(List.of(t13, t14, t15));

    Tuile t16 = new Tuile(2, true);
    Tuile t17 = new Tuile(0, true);
    Tuile t18 = new Tuile(2, true);
    Ligne l6 = new Ligne(List.of(t16, t17, t18));

    Tuile t19 = new Tuile(0, true);
    Tuile t20 = new Tuile(0, true);
    Tuile t21 = new Tuile(0, true);
    Tuile t22 = new Tuile(0, true);
    Tuile t23 = new Tuile(0, true);
    Tuile t24 = new Tuile(0, true);
    Tuile t25 = new Tuile(0, true);
    Tuile t26 = new Tuile(0, true);
    Tuile t27 = new Tuile(0, true);

    Ligne l7 = new Ligne(List.of(t19, t20, t21));
    Ligne l8 = new Ligne(List.of(t22, t23, t24));
    Ligne l9 = new Ligne(List.of(t25, t26, t27));

    Grille g1 = new Grille(List.of(l1, l2, l3), 3);
    Grille g2 = new Grille(List.of(l4, l5, l6), 3);
    Grille g3 = new Grille(List.of(l7, l8, l9), 3);
    List<Grille> lg = List.of(g1, g2, g3);
    Jeu j1 = new Jeu(lg, 0, 3);
    /**
     * test X -> 4
     * test X -> X
     * test 4 -> X
     */
    @Test
   void testDeplacementUneCaseDeDistance2() {
        j1.deplacerHaut();
        assertEquals(t1.getValeur(), 4);
    }
    /**
     * test X -> 4
     * test 4 -> X
     * test X -> X
     */
    @Test
     void testDeplacementUneCaseDeDistance1() {
        assertEquals(t2.getValeur(), 4);
    }
    /**
     * test 4 -> 4
     * test X -> X
     * test X -> X
     */
    @Test
    void testDeplacementUneCaseDeDistance0() {
        assertEquals(t4.getValeur(), 4);
    }

    /**
     * test X -> 4
     * test 2 -> X
     * test 2 -> X
     */
    @Test
    void testFusion1() {
        assertEquals(t10.getValeur(), 4);
    }
    /**
     * test 2 -> 4
     * test 2 -> X
     * test X -> X
     */
    @Test
    public void testFusion2() {
        assertEquals(t11.getValeur(), 4);
    }
    /**
     * test 2 -> 4
     * test 2 -> 2
     * test 2 -> X
     */
    @Test
    void testFusion3() {
        assertEquals(t12.getValeur()+t15.getValeur(), 6);
    }
}
