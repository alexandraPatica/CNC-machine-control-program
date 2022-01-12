package model;

public class ShapeElement {
    private Shape shape;
    private Point startPoint;
    private Point endPoint;
    private int radius;

    public ShapeElement(Shape shape, Point startPoint, Point endPoint, int radius){
        this.shape = shape;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.radius = radius;
    }

    public Shape getShape() {
        return shape;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public int getRadius() {
        return radius;
    }
}
