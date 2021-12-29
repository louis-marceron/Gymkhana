package fr.umontpellier.iut.gymkhana.model;

public abstract class Piece {
    private Couleur couleur;

    public Piece(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }
}

