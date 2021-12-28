package fr.umontpellier.iut.gymkhana.viewmodel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Scanner;

public class PartieJavaFX extends Application {


    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez la taille du plateau : ");
        int i = sc.nextInt();

        for (int row = 0; row < (i+1); row++) {
            for (int col = 0; col < (i+2); col++) {
                if (col == 0 && row != i) {
                    textDisplay(grid, Integer.toString(row), row, col);
                } else if (row != i) {
                    Rectangle rec = new Rectangle();
                    rec.setWidth(20);
                    rec.setHeight(20);
                    if (row % 2 == 0) {
                        if (col % 2 == 1)
                            rec.setFill(Color.WHITE);
                    }
                    if (row % 2 == 1) {
                        if (col % 2 == 0)
                            rec.setFill(Color.RED);
                    }
                    GridPane.setRowIndex(rec, row);
                    GridPane.setColumnIndex(rec, col );
                    grid.getChildren().addAll(rec);
                }
            }
            if (row == i) {
                for (int col = 0; col < (i + 1); col++) {
                    if (col == 0) {
                        textDisplay(grid, "/", row, col);
                    } else {
                        textDisplay(grid, Integer.toString(col), row, col);
                    }
                }
            }
        }

        Scene scene = new Scene(grid, (i+1)*100, (i+1)*100);

        scene.setFill(Color.BLACK);
        stage.setTitle(
                "Gymkhana");
        stage.setScene(scene);

        stage.show();

    }
    private void textDisplay(GridPane grid, String theText, int row, int col) {
        Text text = new Text();
        text.setWrappingWidth(50);
        text.setText(theText);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFill(Color.WHITE);
        GridPane.setRowIndex(text, row);
        GridPane.setColumnIndex(text, col);
        grid.getChildren().addAll(text);
    }
}