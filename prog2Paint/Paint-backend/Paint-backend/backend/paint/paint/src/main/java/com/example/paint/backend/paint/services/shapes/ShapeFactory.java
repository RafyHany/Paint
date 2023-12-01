package com.example.paint.backend.paint.services.shapes;

public class ShapeFactory {
    public shape createShape(shapeDTO dto){

        switch (dto.name ){
            case "line":
                return new Line(dto) ;
            case "square":
                return new Square(dto) ;
            case "rectangle" :
                return new Rectangle(dto) ;
            case "ellipse" :
                return new Elipse(dto) ;
            case "circle" :
                return new Circle(dto) ;
            case "triangle" :
                return new Triangle(dto) ;
            case "pentagon" :
                return new Triangle(dto) ;
            case "hexagon" :
                return new Triangle(dto) ;
            default :
                return null ;
        }
    }
}
