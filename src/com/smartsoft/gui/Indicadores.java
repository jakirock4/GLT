/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alex
 */
public class Indicadores extends Canvas {
    
    private Color lightBlue= new Color(118,195,230);
    private Color rosa= new Color(235,160,181);
    private Color rojo= new Color(255,38,0);
    
    
    public Indicadores() {
        setBackground(Color.WHITE);
        setSize(1000, 360);
    }
    
    @Override
    public void paint(Graphics g){
        
        g.setColor(Color.BLACK);
        g.drawString("ACTIVO", 700, 60); 
        g.setColor(this.rosa);
        g.fillOval(630, 30, 50, 50);
        
        g.setColor(Color.BLACK);
        g.drawString("INACTIVO", 700, 120); 
        g.setColor(this.rojo);        
        g.fillOval(630, 90, 50, 50);
        
        g.setColor(Color.BLACK);
        g.drawString("REPOSO", 700, 180); 
        g.setColor(this.lightBlue);        
        g.fillOval(630, 150, 50, 50);
    }
}
