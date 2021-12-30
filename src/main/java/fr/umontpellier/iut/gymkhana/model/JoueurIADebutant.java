package fr.umontpellier.iut.gymkhana.model;

import java.util.ArrayList;

public class JoueurIADebutant implements Joueur{
    @Override
    public boolean jouer(Plateau plateau, Couleur couleur) {
        int[] s1, s2;
        ArrayList<int[]> sommetJouables, sommetVoisinsP;
        boolean b;
        do {
            sommetJouables = plateau.getSommetJouables(couleur);
            int a = (int) (Math.random() * (sommetJouables.size()));
            if (a == sommetJouables.size()) a -= 1;
            System.out.println("i :" + a + " sur " + sommetJouables.size());
            s1 = sommetJouables.get(a);
            System.out.println("c'est bon");
            sommetVoisinsP = plateau.getVoisinsSommetPossible(s1, couleur);
            a = (int) (Math.random() * (sommetVoisinsP.size()));
            if (a == sommetVoisinsP.size()) a -= 1;
            System.out.println("i :" + a + " sur " + sommetVoisinsP.size());
            s2 = sommetVoisinsP.get(a);
            System.out.println("c'est bon");
            b = plateau.ajouterArete(s1, s2, couleur);
        }while (!b);
        return plateau.gagnant(s1, couleur);
    }
}
