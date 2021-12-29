package fr.umontpellier.iut.gymkhana.model;

import fr.umontpellier.iut.gymkhana.model.Partie;

public class ModelFactory {

    private Partie partie;

    public Partie getPartie() {
        if (partie == null)
            partie = new Partie();
        return partie;
    }
}
