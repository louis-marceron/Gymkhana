package fr.umontpellier.iut.gymkhana.model;

import fr.umontpellier.iut.gymkhana.model.joueurs.Joueur;

/**
 * La classe {@code Partie} initialise un {@code Plateau} et fait avancer une partie de Gymkhana
 * en prenant et en traitant les valeurs entrées par les joueurs.
 */
public class Partie {
    private Plateau plateau;
    private Joueur j1;
    private Joueur j2;
    private Joueur joueurCourant;
    private boolean partieTerminee;


    public Partie() {
        this.plateau = new Plateau(5);
        partieTerminee = false;
        joueurCourant = j1;
    }

    public void jouerTour() {
        jouerJoueurCourant();
        if (estGagnant(joueurCourant)) {
            System.out.println(joueurCourant.getClass() + " a gagné");
            partieTerminee = true;
        } else
            changerJoueurCourant();
    }

    public void changerJoueurCourant() {
        if (joueurCourant == j1)
            joueurCourant = j2;
        else
            joueurCourant = j1;
    }

    public boolean estGagnant(Joueur j) {

        return true;
    }

    public void jouerJoueurCourant() {
        joueurCourant.jouer();
    }

    public void setJ1(Joueur j1) {
        this.j1 = j1;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }
}
