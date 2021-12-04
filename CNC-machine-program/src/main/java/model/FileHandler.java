package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {

    public static InputFile readFromFile(String pathToFile){

        List<ShapeElement> elements = new ArrayList<>();

        try(Stream<String> stream = Files.lines(Paths.get(pathToFile))){
            elements = stream
                    //.filter(line -> !line.startsWith("Title"))
                    .map(line -> {
                        String[] data = line.split(" |,");
                        return new ShapeElement(Shape.valueOf(data[0]), new Point(Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                               new Point(Integer.parseInt(data[2]), Integer.parseInt(data[3])), Integer.parseInt(data[4]));
                    })
                    .collect(Collectors.toList());
        }catch(IOException e) {
            e.printStackTrace();
        }

        return new InputFile(elements);
    }

    public static void writeGcode(List<GcodeElement> list, String name){
        try{
            java.io.FileWriter myWriter = new java.io.FileWriter("G-code"+name+".gcode", true);
            myWriter.write("%");

            for(GcodeElement g: list){
                StringBuffer s =  new StringBuffer();
                s.append(g.getCode().toString());
                s.append(" ");
                if (g.getX() != null){
                    s.append("X").append(g.getX());
                    s.append(" Y").append(g.getY());
                }
                if(g.getI() != null){
                    s.append(" I").append(g.getI());
                    s.append(" J").append(g.getJ());
                }

                myWriter.write(String.valueOf(s));
            }

            myWriter.write("%");



        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
