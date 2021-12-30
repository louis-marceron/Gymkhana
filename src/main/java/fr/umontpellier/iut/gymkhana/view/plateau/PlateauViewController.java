package fr.umontpellier.iut.gymkhana.view.plateau;

import fr.umontpellier.iut.gymkhana.viewmodel.plateau.PlateauViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PlateauViewController {

    private PlateauViewModel viewModel;

    @FXML
    GridPane gridPane;

    public void init(PlateauViewModel vm) {
        viewModel = vm;

        Label label = new Label("hahahhaha");
        Label label2 = new Label("x");

        gridPane.addColumn(viewModel.getNombreColonnes(), label2);
        gridPane.addColumn(viewModel.getNombreColonnes() + 1, label);


    }

//    public void start(Stage stage) {
//        Scanner sc = new Scanner(System.in);
//
//        for (int row = 0; row < (i+1); row++) {
//            for (int col = 0; col < (i+2); col++) {
//                if (col == 0 && row != i) {
//                    textDisplay(grid, Integer.toString(row), row, col);
//                } else if (row != i) {
//                    Rectangle rec = new Rectangle();
//                    rec.setWidth(20);
//                    rec.setHeight(20);
//                    if (row % 2 == 0) {
//                        if (col % 2 == 1)
//                            rec.setFill(Color.WHITE);
//                    }
//                    if (row % 2 == 1) {
//                        if (col % 2 == 0)
//                            rec.setFill(Color.RED);
//                    }
//                    GridPane.setRowIndex(rec, row);
//                    GridPane.setColumnIndex(rec, col );
//                    grid.getChildren().addAll(rec);
//                }
//            }
//            if (row == i) {
//                for (int col = 0; col < (i + 1); col++) {
//                    if (col == 0) {
//                        textDisplay(grid, "/", row, col);
//                    } else {
//                        textDisplay(grid, Integer.toString(col), row, col);
//                    }
//                }
//            }
//        }
//
//        Scene scene = new Scene(grid, (i+1)*100, (i+1)*100);
//
//        scene.setFill(Color.BLACK);
//        stage.setTitle(
//                "Gymkhana");
//        stage.setScene(scene);
//
//        stage.show();
//
//    }
}
