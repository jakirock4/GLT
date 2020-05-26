/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.computations;

import java.util.ArrayList;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.Vector;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 * @since   2019-09-02
 * 
 */
public class GetOverlappingNodes {
/**
 * 
 */   
    private ArrayList<Sensor> Network;
    
       public GetOverlappingNodes(ArrayList<Sensor> _NetworkNodes)
       {
           Network=_NetworkNodes; 
       }
        /**
         * Obtiene los nodos superpuestos para este nodo
         * @param Node Nodod sensor
         */
       private void GetOverlappingNodesForAnode( Sensor _node)
       {
           // agréga a vector.
           _node.Vector = new Vector(_node.getID());// El ID del vector = la identificación del sensor. // el mismo ID para el sensor y el vector.
           _node.Vector.Add(_node);
           if(Network!=null)
           {
               if(Network.size()>0)
               {
                   for(Sensor node: Network)
                   {
                       if (_node.getID()!= node.getID())
                       {
                           //System.out.println("Son diferentes: ");
                           boolean isOverlapped = Operations.isOverlapped(_node, node); 
                           if(isOverlapped)
                           {
                               //System.out.println("Estan superpuestos: ");
                               _node.Vector.Add(node);
                           }
                       }
                       //System.out.println(_node.Vector.Items);
                   }
               }
           }
          
       }
       
       /**
        * Para todos los nodos dentro de la red, encuentre los nodos superpuestos.
        */   

       public void GetOverlappingForAllNodes()
       {
           for(Sensor node: Network)
           {
               GetOverlappingNodesForAnode(node);
           }
       }
    
}
