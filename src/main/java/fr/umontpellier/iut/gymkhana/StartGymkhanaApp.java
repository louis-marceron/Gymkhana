package fr.umontpellier.iut.gymkhana;

import javafx.application.Application;

/**
 * Classe lançant {@code GymkhanaApp}. Il est préférable d'utiliser une classe externe pour lancer {@code GymkhanaApp}
 * qu'une méthode main() directement à l'intérieur de {@code GymkhanaApp}.
 */
public class StartGymkhanaApp {

    public static void main(String[] args) throws Exception {
        Application.launch(GymkhanaApp.class);
    }

}
