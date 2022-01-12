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


    public Integer getX() {
        return x;
    }


    public Integer getY() {
        return y;
    }

    public Integer getI() {
        return i;
    }

    public Integer getJ() {
        return j;
    }

}
