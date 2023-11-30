package com.example.paint.backend.paint.controler;


import com.example.paint.backend.paint.services.shapes.ShapeFactory;
import com.example.paint.backend.paint.services.shapes.shape;
import com.example.paint.backend.paint.services.shapes.shapeDTO;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin("*")
@RequestMapping("/paint")
public class control {

    @PostMapping("/create")
    public shape createShape(@RequestBody shapeDTO dto){
        ShapeFactory factory = new ShapeFactory() ;
        System.out.print("arrived");

        return factory.createShape(dto) ;
    }

}
