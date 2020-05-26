/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.mobileObject;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class VisualPoint {
    
    Point start;
    Point end;
    
    public VisualPoint(){}
    
    public VisualPoint(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
    
}
