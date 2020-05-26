/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.delay;
import com.smartsoft.computations.Operations;
import com.smartsoft.modules.Sensor;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 * @see http://ns2.sourcearchive.com/documentation/2.35~RC4-1/threshold_8cc-source.html
 * @see https://en.wikipedia.org/wiki/Packet_transfer_delay
 * @see https://en.wikipedia.org/wiki/End-to-end_delay
 * End-to-end delay
 * El retraso de extremo a extremo o de un solo sentido se refiere al tiempo 
 * que se tarda en transmitir un paquete a través de una red desde el origen hasta el destino.
 * Es un término común en la supervisión de la red IP y difiere del tiempo de ida y vuelta (RTT)
 */
public class DelayModel {
    
    private double PackageSize = 1024;
    private double TransmissionRate; // transmitir en bits por segundo
    private double Distance;
    private double SpeedOfLight; // m/s.
    private double Delay;

/**
 * 
 * @return regresa el tamaño del paquete
 */    
    public double getPackageSize() {
        return PackageSize;
    }
/**
 * 
 * @return regresa la Velocidad de transmision
 */
    public double getTransmissionRate() {
        return TransmissionRate;
    }
/**
 * 
 * @return regresa la distancia
 */
    public double getDistance() {
        return Distance;
    }

    /**
    * 
    * @return regresa la Velocidad de la luz
    */
    public double getSpeedOfLight() {
        return SpeedOfLight;
    }
/**
 * 
 * @param PackageSize el número del tamaño del paquete
 */
    public void setPackageSize(double PackageSize) {
        this.PackageSize = PackageSize;
    }
/**
 * 
 * @param TransmissionRate el número de la Velocidad de transmision
 */
    public void setTransmissionRate(double TransmissionRate) {
        this.TransmissionRate = TransmissionRate;
    }
/**
 * 
 * @param Distance distancia
 */
    public void setDistance(double Distance) {
        this.Distance = Distance;
    }
/**
 * 
 * @param SpeedOfLight la velocidad de la luz
 */
    public void setSpeedOfLight(double SpeedOfLight) {
        this.SpeedOfLight = SpeedOfLight;
    }
/**
 * 
 * @param tx sensor
 * @param rc sensor 
 */    
    public DelayModel( Sensor tx, Sensor rc )
       {
           TransmissionRate = 2 * 1000000;////2Mbps 100 × 10^6 bit/s , //https://en.wikipedia.org/wiki/Transmission_time
           SpeedOfLight = 299792458;//https://en.wikipedia.org/wiki/Speed_of_light
           Distance = Operations.DistanceBetweenTwoSensors(tx, rc);
       }

       /*
       public DelayModel(Sensor tx, MobileSink ms)
       {
           PackageSize = tx.DataRoutingPackageSize;
           TransmissionRate = 2 * 1000000;////2Mbps 100 × 10^6 bit/s , //https://en.wikipedia.org/wiki/Transmission_time
           SpeedOfLight = 299792458;//https://en.wikipedia.org/wiki/Speed_of_light
           Distance = Operations.DistanceBetweenTwoPoints(tx.CenterLocation, ms.CenterLocation);

       }*/
/**
 * @see https://en.wikipedia.org/wiki/Transmission_delay
 * En una red basada en la conmutación de paquetes, la demora de transmisión 
 * (o demora de almacenamiento y reenvío, también conocida como demora de paquetización) 
 * es la cantidad de tiempo requerida para insertar todos los bits del paquete en el cable. 
 * En otras palabras, este es el retraso causado por la velocidad de datos del enlace.
 * El retraso de transmisión es una función de la longitud del paquete y no tiene 
 * nada que ver con la distancia entre los dos nodos. Este retraso es proporcional a la 
 * longitud del paquete en bits.
 * @return Dt La operación entre el tamaño del paquete y la Velocidad de transmision
 */       
       private double TransmissionDelay() 
       {
           double Dt = PackageSize / TransmissionRate;
           return Dt;
       }
/**
 * @see https://en.wikipedia.org/wiki/Propagation_delay
 * La demora de propagación es un término técnico que puede tener un significado diferente según el contexto.
 * Puede relacionarse con redes, electrónica o física.
 * En general, es el tiempo que tarda la cantidad de interés en llegar a su destino.
 * @return la operación entre la distancia y la velocidad de la luz
 */
       private double PropagationDelay ()
       {
           return Distance / SpeedOfLight;
       }
/**
 * 
 * @return la suma entre el retardo de propagación y retraso de transmisión
 */
    public double getDelay() {
        return TransmissionDelay() + PropagationDelay();
    }
/**
 * 
 * @param Delay El numero de retardo
 */
    public void setDelay(double Delay) {
        this.Delay = Delay;
    }
    
}
