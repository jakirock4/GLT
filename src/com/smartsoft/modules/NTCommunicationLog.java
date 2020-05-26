/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.modules;
import java.sql.Timestamp;


/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class NTCommunicationLog {
    
    public int SensorID;
    public Timestamp Time;
    public double UsedEnergy_Nanojoule; // La energía utilizada para la operación actual
    public String Message;
    public double UsedEnergy_Joule; // La energía utilizada para la operación actual
    public double RemaimBatteryEnergy_Joule; // la energía restante de la batería
    public double Distance_M;
    public boolean IsInsert; // si se envio la operación
    public boolean IsDelete;
/**
 * 
 * @return Regresa el identificador del sensor
 */
    public int getSensorID() {
        return SensorID;
    }
/**
 * 
 * @return Regresa la fecha del registro del arbol de expansión jerárquico 
 */
    public Timestamp getTime() {
        return Time;
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
        double _e9 = 1000000000; // 1*e^-9
        double _ONE = 1;
        double oNE_DIVIDE_e9 = _ONE / _e9;
        double re = UsedEnergy_Nanojoule * oNE_DIVIDE_e9;
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
 * @return Regresa la distancia recorrida por el objeto
 */
    public double getDistance_M() {
        return Distance_M;
    }
/**
 * 
 * @return Regresa un verdadero si fue insertado el mensaje al árbol de Notificaciones y un falso si no lo fue
 */
    public boolean getIsInsert() {
        return IsInsert;
    }
/**
 * 
 * @return Regresa un verdadero si fue borrado el mensaje al árbol de Notificaciones y un falso si no lo fue
 */
    public boolean getIsDelete() {
        return IsDelete;
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
 * @param Time  Coloca la fecha de la operación realizada
 */
    public void setTime(Timestamp Time) {
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
 * @param UsedEnergy_Joule  Coloca la energia usada en Joule 
 */
    public void setUsedEnergy_Joule(double UsedEnergy_Joule) {
        this.UsedEnergy_Joule = UsedEnergy_Joule;
    }
/**
 * 
 * @param Distance_M Coloca la tistancia entre dos sensores
 */
    public void setDistance_M(double Distance_M) {
        this.Distance_M = Distance_M;
    }
/**
 * 
 * @param IsInsert Coloca un verdadero si fue insertado el mensaje al árbol de Notificaciones y un falso si no lo fue
 */
    public void setIsInsert(boolean IsInsert) {
        this.IsInsert = IsInsert;
    }
/**
 * 
 * @param IsDelete Coloca un verdadero si fue borrado el mensaje en el árbol de Notificaciones y un falso si no lo fue
 */
    public void setIsDelete(boolean IsDelete) {
        this.IsDelete = IsDelete;
    }

    public void setRemaimBatteryEnergy_Joule(double RemaimBatteryEnergy_Joule) {
        this.RemaimBatteryEnergy_Joule = RemaimBatteryEnergy_Joule;
    }
}
