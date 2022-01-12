package controller;

import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
                               new Point(Integer.parseInt(data[3]), Integer.parseInt(data[4])), Integer.parseInt(data[5]));
                    })
                    .collect(Collectors.toList());
        }catch(IOException e) {
            e.printStackTrace();
        }

        return new InputFile(elements);
    }

    public static void writeGcode(List<GcodeElement> list, String name){
        try{
            java.io.FileWriter myWriter = new java.io.FileWriter("G-code"+name+".gcode", false);
            myWriter.write("%\n");

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

                s.append("\n");

                myWriter.write(String.valueOf(s));
            }

            myWriter.write("%");

            myWriter.flush();
            myWriter.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static List<GcodeElement> readGcodeFile(String pathToFile){
        List<GcodeElement> elements = new ArrayList<>();

        try(Stream<String> stream = Files.lines(Paths.get(pathToFile))){
            elements = stream
                    .filter(line -> !line.startsWith("%"))
                    .map(line -> {
                        String[] data = line.split(" ");
                        Gcode code = Gcode.valueOf(data[0]);
                        if (data.length == 1){
                            return new GcodeElement(code);
                        }
                        else {
                            Integer x = Integer.parseInt(data[1].substring(1));
                            Integer y = Integer.parseInt(data[2].substring(1));

                            if (data.length == 3){
                                return new GcodeElement(code, x, y);
                            }
                            else{
                                Integer i = Integer.parseInt(data[3].substring(1));
                                Integer j = Integer.parseInt(data[4].substring(1));

                                return new GcodeElement(code, x, y, i, j);
                            }
                        }
                    })
                    .collect(Collectors.toList());
        }catch(IOException e) {
            e.printStackTrace();
        }

        return elements;
    }
}
