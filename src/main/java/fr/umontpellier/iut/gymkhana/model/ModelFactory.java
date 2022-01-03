package fr.umontpellier.iut.gymkhana.model;

public class ModelFactory {

    public Partie getPartie() {
        return new Partie();
    }
}

// TODO egalité de fin de partie
// TODO Recommencer la partie (durant la partie et à la fin)