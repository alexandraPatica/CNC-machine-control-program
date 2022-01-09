package controller;

import javafx.scene.shape.*;
import model.FileHandler;
import model.GcodeElement;
import model.GcodeInterpreter;
import model.Point;
import org.apache.commons.io.FilenameUtils;
import view.GridView;

import java.util.List;

public class SimulationControl {


    public static Path getPath(){
        Point lastPoint = new Point(0,0);
        Path path = new Path();
        path.getElements().add(new MoveTo(0, 0));

        List<GcodeElement> gCodeList = FileHandler.readGcodeFile("G-code"+FilenameUtils.removeExtension(FileControl.file.getName())+".gcode");

        for (GcodeElement gcodeElement: gCodeList){
            double radius = 0.0;
            ArcTo arcTo = new ArcTo();

            switch (gcodeElement.getCode()){
                case G00:
                    path.getElements().add(new MoveTo(gcodeElement.getX(), gcodeElement.getY()));
                    lastPoint.setX(gcodeElement.getX());
                    lastPoint.setY(gcodeElement.getY());
                    break;
                case G21:
                    break;

                case G17:
                    break;
                case G01:
                    path.getElements().add(new LineTo(gcodeElement.getX(), gcodeElement.getY()));
                    lastPoint.setX(gcodeElement.getX());
                    lastPoint.setY(gcodeElement.getY());
                    break;

                case G02:

                    radius = Math.sqrt(gcodeElement.getI()*gcodeElement.getI() + gcodeElement.getJ()* gcodeElement.getJ());
                    arcTo.setX(gcodeElement.getX());
                    arcTo.setY(gcodeElement.getY());
                    arcTo.setRadiusX(radius);
                    arcTo.setRadiusY(radius);
                    arcTo.setSweepFlag(true);
                    path.getElements().add(arcTo);


                    lastPoint.setX(gcodeElement.getX());
                    lastPoint.setY(gcodeElement.getY());
                    break;

                case G03:
                    radius = Math.sqrt(gcodeElement.getI()*gcodeElement.getI() + gcodeElement.getJ()* gcodeElement.getJ());
                    arcTo.setX(gcodeElement.getX());
                    arcTo.setY(gcodeElement.getY());
                    arcTo.setRadiusX(radius);
                    arcTo.setRadiusY(radius);
                    arcTo.setSweepFlag(false);
                    path.getElements().add(arcTo);

                    lastPoint.setX(gcodeElement.getX());
                    lastPoint.setY(gcodeElement.getY());
                    break;

                case G28:
                    path.getElements().add(new MoveTo(0, 0));
                    break;

                default:
                    break;
            }
        }
        return path;
    }
}
