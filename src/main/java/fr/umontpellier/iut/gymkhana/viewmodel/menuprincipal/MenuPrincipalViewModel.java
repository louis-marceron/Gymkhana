package fr.umontpellier.iut.gymkhana.viewmodel.menuprincipal;

import fr.umontpellier.iut.gymkhana.model.Partie;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MenuPrincipalViewModel {

    private Partie partie;
    private StringProperty joueur1;
    private StringProperty joueur2;

    public MenuPrincipalViewModel(Partie partie) {
        this.partie = partie;
        joueur1 = new SimpleStringProperty();
        joueur2 = new SimpleStringProperty();

    }

    public StringProperty getJoueur1() {
        return joueur1;
    }

    public StringProperty getJoueur2() {
        return joueur2;
    }
}
