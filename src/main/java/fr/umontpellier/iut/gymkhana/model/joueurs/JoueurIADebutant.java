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
            s1 = sommetJouables.get((int) (Math.random() * (sommetJouables.size())));
            sommetVoisinsP = getPlateau().getVoisinsSommetPossible(s1, getCouleur());
            s2 = sommetVoisinsP.get((int) (Math.random() * (sommetVoisinsP.size())));
            b = getPlateau().ajouterArete(s1, s2, getCouleur());
        } while (!b);
    }
}
