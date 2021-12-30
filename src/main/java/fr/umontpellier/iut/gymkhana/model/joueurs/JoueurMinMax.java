package fr.umontpellier.iut.gymkhana.model.joueurs;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.Plateau;

public class JoueurMinMax extends Joueur {
    public JoueurMinMax(Plateau plateau, Couleur couleur) {
        super(plateau, couleur);
    }

    public void jouer(Plateau plateau, Couleur couleur) {
    }

    public void minmax(Plateau plateau ,int profondeur, Joueur joueur){
//        if (profondeur == 0 || )
    }
}
