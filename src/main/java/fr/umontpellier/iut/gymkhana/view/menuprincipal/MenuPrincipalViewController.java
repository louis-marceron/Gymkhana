package fr.umontpellier.iut.gymkhana.view.menuprincipal;

import fr.umontpellier.iut.gymkhana.view.ViewHandler;
import fr.umontpellier.iut.gymkhana.viewmodel.menuprincipal.MenuPrincipalViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.io.IOException;
import java.util.Objects;

public class MenuPrincipalViewController {

    private MenuPrincipalViewModel viewModel;
    private ViewHandler viewHandler;

    private TextField tfJoueur1;
    private TextField tfJoueur2;

    @FXML
    private ComboBox<String> comboBoxJoueur1;

    @FXML
    private ComboBox<String> comboBoxJoueur2;

    @FXML
    private TextField textField;

    public void init(MenuPrincipalViewModel vm, ViewHandler vh) {
        viewModel = vm;
        viewHandler = vh;

        // On initialise les valeurs dans les listes déroulantes (comboBox)
        comboBoxJoueur1.getItems().addAll("Humain", "IA débutante", "IA MinMax");
        comboBoxJoueur2.getItems().addAll("Humain", "IA débutante", "IA MinMax");

        // On met des valeurs par défaut dans la liste
        comboBoxJoueur1.getSelectionModel().selectFirst();
        comboBoxJoueur2.getSelectionModel().selectFirst();

        // On empêche de rentrer de valeurs non-numériques dans le TextField
        textField.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));

        // TODO réparer ça
        // Hack pour binder la valeur sélectionner dans la liste déroulante avec le model
        tfJoueur1 = new TextField();
        tfJoueur2 = new TextField();
        tfJoueur1.textProperty().bindBidirectional(vm.getJoueur1());
        tfJoueur2.textProperty().bindBidirectional(vm.getJoueur2());
    }

    public void onButtonLancerPartie(ActionEvent actionEvent) throws IOException {
        tfJoueur1.textProperty().setValue(comboBoxJoueur1.getSelectionModel().getSelectedItem());
        tfJoueur2.textProperty().setValue(comboBoxJoueur2.getSelectionModel().getSelectedItem());

        // On récupère la valeur du textfield et on créer un nouveau plateau
        String text = textField.getText();
        if (Objects.equals(text, ""))
            viewModel.remplacerPlateau(5); // Taille par défaut
        else {
            int taille = Integer.parseInt(text);
            viewModel.remplacerPlateau(taille);
        }

        viewModel.setJoueurs();
        viewHandler.openView("Plateau");
    }
}
