package view;

import controller.SimulationControl;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private static Grid grid = new Grid();

    public static void display(int n) {

        Stage window = new Stage();

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> SimulationControl.simulate());

        VBox vBox = new VBox(startButton, grid.makeGrid(n));


        window.initModality(Modality.APPLICATION_MODAL); //block interactions with other window until this is closed
        window.setTitle("Simulation");

        Scene scene = new Scene(vBox, 510, 510);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }

    public static void setTileColor(int x, int y){
        grid.setColorRed(x, y);
    }

}
