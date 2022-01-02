package fr.umontpellier.iut.gymkhana.model.joueurs;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.Plateau;

public class JoueurHumain extends Joueur {
    private int x;
    private int y;

    public JoueurHumain(Plateau plateau, Couleur couleur) {
        super(plateau, couleur);
    }

        public void jouer() { // TODO try/catch si sommets pas initialisés
        getPlateau().ajouterAreteShlag(x, y, getCouleur());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
