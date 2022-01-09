package model;

public class Point {
    private int x;
    private int y;

    public Point (int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o){
        if (o == this){
            return true;
        }

        if (!(o instanceof Point)){
            return false;
        }

        Point p = (Point) o;

        return this.x == p.getX() && this.y == p.getY();
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
