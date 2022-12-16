package jeu2048;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

class DeplacerDroite {
    Tuile t1 = new Tuile(4, false);
    Tuile t2 = new Tuile(0, true);
    Tuile t3 = new Tuile(0, true);
    Ligne l1 = new Ligne(List.of(t1, t2, t3));

    Tuile t4 = new Tuile(0, true);
    Tuile t5 = new Tuile(4, false);
    Tuile t6 = new Tuile(0, true);
    Ligne l2 = new Ligne(List.of(t4, t5, t6));

    Tuile t7 = new Tuile(0, true);
    Tuile t8 = new Tuile(0, true);
    Tuile t9 = new Tuile(4, false);
    Ligne l3 = new Ligne(List.of(t7, t8, t9));

    Tuile t10 = new Tuile(2, false);
    Tuile t11 = new Tuile(2, false);
    Tuile t12 = new Tuile(0, true);
    Ligne l4 = new Ligne(List.of(t10, t11, t12));

    Tuile t13 = new Tuile(0, true);
    Tuile t14 = new Tuile(2, false);
    Tuile t15 = new Tuile(2, false);
    Ligne l5 = new Ligne(List.of(t13, t14, t15));

    Tuile t16 = new Tuile(2, false);
    Tuile t17 = new Tuile(2, false);
    Tuile t18 = new Tuile(2, false);
    Ligne l6 = new Ligne(List.of(t16, t17, t18));

    Tuile t19 = new Tuile(0, true);
    Tuile t20 = new Tuile(0, true);
    Tuile t21 = new Tuile(0, true);
    Ligne l7 = new Ligne(List.of(t19, t20, t21));

    Tuile t22 = new Tuile(0, true);
    Tuile t23 = new Tuile(0, true);
    Tuile t24 = new Tuile(0, true);
    Ligne l8 = new Ligne(List.of(t22, t23, t24));

    Tuile t25 = new Tuile(0, true);
    Tuile t26 = new Tuile(0, true);
    Tuile t27 = new Tuile(0, true);
    Ligne l9 = new Ligne(List.of(t25, t26, t27));

    Grille g1 = new Grille(List.of(l1, l2, l3), 3);
    Grille g2 = new Grille(List.of(l4, l5, l6), 3);
    Grille g3 = new Grille(List.of(l7, l8, l9), 3);
    List<Grille> lg = List.of(g1, g2, g3);
    Jeu j1 = new Jeu(lg, 0, 3);
    /**
     * test 4 X X  | 2 2 X  | X X X
     * test X 4 X  | X 2 2  | X X X
     * test X X 4  | 2 2 2  | X X X
     * but
     * test X X 4  | X X 4  | X X X
     * test X X 4  | X X 4  | X X X
     * test X X 4  | X 2 4  | X X X
     */
    @Test
    void testDeplacement() {
        j1.deplacerDroite();
        assertEquals("------------------[    ][    ][  4 ]------------------[    ][    ][  4 ]------------------[    ][    ][  4 ]------------------------------------[    ][    ][  4 ]------------------[    ][    ][  4 ]------------------[    ][  2 ][  4 ]------------------------------------[    ][    ][    ]------------------[    ][    ][    ]------------------[    ][    ][    ]------------------", j1.getStringGrille());
    }
}
