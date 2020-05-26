/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.tracking;

import java.util.ArrayList;
//import com.smartsoft.tracking.TrackingRow;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class TrackingTable {
    
    private ArrayList<TrackingRow> _TrackingTableList = new ArrayList<>(); 
/**
 * 
 * @param row Fila para el reporte de seguimiento del objeto
 */ 
    public void add(TrackingRow row)
    {
        _TrackingTableList.add(row);
    }
    
}
