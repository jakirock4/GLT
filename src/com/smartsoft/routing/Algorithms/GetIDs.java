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
public class GetIDs {
    
    
    private  ArrayList<Integer> LISSS = new ArrayList<>();
    NTnode _node;
/**
 * 
 * @param node Nodo del árbol de notificaciones
 */
    public GetIDs(NTnode node)
    {
        _node = node;
    }
/**
 * 
 * @return Regresa ArrayList  LISSS
 */
    public ArrayList<Integer> GetList()
    {
        GetNotificationString(_node);
        return LISSS;
    }
/**
 * 
 * @param node Nodo, 
 */
    private void GetNotificationString(NTnode node)
    {
        if (node != null)
        {
            LISSS.add(node.ID);
        }
        for(NTnode n : node.ChildrenN)
        {
            GetNotificationString(n);
        }
    }
    
}