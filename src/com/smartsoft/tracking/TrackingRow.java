/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.tracking;

import com.smartsoft.mobileObject.MobileObject;
import com.smartsoft.modules.Sensor;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class TrackingRow {
    
    public MobileObject Object;
    public Sensor PoxyNode;
/**
 * 
 * @return retorno de objeto
 */
    public MobileObject getObject() {
        return Object;
    }
/**
 * 
 * @return retorno posición x y y de nodo
 */
    public Sensor getPoxyNode() {
        return PoxyNode;
    }
/**
 * 
 * @param Object Información del objeto en movimiento
 */
    public void setObject(MobileObject Object) {
        this.Object = Object;
    }
/**
 * 
 * @param PoxyNode  Sensor
 */
    public void setPoxyNode(Sensor PoxyNode) {
        this.PoxyNode = PoxyNode;
    }
    
}
