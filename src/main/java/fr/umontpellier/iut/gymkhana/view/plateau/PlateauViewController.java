package fr.umontpellier.iut.gymkhana.view.plateau;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.Plateau;
import fr.umontpellier.iut.gymkhana.model.pieces.Arete;
import fr.umontpellier.iut.gymkhana.model.pieces.Piece;
import fr.umontpellier.iut.gymkhana.model.pieces.Sommet;
import fr.umontpellier.iut.gymkhana.model.pieces.Vide;
import fr.umontpellier.iut.gymkhana.view.ViewHandler;
import fr.umontpellier.iut.gymkhana.viewmodel.plateau.PlateauViewModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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

    public void init(PlateauViewModel vm, ViewHandler vh) throws FileNotFoundException {
        viewModel = vm;
        viewHandler = vh;
        plateauViewController = this;
        plateau = viewModel.getPlateau().getMatrice();
        int largeur = viewModel.getNombreColonnes();

        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (plateau[i][j].getClass() == Vide.class) {
                    // TODO ajouter case non affectée
                    ImageView image = creerImageViewCaseVide();
                    image.setId(i + " " + j);
                    gridPane.add(image, i, j);
                }
                if (plateau[i][j].getClass() == Arete.class) {
                    if (plateau[i][j].getCouleur() == Couleur.Rouge)
                        gridPane.add(creerImageView("areteBlanche.png"), i, j);
                    if (plateau[i][j].getCouleur() == Couleur.Blanc)
                        gridPane.add(creerImageView("areteRouge.png"), i, j);
                }
                if (plateau[i][j].getClass() == Sommet.class)
                    if (plateau[i][j].getCouleur() == Couleur.Rouge)
                        gridPane.add(creerImageView("sommetRouge.png"), i, j);
                if (plateau[i][j].getCouleur() == Couleur.Blanc)
                    gridPane.add(creerImageView("sommetBlanc.png"), i, j);
            }
        }
    }

    private ImageView creerImageView(String nomImage) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/main/java/fr/umontpellier/iut/gymkhana/view/plateau/" + nomImage));
        ImageView imageView = new ImageView(image);
        int taille = 70;
        imageView.setFitHeight(taille);
        imageView.setFitWidth(taille);
        return imageView;
    }

    private ImageView creerImageViewCaseVide() throws FileNotFoundException {
        ImageView imageView = creerImageView("vide.jpg");

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

                System.out.println(((ImageView) mouseEvent.getSource()).getId());

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

