/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.modules;

import java.util.ArrayList;
//import com.smartsoft.modules.Sensor;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class SensorsGroup {
    
    public ArrayList<Sensor> Sensors = new ArrayList<>(); //cada grupo tiene un miembro de sensores
    public int ID;
    
    public SensorsGroup(){}
/**
 * 
 * @param GID Identificador del grupo de sensores
 */    
    public SensorsGroup(int GID)
    {
        ID = GID;
    }
/**
 * 
 * @return Regresa el Identificador del grupo de sensores
 */
    public int getID() {
        return ID;
    }
/**
 * 
 * @param ID Coloca el Identificador del grupo de sensores
 */
    public void setID(int ID) {
        this.ID = ID;
    }
    
}
