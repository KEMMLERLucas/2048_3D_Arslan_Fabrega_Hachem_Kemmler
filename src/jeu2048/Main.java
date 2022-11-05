package jeu2048;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tuile t1 = new Tuile(0,true);
        Tuile t2 = new Tuile(0,true);
        Tuile t3 = new Tuile(0,true);
        Tuile t4 = new Tuile(2,false);
        Tuile t5 = new Tuile(0,true);
        Tuile t6 = new Tuile(0,true);
        Tuile t7 = new Tuile(0,true);
        Tuile t8 = new Tuile(0,true);
        Tuile t9 = new Tuile(2,false);
        Tuile t10 = new Tuile(0,true);
        Tuile t11 = new Tuile(0,true);
        Tuile t12 = new Tuile(0,true);
        Tuile t13 = new Tuile(0,true);
        Tuile t14 = new Tuile(0,true);
        Tuile t15 = new Tuile(0,true);
        Tuile t16 = new Tuile(0,true);
        Tuile t17 = new Tuile(0,true);
        Tuile t18 = new Tuile(0,true);
        Tuile t19 = new Tuile(0,true);
        Tuile t20 = new Tuile(0,true);
        Tuile t21 = new Tuile(0,true);
        Tuile t22 = new Tuile(0,true);
        Tuile t23 = new Tuile(0,true);
        Tuile t24 = new Tuile(0,true);
        Tuile t25 = new Tuile(0,true);
        Tuile t26 = new Tuile(0,true);
        Tuile t27 = new Tuile(0,true);
        Ligne l1 = new Ligne(List.of(t1,t2,t3));
        Ligne l2 = new Ligne(List.of(t4,t5,t6));
        Ligne l3 = new Ligne(List.of(t7,t8,t9));
        Ligne l4 = new Ligne(List.of(t10,t11,t12));
        Ligne l5 = new Ligne(List.of(t13,t14,t15));
        Ligne l6 = new Ligne(List.of(t16,t17,t18));
        Ligne l7 = new Ligne(List.of(t19,t20,t21));
        Ligne l8 = new Ligne(List.of(t22,t23,t24));
        Ligne l9 = new Ligne(List.of(t25,t26,t27));

        Grille g1 = new Grille(List.of(l1,l2,l3),3);
        Grille g2 = new Grille(List.of(l4,l5,l6),3);
        Grille g3 = new Grille(List.of(l7,l8,l9),3);
        List<Grille> lg = List.of(g1,g2,g3);
        System.out.println("Grille 1");
        g1.afficherGrille();
        System.out.println();
        System.out.println("Grille 2");
        g2.afficherGrille();
        System.out.println();
        System.out.println("Grille 3");
        g3.afficherGrille();
        Jeu j1 = new Jeu(lg,0,3);
        j1.afficherJeuConsole();
        /*j2.randomize();
        j3.randomize();
        j4.randomize();
        j5.randomize();*/
        List<Jeu> enCours = List.of(j1);
        SerializerJeu sj = new SerializerJeu(enCours);
        sj.serialize();
        DeserializerJeu dj = new DeserializerJeu();
        List<Jeu> lj = dj.deserialize();
        /// A voir si il ne faut pas tout faire dans la même classe (serialisation et deserilisation)
        lj.get(0).afficherJeuConsole();
    }
}

