package fr.umontpellier.iut.gymkhana.viewmodel.plateau;

import fr.umontpellier.iut.gymkhana.model.Plateau;
import fr.umontpellier.iut.gymkhana.model.joueurs.Joueur;
import fr.umontpellier.iut.gymkhana.model.joueurs.JoueurHumain;
import fr.umontpellier.iut.gymkhana.model.Partie;

public class PlateauViewModel {

    private Partie partie;
    private Joueur joueurCourant;
    private int nombreColonnes;

    private int x;
    private int y;

    public PlateauViewModel(Partie partie) {
        this.partie = partie;
        this.joueurCourant = partie.getJoueurCourant();
        nombreColonnes = partie.getPlateau().getTaille() * 2 + 1;

        // Lancer automatiquement le jeu si le premier joueur n'est humain
        if (joueurCourant.getClass() != JoueurHumain.class) {
            jouer();
        }
    }

    public void jouer() {
        if (!partie.estTerminee()) {
            if (joueurCourant.getClass() == JoueurHumain.class) {
                ((JoueurHumain) joueurCourant).setX(x);
                ((JoueurHumain) joueurCourant).setY(y);
            }
            partie.jouerTour();

            this.joueurCourant = partie.getJoueurCourant();

            // Lancer automatiquement le prochain tour si le prochain joueur n'est pas humain
            if (joueurCourant.getClass() != JoueurHumain.class) {
                jouer();
            }
        }
    }

    public int getNombreColonnes() {
        return nombreColonnes;
    }

    public Plateau getPlateau() {
        return partie.getPlateau();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Partie getPartie() {
        return partie;
    }
}
