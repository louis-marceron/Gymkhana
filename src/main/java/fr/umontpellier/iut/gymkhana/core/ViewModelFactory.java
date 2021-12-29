package fr.umontpellier.iut.gymkhana.core;

import fr.umontpellier.iut.gymkhana.view.menuprincipal.MenuPrincipalViewModel;

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
