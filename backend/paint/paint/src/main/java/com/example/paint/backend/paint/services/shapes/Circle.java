package com.example.paint.backend.paint.services.shapes;

public class Circle extends shape {
    private double radius ;
    public Circle(shapeDTO c){
        super(c);
        this.radius = c.radius ;
    }
    public Circle() {}

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


    public void drawShape() {

    }
//    @Override
//    public Circle clone(){
//
//    }
}
