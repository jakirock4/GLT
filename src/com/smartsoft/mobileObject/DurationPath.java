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
public class DurationPath {
   
    public int ID;
    public int start;
    public int End;
/**
 * 
 * @return  Regresa el ID
 */
    public int getID() {
        return ID;
    }
/**
 * 
 * @return Regresa Start (inicio)
 */
    public int getStart() {
        return start;
    }
/**
 * 
 * @return Regresa End (fin)
 */
    public int getEnd() {
        return End;
    }
/**
 * 
 * @param ID asignación de ID
 */
    public void setID(int ID) {
        this.ID = ID;
    }
/**
 * 
 * @param start Asignacion de Start (Inicio)
 */
    public void setStart(int start) {
        this.start = start;
    }
/**
 * 
 * @param End Asignacion de End (Fin)
 */
    public void setEnd(int End) {
        this.End = End;
    }
    
    
}
