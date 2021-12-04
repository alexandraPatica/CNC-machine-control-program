package model;

public class GcodeElement {
    private Gcode code;
    private Integer x = null;
    private Integer y = null;
    private Integer i = null;
    private Integer j = null;

    public GcodeElement(Gcode code){
        this.code = code;
    }

    public GcodeElement(Gcode code, Integer x, Integer y){
        this.code = code;
        this.x = x;
        this.y = y;
    }

    public GcodeElement(Gcode code, Integer x, Integer y, Integer i, Integer j){
        this.code = code;
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
    }

    public Gcode getCode() {
        return code;
    }

    public void setCode(Gcode code) {
        this.code = code;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }
}
