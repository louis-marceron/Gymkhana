package fr.umontpellier.iut.gymkhana.view.menuprincipal;

import fr.umontpellier.iut.gymkhana.model.Joueur;
import fr.umontpellier.iut.gymkhana.model.JoueurHumain;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;


import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Set;

public class MenuPrincipalViewController {

    private MenuPrincipalViewModel viewModel; // FIXME comprendre à quoi ça sert

    @FXML
    ComboBox<String> comboBoxJoueur1;

    @FXML
    ComboBox<String> comboBoxJoueur2;

    @FXML
    Button buttonLancerPartie;

    public void init(MenuPrincipalViewModel menuPrincipalViewModel) {
        this.viewModel = menuPrincipalViewModel; // FIXME comprendre à quoi ça sert

        // On initialise les valeurs dans les listes déroulantes (comboBox)
        // TODO faire un truc plus propre qui ajouter automatiquement les implémentations de Joueur
        comboBoxJoueur1.getItems().addAll("Humain", "IA débutante", "IA MinMax");
        comboBoxJoueur2.getItems().addAll("Humain", "IA débutante", "IA MinMax");

        // On met des valeurs par défaut dans la liste
        comboBoxJoueur1.getSelectionModel().selectFirst();
        comboBoxJoueur2.getSelectionModel().selectFirst();

        // Envoie les valeurs par défaut et initialise le programme quand on appuie sur le bouton
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                // On récupère les valeurs sélectionnées
                String valeurCB1 = comboBoxJoueur1.getSelectionModel().getSelectedItem();
                String valeurCB2 = comboBoxJoueur2.getSelectionModel().getSelectedItem();
                // TODO initialiser partie
                System.out.println(valeurCB1 + valeurCB2);
            }
        };
        buttonLancerPartie.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);


    }
}
