package fr.umontpellier.iut.gymkhana.viewmodel;

import fr.umontpellier.iut.gymkhana.model.ModelFactory;
import fr.umontpellier.iut.gymkhana.viewmodel.menuprincipal.MenuPrincipalViewModel;

public class ViewModelFactory {

    private final ModelFactory mf;
    private MenuPrincipalViewModel menuPrincipalViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }

    public MenuPrincipalViewModel getMenuPrincipalViewModel() {
        if(menuPrincipalViewModel == null)
            menuPrincipalViewModel = new MenuPrincipalViewModel(mf.getPartie());
        return menuPrincipalViewModel;
    }
}
