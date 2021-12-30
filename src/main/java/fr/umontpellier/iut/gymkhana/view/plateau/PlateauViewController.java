package fr.umontpellier.iut.gymkhana.view.plateau;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.pieces.Arete;
import fr.umontpellier.iut.gymkhana.model.pieces.Piece;
import fr.umontpellier.iut.gymkhana.model.pieces.Sommet;
import fr.umontpellier.iut.gymkhana.model.pieces.Vide;
import fr.umontpellier.iut.gymkhana.viewmodel.plateau.PlateauViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlateauViewController {

    private PlateauViewModel viewModel;
    private Piece[][] plateau;

    @FXML
    GridPane gridPane;

    @FXML
    ImageView imageView2;

    @FXML
    Image image2;

    public void init(PlateauViewModel vm) throws FileNotFoundException {
        viewModel = vm;
        plateau = viewModel.getPlateau().getMatrice();
        int largeur = viewModel.getNombreColonnes();



        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (plateau[i][j].getClass() == Vide.class) {
                    gridPane.add(imageCorrespondante("vide.jpg"), i, j);
                }
                if (plateau[i][j].getClass() == Arete.class) {
                    if (plateau[i][j].getCouleur() == Couleur.Rouge)
                        gridPane.add(new Label("AR"), i, j);
                    if (plateau[i][j].getCouleur() == Couleur.Blanc)
                        gridPane.add(new Label("AB"), i, j);
                }
                if (plateau[i][j].getClass() == Sommet.class)
                    if (plateau[i][j].getCouleur() == Couleur.Rouge)
                        gridPane.add(imageCorrespondante("sommetRouge.png"), i, j);
                if (plateau[i][j].getCouleur() == Couleur.Blanc)
                    gridPane.add(imageCorrespondante("sommetBlanc.png"), i, j);
            }
        }
    }

    private ImageView imageCorrespondante(String nomImage) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/main/java/fr/umontpellier/iut/gymkhana/view/plateau/"+nomImage));
        ImageView imageView = new ImageView(image);
        int taille = 70;
        imageView.setFitHeight(taille);
        imageView.setFitWidth(taille);
        return imageView;
    }
}

