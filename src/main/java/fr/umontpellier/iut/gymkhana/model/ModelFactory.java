package fr.umontpellier.iut.gymkhana.model;

public class ModelFactory {

    public Partie getPartie() {
        return new Partie();
    }
}

//TODO patrons de singleton pétés
// Vue de fin de partie
// Recommencer la partie (durant la partie et à la fin)