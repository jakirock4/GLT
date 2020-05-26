/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.modules;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 */
public class TotalSensorLog {
    
   // gernal
    private int NodeID;
    private double ResidualEnergyJouls;
    private double ResidualEnergyPercentage;

    // used ENergys
    private double TotalUsedEnergyJouleForHST;
    private double TotalUsedEnergyJouleForNT;
    private double TotalUsedEnergyJoule;

    // send/recived operations.
    private int SendDataOperations;
    private int ReciveDataOperations;
    private int TotalOperations;

    // insert/ delete
    private int InsertNotifications; 
    private int DeleteNotifcations; 
    private int TotalNotifications;

    public int getNodeID() {
        return NodeID;
    }

    public double getResidualEnergyJouls() {
        return ResidualEnergyJouls;
    }

    public double getResidualEnergyPercentage() {
        return ResidualEnergyPercentage;
    }

    public double getTotalUsedEnergyJouleForHST() {
        return TotalUsedEnergyJouleForHST;
    }

    public double getTotalUsedEnergyJouleForNT() {
        return TotalUsedEnergyJouleForNT;
    }

    public double getTotalUsedEnergyJoule() {
        //return TotalUsedEnergyJoule;
        return TotalUsedEnergyJouleForHST + TotalUsedEnergyJouleForNT;
    }

    public int getSendDataOperations() {
        return SendDataOperations;
    }

    public int getReciveDataOperations() {
        return ReciveDataOperations;
    }

    public int getTotalOperations() {
        //return TotalOperations;
        return SendDataOperations + ReciveDataOperations;
    }

    public int getInsertNotifications() {
        return InsertNotifications;
    }

    public int getDeleteNotifcations() {
        return DeleteNotifcations;
    }

    public int getTotalNotifications() {
        //return TotalNotifications;
        return InsertNotifications + DeleteNotifcations;
    }

    public void setNodeID(int NodeID) {
        this.NodeID = NodeID;
    }

    public void setResidualEnergyJouls(double ResidualEnergyJouls) {
        this.ResidualEnergyJouls = ResidualEnergyJouls;
    }

    public void setResidualEnergyPercentage(double ResidualEnergyPercentage) {
        this.ResidualEnergyPercentage = ResidualEnergyPercentage;
    }

    public void setTotalUsedEnergyJouleForHST(double TotalUsedEnergyJouleForHST) {
        this.TotalUsedEnergyJouleForHST = TotalUsedEnergyJouleForHST;
    }

    public void setTotalUsedEnergyJouleForNT(double TotalUsedEnergyJouleForNT) {
        this.TotalUsedEnergyJouleForNT = TotalUsedEnergyJouleForNT;
    }

    public void setTotalUsedEnergyJoule(double TotalUsedEnergyJoule) {
        this.TotalUsedEnergyJoule = TotalUsedEnergyJoule;
    }

    public void setSendDataOperations(int SendDataOperations) {
        this.SendDataOperations = SendDataOperations;
    }

    public void setReciveDataOperations(int ReciveDataOperations) {
        this.ReciveDataOperations = ReciveDataOperations;
    }

    public void setTotalOperations(int TotalOperations) {
        this.TotalOperations = TotalOperations;
    }

    public void setInsertNotifications(int InsertNotifications) {
        this.InsertNotifications = InsertNotifications;
    }

    public void setDeleteNotifcations(int DeleteNotifcations) {
        this.DeleteNotifcations = DeleteNotifcations;
    }

    public void setTotalNotifications(int TotalNotifications) {
        this.TotalNotifications = TotalNotifications;
    }
    
}
