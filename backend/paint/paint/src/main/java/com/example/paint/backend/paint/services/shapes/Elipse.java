package com.example.paint.backend.paint.services.shapes;

public class Elipse extends shape{
    private double radiusX;
    private double radiusY;

    public Elipse(shapeDTO e){
        super(e);
        this.radiusX = e.radiusX ;
        this.radiusY = e.radiusY ;
    }
    public Elipse (){}

    public double getRadiusX() {
        return radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }
}
