/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.modules;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class NotfPaths {
    
    private int NodeID;
    private String NotiPath;
    private double NTsize;
    
    public NotfPaths(){}
    
    public NotfPaths(int NodeID, double NTsize, String NotiPath){
        this.NodeID = NodeID;
        this.NTsize = NTsize;
        this.NotiPath = NotiPath;
    }

    public int getNodeID() {
        return NodeID;
    }

    public String getNotiPath() {
        return NotiPath;
    }

    public double getNTsize() {
        return NTsize;
    }

    public void setNodeID(int NodeID) {
        this.NodeID = NodeID;
    }

    public void setNotiPath(String NotiPath) {
        this.NotiPath = NotiPath;
    }

    public void setNTsize(double NTsize) {
        this.NTsize = NTsize;
    }
    
}
