/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gltjava;

import com.smartsoft.routing.Packages.HSTPacket;
import java.util.ArrayList;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Init {

/**
 * ArrayList / Para guardar los paquetes HST (Árbol de estructura jerárquica) de todos los nodos
 */
    public ArrayList<HSTPacket> RoutingPackages = new ArrayList<>();
    private int rootNodeIndex = 0; // s1= 0, s2= 192.
    private double SinkNodeBattryCapacity = 1000000;// jouls.
    private double NodesBattryCapacity = 10; // Energía inicial
    private double EnergyThreshould = 70;
/**
 * 
 * @return Regresa el indice del nodo raíz.
 */
    public int getRootNodeIndex() {
        return rootNodeIndex;
    }
/**
 * 
 * @return Regresa la capacidad de bateria del nodo receptor
 */
    public double getSinkNodeBattryCapacity() {
        return SinkNodeBattryCapacity;
    }
/**
 * 
 * @return Regresa la capacidad de bateria del nodo
 */
    public double getNodesBattryCapacity() {
        return NodesBattryCapacity;
    }
/**
 * 
 * @return Regresa el umbral de energía
 */
    public double getEnergyThreshould() {
        return EnergyThreshould;
    }
/**
 * 
 * @param rootNodeIndex  Indice del nodo raíz
 */
    public void setRootNodeIndex(int rootNodeIndex) {
        this.rootNodeIndex = rootNodeIndex;
    }
/**
 * 
 * @param SinkNodeBattryCapacity   Capacidad de bateria del nodo receptor
 */
    public void setSinkNodeBattryCapacity(double SinkNodeBattryCapacity) {
        this.SinkNodeBattryCapacity = SinkNodeBattryCapacity;
    }
/**
 * 
 * @param NodesBattryCapacity  Capacidad de la bateria del nodo
 */
    public void setNodesBattryCapacity(double NodesBattryCapacity) {
        this.NodesBattryCapacity = NodesBattryCapacity;
    }
/**
 * 
 * @param EnergyThreshould Umbral de energía
 */
    public void setEnergyThreshould(double EnergyThreshould) {
        this.EnergyThreshould = EnergyThreshould;
    }
    
}
