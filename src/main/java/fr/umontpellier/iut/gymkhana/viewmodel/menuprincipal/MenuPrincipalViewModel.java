package fr.umontpellier.iut.gymkhana.viewmodel.menuprincipal;

import fr.umontpellier.iut.gymkhana.model.JoueurHumain;
import fr.umontpellier.iut.gymkhana.model.JoueurIADebutant;
import fr.umontpellier.iut.gymkhana.model.JoueurMinMax;
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

    public void setJoueurs() { // TODO try/catch ?
        String j1 = joueur1.getValue();
        String j2 = joueur2.getValue();

        switch (j1) {
            case "Humain":
                partie.setJ1(new JoueurHumain());
                break;
            case "IA débutante":
                partie.setJ1(new JoueurIADebutant());
                break;
            case "IA MinMax":
                partie.setJ1(new JoueurMinMax());
                break;
        }

        switch (j2) {
            case "Humain":
                partie.setJ2(new JoueurHumain());
                break;
            case "IA débutante":
                partie.setJ2(new JoueurIADebutant());
                break;
            case "IA MinMax":
                partie.setJ2(new JoueurMinMax());
                break;
        }
    }

    public void lancerPartie() {
//        partie.run2();
        // FIXME réparer ça
    }
}
