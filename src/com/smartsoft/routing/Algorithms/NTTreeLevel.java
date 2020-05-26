/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.routing.Algorithms;

import java.util.ArrayList;
import com.smartsoft.nt.NTnode;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class NTTreeLevel {
    
    public int ID;
    private ArrayList<NTnode> Tnodes = new ArrayList<>();
    public ArrayList<NTnode> Nodes;
    public int Count;
    public String NodesString;
/**
 * 
 * @return Regresa el identificador del nivel en el arbol de notificaciones
 */
    public int getID() {
        return ID;
    }
/**
 * 
 * @return Regresa el total del nivel en el árbol de notificaciones
 */
    public int getCount() {
        return this.Tnodes.size();
    }
/**
 * 
 * @return retorna NodesString (cadena de nodos)
 */
    public String getNodesString() {
        return NodesString;
    }    
/**
 * @return Regresa una lista de nodos en el árbol de notificaciones
 */
    public ArrayList<NTnode> getTnodes() {
        return Tnodes;
    }

    public ArrayList<NTnode> getNodes() {
        return Tnodes;
    }
    
 /**
  * 
  * @param ID Coloca el identificador del nivel en el arbol
  */
    public void setID(int ID) {
        this.ID = ID;
    }
    
 /**
   * 
   * @param Count Coloca el total del nivel en el arbol
   */
    public void setCount(int Count) {
        this.Count = Count;
    }

 /**
   * 
   * @param NodesString Coloca una cadena de nodos
   */
    public void setNodesString(String NodesString) {
        this.NodesString = NodesString;
    }
 /**
  * devolver [i] el sensor en la Lista de sensores del sensor
  * @param position Posicion de la lista
  * @return Regresa 
  */   
    public NTnode getTnodes(int position) {
        return Tnodes.get(position);
    }
/**
 * 
 * @param position Posición que indica el lugar para agregar un valor dentro de la lista
 * @param value Valor a agregar
 */
    public void setTnodes(int position, NTnode value) {
        Tnodes.add(position, value);
    }
    
 /**
   * 
   */
    private void GetString()
    {
        NodesString = "";
        for(NTnode sen : Tnodes)
        {
            NodesString += sen.ID + ",";
        }
    }
   
 /**
   * 
   * @param tnode para borrar dentro de la lista en el nivel del árbol de notificaciones
  */
    public void Remove(NTnode tnode) 
    { 
        Tnodes.remove(tnode);
        this.GetString();
    }
    
 /**
   * 
   * @param tnode para agregar dentro de la lista en el nivel del árbol de notificaciones
   */
    public void add(NTnode tnode) { 
        Tnodes.add(tnode);
        this.GetString(); 
    }
    
 /**
   * @param tnodes lista de nodos para agregar dentro de la lista en el nivel del árbol de notificaciones
   */
    public void add(ArrayList<NTnode> tnodes) { 
        Tnodes.addAll(tnodes); 
        this.GetString(); 
    }
    
}
