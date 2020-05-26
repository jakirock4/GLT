/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.routing.Packages;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class HSTPacket {
    
    
    public int SourceNode; // No se puede cambiar
    public String RoutingPath;
    public int HopsCount;// Se puede cambiar
    public boolean isDelivered;
    public String MessageContent;
    public double Delay;
    
    /**
     * 
     * @return Regresa el identificadoe del nodo fuente.
     */
    public int getSourceNode() {
        return SourceNode;
    }
    /**
     * 
     * @return Regresa la ruta del routing
     */
    public String getRoutingPath() {
        return RoutingPath;
    }
    /**
     * 
     * @return regresa el conteno de saltos
     */
    public int getHopsCount() {
        return HopsCount;
    }
    /**
     * 
     * @return Regresa un boleano verdadero si fue entregado el paquete y un falso si no fue entregado
     */
    public boolean getIsDelivered() {
        return isDelivered;
    }
    /**
     * 
     * @return retorno de contenido de mensajes
     */
    public String getMessageContent() {
        return MessageContent;
    }
    /**
     * 
     * @return Regresa el retardo del mensaje
     */
    public double getDelay() {
        return Delay;
    }
    /**
     * 
     * @param SourceNode  Sensor remitente del mensaje
     */
    public void setSourceNode(int SourceNode) {
        this.SourceNode = SourceNode;
    }
    /**
     * 
     * @param RoutingPath Ruta del routing
     */
    public void setRoutingPath(String RoutingPath) {
        this.RoutingPath = RoutingPath;
    }
    /**
     * 
     * @param HopsCount Conteo de saltos
     */
    public void setHopsCount(int HopsCount) {
        this.HopsCount = HopsCount;
    }
    /**
     * 
     * @param isDelivered boleano verdadero si fue entregado el paquete y un falso si no fue entregado
     */
    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }
    /**
     * 
     * @param MessageContent Contenido del mensaje
     */
    public void setMessageContent(String MessageContent) {
        this.MessageContent = MessageContent;
    }
    /**
     * 
     * @param Delay Retardo del paquete
     */
    public void setDelay(double Delay) {
        this.Delay = Delay;
    }
    
}
