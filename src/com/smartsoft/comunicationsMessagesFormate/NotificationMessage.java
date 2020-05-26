/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.comunicationsMessagesFormate;

import com.smartsoft.modules.Sensor;
import com.smartsoft.mobileObject.MobileObject;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class NotificationMessage {
    
    
    public Notification NotificationType;
    public Sensor Source;
    public String Data;// message
    public MobileObject MobileObject;
    public Sensor FromSensor;
    public Sensor ToSensor;

    
/**
 * @return Objeto Notificacion
 */    
    public Notification getNotificationType() {
        return NotificationType;
    }
/**
 * @return un sensor
 */
    public Sensor getSource() {
        return Source;
    }
/**
 * @return un mensaje
 */
    public String getData() {
        return Data;
    }
/**
 * @return MobileObject
 */
    public MobileObject getMobileObject() {
        return MobileObject;
    }
/**
 * @return Desde donde se envio el mensaje
 */
    public Sensor getFromSensor() {
        return FromSensor;
    }
/**
 * @return para donde se envia el mensaje
 */
    public Sensor getToSensor() {
        return ToSensor;
    }
/**
 * 
 * @param NotificationType un objeto de la instancia notififcación
 */
    public void setNotificationType(Notification NotificationType) {
        this.NotificationType = NotificationType;
    }
/**
 * 
 * @param Source la fuente del sensor
 */
    public void setSource(Sensor Source) {
        this.Source = Source;
    }
/**
 * 
 * @param Data Variable del mensaje de la notificación
 */
    public void setData(String Data) {
        this.Data = Data;
    }
/**
 * 
 * @param MobileObject una instancia de la clase MobileObject
 */
    public void setMobileObject(MobileObject MobileObject) {
        this.MobileObject = MobileObject;
    }
/**
 * 
 * @param FromSensor Variable desde donde viene el mensaje
 */
    public void setFromSensor(Sensor FromSensor) {
        this.FromSensor = FromSensor;
    }
/**
 * 
 * @param ToSensor Variable hacia donde va dirigido el mensaje
 */
    public void setToSensor(Sensor ToSensor) {
        this.ToSensor = ToSensor;
    }
    
    
}
