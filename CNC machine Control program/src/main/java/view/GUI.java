package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application{
    private Stage window;


    public static void launchGUI() {
        launch();
    }

    @Override
    public void start(Stage primaryStage)  {
        window = primaryStage;
        window.setTitle("CNC flame cutting machine simulator");

        window.setScene(new MainScene().display(window));
        window.show();

    }
}
