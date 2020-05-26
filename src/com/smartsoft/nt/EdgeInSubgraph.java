/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.nt;

import java.util.ArrayList;
//import com.smartsoft.nt.NTnode;
import com.smartsoft.graphx.Vertex;
//import com.smartsoft.modules.Sensor;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class EdgeInSubgraph {
    
    
    public int ID; // Identificador del borde
    public boolean wasVisited;
    public ArrayList<NTnode> ListofNodes = new ArrayList<>();
    public ArrayList<Vertex> vertexSet = new ArrayList<>();
    public String NS;
    
/**
 * 
 * @return Identificador del borde
 */
    public int getID() {
        return ID;
    }
/**
 * 
 * @return Regresa un boleano Verdadero si fue visitado el nodo y un falso si no lo fue
 */
    public boolean isWasVisited() {
        return wasVisited;
    }
/**
 * 
 * @return Regresa una cadena con todos los identificadores de los sensores de la lista de nodos
 */
    public String getNS() {
        String str = "";
        for(NTnode node : ListofNodes )
        {
            str += node.Sensor.getID() + ",";
        }
        return str;
    }
/**
 * 
 * @param ID Identificador del borde
 */
    public void setID(int ID) {
        this.ID = ID;
    }
/**
 * 
 * @param wasVisited configura un boleano Verdadero si fue visitado el nodo y un falso si no lo fue
 */
    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }
/**
 * 
 * @return Regresa una lista con los vertices de cada nodo
 */
    public ArrayList<Vertex> getVertexSet() {
        return vertexSet;
    }
/**
 * 
 * @param v Nuevo vertice de un nodo
 */    
    public void addVertex( Vertex v)
    {
        boolean re = false;
        for(Vertex u : vertexSet)
        {
            if (u.ID == v.ID) { re = true; break; }
        }
        if (!re) { vertexSet.add(v); }
    }
    
    
}