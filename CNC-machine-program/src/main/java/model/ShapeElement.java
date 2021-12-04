package model;

public class ShapeElement {
    private Shape shape;
    private Point startPoint;
    private Point endPoint;
    private int radius;

    public ShapeElement(){
    }

    public ShapeElement(Shape shape, Point startPoint, Point endPoint, int radius){
        this.shape = shape;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.radius = radius;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
