package fr.umontpellier.iut.gymkhana.view.plateau;

import fr.umontpellier.iut.gymkhana.GymkhanaApp;
import fr.umontpellier.iut.gymkhana.StartGymkhanaApp;
import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.pieces.*;
import fr.umontpellier.iut.gymkhana.view.ViewHandler;
import fr.umontpellier.iut.gymkhana.viewmodel.plateau.PlateauViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlateauViewController {
    private PlateauViewController plateauViewController;
    private PlateauViewModel viewModel;
    private ViewHandler viewHandler;
    private Piece[][] plateau;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label gagnant;

    @FXML
    ImageView recommencer;


    public void init(PlateauViewModel vm, ViewHandler vh) throws FileNotFoundException {
        viewModel = vm;
        viewHandler = vh;
        plateauViewController = this;
        plateau = viewModel.getPlateau().getMatrice();
        int largeur = viewModel.getNombreColonnes();

        // Label message gagnant
        gagnant.setMouseTransparent(true);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        int taillePolice = (int) screenBounds.getMaxY() / 15;
        gagnant.setStyle("-fx-font: " + taillePolice + " \"Berlin Sans FB\";");

        // Bouton recommencer
        recommencer.setMouseTransparent(true);
        recommencer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GymkhanaApp g = new GymkhanaApp();
                viewHandler.getStage().close(); // Ferme l'ancien stage
                try {
                    g.start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        if (viewModel.getPartie().estTerminee()) {
            gagnant.setText("Le joueur " + viewModel.getPartie().getJoueurCourant().getCouleur().nomCouleur() + " a gagn?? !");
            gagnant.setVisible(true);
            recommencer.setVisible(true);
            recommencer.setMouseTransparent(false);
            gridPane.setMouseTransparent(true); // On ne peut plus cliquer sur les cases
            gridPane.setEffect(new GaussianBlur(9));
        }

        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (plateau[i][j].getClass() == NonAffectee.class) {
                    gridPane.add(creerImageView("vide.png"), j, i);
                }

                if (plateau[i][j].getClass() == Vide.class) {
                    if ((viewModel.getPartie().getJoueurCourant().getCouleur() == Couleur.Blanc && (j == 0 || j == plateau[i].length - 1))) {
                        gridPane.add(creerImageView("vide.png"), j, i);
                    } else if (viewModel.getPartie().getJoueurCourant().getCouleur() == Couleur.Rouge && (i == 0 || i == plateau.length - 1)) {
                        gridPane.add(creerImageView("vide.png"), j, i);
                    } else { // coup dur
                        ImageView image = creerImageViewCaseVide();
                        image.setId(i + " " + j);
                        gridPane.add(image, j, i);
                    }
                }

                if (plateau[i][j].getClass() == Arete.class) {
                    if (((PieceColoree) plateau[i][j]).getCouleur() == Couleur.Blanc) {
                        if ((j - 1 >= 0 && ((PieceColoree) plateau[i][j - 1]).getCouleur() == Couleur.Blanc)
                                || (j + 1 <= plateau.length - 1 && ((PieceColoree) plateau[i][j + 1]).getCouleur() == Couleur.Blanc))
                            gridPane.add(creerImageView("areteHorizontaleBlanche.png"), j, i);

                        if ((i - 1 >= 0 && ((PieceColoree) plateau[i - 1][j]).getCouleur() == Couleur.Blanc)
                                || (i + 1 <= plateau.length - 1 && ((PieceColoree) plateau[i + 1][j]).getCouleur() == Couleur.Blanc))
                            gridPane.add(creerImageView("areteVerticaleBlanche.png"), j, i);
                    }

                    if (((PieceColoree) plateau[i][j]).getCouleur() == Couleur.Rouge) {
                        if ((j - 1 >= 0 && ((PieceColoree) plateau[i][j - 1]).getCouleur() == Couleur.Rouge)
                                || (j + 1 <= plateau.length - 1 && ((PieceColoree) plateau[i][j + 1]).getCouleur() == Couleur.Rouge))
                            gridPane.add(creerImageView("areteHorizontaleRouge.png"), j, i);

                        if ((i - 1 >= 0 && ((PieceColoree) plateau[i - 1][j]).getCouleur() == Couleur.Rouge)
                                || (i + 1 <= plateau.length - 1 && ((PieceColoree) plateau[i + 1][j]).getCouleur() == Couleur.Rouge))
                            gridPane.add(creerImageView("areteVerticaleRouge.png"), j, i);
                    }
                }

                if (plateau[i][j].getClass() == Sommet.class) {
                    // On stock la couleur du sommet courant
                    Couleur couleur = ((PieceColoree) plateau[i][j]).getCouleur();
                    String couleurExtensions;

                    if (couleur == Couleur.Blanc) {
                        gridPane.add(creerImageView("sommetBlanc.png"), j, i);
                        couleurExtensions = "FCF6F6";
                    } else {
                        gridPane.add(creerImageView("sommetRouge.png"), j, i);
                        couleurExtensions = "D90000";
                    }

                    // On ajoute des extensions sur les sommets s'ils sont ?? c??t?? d'une ar??te de la m??me couleur (esth??tique)
                    double taille = screenBounds.getMaxY() / viewModel.getNombreColonnes() * 0.8;

                    if (j - 1 >= 0 && plateau[i][j - 1].getClass() == Arete.class && couleur == ((PieceColoree) plateau[i][j - 1]).getCouleur()) {
                        Rectangle rectangle = new Rectangle(taille / 2.5 + 1, taille / 5, Color.web(couleurExtensions));
                        GridPane.setHalignment(rectangle, HPos.LEFT);
                        GridPane.setValignment(rectangle, VPos.CENTER);
                        gridPane.add(rectangle, j, i);
                    }

                    if (j + 1 <= plateau.length - 1 && plateau[i][j + 1].getClass() == Arete.class && couleur == ((PieceColoree) plateau[i][j + 1]).getCouleur()) {
                        Rectangle rectangle = new Rectangle(taille / 2.5 + 1, taille / 5, Color.web(couleurExtensions));
                        GridPane.setHalignment(rectangle, HPos.RIGHT);
                        GridPane.setValignment(rectangle, VPos.CENTER);
                        gridPane.add(rectangle, j, i);
                    }

                    if (i - 1 >= 0 && plateau[i - 1][j].getClass() == Arete.class && couleur == ((PieceColoree) plateau[i - 1][j]).getCouleur()) {
                        Rectangle rectangle = new Rectangle(taille / 5, taille / 2.5 + 1, Color.web(couleurExtensions));
                        GridPane.setHalignment(rectangle, HPos.CENTER);
                        GridPane.setValignment(rectangle, VPos.TOP);
                        gridPane.add(rectangle, j, i);
                    }

                    if (i + 1 <= plateau.length - 1 && plateau[i + 1][j].getClass() == Arete.class && couleur == ((PieceColoree) plateau[i + 1][j]).getCouleur()) {
                        Rectangle rectangle = new Rectangle(taille / 5, taille / 2.5 + 1, Color.web(couleurExtensions));
                        GridPane.setHalignment(rectangle, HPos.CENTER);
                        GridPane.setValignment(rectangle, VPos.BOTTOM);
                        gridPane.add(rectangle, j, i);
                    }
                }
            }
        }
    }


    private ImageView creerImageView(String nomImage) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/main/java/fr/umontpellier/iut/gymkhana/view/plateau/images/" + nomImage));
        ImageView imageView = new ImageView(image);

        // R??cup??re la hauteur de l'??cran et adapte la taille des cases du plateau
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
                // R??cup??re les ID des ImageView contenant leurs coordonn??es
                String[] coordonnees = ((ImageView) mouseEvent.getSource()).getId().split(" ");

                // Envoie les coordonn??es dans la vue model
                int x = Integer.parseInt(coordonnees[0]);
                int y = Integer.parseInt(coordonnees[1]);
                viewModel.setX(x);
                viewModel.setY(y);

                try {
                    plateauViewController.jouerLeTour();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Hover
        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                imageView.setEffect(new Glow(8));
            }
        });
        imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                imageView.setEffect(null);
            }
        });

        // Curseur
        imageView.setCursor(Cursor.HAND);

        return imageView;
    }

    private void jouerLeTour() throws IOException {
        viewModel.jouer();
        viewHandler.openView("Plateau");
    }
}