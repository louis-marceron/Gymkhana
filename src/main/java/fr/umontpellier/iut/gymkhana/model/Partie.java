package fr.umontpellier.iut.gymkhana.model;

import fr.umontpellier.iut.gymkhana.model.joueurs.Joueur;

/**
 * La classe {@code Partie} initialise un {@code Plateau} et fait avancer une partie de Gymkhana
 * en prenant et en traitant les valeurs entrées par les joueurs.
 */
public class Partie {
    private final Plateau plateau;
    private Joueur j1;
    private Joueur j2;
    private Joueur joueurCourant;
    private boolean partieTerminee;


    public Partie() {
        this.plateau = new Plateau(5);
        partieTerminee = false;
    }

    public void jouerTour() {
        joueurCourant.jouer();
        if (plateau.estGagnant(joueurCourant.getCouleur())) {
            System.out.println("Gagné !");
            partieTerminee = true;
        } else
            changerJoueurCourant();
    }

    private void changerJoueurCourant() {
        if (joueurCourant == j1)
            joueurCourant = j2;
        else
            joueurCourant = j1;
    }

    public void setJ1(Joueur j1) {
        if (joueurCourant == null) // Garantit que le joueur courant ne soit jamais null
            joueurCourant = j1;
        this.j1 = j1;
    }

    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public boolean estTerminee() {
        return partieTerminee;
    }
}