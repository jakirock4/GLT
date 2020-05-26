/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.graphx;

import com.smartsoft.modules.Sensor;
/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Edge {
    
    public Vertex startVertex;
    public Vertex EndVertex;
    public Sensor SourceSensor;
    public String LS;
    public Sensor LeadersSensor;

    public String getLS() {
        //return LeadersSensor.ID.toString();
        return Integer.toString(LeadersSensor.getID());
    }
   
/**
 * 
 * @return re Regresa el sensor lider
 */
    public Sensor getLeadersSensor() {
        Sensor re = null;
        for(Sensor ss : startVertex.GroupOfSensors.Sensors)
        {
            if (ss.getID() != SourceSensor.getID())
            {
                for(Sensor se : EndVertex.GroupOfSensors.Sensors)
                {
                    if (ss.getID() == se.getID())
                    {
                        re = ss;
                    }
                }
            }
        }
        return re;
    }
/**
 * 
 * @param startVertex setea el vertice de inicio
 */
    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }
/**
 * 
 * @param EndVertex setea el vertice final
 */
    public void setEndVertex(Vertex EndVertex) {
        this.EndVertex = EndVertex;
    }
/**
 * 
 * @param SourceSensor setea el sensor a evaluar
 */
    public void setSourceSensor(Sensor SourceSensor) {
        this.SourceSensor = SourceSensor;
    }
    
}
