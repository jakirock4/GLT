/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

import com.smartsoft.modules.PrintGroup;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.SensorsGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class GUIPrintNG extends JFrame implements ActionListener{
    private JFrame frame;
    private JScrollPane scrollPane;
    private JTable table;
    private ArrayList<SensorsGroup> NetWorkGroups;
  
    /**
    * Ventana donde se visualiza el reporte de los grupos de red.
    */

    public GUIPrintNG(ArrayList<SensorsGroup> NetWorkGroups){
        this.NetWorkGroups = NetWorkGroups;
        this.frame=new JFrame ("Grupos de red");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(java.awt.Color.WHITE);
        //this.frame.setSize(300,450);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        frame.setLayout(null);
        this.frame.setVisible(true);
       
        this.ConstruirTabla();
    }
    
    private void ConstruirTabla(){
        String[] columnNames = {"ID","Sensores"};
        
        ArrayList<PrintGroup> logs = new ArrayList<PrintGroup>();
        for(SensorsGroup group : NetWorkGroups)
        {
            PrintGroup gs = new PrintGroup();
            gs.setID(group.getID());
            gs.setSensors(PrintSensors(group));
            logs.add(gs);
        }
        
        
        
        Object[][] data = new Object [logs.size()][2];
        
        int i = 0;
        for(PrintGroup gro : logs){
            data[i][0] = gro.getID();
            data[i][1] = gro.getSensors();
            i++;
        }
        
        this.table = new JTable(data, columnNames);
        this.scrollPane = new JScrollPane(this.table);
        this.table.setFillsViewportHeight(true);
        
        this.frame.setLayout(new BorderLayout());
        this.frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
        this.frame.add(this.scrollPane, BorderLayout.CENTER);
        this.frame.pack();
    }
    
    private String PrintSensors(SensorsGroup g)
    {
        String str = "";
        for(Sensor s : g.Sensors)
        {
            str += s.getID() + ",";
        }
        return str;
    }

    @Override
    public void actionPerformed(ActionEvent e) {throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
