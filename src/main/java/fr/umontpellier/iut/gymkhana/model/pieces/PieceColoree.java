package fr.umontpellier.iut.gymkhana.model.pieces;

import fr.umontpellier.iut.gymkhana.model.Couleur;

public abstract class PieceColoree implements Piece {
    private Couleur couleur;

    public PieceColoree(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }
}