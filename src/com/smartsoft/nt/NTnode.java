/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.nt;

import java.util.ArrayList;
import com.smartsoft.modules.Sensor;
import com.smartsoft.comunicationsMessagesFormate.NotificationMessage;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class NTnode {
    
    public Sensor Sensor;
    public ArrayList<NTnode> _NotificationChildern = new ArrayList<>();
    public ArrayList<NTnode> _MyNotificationNodes = new ArrayList<>();  
    public ArrayList<NTnode> ChildrenR = new ArrayList<>();
    public ArrayList<NTnode> ChildrenN = new ArrayList<>();;
    public NTnode ParentN; // para las notificaciones
    public NTnode ParentR; // para el ruteo.
    public int TreeLevel;// para el ruteo.
    public int ID;
    public int NotificationChildernCount;
    public int ChildernRCount;
    
    public NTnode(){}
    
    public NTnode(int ID){
        this.ID = ID;
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
 * @return Regresa la información del nodo sensor padre de notificaciones
 */
    public NTnode getParentN() {
        return ParentN;
    }
/**
 * 
 * @return Regresa la información del nodo sensor padre de ruteo
 */
    public NTnode getParentR() {
        return ParentR;
    }
/**
 * 
 * @return Regresa el nivel del arbol de notificaciones
 */
    public int getTreeLevel() {
        return TreeLevel;
    }
/**
 * 
 * @return Regresa el identificador del sensor
 */
    public int getID() {
        //if (Sensor != null) return Sensor.getID();
        //else return -1;
        return this.Sensor.getID();
    }
/**
 * 
 * @return Regresa el total de notificaciones para el nodo sensor hijo
 */
    public int getNotificationChildernCount() {
        return _NotificationChildern.size();
    }
/**
 * 
 * @return Regresa el total de rutas para el nodo sensor hijo
 */
    public int getChildernRCount() {
        return ChildrenR.size();
    }
/**
 * 
 * @return Regresa una lista de nodos en el arbol de notificaciones 
 */
    public ArrayList<NTnode> getChildernN() {
        //System.out.println("_NotificationChildern: " + _NotificationChildern.size());
        return _NotificationChildern;
    }
/**
 * 
 * @return Regresa el nodo en el arbol de notificaciones 
 */    
    public ArrayList<NTnode> GetMyNotificationNodes()
    {
        getMyNotificationNodes();
        return _MyNotificationNodes; 
    }      
    private void getMyNotificationNodes()
    {
        GetMyNOTnODES(this.Sensor.getMyNTnode());
    }
/**
 * 
 * @param parent Nodo sensor padre en el árbol de notificaciones
 */
    private void GetMyNOTnODES(NTnode parent)
    {
        _MyNotificationNodes.add(parent);
        //System.out.println("_MyNotificationNodes " + _MyNotificationNodes.size());
        for(NTnode child : parent.getChildernN())
        {
            GetMyNOTnODES(child);
        }
    }

    public void setSensor(Sensor Sensor) {
        this.Sensor = Sensor;
    }

    public void setChildrenR(ArrayList<NTnode> ChildrenR) {
        this.ChildrenR = ChildrenR;
    }

    public void setChildrenN(ArrayList<NTnode> ChildrenN) {
        this.ChildrenN = ChildrenN;
    }

    public void setParentN(NTnode ParentN) {
        this.ParentN = ParentN;
    }

    public void setParentR(NTnode ParentR) {
        this.ParentR = ParentR;
    }

    public void setTreeLevel(int TreeLevel) {
        this.TreeLevel = TreeLevel;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNotificationChildernCount(int NotificationChildernCount) {
        this.NotificationChildernCount = NotificationChildernCount;
    }

    public void setChildernRCount(int ChildernRCount) {
        this.ChildernRCount = ChildernRCount;
    }
    
    
    
/**
 *  agregando hijo al árbol de notificaciones
 * @param childNode Nodo hijo en el árbol de notificaciones 
 */    
    public void AddChildNotification( NTnode childNode) 
    {
        //System.out.println("Sensor: " + childNode.getSensor().getID());
        _NotificationChildern.add(childNode);
        childNode.ParentN = this;
    }
/**
 *  eliminando hijo de árbol de notificaciones
 * @param childNode Nodo sensor en el árbol de notificaciones 
 */    
    public void RemoveNotificationChilde(NTnode childNode)
    {
        _NotificationChildern.remove(childNode);
        childNode.ParentN = null;
    }

/**
 * 
 * @param Message mensaje de notificación
 */
    public void BroadCastInsertMessage( NotificationMessage Message)
    {
        if(NotificationChildernCount>0)
        {
            for(NTnode childNode : ChildrenN)
            {
                childNode.Sensor.NMT.add(Message);
            }
        }
    }
/**
 * 
 * @param Message mensaje de notificación
 */
    public void BroadCastDeleteMessage(NotificationMessage Message)
    {
        if (NotificationChildernCount > 0)
        {
            for(NTnode childNode : ChildrenN)
            {
                childNode.Sensor.NMT.remove(Message);
            }
        }
    }
    
}
