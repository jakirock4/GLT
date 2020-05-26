/*
 * Esta clase de usa para almacenar los sensores
 * de las topologias importadas
 */
package com.smartsoft.db;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class ImportedSensor {
    
    private int id;
    private int NodeID;
    private double Pox;
    private double Poy;
    private double R; // radio del nodo

    /**
    * 
    * @return Obtiene el ID del sensor
    */
    public int getId() {
        return id;
    }
    
    /**
    * 
    * @return Obtiene el ID del nodo Sensor
    */
    
    public int getNodeID() {
        return NodeID;
    }
    
    /**
    * 
    * @return Obtiene la posición X
    */

    public double getPox() {
        return Pox;
    }
    
    /**
    * 
    * @return Obtiene la posición Y
    */

    public double getPoy() {
        return Poy;
    }
    
    /**
    * 
    * @return Obtiene el radio de alcance del sensor
    */

    public double getR() {
        return R;
    }
    
   /**
   * @param id Identificador del sensor
   */
    
    public void setId(int id) {
        this.id = id;
    }
    
   /**
   * @param NodeID Identificador del nodo
   */

    public void setNodeID(int NodeID) {
        this.NodeID = NodeID;
    }
    
   /**
   * @param Pox Posición X
   */

    public void setPox(double Pox) {
        this.Pox = Pox;
    }
    
   /**
   * @param Poy Posición Y
   */

    public void setPoy(double Poy) {
        this.Poy = Poy;
    }

   /**
   * @param R Redio de alcance
   */
    
    public void setR(double R) {
        this.R = R;
    }
    
}
