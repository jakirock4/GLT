/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

//import com.sun.prism.paint.Color;
import com.smartsoft.routing.Packages.HSTPacket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class GUIShowPackages extends JFrame implements ActionListener{
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private ArrayList<HSTPacket> RoutingPackages;

    /**
    * Ventana donde se visualiza el reporte de los paquetes enviados por los sensores.
    */

    public GUIShowPackages (ArrayList<HSTPacket> RoutingPackages){
        this.RoutingPackages = RoutingPackages;
        this.frame=new JFrame ("Vista paquetes");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(java.awt.Color.WHITE);
        //this.frame.setSize(800,200);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        frame.setLayout(null);
        this.frame.setVisible(true);
    
        this.ConstruirTabla();
    }
    
    private void ConstruirTabla(){
        String[] columnNames = {"Nodo fuente","Enrutamiento","Conteo de saltos","Entregado","Mensaje","Demorado"};
        Object[][] data = new Object [this.RoutingPackages.size()][6];
        
        int i = 0;
        for(HSTPacket pack : this.RoutingPackages){
            data[i][0] = pack.getSourceNode();
            data[i][1] = pack.getRoutingPath();
            data[i][2] = pack.getHopsCount();
            data[i][3] = pack.getIsDelivered();
            data[i][4] = pack.getMessageContent();
            data[i][5] = pack.getDelay();
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
    public void actionPerformed(ActionEvent e) {}
    
}
