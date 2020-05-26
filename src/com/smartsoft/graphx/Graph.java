/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.graphx;

import java.util.ArrayList;
import com.smartsoft.modules.SensorsGroup;
import com.smartsoft.modules.Sensor;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Graph {
    
    public ArrayList<SensorsGroup> MyGroups;
    private ArrayList<Vertex> vertexSet = new ArrayList<>();
    public Sensor SourceSensor;
    public Graph(Sensor _SourceSensor) 
    {
        SourceSensor=_SourceSensor;
        MyGroups = SourceSensor.MyGroups;
        BuildgetVertextSet();
        FindEdgesSet();
    }    
/**
 * @param GetVertexSet Una lista de los vertices de los nodos
 * @return vertexSet Regresa una lista de vertices del grupo de sensores
 */
    public ArrayList<Vertex> GetVertexSet() { return vertexSet; }
    private void BuildgetVertextSet()
    {
        int i = 0;
        for(SensorsGroup g: MyGroups)
        {
            Vertex ver = new Vertex();
            ver.SourceSensor = SourceSensor;
            ver.GroupOfSensors = g;
            ver.ID = i;
            ver.wasVisited = false;
            i++;
            vertexSet.add(ver);
        }
    }

    private void FindEdgesSet()
    {
        for(Vertex v: vertexSet) 
        {
            GetMyAdjVertexes(v);
        }
    }
/**
 * 
 * @param v Obtiene los vértices adyacentes
 */
    private void GetMyAdjVertexes(Vertex v) 
    {
        for(Vertex u: vertexSet)
        {
            if (v.ID != u.ID)
            {
                boolean YesAjacent = AreTwoVerticiesAdjacent(v, u);
                if (YesAjacent)
                {
                    v.addAdjacentVertex(u);// agrega a la lista un vertice de adyacencia
                    Edge e = new Edge();
                    e.SourceSensor = SourceSensor;
                    e.startVertex = v;
                    e.EndVertex = u;
                    v.Edges.add(e);
                }
            }
        }
    }

/**
 * Metodo para ver la adyacencia entre dos vertices
 * @param v Vertice 1
 * @param u Vertice 2
 * @return Retorna un boleano si es Adyacente
 */
    private boolean AreTwoVerticiesAdjacent(Vertex v, Vertex u)
    {
        boolean YesAjacent = false;
        for( Sensor sv: v.GroupOfSensors.Sensors)
        {
            if (sv.getID() != SourceSensor.getID())
            {
                for (Sensor su : u.GroupOfSensors.Sensors)
                {
                    if (su.getID() != SourceSensor.getID())
                    {
                        if (sv.getID() == su.getID())
                        {
                            YesAjacent = true;
                            break;
                        }
                    }
                }
            }
        }
        return YesAjacent;
    }
    
}
