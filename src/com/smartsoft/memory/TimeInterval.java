/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.memory;

import java.util.ArrayList;
//import com.smartsoft.memory.Message;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class TimeInterval {
    
    public int NodeID;
    public ArrayList<Message> Messages = new ArrayList<>();
/**
 * 
 * @return Regresa el identificador del nodo sensor
 */
    public int getNodeID() {
        return NodeID;
    }
/**
 * 
 * @param NodeID Coloca el identificador del nodo sensor
 */
    public void setNodeID(int NodeID) {
        this.NodeID = NodeID;
    }
    
}
