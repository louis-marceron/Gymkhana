package fr.umontpellier.iut.gymkhana.model.joueurs;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.Plateau;

import java.util.ArrayList;

public class JoueurIADebutant extends Joueur {
    public JoueurIADebutant(Plateau plateau, Couleur couleur) {
        super(plateau, couleur);
    }

    public void jouer() {
        int[] s1, s2;
        ArrayList<int[]> sommetJouables, sommetVoisinsP;
        boolean b;
        do {
            sommetJouables = getPlateau().getSommetJouables(getCouleur());
            int a = (int) (Math.random() * (sommetJouables.size()));
            if (a == sommetJouables.size()) a -= 1;
            s1 = sommetJouables.get(a);
            sommetVoisinsP = getPlateau().getVoisinsSommetPossible(s1, getCouleur());
            a = (int) (Math.random() * (sommetVoisinsP.size()));
            if (a == sommetVoisinsP.size()) a -= 1;
            s2 = sommetVoisinsP.get(a);
            b = getPlateau().ajouterArete(s1, s2, getCouleur());
        } while (!b);
    }
}