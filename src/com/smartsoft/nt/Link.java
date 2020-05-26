/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.nt;
import com.smartsoft.modules.Sensor;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Link {
    
    public boolean isLeader;
    public Sensor Sensor;
    public Link Next; 
    public int ID;
/**
 * 
 * @return un boleano verdadero si un nodo es un nodo lider y un falso si no lo es
 */
    public boolean isIsLeader() {
        return isLeader;
    }
/**
 * 
 * @return Regresa la información del sensor
 */
    public Sensor getSensor() {
        return Sensor;
    }
/**
 * 
 * @return Regresa la liga siguiente del nodo
 */
    public Link getNext() {
        return Next;
    }
/**
 * 
 * @return Regresa el identificador del sensor
 */
    public int getID() {
        if (Sensor != null) return Sensor.getID();
        else return -1;
    }
/**
 * 
 * @param isLeader Configura un boleano verdadero si un nodo es un nodo lider y un falso si no lo es
 */
    public void setIsLeader(boolean isLeader) {
        this.isLeader = isLeader;
    }
/**
 * 
 * @param Sensor Coloca la información del sensor
 */
    public void setSensor(Sensor Sensor) {
        this.Sensor = Sensor;
    }
/**
 * 
 * @param Next Coloca la información de la liga siguiente del nodo sensor
 */
    public void setNext(Link Next) {
        this.Next = Next;
    }
/**
 * 
 * @param ID Coloca el identificador del borde
 */
    public void setID(int ID) {
        this.ID = ID;
    }  
    
}
