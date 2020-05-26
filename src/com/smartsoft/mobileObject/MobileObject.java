/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.mobileObject;

import java.util.ArrayList;


/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class MobileObject {
    
    
    private int ID;
    private double Pause_Time;
    private int CellCurrent;
    private int AngleCurrent;
    private int Distance;
    private Point Location;
    private int x;
    private int y;
    private Point CenterLocation;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getCenterLocation() {
        //return new Point(x, y);
        return new Point(this.getLocation().getX(), this.getLocation().getY());
    }
    
    
    
    private ArrayList<Point> Trajectory = new ArrayList<Point>();
    
    public MobileObject(){}

    public MobileObject(int id, Point Location) {
        this.ID = id;
        this.Location = Location;
        this.x = Location.getX();
        this.y = Location.getY();
    }
/**
 * 
 * @return regresa ID
 */
    public int getID() {
        return ID;
    }

    /**
     * 
     * @return Regresa el tiempo de pausa
     */
    public double getPause_Time() {
        return Pause_Time;
    }

    /**
     * 
     * @return Regresa La celda acutal
     */
    public int getCellCurrent() {
        return CellCurrent;
    }

    /**
     * 
     * @return Regresa la angulo actual
     */
    public int getAngleCurrent() {
        return AngleCurrent;
    }

    /**
     * 
     * @return Regresa la distancia
     */
    public int getDistance() {
        return Distance;
    }

    /**
     * 
     * @return Regresa la ubicacióm
     */
    public Point getLocation() {
        return Location;
    }

    /**
     * 
     * @return Regresa una lista de la trayectoria del objeto
     */
    public ArrayList<Point> getTrajectory() {
        return Trajectory;
    }
/**
 * 
 * @param ID asignacion de ID
 */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * 
     * @param Pause_Time parametro de tiempo en pausa
     */
    public void setPause_Time(double Pause_Time) {
        this.Pause_Time = Pause_Time;
    }

    /**
     * 
     * @param CellCurrent parametro de la celda actual
     */
    public void setCellCurrent(int CellCurrent) {
        this.CellCurrent = CellCurrent;
    }

    /**
     * 
     * @param AngleCurrent parametro del angulo actual
     */
    public void setAngleCurrent(int AngleCurrent) {
        this.AngleCurrent = AngleCurrent;
    }

    /**
     * 
     * @param Distance parametro de distancia
     */
    public void setDistance(int Distance) {
        this.Distance = Distance;
    }

    /**
     * 
     * @param Location parametro de puntos de ubicación
     */
    public void setLocation(Point Location) {
        this.Location = Location;
    }

    /**
     * 
     * @param Trajectory parametrode Lista de puntos del objeto
     */
    public void setTrajectory(Point point) {
        this.Trajectory.add(point);
    }
    
}
