package com.example.paint.backend.paint;

import java.util.ArrayList;
import java.util.List;
import com.example.paint.backend.paint.services.shapes.shape;

public class Save {
    
    List<shape> lastUpdate = new ArrayList<>();
    String idCounter = null;

    public void setIdCounter(String idCounter){
        this.idCounter = idCounter;
    }

    public void setLasUpdate(List<shape> lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public String getIdCounter(){
        return this.idCounter;
    }

    public List<shape> getLastUpdate(){
        return this.lastUpdate;
    }

}
