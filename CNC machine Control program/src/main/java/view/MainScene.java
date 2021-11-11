/*
 *   Tis class implements the main scene of the calculator
 */

package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class MainScene {

    private static Scene mainScene;
    private Desktop desktop = Desktop.getDesktop();

    //controller


    public Scene display(Stage window){


        GridPane gridMainScene = new GridPane();
        gridMainScene.setPadding( new Insets(20, 20, 20, 20)); //padding for the grid margins
        gridMainScene.setVgap(20);//set vertical spacing between cells
        gridMainScene.setHgap(20); //horizontal spacing


        //choose image
        Text openResultText = new Text();
        FileChooser fileChooser = new FileChooser();
        Button chooseFileButton = new Button("Choose a file");
        chooseFileButton.setOnAction(e -> GridView.display(50));
        chooseFileButton.setOnAction(
                e -> {
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(window);
                    if (file != null) {
                        openFile(file, openResultText);
                    }
                });


        //convert image
        Button convertToGcodeButton = new Button("Convert to G-code");
        //TODO

        //view G-code file
        Button viewGcodeButton = new Button("View G-code file");
        //TODO


        //Start the simulation
        //TODO
        Button startSimulationButton = new Button("Start simulation");
        startSimulationButton.setOnAction(e -> GridView.display(50));

        GridPane gridPane = new GridPane();
        gridPane.getChildren().addAll(chooseFileButton, startSimulationButton, openResultText, convertToGcodeButton, viewGcodeButton);
        gridPane.setPadding( new Insets(10, 10, 10, 10)); //padding for the grid margins
        gridPane.setVgap(8);//set vertical spacing between cells
        gridPane.setVgap(10); //horizontal spacing

        GridPane.setConstraints(chooseFileButton, 0, 0);
        GridPane.setConstraints(openResultText, 1, 0);
        GridPane.setConstraints(convertToGcodeButton, 0, 1);
        GridPane.setConstraints(viewGcodeButton, 0, 2);
        GridPane.setConstraints(startSimulationButton, 0, 3);

        gridMainScene.getChildren().add(gridPane);

        mainScene = new Scene(gridMainScene, 600, 600);
        return mainScene;
    }

    private static void configureFileChooser(FileChooser fileChooser) {
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("IN", "*.in"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
    }

    private void openFile(File file, Text text) {
        try {
            desktop.open(file);
            text.setText("File opened");
            text.setStyle("-fx-text-inner-color: #008002;");
        } catch (IOException ex) {
            System.out.println("Cannot open file");
            text.setStyle("-fx-text-inner-color: red;");
        }
    }
}

