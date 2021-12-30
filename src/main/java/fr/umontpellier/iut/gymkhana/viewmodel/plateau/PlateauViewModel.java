package fr.umontpellier.iut.gymkhana.viewmodel.plateau;

import fr.umontpellier.iut.gymkhana.model.Plateau;
import fr.umontpellier.iut.gymkhana.model.joueurs.Joueur;
import fr.umontpellier.iut.gymkhana.model.joueurs.JoueurHumain;
import fr.umontpellier.iut.gymkhana.model.Partie;

public class PlateauViewModel {

    private Partie partie;
    private Joueur joueurCourant;
    private int nombreColonnes;

    // TODO binding avec les sommets à faire
    private int[] sommet1;
    private int[] sommet2;

    public PlateauViewModel(Partie partie) {
        this.partie = partie;
        this.joueurCourant = partie.getJoueurCourant();
        nombreColonnes = partie.getPlateau().getTaille() * 2 + 1;
    }

    public void jouer() {
        if (joueurCourant.getClass() == JoueurHumain.class) {
            ((JoueurHumain) joueurCourant).setSommet1(sommet1);
            ((JoueurHumain) joueurCourant).setSommet2(sommet2);
        }
        partie.jouerTour();
    }

    public int getNombreColonnes() {
        return nombreColonnes;
    }

    public Plateau getPlateau() {
        return partie.getPlateau();
    }
    //TODO savoir si la partie est fini

}
