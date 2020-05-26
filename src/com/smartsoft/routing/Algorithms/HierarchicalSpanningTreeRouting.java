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

public class HierarchicalSpanningTreeRouting {
    
    private ArrayList<NTnode> Network; // la red
    private ArrayList<NTnode> AlreadyInTree = new ArrayList<>(); // los sensores ya están en el árbol
    private ArrayList<NTnode> Leaves = new ArrayList<>(); // los últimos nodos se agregan al árbol. Iniciado por la raíz solamente.
    private int _RootNodeIndex = 0;
    private ArrayList<NTTreeLevel> TreeLevels = new ArrayList<>();
    private int TreeLevelsCount = 0;
    int i = 0;
    /**
     * 
     * @param _Network Lista de nodos del arbol de notificaciones
     * @param RootNodeIndex Indice de la raiz
     */
    public HierarchicalSpanningTreeRouting(ArrayList<NTnode> _Network, int RootNodeIndex) 
    {
        this.Network = _Network;
        //System.out.println("Tamaño del arbol: " + this.Network.size());
        _RootNodeIndex = RootNodeIndex;
        //System.out.println("_RootNodeIndex: " + _RootNodeIndex);
        if (RootNodeIndex < _Network.size())
        {
            // sensors
            AlreadyInTree.add(this.Network.get(RootNodeIndex));// agregue la raíz, por defecto el primer nodo en la red.
            Leaves.add(this.Network.get(RootNodeIndex));// agregue la raíz, por defecto el primer nodo en la red.
            this.Network.get(RootNodeIndex).setParentN(null);//
            this.Network.get(RootNodeIndex).setTreeLevel(0); // El primer nivel.
            this.Network.get(RootNodeIndex).getSensor().setLevel(0);// Raiz.
            // Añade el primer nivel.
            NTTreeLevel firstLevl = new NTTreeLevel();
            firstLevl.setID(0);
            TreeLevelsCount = 0;
            firstLevl.add(Leaves);
            TreeLevels.add(firstLevl); // agregue la raíz en el primer nivel.
        }
        else
        {
            System.out.println("¡El índice del nodo raíz no es correcto!");
        }
    }

    public void BuildTree()
    {
        //System.out.println("Nivelews: " + Leaves);
        BuildTreeForLastLevelSensors(Leaves);
    }
/**
 * 
 * @param LeavesNode // Construir el árbol lógico de sensores desde el último nivel actual.
    /// cuando el recuento de hojas recientes es cero ... el proceso ha finalizado.
 */
    
    private void BuildTreeForLastLevelSensors(ArrayList<NTnode> LeavesNode)
    {
        i++;
        TreeLevelsCount++;
        NTTreeLevel newLevel = new NTTreeLevel();
        newLevel.setID(TreeLevelsCount);
        //System.out.println("Antes del For Count: i " + i + " " + newLevel.getCount());
        for(NTnode leafNode : LeavesNode)
        {
            //System.out.println("i: " + i);
            if (leafNode.getChildernN() != null) // construir desde el árbol de notificaciones.
            {
                //System.out.println("Antes del For Count: i " + i + " " + newLevel.Count + " GetMyNotificationNodes " + leafNode.GetMyNotificationNodes().size());
                for(NTnode childnode : leafNode.GetMyNotificationNodes())
                {
                    if (!IsFound(childnode)) // No se encuentra en el árbol.
                    {
                        //System.out.println("Antes del For Count: i " + i + " Es encontrado " + newLevel.getCount());
                        // agrégalo al árbol.
                        //leafNode.ChildernR.add(childnode); //agregar a los hijos
                        //System.out.println("Sensor: " + leafNode.getSensor().getID() + " Childern " + childnode.getSensor().getID());
                        leafNode.getSensor().getChildern().add(childnode.getSensor());
                        childnode.getSensor().setParentSensor(leafNode.getSensor());
                        childnode.getSensor().setLevel(TreeLevelsCount);// Establecer el nivel.

                        //childnode.ParentR = leafNode; // establecer padre.
                        AlreadyInTree.add(childnode);// añadir al árbol
                        childnode.setTreeLevel(TreeLevelsCount);
                        newLevel.add(childnode);// Nodos de último nivel.

                    }
                }
            }
        }
        //System.out.println("Despues del For Count: i " + i + " " + newLevel.getCount());
        if (newLevel.getCount() > 0)
        {
            //System.out.println("No fue encontrado: " + i);
            TreeLevels.add(newLevel);// Añadir nuevo nivel.
            BuildTreeForLastLevelSensors(newLevel.getNodes()); // Llamada recursiva.
        }
    }

/**
 * 
 * @param tnode Nodo del árbol de notificaciones
 * @return Regresa un booleano Verdadero si el nodo fue encontrado y un falso si no lo fue encontrado
 */

    private boolean IsFound(NTnode tnode)
    {
        boolean is_found = false;
        for(NTnode _node : AlreadyInTree)
        {
            //System.out.println("ID tnode " + tnode.getID() + " ID _node " + _node.getID());
            //System.out.println(_node.getID() == tnode.getID());
            if (_node.getID() == tnode.getID()) { 
                is_found = true; 
                break; 
            }
        }
        //System.out.println("Encontrado: " + is_found);
        return is_found;
    }
    
}
