package model;

public enum Gcode {
    M05,  //Spindle stop
    M30,  //End of program
    G00,  //Rapid Positioning
    G01,  //linear interpolation
    G02,  //circular clockwise
    G03,  //circular counterclockwise
    G17,  // xy plane
    G20,  //inches
    G21,  //millimeters
    G28   //return home
}
