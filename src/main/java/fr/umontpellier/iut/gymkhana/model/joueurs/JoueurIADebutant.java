package fr.umontpellier.iut.gymkhana.model.joueurs;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.Plateau;

import java.util.ArrayList;

public class JoueurIADebutant extends Joueur {
    public JoueurIADebutant(Plateau plateau, Couleur couleur) {
        super(plateau, couleur);
    }

    public void jouer() {
        ArrayList<int[]> sommetJouables;
        sommetJouables = getPlateau().getSommetJouables(getCouleur());
        int[] s = sommetJouables.get((int) (Math.random() * (sommetJouables.size())));

        getPlateau().ajouterAreteShlag(s[0], s[1], getCouleur());
    }
}