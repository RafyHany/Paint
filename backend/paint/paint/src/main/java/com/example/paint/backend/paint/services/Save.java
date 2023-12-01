package com.example.paint.backend.paint.services;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.paint.backend.paint.services.shapes.shape;
public class Save {
    
    List<shape> lastUpdate = new ArrayList<>();
    String idCounter = null;

    public void setIdCounter(String idCounter){
        this.idCounter = idCounter;
    }

    public void setLastUpdate(List<shape> lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public String getIdCounter(){
        return this.idCounter;
    }

    public List<shape> getLastUpdate(){
        return this.lastUpdate;
    }

    public void saveToXML(String path) throws IOException {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)))) {
            encoder.writeObject(this);
        }
    }

    public static Save loadFromXML(String path) throws IOException {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)))) {
            return (Save) decoder.readObject();
        }
    }
}
