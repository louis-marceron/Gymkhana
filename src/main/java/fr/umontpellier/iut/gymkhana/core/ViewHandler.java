package fr.umontpellier.iut.gymkhana.core;

import fr.umontpellier.iut.gymkhana.view.menuprincipal.MenuPrincipalViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ViewHandler {

    private Scene menuPrincipalScene;
    private Stage stage;
    private ViewModelFactory vmf;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public void start() {
        stage = new Stage();
        openPartie();
    }

    private void openPartie() {
        if (menuPrincipalScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                // Seul le chemin absolu marche, je comprends pas pourquoi :/
                URL url = new URL("file:/Users/Louis/Gymkhana/src/main/java/fr/umontpellier/iut/gymkhana/view/menuprincipal/MenuPrincipalView.fxml");
//                URL url2 = new URL("file:/fr/umontpellier/iut/gymkhana/view/menuprincipal/MenuPrincipalView.fxml");
                loader.setLocation(url);
//                loader.setLocation(getClass().getResource("/fr/umontpellier/iut/gymkhana/view/menuprincipal/MenuPrincipalView.fxml"));
                Parent root = loader.load();

                MenuPrincipalViewController ctrl = loader.getController();
                ctrl.init(vmf.getMenuPrincipalViewModel());

                stage.setTitle("Menu principal");
                menuPrincipalScene = new Scene(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(menuPrincipalScene);
        stage.show();
    }
}
