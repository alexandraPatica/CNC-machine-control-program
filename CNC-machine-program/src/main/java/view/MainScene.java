/*
 *   Tis class implements the main scene of the calculator
 */

package view;


import controller.FileControl;
import controller.SimulationControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class MainScene {

    private static Scene mainScene;
    private Desktop desktop = Desktop.getDesktop();

    private File file = null;
    private FileChooser fileChooser = new FileChooser();
    Text openResultText = new Text();

    private Stage window = null;


    public Scene display(Stage window){

        this.window = window;
        GridPane gridMainScene = new GridPane();
        gridMainScene.setPadding( new Insets(20, 20, 20, 20)); //padding for the grid margins
        gridMainScene.setVgap(20);//set vertical spacing between cells
        gridMainScene.setHgap(20); //horizontal spacing


        //choose lines and arcs file
        Button chooseFileButton = new Button("Choose a file");
        chooseFileButton.setOnAction(e -> chooseFile());


        //convert file to G-code
        Text noFileText = new Text();
        Button convertToGcodeButton = new Button("Convert to G-code");
        convertToGcodeButton.setOnAction(e -> convertToGcode(noFileText));

        //view G-code file
        Text noGcodeFileText = new Text();
        Button viewGcodeButton = new Button("View G-code file");
        viewGcodeButton.setOnAction(e -> openFile(new File("G-code" + FilenameUtils.removeExtension(file.getName()) + ".gcode"), noGcodeFileText));


        Label choiceBoxLabel = new Label("Choose grid size");
        ChoiceBox<Integer> choiceBox = new ChoiceBox<Integer>();

        for (int i=40; i<100; i++){
            choiceBox.getItems().add(i);
        }

        choiceBox.setValue(50);




        //Start the simulation
        //TODO
        Button startSimulationButton = new Button("Start simulation");
        startSimulationButton.setOnAction(e -> {
            GridView.display(choiceBox.getValue());
        });


        GridPane gridPane = new GridPane();
        gridPane.getChildren().addAll(chooseFileButton, startSimulationButton, openResultText, convertToGcodeButton, viewGcodeButton, noFileText,
                choiceBox, choiceBoxLabel, noGcodeFileText);
        gridPane.setPadding( new Insets(10, 10, 10, 10)); //padding for the grid margins
        gridPane.setVgap(8);//set vertical spacing between cells
        gridPane.setVgap(10); //horizontal spacing

        GridPane.setConstraints(chooseFileButton, 0, 0);
        GridPane.setConstraints(openResultText, 1, 0);
        GridPane.setConstraints(convertToGcodeButton, 0, 1);
        GridPane.setConstraints(noFileText, 1, 1);
        GridPane.setConstraints(viewGcodeButton, 0, 2);
        GridPane.setConstraints(noGcodeFileText, 1, 2);
        GridPane.setConstraints(choiceBoxLabel, 0, 3);
        GridPane.setConstraints(choiceBox, 1, 3);
        GridPane.setConstraints(startSimulationButton, 0, 4);

        gridMainScene.getChildren().add(gridPane);

        mainScene = new Scene(gridMainScene, 350, 250);
        return mainScene;
    }

    private void chooseFile (){
        FileControl.configureFileChooser(fileChooser);
        file = fileChooser.showOpenDialog(window);
        if (file != null) {
            openFile(file, openResultText);
        }
    }

    private void openFile(File file, Text text) {
        try {
            if (file != null) {
                desktop.open(file);

                text.setText("File opened");
                //text.setStyle("-fx-text-inner-color: #008002;");
                text.setFill(Color.GREEN);
            }else{
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Cannot open file");
            text.setText("Cannot open file!");
            //text.setStyle("-fx-text-inner-color: #ff0000;");
            text.setFill(Color.RED);
        }
    }

    private void convertToGcode(Text text){
        {
            try{
                if (file != null) {
                    FileControl.convertToGcode(file);
                    text.setText("");
                }else{
                    throw new Exception();
                }
            }catch(Exception ex){
                System.out.println("No file chosen!");
                text.setText("No file chosen!");
                //text.setStyle("-fx-text-inner-color: #ff0000;");
                text.setFill(Color.RED);
            }
        }
    }

}

