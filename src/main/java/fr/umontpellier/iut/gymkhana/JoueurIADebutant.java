package fr.umontpellier.iut.gymkhana;

import java.util.ArrayList;
import java.util.Scanner;

public class JoueurIADebutant implements Joueur{
    @Override
    public boolean jouer(Plateau plateau, Couleur couleur) {
        int[] s1, s2;
        ArrayList<int[]> sommetJouables, sommetVoisinsP;
        boolean b;
        do {
            sommetJouables = plateau.getSommetJouables(couleur);
            s1 = sommetJouables.get((int) (Math.random() * (sommetJouables.size())));
            sommetVoisinsP = plateau.getVoisinsSommetPossible(s1, couleur);
            s2 = sommetVoisinsP.get((int) (Math.random() * (sommetVoisinsP.size())));
            b = plateau.ajouterArete(s1, s2, couleur);
        }while (!b);
        return plateau.gagnant(s1, couleur);
    }
}
