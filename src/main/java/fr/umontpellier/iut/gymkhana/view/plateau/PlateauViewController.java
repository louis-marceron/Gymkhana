package fr.umontpellier.iut.gymkhana.view.plateau;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.pieces.*;
import fr.umontpellier.iut.gymkhana.view.ViewHandler;
import fr.umontpellier.iut.gymkhana.viewmodel.plateau.PlateauViewModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlateauViewController {

    private PlateauViewController plateauViewController;
    private PlateauViewModel viewModel;
    private ViewHandler viewHandler;
    private Piece[][] plateau;

    @FXML
    GridPane gridPane;

    // TODO animation quand on passe par-dessus une case cliquable
    // TODO animation quand on pose une arête

    public void init(PlateauViewModel vm, ViewHandler vh) throws FileNotFoundException {
        viewModel = vm;
        viewHandler = vh;
        plateauViewController = this;
        plateau = viewModel.getPlateau().getMatrice();
        int largeur = viewModel.getNombreColonnes();
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (plateau[i][j].getClass() == NonAffectee.class) {
                    gridPane.add(creerImageView("nonAffectee.jpg"), j, i);
                }

                if (plateau[i][j].getClass() == Vide.class) {
                    ImageView image = creerImageViewCaseVide();
                    image.setId(i + " " + j);
                    gridPane.add(image, j, i);
                }

                if (plateau[i][j].getClass() == Arete.class) { //FIXME c'est buggé
                    if (plateau[i][j].getCouleur() == Couleur.Blanc) {
                        if ((j - 1 >= 0 && plateau[i][j - 1].getCouleur() == Couleur.Blanc)
                                || (j + 1 <= plateau.length - 1 && plateau[i][j + 1].getCouleur() == Couleur.Blanc))
                            gridPane.add(creerImageView("areteHorizontaleBlanche.png"), j, i);

                        if ((i - 1 >= 0 && plateau[i - 1][j].getCouleur() == Couleur.Blanc)
                                || (i + 1 <= plateau.length - 1 && plateau[i + 1][j].getCouleur() == Couleur.Blanc))
                            gridPane.add(creerImageView("areteVerticaleBlanche.png"), j, i);
                    }

                    if (plateau[i][j].getCouleur() == Couleur.Rouge) {
                        if ((j - 1 >= 0 && plateau[i][j - 1].getCouleur() == Couleur.Rouge)
                                || (j + 1 <= plateau.length - 1 && plateau[i][j + 1].getCouleur() == Couleur.Rouge))
                            gridPane.add(creerImageView("areteHorizontaleRouge.png"), j, i);

                        if ((i - 1 >= 0 && plateau[i - 1][j].getCouleur() == Couleur.Rouge)
                                || (i + 1 <= plateau.length - 1 && plateau[i + 1][j].getCouleur() == Couleur.Rouge))
                            gridPane.add(creerImageView("areteVerticaleRouge.png"), j, i);
                    }
                }

                if (plateau[i][j].getClass() == Sommet.class) {
                    if (plateau[i][j].getCouleur() == Couleur.Rouge)
                        gridPane.add(creerImageView("sommetRouge.png"), j, i);
                    if (plateau[i][j].getCouleur() == Couleur.Blanc)
                        gridPane.add(creerImageView("sommetBlanc.png"), j, i);
                }
            }
        }

//        gridPane.add(creerImageView("sommet.jpg"), 4, 8);
    }

    private ImageView creerImageView(String nomImage) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/main/java/fr/umontpellier/iut/gymkhana/view/plateau/" + nomImage));
        ImageView imageView = new ImageView(image);

        // Récupère la hauteur de l'écran et adapte la taille des cases du plateau
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double taille = screenBounds.getMaxY() / viewModel.getNombreColonnes() * 0.8;
        imageView.setFitHeight(taille);
        imageView.setFitWidth(taille);

        return imageView;
    }

    private ImageView creerImageViewCaseVide() throws FileNotFoundException {
        ImageView imageView = creerImageView("vide.png");

        // Listener quand on clique sur une ImageView
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // Récupère les ID des ImageView contenant leurs coordonnées
                String[] coordonnees = ((ImageView) mouseEvent.getSource()).getId().split(" ");

                // Envoie les coordonnées dans la vue model
                int x = Integer.parseInt(coordonnees[0]);
                int y = Integer.parseInt(coordonnees[1]);
                viewModel.setX(x); // TODO coordonnées buggées
                viewModel.setY(y);

                try {
                    plateauViewController.jouerLeTour();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return imageView;
    }

    private void jouerLeTour() throws IOException {
        viewModel.jouer();
        viewHandler.openView("Plateau");
    }
}

