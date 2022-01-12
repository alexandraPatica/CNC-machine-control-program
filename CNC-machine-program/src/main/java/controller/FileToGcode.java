package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class FileToGcode {

    public static List<GcodeElement> convert(InputFile inputFile){
        List<GcodeElement> list = new ArrayList<>();
        list.add(new GcodeElement(Gcode.G21));
        list.add(new GcodeElement(Gcode.G17));
        list.add(new GcodeElement(Gcode.G00, inputFile.getElements().get(0).getStartPoint().getX(),
                                             inputFile.getElements().get(0).getStartPoint().getY()));
        Point lastPoint = new Point(inputFile.getElements().get(0).getStartPoint().getX(), inputFile.getElements().get(0).getStartPoint().getY());
        int size = list.size();

        for(int i=0; i<size; i++){

            if (lastPoint != inputFile.getElement(i).getStartPoint()){
                list.add(new GcodeElement(Gcode.G00, inputFile.getElement(i).getStartPoint().getX(),
                                                     inputFile.getElement(i).getStartPoint().getY()));
            }

            if (inputFile.getElement(i).getShape() == Shape.ARC){
                //determine if clockwise or counterclockwise
                Point endPointLastElement = null;
                if (i==0){
                    endPointLastElement = inputFile.getElement(0).getStartPoint();
                }
                else{
                    endPointLastElement = inputFile.getElement(i-1).getEndPoint();
                }

                int xOffset = (int)(getCenterX(inputFile.getElement(i).getStartPoint().getX(),
                                                inputFile.getElement(i).getStartPoint().getY(),
                                                inputFile.getElement(i).getEndPoint().getX(),
                                                inputFile.getElement(i).getEndPoint().getY(),
                                                inputFile.getElement(i).getRadius())) - inputFile.getElement(i).getEndPoint().getX();
                int yOffset = (int)(getCenterY(inputFile.getElement(i).getStartPoint().getX(),
                                            inputFile.getElement(i).getStartPoint().getY(),
                                            inputFile.getElement(i).getEndPoint().getX(),
                                            inputFile.getElement(i).getEndPoint().getY(),
                                            inputFile.getElement(i).getRadius())) - inputFile.getElement(i).getEndPoint().getY();

                Gcode gcode = null;
                if (endPointLastElement.getY() > inputFile.getElement(i).getEndPoint().getY()){
                    if (endPointLastElement.getX() > inputFile.getElement(i).getEndPoint().getX()){     //clockwise
                        gcode = Gcode.G02;
                    }
                    else{                                                                               //counterclockwise
                        gcode = Gcode.G03;
                    }
                }else{
                    if (endPointLastElement.getX() > inputFile.getElement(i).getEndPoint().getX()){     //counterclockwise
                        gcode = Gcode.G03;
                    }
                    else{                                                                               //clockwise
                        gcode = Gcode.G02;
                    }
                }
                list.add(new GcodeElement(gcode, inputFile.getElement(i).getEndPoint().getX(), inputFile.getElement(i).getEndPoint().getY(), xOffset, yOffset));

            }else{
                list.add(new GcodeElement(Gcode.G01, inputFile.getElement(i).getEndPoint().getX(), inputFile.getElement(i).getEndPoint().getY()));
            }

            lastPoint = inputFile.getElement(i).getEndPoint();
        }
        list.add(new GcodeElement( Gcode.G28, 0, 0));
        list.add(new GcodeElement(Gcode.M05));
        list.add(new GcodeElement(Gcode.M30));

        return list;
    }

    private static double getCenterX(int x1,int y1, int x2, int y2,int radius)
    {
        double radsq = radius * radius;
        double q = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
        double x3 = (double)(x1 + x2) / 2;

        return x3 + Math.sqrt(radsq - ((q / 2) * (q / 2))) * ((y1 - y2) / q);
    }

    private static double getCenterY(int x1, int y1, int x2, int y2, int radius)
    {
        double radsq = radius * radius;
        double q = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
        double y3 = (double)(y1 + y2) / 2;

        return y3 + Math.sqrt(radsq - ((q / 2) * (q / 2))) * ((x2-x1) / q);
    }

}
