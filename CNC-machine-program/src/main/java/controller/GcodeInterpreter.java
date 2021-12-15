package controller;

import model.Gcode;
import model.GcodeElement;
import model.Point;

import java.util.ArrayList;
import java.util.List;

public class GcodeInterpreter {

    public static List<Point> gCodeToTask(GcodeElement gcodeElement, Point initPoint){

        List<Point> pointList = new ArrayList<>();

        switch (gcodeElement.getCode()){
            case G00:
                pointList.add(new Point (gcodeElement.getX(), gcodeElement.getY()));
                break;
            case G21:
                break;

            case G17:
                break;
            case G01:
                pointList.addAll(lineFromPoints(new Point(initPoint.getX(), initPoint.getY()), new Point(gcodeElement.getX(), gcodeElement.getY())));
                break;

            default:
                break;
        }

        return pointList;
    }

    private static List<Point> lineFromPoints(Point p, Point q)
    {
        List<Point> pointList = new ArrayList<>();

        int a = q.getY() - p.getY();
        int b = p.getX() - q.getX();
        int c = a * (p.getX()) + b * (p.getY());

        Point newP = new Point(Math.min(p.getX(), q.getX()), Math.min(p.getY(), q.getY()));
        Point newQ = new Point(Math.max(p.getX(), q.getX()), Math.max(p.getY(), q.getY()));

        for (int x= newP.getX(); x<newQ.getX(); x++){
            for (int y= newP.getY(); y< newQ.getY(); y++){
                if (a*x + b*y + c == 0){
                    pointList.add(new Point(x, y));
                }
            }
        }

        return pointList;
    }
}
