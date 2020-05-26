/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.modules;

import com.smartsoft.mobileObject.Point;
import java.util.ArrayList;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class EdgeLine {
    
    private Sensor StartVertex;
    private Sensor EndVertex;
    private Line edg = new Line();

    public Sensor getStartVertex() {
        return StartVertex;
    }

    public Sensor getEndVertex() {
        return EndVertex;
    }

    public Line getEdg() {
        return edg;
    }

    public void setStartVertex(Sensor StartVertex) {
        this.StartVertex = StartVertex;
    }

    public void setEndVertex(Sensor EndVertex) {
        this.EndVertex = EndVertex;
    }

    public void setEdg(Line edg) {
        this.edg = edg;
    }
    
    public EdgeLine(){}
    
    public void DrawEdge()
    {
        edg.setX1(StartVertex.getCenterLocation().getX() + 25);
        edg.setY1(StartVertex.getCenterLocation().getY() + 25);
        edg.setX2(EndVertex.getCenterLocation().getX() + 25);
        edg.setY2(EndVertex.getCenterLocation().getY() + 25);
    }
    
}
