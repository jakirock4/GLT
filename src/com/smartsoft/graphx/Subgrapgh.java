/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.graphx;

import java.util.ArrayList;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Subgrapgh {
    
    public ArrayList<Vertex> vertexSet;
    public ArrayList<Edge> Edges;
    
    /**
    * @return vertexSet Regresa la lista del conjunto de vertices
    */

    public ArrayList<Vertex> getVertexSet() {
        return vertexSet;
    }
/**
 * 
 * @return edges Regresa la lista de los bordes de los vertices
 */
    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> edges = new ArrayList<>();
            for(Vertex v: vertexSet)
        {
            edges.addAll(v.Edges);
        }
        return edges;
    }
/**
 * 
 * @param vertexSet setea una lista de vertices
 */
    public void setVertexSet(ArrayList<Vertex> vertexSet) {
        this.vertexSet = vertexSet;
    }
    
}
