package controller;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import model.GcodeElement;
import model.GcodeInterpreter;
import model.Point;
import view.GridView;

public class SimulationControl {


    public static Path getPath(){
        Point lastPoint = new Point(0,0);
        Path path = new Path();
        path.getElements().add(new MoveTo(0, 0));

        for (GcodeElement gcodeElement: FileControl.gCodeList){
            switch (gcodeElement.getCode()){
                case G00:
                    path.getElements().add(new MoveTo(gcodeElement.getX(), gcodeElement.getY()));
                    break;
                case G21:
                    break;

                case G17:
                    break;
                case G01:
                    path.getElements().add(new LineTo(gcodeElement.getX(), gcodeElement.getY()));
                    break;

                default:
                    break;
            }
        }



        return path;
    }
}
