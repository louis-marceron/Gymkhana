package fr.umontpellier.iut.gymkhana.model;

public class ModelFactory {

    private Partie partie;

    // Singleton pour avoir une partie commune entre chaque vue
    public Partie getPartie() {
        if (partie == null)
            partie = new Partie();
        return partie;
    }
}
