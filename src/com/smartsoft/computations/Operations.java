/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.computations;
import com.smartsoft.modules.Sensor;
import com.smartsoft.mobileObject.Point;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Operations {
 
    /**
     * @param p1 punto 1
     * @param p2 punto 2
     * @return retorna la operacion entre los dos puntos dx y dy
     */    
    public static double DistanceTwoPoints( Point p1,Point p2)
    {
        int dx = (p1.getX() - p2.getX());
        dx *= dx;
        int dy = (p1.getY() - p2.getY());
        dy *= dy;
        return Math.sqrt(dx + dy);
    }
    
    /**
    * @param sensor1 Sensor 1
    * @param sensor2 Sensor 2
    * @return retorna la distancia entre los dos sensores dx y dy
    */

    public static double DistanceBetweenTwoSensors(Sensor sensor1, Sensor sensor2)
    {
        int dx = (sensor1.getCenterLocation().getX() - sensor2.getCenterLocation().getX());
        dx *= dx;
        int dy = (sensor1.getCenterLocation().getY() - sensor2.getCenterLocation().getY());
        dy *= dy;
        return Math.sqrt(dx + dy);
    }
    
    /**
    * 
    * @param sensor1 sensor 1
    * @param sensor2 sensor 2
    * @return retorna un booleano verdadero si se superpone y false si no
    */
    
    public static boolean isOverlapped(Sensor sensor1, Sensor sensor2)
    {
        boolean re = false;
        double disttance = DistanceBetweenTwoSensors(sensor1, sensor2);
        if (disttance < (sensor1.getR() + sensor2.getR()))
        {
            re = true;
        }
        return re;
    }
    
}
