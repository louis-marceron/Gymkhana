package fr.umontpellier.iut.gymkhana.old;

public class Partie {

    private Joueur joueurA;
    private Joueur joueurB;

    private Plateau plateau;

    public Partie(Joueur joueurA, Joueur joueurB, Plateau plateau) {
        this.joueurA = joueurA;
        this.joueurB = joueurB;
        this.plateau = plateau;
    }

}
