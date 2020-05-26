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
public class Objeto {
    
    private int id;
    private Point point;
    
    public Objeto(int id, Point point){
        this.id = id;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    
}
