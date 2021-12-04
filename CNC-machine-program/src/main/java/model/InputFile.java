package model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class InputFile {
    private List<ShapeElement> elements;

    public InputFile(){
        elements = new ArrayList<>();
    }

    public InputFile(List<ShapeElement> elements){
        this.elements = elements;
    }

    public void addToList (ShapeElement element){
        elements.add(element);
    }

    public void addToList (Shape shape, Point startPoint, Point endPoint, int radius){
        elements.add(new ShapeElement(shape, startPoint, endPoint, radius));
    }

    public void removeFromList (int index){
        elements.remove(index);
    }

    public ShapeElement getElement(int index){
        return elements.get(index);
    }

    public void sortElements(){
        List<ShapeElement> elems = new ArrayList<>();
        elems.add(elements.get(0));
        int size = 1;
        elements.remove(0);

        while (!elements.isEmpty()){
            for(ShapeElement e: elements){
                if (e.getStartPoint().getX() == elems.get(size-1).getEndPoint().getX() && e.getStartPoint().getY() == elems.get(size-1).getEndPoint().getY()){
                    size++;
                    elems.add(e);
                    elements.remove(e);
                    break;
                }
            }
        }
    }

    public List<ShapeElement> getElements(){
        return elements;
    }
}
