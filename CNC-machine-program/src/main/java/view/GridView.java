package view;

import controller.SimulationControl;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.util.Duration;

public class GridView {

    private static Grid grid;
    private static Circle circle = new Circle();
    private static Button startButton = new Button("Start");
    private static Button pauseButton = new Button("Pause");
    private static boolean isPlaying = false;
    private static boolean isPaused = false;

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

        pauseButton.setVisible(false);
        startButton.setOnAction(e -> {
            if (isPlaying){
                isPlaying = false;
                pathTransition.stop();
                pathTransition.setPath(null);
                startButton.setText("Start");
                pauseButton.setVisible(false);
            }
            else{
                isPlaying = true;
                pathTransition.setNode(circle);
                pathTransition.setDuration(Duration.seconds(3));
                Path path = SimulationControl.getPath();
                pathTransition.setPath(path);
                pathTransition.setCycleCount(PathTransition.INDEFINITE);
                fillGrid(n, path);
                pathTransition.play();
                startButton.setText("Stop");
                pauseButton.setVisible(true);
            }
        });

        pauseButton.setOnAction(e -> {
            if (isPaused) {
                isPaused = false;
                pathTransition.play();
                pauseButton.disarm();
            }else{
                isPaused = true;
                pathTransition.pause();
                pauseButton.arm();
            }
        });


        HBox hBox = new HBox(startButton, pauseButton);
        Group group = new Group(grid.getGridPane(), hBox, circle);


        window.initModality(Modality.APPLICATION_MODAL); //block interactions with other window until this is closed
        window.setTitle("Simulation");

        Scene scene = new Scene(group, 510, 510);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

        window.setOnCloseRequest(e -> {
            pathTransition.stop();
            pathTransition.setPath(null);
        });
    }

    private static void fillGrid(int n, Path path){
        for (int x = 0; x < n; x++){
            for (int y = 0; y < n; y++){
                if (((Path) Shape.intersect(path,grid.getGrid()[x][y])).getElements().size()>0){
                    grid.setColorRed(x, y);
                }
            }
        }

       /* for (int x = 1; x < n-1; x++){
            for (int y = 1; y < n-1; y++){
                if (grid.getGrid()[x][y].getFill().equals(Color.RED)) {
                    if (grid.getGrid()[x - 1][y].getFill().equals(Color.RED) && grid.getGrid()[x + 1][y].getFill().equals(Color.RED)) {
                        grid.getGrid()[x][y - 1].setFill(Color.WHITE);
                        grid.getGrid()[x][y + 1].setFill(Color.WHITE);
                    }
                    if (grid.getGrid()[x][y - 1].getFill().equals(Color.RED) && grid.getGrid()[x][y + 1].getFill().equals(Color.RED)) {
                        grid.getGrid()[x - 1][y].setFill(Color.WHITE);
                        grid.getGrid()[x + 1][y].setFill(Color.WHITE);
                    }
                }
            }
        }*/
    }


}
