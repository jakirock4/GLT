/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.modules;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class HSTCommunicationLog {
    
    public int SensorID;
    public Date Time;
    public double UsedEnergy_Nanojoule; // la energía utilizada para la operación actual
    public String Message;
    public String Operation;
    public double UsedEnergy_Joule; // la energía utilizada para la operación actual
    public double RemaimBatteryEnergy_Joule; // la energía restante de la batería
    public double Distance_M;
    
    public boolean IsSend; // si se envio la operación
    public boolean IsReceive;
/**
 * 
 * @return Regresa el identificador del sensor
 */
    public int getSensorID() {
        return SensorID;
    }
/**
 * 
 * @return Regresa la fecha del registro del arbol de expansión jerarquico 
 */
    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(Time);
    }
/**
 * 
 * @return Regresa la energía utilizada en Nano joule
 */
    public double getUsedEnergy_Nanojoule() {
        return UsedEnergy_Nanojoule;
    }
/**
 * 
 * @return Regresa el mensaje
 */
    public String getMessage() {
        return Message;
    }
/**
 * 
 * @return Regresa la energía utilizada en Joule
 */
    public double getUsedEnergy_Joule() {
        DecimalFormat df = new DecimalFormat("###.#");
        DecimalFormat df2 = new DecimalFormat("#.#######");

        double _e9 = 1000000000; // 1*e^-9
        double _ONE = 1;
        double oNE_DIVIDE_e9 = _ONE / _e9;
        double re = Double.parseDouble(df.format(UsedEnergy_Nanojoule)) * oNE_DIVIDE_e9;
        //System.out.println("Nano: " + Double.parseDouble(df.format(UsedEnergy_Nanojoule)));
        //System.out.println("Joule: " + df2.format(re));
        return re;
    }
    
/**
 * 
 * @return Regresa la energía restante de la batería en Joule
 */
    public double getRemaimBatteryEnergy_Joule() {
        return RemaimBatteryEnergy_Joule;
    }
/**
 * 
 * @return Regresa la distancia recorridad por el objeto
 */
    public double getDistance_M() {
        return Distance_M;
    }
/**
 * 
 * @return Regresa la operación realizada por el sensor
 */
    public String getOperation() {
        return Operation;
    }
/**
 * 
 * @return Regresa un verdadero si fue enviado el mensaje al arbol de expansión jerarquico y un falso si no lo fue
 */
    public boolean isIsSend() {
        return IsSend;
    }
/**
 * 
 * @return Regresa un verdadero si fue recibido el mensaje al arbol de expansión jerarquico y un falso si no lo fue
 */
    public boolean isIsReceive() {
        return IsReceive;
    }
/**
 * 
 * @param SensorID Colca el identificador del sensor
 */
    public void setSensorID(int SensorID) {
        this.SensorID = SensorID;
    }
/**
 * 
 * @param Time Coloca la fecha de la operación realizada
 */
    public void setTime(Date Time) {
        this.Time = Time;
    }
/**
 * 
 * @param UsedEnergy_Nanojoule Coloca la energía utilizada en Nano joule
 */
    public void setUsedEnergy_Nanojoule(double UsedEnergy_Nanojoule) {
        this.UsedEnergy_Nanojoule = UsedEnergy_Nanojoule;
    }
/**
 * 
 * @param Message Coloca el mensaje
 */
    public void setMessage(String Message) {
        this.Message = Message;
    }
/**
 * 
 * @param UsedEnergy_Joule Coloca la energia usada en Joule
 */
    public void setUsedEnergy_Joule(double UsedEnergy_Joule) {
        this.UsedEnergy_Joule = UsedEnergy_Joule;
    }
/**
 * 
 * @param Distance_M Coloca la distancia entre dos sensores
 */
    public void setDistance_M(double Distance_M) {
        this.Distance_M = Distance_M;
    }
/**
 * 
 * @param Operation Coloca la operación realizada por parte del nodo sensor
 */
    public void setOperation(String Operation) {
        this.Operation = Operation;
    }
/**
 * 
 * @param IsSend coloca un boleando Verdadero si fue enviado el mensaje y un Falso si no lo fue
 */
    public void setIsSend(boolean IsSend) {
        this.IsSend = IsSend;
    }
/**
 * 
 * @param IsReceive Coloca un verdadero si fue recibiddo el mensaje al arbol de expansión jerarquico y un falso si no lo fue
 */
    public void setIsReceive(boolean IsReceive) {
        this.IsReceive = IsReceive;
    }   

    public void setRemaimBatteryEnergy_Joule(double RemaimBatteryEnergy_Joule) {
        this.RemaimBatteryEnergy_Joule = RemaimBatteryEnergy_Joule;
    }
}
