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
public class SensoreBasicsDetails {
    
    private String  NodeID;
    private int Pox;
    private int Poy;
    private int R;
    private Double BatteryCapacity; 
    private String RealLocation; // real location
    private String CenterLocation; // center location
    private String OverlappingNodes;
    private String ParentID;
    private String Children;
    private String RoutingPath; 

    public String getNodeID() {
        return NodeID;
    }

    public int getPox() {
        return Pox;
    }

    public int getPoy() {
        return Poy;
    }

    public int getR() {
        return R;
    }

    public Double getBatteryCapacity() {
        return BatteryCapacity;
    }

    public String getRealLocation() {
        return RealLocation;
    }

    public String getCenterLocation() {
        return CenterLocation;
    }

    public String getOverlappingNodes() {
        return OverlappingNodes;
    }

    public String getParentID() {
        return ParentID;
    }

    public String getChildren() {
        return Children;
    }

    public String getRoutingPath() {
        return RoutingPath;
    }

    public void setNodeID(String NodeID) {
        this.NodeID = NodeID;
    }

    public void setPox(int Pox) {
        this.Pox = Pox;
    }

    public void setPoy(int Poy) {
        this.Poy = Poy;
    }

    public void setR(int R) {
        this.R = R;
    }

    public void setBatteryCapacity(Double BatteryCapacity) {
        this.BatteryCapacity = BatteryCapacity;
    }

    public void setRealLocation(String RealLocation) {
        this.RealLocation = RealLocation;
    }

    public void setCenterLocation(String CenterLocation) {
        this.CenterLocation = CenterLocation;
    }

    public void setOverlappingNodes(String OverlappingNodes) {
        this.OverlappingNodes = OverlappingNodes;
    }

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }

    public void setChildren(String Children) {
        this.Children = Children;
    }

    public void setRoutingPath(String RoutingPath) {
        this.RoutingPath = RoutingPath;
    }
    
}
