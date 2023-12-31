package com.example.paint.backend.paint.controler;


import com.example.paint.backend.paint.services.PaintService;
import com.example.paint.backend.paint.services.Save;
import com.example.paint.backend.paint.services.shapes.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin("*")
@RequestMapping("/paint")
public class control {
    /* 
    @PostMapping("/create")
    public shape createShape(@RequestBody shapeDTO dto){
        ShapeFactory factory = new ShapeFactory() ;
        System.out.print("arrived");
        return factory.createShape(dto) ;
    }
    */
    @Autowired
    private PaintService paintService;

    @PostMapping("/create")
    public ResponseEntity<Object> createShape(@RequestBody shapeDTO dto) {
        try {
            System.out.println("arrivee");
            ShapeFactory factory = new ShapeFactory();
            shape obj = factory.createShape(dto);
            paintService.addshape(obj);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @DeleteMapping("/remove/{shapeId}")
    public ResponseEntity<List<shape>> removeShape(@PathVariable String shapeId) {
        try {

            return ResponseEntity.ok(paintService.removeshape(shapeId));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/clearAll")
    public ResponseEntity<String> clearAllShapes() {
        try {
            paintService.clearAllshapes();
            return ResponseEntity.ok("All shapes cleared successfully");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateShape(@RequestBody shapeDTO dto) {
        try {
            ShapeFactory factory = new ShapeFactory();
            shape updatedShape = factory.createShape(dto);
            paintService.updateshape(updatedShape);
            return ResponseEntity.ok(updatedShape);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    /*
    @PostMapping("/undo")
    public ResponseEntity<String> undo() {
        try {
            paintService.undo();
            return ResponseEntity.ok("Undo operation successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PostMapping("/redo")
    public ResponseEntity<String> redo() {
        try {
            paintService.redo();
            return ResponseEntity.ok("Redo operation successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    */
    @PostMapping("/clone/{idOld}/{idNew}")
    public ResponseEntity<shape> clone(@PathVariable String idOld , @PathVariable String idNew ){
        System.out.println("arriave");
        try {
            shape s = paintService.getShapeById(idOld).clone(idNew);
            paintService.addshape(s);
            return ResponseEntity.ok(s);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/undo")
    public ResponseEntity<List<shape>> undo() {
       /// System.out.println("undooooooooo");
        try {
            List<shape> result = paintService.undo();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/redo")
    public ResponseEntity<List<shape>> redo() {
        try {
            List<shape> result = paintService.redo();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
     
    @GetMapping("/printShapeStack")
    public ResponseEntity<String> printShapeStack() {
        try {
            paintService.printShapeStack();
            return ResponseEntity.ok("Printing shapeStack");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam String path ,@RequestParam String idCounter  ) {
        System.out.println("sent");
        try {
            System.out.println("arrive");
            return ResponseEntity.ok(paintService.saveFactory( path, idCounter));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save shapes ");
        }
    }

    @PostMapping("/load")
    public ResponseEntity<Save> load(@RequestParam String path){
        try {
            System.out.println("path : ");
            System.out.println(path);
            Save loadedSave = paintService.loadFactory(path);
            if (loadedSave != null) {
                return ResponseEntity.ok(loadedSave);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
