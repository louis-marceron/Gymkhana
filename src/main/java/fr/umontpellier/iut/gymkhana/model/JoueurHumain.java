package fr.umontpellier.iut.gymkhana.model;

import java.util.Scanner;

public class JoueurHumain implements Joueur {
    @Override
    public boolean jouer(Plateau plateau, Couleur couleur) {
        int[] s1,s2;
        int x1, y1, x2, y2;
        boolean b = true;
        do {
            Scanner entree = new Scanner(System.in);
            System.out.println("sélectionnez Y et X du premier point");
            x1 = entree.nextInt();
            y1 = entree.nextInt();
            s1 = new int[]{x1, y1};
            System.out.println("sélectionnez Y et X du deuxième point");
            x2 = entree.nextInt();
            y2 = entree.nextInt();
            b = plateau.ajouterArete(s1, new int[]{x2, y2}, couleur);
        }while (!b);
        return plateau.gagnant(s1, couleur);
    }
}
