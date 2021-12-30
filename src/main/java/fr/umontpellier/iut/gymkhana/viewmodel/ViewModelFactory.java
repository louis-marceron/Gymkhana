package fr.umontpellier.iut.gymkhana.viewmodel;

import fr.umontpellier.iut.gymkhana.model.ModelFactory;
import fr.umontpellier.iut.gymkhana.viewmodel.menuprincipal.MenuPrincipalViewModel;
import fr.umontpellier.iut.gymkhana.viewmodel.plateau.PlateauViewModel;

public class ViewModelFactory {

    private final ModelFactory mf;
    private MenuPrincipalViewModel menuPrincipalViewModel;
    private PlateauViewModel plateauViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }

    // Il ne peut avoir qu'un menu principal
    // FIXME je pense que le singleton n'est pas nécessaire
    public MenuPrincipalViewModel getMenuPrincipalViewModel() {
        if (menuPrincipalViewModel == null)
            menuPrincipalViewModel = new MenuPrincipalViewModel(mf.getPartie());
        return menuPrincipalViewModel;
    }

    public PlateauViewModel getPlateauViewModel() {
        if (plateauViewModel == null)
            plateauViewModel = new PlateauViewModel(mf.getPartie());
        return plateauViewModel;
    }
}
