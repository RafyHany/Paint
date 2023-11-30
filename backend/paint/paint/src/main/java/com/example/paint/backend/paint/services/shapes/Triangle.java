package com.example.paint.backend.paint.services.shapes;

public class Triangle extends shape{ // this class can also be for pentagon and hexagon according to #of sides
    private double radius ;
    private int sides ;
    public Triangle (shapeDTO t){
        super(t);
        this.radius = t.radius ;
        this.sides = t.sides ;
    }
    public Triangle(){}

    public double getRadius() {
        return radius;
    }

    public int getSides() {
        return sides;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }
}
