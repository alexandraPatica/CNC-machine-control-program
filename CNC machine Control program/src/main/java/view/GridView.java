package view;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class GridView {

    private Stage window;


    public static void display(int n) {

        Stage window = new Stage();

        Grid grid = new Grid();

        window.initModality(Modality.APPLICATION_MODAL); //block interactions with other window until this is closed
        window.setTitle("Simulation");

        Scene scene = new Scene(grid.makeGrid(n), 500, 500);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }


}
