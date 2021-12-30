package fr.umontpellier.iut.gymkhana.model.joueurs;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.Plateau;

public class JoueurHumain extends Joueur {
    private int[] sommet1;
    private int[] sommet2;

    public JoueurHumain(Plateau plateau, Couleur couleur) {
        super(plateau, couleur);
    }

    public void jouer() { // TODO try/catch si sommets pas initialisés
        getPlateau().ajouterArete(sommet1, sommet2, getCouleur());
    }

    public void setSommet1(int[] sommet1) {
        this.sommet1 = sommet1;
    }

    public void setSommet2(int[] sommet2) {
        this.sommet2 = sommet2;
    }
}
