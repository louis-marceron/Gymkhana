package fr.umontpellier.iut.gymkhana.view.menuprincipal;

import fr.umontpellier.iut.gymkhana.view.ViewHandler;
import fr.umontpellier.iut.gymkhana.viewmodel.ViewModelFactory;
import fr.umontpellier.iut.gymkhana.viewmodel.menuprincipal.MenuPrincipalViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MenuPrincipalViewController {

    private MenuPrincipalViewModel viewModel;
    private ViewHandler viewHandler;

    TextField tfJoueur1;
    TextField tfJoueur2;

    @FXML
    ComboBox<String> comboBoxJoueur1;

    @FXML
    ComboBox<String> comboBoxJoueur2;

    @FXML
    Button buttonLancerPartie;

    public void init(MenuPrincipalViewModel vm, ViewHandler vh) {
        viewModel = vm;
        viewHandler = vh;

        // On initialise les valeurs dans les listes déroulantes (comboBox)
        // TODO faire un truc plus propre qui ajouter automatiquement les implémentations de Joueur
        comboBoxJoueur1.getItems().addAll("Humain", "IA débutante", "IA MinMax");
        comboBoxJoueur2.getItems().addAll("Humain", "IA débutante", "IA MinMax");

        // On met des valeurs par défaut dans la liste
        comboBoxJoueur1.getSelectionModel().selectFirst();
        comboBoxJoueur2.getSelectionModel().selectFirst();

        // Hack pour binder la valeur sélectionner dans la liste déroulante avec le model
        tfJoueur1 = new TextField();
        tfJoueur2 = new TextField();
        tfJoueur1.textProperty().bindBidirectional(vm.getJoueur1());
        tfJoueur2.textProperty().bindBidirectional(vm.getJoueur2());
    }

    public void onButtonLancerPartie(ActionEvent actionEvent) throws IOException {
        tfJoueur1.textProperty().setValue(comboBoxJoueur1.getSelectionModel().getSelectedItem());
        tfJoueur2.textProperty().setValue(comboBoxJoueur2.getSelectionModel().getSelectedItem());

        viewHandler.openView("Plateau");

        viewModel.setJoueurs();
        viewModel.lancerPartie();
    }
}
