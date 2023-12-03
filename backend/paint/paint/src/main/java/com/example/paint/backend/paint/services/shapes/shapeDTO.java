package com.example.paint.backend.paint.services.shapes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
public class shapeDTO {
    public double x ;
    public double y ;
    public String id;
    public String fill;
    public String name ;
    public String stroke;
    public double strokeWidth;
    public double rotation;
    public boolean draggable ;
    public double radius ; // circle - triangle - poly
    public int sides ; // for any shape above 3 sides
    public double []points; //line
    public String lineCap ; // line
    public String lineJoin ; // line
    public double radiusX; // ellipse
    public double radiusY; // ellipse
    public double width ; // rectangle - square
    public double height ; //rectangle - square


}
