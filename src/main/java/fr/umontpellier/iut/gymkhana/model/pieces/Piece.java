package fr.umontpellier.iut.gymkhana.model.pieces;

import fr.umontpellier.iut.gymkhana.model.Couleur;

public abstract class Piece {
    private Couleur couleur;

    public Piece(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }
}

