package controller;

import model.GcodeElement;
import model.Point;
import view.Grid;
import view.GridView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationControl {


    public static void simulate(){
        Point lastPoint = new Point(0,0);

        while(!FileControl.gCodeList.isEmpty()){
            for(Point e : GcodeInterpreter.gCodeToTask(FileControl.gCodeList.get(0), lastPoint)){
                GridView.setTileColor(e.getX(), e.getY());
                lastPoint.setX(e.getX());
                lastPoint.setY(e.getY());
            }
            FileControl.gCodeList.remove(0);
            /*try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException ex){
                System.out.println(ex);
            }*/

        }
    }
}
