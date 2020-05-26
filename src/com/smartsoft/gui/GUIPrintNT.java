/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

import com.smartsoft.modules.NotfPaths;
import com.smartsoft.modules.Sensor;
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
public class GUIPrintNT extends JFrame implements ActionListener{
    private JFrame frame;
    private JScrollPane scrollPane;
    private JTable table;
    private ArrayList<Sensor> myNetWork;
  
    /**
    * Ventana donde se visualiza el reporte de los grupos de red.
    */

    public GUIPrintNT(ArrayList<Sensor> myNetWork){
        this.myNetWork = myNetWork;
        this.frame=new JFrame ("Imprime Árbol de Notificaciones");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(java.awt.Color.WHITE);
        //this.frame.setSize(400,450);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        frame.setLayout(null);
        this.frame.setVisible(true);
       
        this.ConstruirTabla();
    }    
    private void ConstruirTabla(){
        String[] columnNames = {"ID nodo","Enrutamiento","Tamaño de NT"};
        
        ArrayList<NotfPaths> List = new ArrayList<NotfPaths>();
        for(Sensor sen : this.myNetWork)
        {
            NotfPaths pa = new NotfPaths();
            pa.setNodeID(sen.getID());
            pa.setNotiPath(sen.GetNotificationString(sen.getMyNTnode()));
            pa.setNotiPath(pa.getNotiPath().substring(0, pa.getNotiPath().length() - 1));
            pa.setNTsize(pa.getNotiPath().split("→").length);
            List.add(pa);
        }

        double avr = 0;
        for (NotfPaths n : List)
        {
            avr += n.getNTsize();
        }

        avr /= List.size();
        List.add(new NotfPaths(-1, avr, "-"));
            
        Object[][] data = new Object [List.size()][3];
        
        int i = 0;
        for(NotfPaths list : List){
            data[i][0] = list.getNodeID();
            data[i][1] = list.getNotiPath();
            data[i][2] = list.getNTsize();
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

    @Override
    public void actionPerformed(ActionEvent e) {throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
