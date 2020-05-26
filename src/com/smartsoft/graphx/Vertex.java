/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.graphx;

import java.util.ArrayList;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.SensorsGroup;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Vertex {
    
    public int ID;
    public Sensor SourceSensor;
    public ArrayList<Edge> Edges = new ArrayList<>();
    public SensorsGroup GroupOfSensors; // los grupos de sensor
    public boolean wasVisited;
    public String Ms;
    public boolean IsIsolatedVertex;
    public ArrayList<Vertex> AdjacentVertices = new ArrayList<>();
/**
 * 
 * @return ID Regresa el ID del Vertice
 */
    public int getID() {
        return ID;
    }
/**
 * 
 * @return SourceSensor Regresa el sensor fuente para el vertice
 */
    public Sensor getSourceSensor() {
        return SourceSensor;
    }
/**
 * 
 * @return Regresa un Grupo de sensores
 */
    public SensorsGroup getGroupOfSensors() {
        return GroupOfSensors;
    }
/**
 * 
 * @return Regresa un boleano si ya fue visitado el nodo
 */
    public boolean getWasVisited() {
        return wasVisited;
    }
/**
 * 
 * @return Regresa los ID de los sensores
 */
    public String getMs() {
        String s = "";
        if (GroupOfSensors!=null)
        {
            for(Sensor sen : GroupOfSensors.Sensors )
            {
                s += sen.getID() + ",";
            }
        }
        return s;
    }
/**
 * 
 * @return regresa un boleado si es un vértice aislado
 */
    public boolean getIsIsolatedVertex() {
        return IsIsolatedVertex;
    }
/**
 * 
 * @param EndVertex Vertice final del nodo
 */    
    public void addAdjacentVertex(Vertex EndVertex)
    {
        if(AdjacentVertices.isEmpty())
        {
            AdjacentVertices.add(EndVertex);
        }
        else
        {
            boolean isExistted = false;
            for(Vertex u : AdjacentVertices)
            {
                if(u.getID()==EndVertex.getID())
                {
                    isExistted = true;
                    break;
                }
            }

            if(!isExistted)
            {
                AdjacentVertices.add(EndVertex);
            }
        }
    }
    
}
