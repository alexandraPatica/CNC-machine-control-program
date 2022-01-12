package model;

import java.util.List;

public class InputFile {
    private List<ShapeElement> elements;

    public InputFile(List<ShapeElement> elements){
        this.elements = elements;
    }

    public ShapeElement getElement(int index){
        return elements.get(index);
    }

    public List<ShapeElement> getElements(){
        return elements;
    }
}
