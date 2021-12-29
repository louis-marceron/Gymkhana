package fr.umontpellier.iut.gymkhana;

import fr.umontpellier.iut.gymkhana.core.ModelFactory;
import fr.umontpellier.iut.gymkhana.core.ViewHandler;
import fr.umontpellier.iut.gymkhana.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class GymkhanaApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(vmf);
        vh.start();
    }
}
