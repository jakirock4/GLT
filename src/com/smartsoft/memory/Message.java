/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.memory;

import com.smartsoft.mobileObject.Point;

/**
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 * Memoria para guardar los datos del objeto detectado
 * El sensor no significa observación o notificación del objeto a la estación de datos
 */
public class Message {
    
    private int ID;
    private boolean isSent;
    public String MessageContent;
    private int NodeID;
    private int ObjectID;
    private int Time;
    private Point ObjectLocation;
    
    private double X;
    private double Y;
/**
 * 
 * @return el identificador del mesaje
 */
    public int getID() {
        return ID;
    }
/**
 * 
 * @return Regresa si el mensaje fue enviado
 */
    public boolean getIsSent() {
        return isSent;
    }
/***
 * 
 * @return Regresa el contenido del mensaje
 */
    public String getMessageContent() {
        return MessageContent;
    }
/**
 * 
 * @return Regresa el identificador del nodo sensor del remitente
 */
    public int getNodeID() {
        return NodeID;
    }
/**
 * 
 * @return Regresa el identificador del objeto
 */
    public int getObjectID() {
        return ObjectID;
    }
/**
 * 
 * @return Regresa el tiempo
 */
    public int getTime() {
        return Time;
    }
/**
 * 
 * @return Regresa los puntos de la ubicación del objeto
 */
    public Point getObjectLocation() {
        return ObjectLocation;
    }
/**
 * 
 * @return Regresa la posición X
 */
    public double getX() {
        return ObjectLocation.getX();
    }
/**
 * 
 * @return Regresa la posición Y
 */
    public double getY() {
        return ObjectLocation.getY();
    }
/**
 * 
 * @param ID Identificador del mensaje
 */
    public void setID(int ID) {
        this.ID = ID;
    }
/**
 * 
 * @param isSent variable para saber si un mensaje fue enviado
 */
    public void setIsSent(boolean isSent) {
        this.isSent = isSent;
    }
/**
 * 
 * @param MessageContent Variable del contenido del mensaje
 */
    public void setMessageContent(String MessageContent) {
        this.MessageContent = MessageContent;
    }
/**
 * 
 * @param NodeID Identificador del nodo sensor remitente
 */
    public void setNodeID(int NodeID) {
        this.NodeID = NodeID;
    }
/**
 * 
 * @param ObjectID  Identificador del objeto
 */
    public void setObjectID(int ObjectID) {
        this.ObjectID = ObjectID;
    }
/**
 * 
 * @param Time Tiempo desde cuando se envio el mensaje
 */
    public void setTime(int Time) {
        this.Time = Time;
    }
/**
 * 
 * @param ObjectLocation Ubicación del objeto
 */
    public void setObjectLocation(Point ObjectLocation) {
        this.ObjectLocation = ObjectLocation;
    }
/**
 * 
 * @param X Posición X
 */
    public void setX(double X) {
        this.X = X;
    }
/**
 * 
 * @param Y Posición Y
 */
    public void setY(double Y) {
        this.Y = Y;
    }
    
}
