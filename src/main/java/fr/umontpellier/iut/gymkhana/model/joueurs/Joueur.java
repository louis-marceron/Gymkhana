package fr.umontpellier.iut.gymkhana.model.joueurs;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.Plateau;

public abstract class Joueur {
    private final Plateau plateau;

    private final Couleur couleur;

    public Joueur(Plateau plateau, Couleur couleur) {
        this.plateau = plateau;
        this.couleur = couleur;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void jouer() {}
}
