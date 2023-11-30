package com.example.paint.backend.paint.services.shapes;

public class Square extends shape{
    private double width ;
    private double height ;
    public Square(shapeDTO s){
        super(s);
        this.height = s.height ;
        this.width = s.width ;
    }
    public Square(){}

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

}
