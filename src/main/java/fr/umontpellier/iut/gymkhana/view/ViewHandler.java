package fr.umontpellier.iut.gymkhana.view;

import fr.umontpellier.iut.gymkhana.view.plateau.PlateauViewController;
import fr.umontpellier.iut.gymkhana.viewmodel.ViewModelFactory;
import fr.umontpellier.iut.gymkhana.view.menuprincipal.MenuPrincipalViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;

public class ViewHandler {

    private Scene menuPrincipalScene;
    private Stage stage;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    public void start() throws IOException {
        stage = new Stage();
        stage.setResizable(false);
        openView("MenuPrincipal");
    }

    public void openView(String viewToOpen) throws IOException {
        Scene scene = null;
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;

        loader.setLocation(new URL("file:src/main/java/fr/umontpellier/iut/gymkhana/view/"+viewToOpen.toLowerCase()+"/"+viewToOpen+"View.fxml"));
        root = loader.load();

        if ("MenuPrincipal".equals(viewToOpen)) {
            MenuPrincipalViewController controller = loader.getController();
            controller.init(viewModelFactory.getMenuPrincipalViewModel(), this); // La factory permet de ne pas avoir plusieurs models en attribut
            stage.setTitle("Menu Principal");
        }

        if ("Plateau".equals(viewToOpen)) {
            PlateauViewController controller = loader.getController();
            controller.init(viewModelFactory.getPlateauViewModel());
            stage.setTitle("Plateau");
        }

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
