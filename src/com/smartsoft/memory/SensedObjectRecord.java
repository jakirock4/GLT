/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.memory;

import java.util.ArrayList;
import com.smartsoft.mobileObject.MobileObject;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 * Registro de detección de objetos
 */
public class SensedObjectRecord {
    
    public int ID;
    public MobileObject MobileObject;
    public ArrayList<Message> MessageTable = new ArrayList<>();
    public boolean IsReported;
/**
 * 
 * @return Regresa el identificador del registro
 */
    public int getID() {
        return ID;
    }
/**
 * 
 * @return Regresa el Objeto en movimiento
 */
    public MobileObject getMobileObject() {
        return MobileObject;
    }
/**
 * 
 * @return Regresa un boleano verdadero si el objeto fue reportado de lo contrario un falso si no lo fue
 */
    public boolean getIsReported() {
        return allSent();
    }
    
    /**
     * 
     * @return Regresa una lista de los mensaje sensados
     */
    public ArrayList<Message> getMessageTable() {
        return MessageTable;
    }
    
    
/**
 * 
 * @param ID Variable del identificador 
 */
    public void setID(int ID) {
        this.ID = ID;
    }
/**
 * 
 * @param MobileObject Variable de la información del objeto en movimiento
 */
    public void setMobileObject(MobileObject MobileObject) {
        this.MobileObject = MobileObject;
    }

    /**
     * 
     * @param MessageTable Lista de mensajes sensados
     */
    public void setMessageTable(ArrayList<Message> MessageTable) {
        this.MessageTable = MessageTable;
    }
    
    public void messageTableAdd(Message message) {
        this.MessageTable.add(message);
    }

    /**
     * 
     * @param IsReported Boleando si el mensaje fue reportado
     */
    public void setIsReported(boolean IsReported) {
        this.IsReported = IsReported;
    }
    
 /**
  * 
  * @return  Regresa un boleano verdadero si todos los mensajes del registro de objetos sensados fueron enviados y un falso si no lo fue
  */   
    private boolean allSent()
      {
          boolean yes = true;
          for (Message m: MessageTable)
          {
              if (m.getIsSent()==false) { yes = false; break; }
          }
          return yes;
      }
    
}
