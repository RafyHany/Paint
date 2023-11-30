package com.example.paint.backend.paint.services.shapes;

public class Line extends shape{
    private double []points;
    private String lineCap ;
    private String lineJoin ;
    public Line (shapeDTO l) {
        super(l);
        this.points = l.points ;
        this.lineCap = l.lineCap ;
        this.lineJoin = l.lineJoin ;
    }
    public Line () {}

    public double[] getPoints() {
        return points;
    }

    public String getLineCap() {
        return lineCap;
    }

    public String getLineJoin() {
        return lineJoin;
    }

    public void setLineCap(String lineCap) {
        this.lineCap = lineCap;
    }

    public void setLineJoin(String lineJoin) {
        this.lineJoin = lineJoin;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }

    public void drawShape() {

    }
}
