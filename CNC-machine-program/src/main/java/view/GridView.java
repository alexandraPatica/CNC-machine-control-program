package view;

import controller.SimulationControl;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class GridView {

    private Stage window;
    private static Grid grid;
    private static Circle circle = new Circle();

    public static void display(int n) {

        Stage window = new Stage();

        grid = new Grid(n);

        PathTransition pathTransition = new PathTransition();
        //Drawing a Circle

        //Setting the position of the circle
        circle.setCenterX(300.0f);
        circle.setCenterY(135.0f);

        //Setting the radius of the circle
        circle.setRadius(5.0f);

        //Setting the color of the circle
        circle.setFill(Color.BROWN);

        //Setting the stroke width of the circle
        circle.setStrokeWidth(20);

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            pathTransition.setNode(circle);
            pathTransition.setDuration(Duration.seconds(3));
            Path path = SimulationControl.getPath();
            pathTransition.setPath(path);
            pathTransition.setCycleCount(PathTransition.INDEFINITE);
            fillGrid(n, path);
            pathTransition.play();

        });

        /*pathTransition.setNode(circle);
        pathTransition.setDuration(Duration.seconds(3));
        pathTransition.setPath(SimulationControl.getPath());
        pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.play();*/

        //VBox vBox = new VBox(startButton, grid.makeGrid(n), circle);
        Group group = new Group(grid.getGridPane(), startButton, circle);


        window.initModality(Modality.APPLICATION_MODAL); //block interactions with other window until this is closed
        window.setTitle("Simulation");

        Scene scene = new Scene(group, 510, 510);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

       // ((Path)Shape.intersect(transition.getPath(),cell)).getElements().size()>0
    }

    private static void fillGrid(int n, Path path){
        for (int x = 0; x < n; x++){
            for (int y = 0; y < n; y++){
                if (((Path) Shape.intersect(path,grid.getGrid()[x][y])).getElements().size()>0){
                    grid.setColorRed(x, y);
                }
            }
        }
    }

    public static void setTileColor(int x, int y){
        grid.setColorRed(x, y);
    }

}
