package com.example.paint.backend.paint.services;
import org.springframework.stereotype.Service;
import com.example.paint.backend.paint.services.shapes.shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class PaintService {

    private static PaintService paintService = null;
    private PaintService() {
    }
    public static PaintService getInstance() {
        if (paintService == null) {
            paintService = new PaintService();
        }
        return paintService;
    }

    private Stack<List<shape>> shapeStack = new Stack<>();
    private Stack<List<shape>> redoStack = new Stack<>();


    public void addshape(shape shape) {
        List<shape> currentshapes = getCurrentState();
        currentshapes.add(shape);
        saveState(currentshapes);
    }


    public void removeshape(String shapeId) {
        List<shape> currentshapes = getCurrentState();
        currentshapes.removeIf(shape -> shape.getId().equals(shapeId));
        saveState(currentshapes);
    }


    public void clearAllshapes() {
        saveState(new ArrayList<>());
    }


    public void updateshape(shape updatedshape) {
        List<shape> currentshapes = getCurrentState();
        for (int i = 0; i < currentshapes.size(); i++) {
            shape shape = currentshapes.get(i);
            if (shape.getId().equals(updatedshape.getId())) {
                currentshapes.set(i, updatedshape);
                break;
            }
        }
        saveState(currentshapes);
    }

    public List<shape> getCurrentshapes() {
        return getCurrentState();
    }
    
    public List<shape> undo() {
        if (!shapeStack.isEmpty()) {
            redoStack.push(shapeStack.pop());
            return getCurrentState();
        }
        return null;
    }
    
    public List<shape> redo() {
        if (!redoStack.isEmpty()) {
            shapeStack.push(redoStack.pop());
            return getCurrentState();
        }
        return null;
    }

    private List<shape> getCurrentState() {
        if (!shapeStack.isEmpty()) {
            return new ArrayList<>(shapeStack.peek());
        } else {
            return new ArrayList<>();
        }
    }

    private void saveState(List<shape> shapes) {
        shapeStack.push(new ArrayList<>(shapes));
        redoStack.clear();
    }

    public void printShapeStack() {
        System.out.println("Printing shape Stack:");
        for (List<shape> shapes : shapeStack) {
            System.out.println("Stack Element:");
            for (shape s : shapes) {
                System.out.println("Shape: " + s.toString());
            }
        }
    }


}
