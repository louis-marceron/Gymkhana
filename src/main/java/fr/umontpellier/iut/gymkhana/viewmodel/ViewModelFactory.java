package fr.umontpellier.iut.gymkhana.viewmodel;

import fr.umontpellier.iut.gymkhana.model.ModelFactory;
import fr.umontpellier.iut.gymkhana.model.Partie;
import fr.umontpellier.iut.gymkhana.viewmodel.menuprincipal.MenuPrincipalViewModel;
import fr.umontpellier.iut.gymkhana.viewmodel.plateau.PlateauViewModel;

public class ViewModelFactory {

    private Partie partie;

    public ViewModelFactory(ModelFactory mf) {
        partie = mf.getPartie();
    }

    public MenuPrincipalViewModel getMenuPrincipalViewModel() {
        return new MenuPrincipalViewModel(partie);
    }

    public PlateauViewModel getPlateauViewModel() {
        return new PlateauViewModel(partie);
    }
}
