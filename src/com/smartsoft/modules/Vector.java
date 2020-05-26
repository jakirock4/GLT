/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.modules;

import java.util.ArrayList;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Vector {
    
    public String Items;
    private ArrayList<Sensor> Sensors = new ArrayList<>();// Los miembros de este vector
    public ArrayList<Vector> NeighborVectors = new ArrayList<>();
    public ArrayList<Vector> FilterdedVectors = new ArrayList<>();// Miemebros vecinos-vector
    public int ID;
    public ArrayList<Sensor> Members;

    public Vector() {}

    /**
    * 
    * @return Regresa una lista de sensores miembros al vector
    */  
    public ArrayList<Sensor> getMembers() {
        return this.Sensors;
    }
/**
 * 
 * @param _ID Coloca el identificador del vector
 */    
    public Vector(int _ID) 
    {
        ID = _ID;
    }
/**
 * 
 * @return Regresa el identificador del vector
 */
    public int getID() {
        return ID;
    }
    
    
/**
 * 
 * @param ID Coloca el identificador del vector
 */
    public void setID(int ID) {
        this.ID = ID;
    }
/**
 * 
 * @param sensor Información del sensor
 */
    public void Add(Sensor sensor) 
    {
        this.Sensors.add(sensor);
        Items = "";
        for(Sensor s : this.Sensors)
        {
            Items += s.getID() + ",";
        }
    }
/**
 * 
 * @param sensors  Lista de sensores para ser añadidos
 */    
    public void Add(ArrayList<Sensor> sensors) 
    {
        //Sensors.addRange(sensors);
        this.Sensors.addAll(sensors);
        Items = "";
        for (Sensor s : this.Sensors)
        {
            Items += s.getID() + ",";
        }
    }
/**
 * 
 */
    public void Clear() 
    { 
        this.Sensors.clear();
        NeighborVectors.clear();
        FilterdedVectors.clear();
    }
    
}
