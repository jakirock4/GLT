/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

import com.smartsoft.mobileObject.Point;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
class DibujoTrayectory extends Canvas  
{  
    
    private ArrayList<Point> Trajectory;
    
    public DibujoTrayectory(ArrayList<Point> Trajectory) {  
        this.Trajectory = Trajectory;
        setBackground (Color.WHITE);  
        setSize(500, 600);  
    }  
    
    public void paint(Graphics g){  
        g.setColor(Color.BLACK);  
        
        try {
            if(!this.Trajectory.isEmpty()){
                for(Point point : this.Trajectory){
                    g.fillOval(point.getX(), point.getY(), 3, 3);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al dibujar la trayectoria: " + e);
        }
    }  
}    
