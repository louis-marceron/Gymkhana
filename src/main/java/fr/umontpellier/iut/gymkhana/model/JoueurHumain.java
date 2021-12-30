package fr.umontpellier.iut.gymkhana.model;

import java.util.ArrayList;
import java.util.Scanner;

public class JoueurHumain implements Joueur {
    @Override
    public boolean jouer(Plateau plateau, Couleur couleur) {
        int[] s1 = new int[2],s2;
        int x1, y1, x2, y2;
        boolean b = true, valide = false;
        do {
            Scanner entree = new Scanner(System.in);
            ArrayList<int[]> list= plateau.areteJouable(couleur);
//            System.out.println("Les " + list.size() + " aretes jouables sont :");
//            for (int[] s: list)
//                System.out.println("{" + s[0] +"," + s[1] + "}");

            if (valide) System.out.println("Points non valides, veuillez réessayer");
            System.out.println("sélectionnez Y et X du premier point");
            x1 = entree.nextInt();
            if (x1 == 12) continue;
            y1 = entree.nextInt();
            s1 = new int[]{x1, y1};
            System.out.println("sélectionnez Y et X du deuxième point");
            x2 = entree.nextInt();
            y2 = entree.nextInt();
            b = plateau.ajouterArete(s1, new int[]{x2, y2}, couleur);
            valide = true;
        }while (!b);
        return plateau.gagnant(s1, couleur);
    }
}
