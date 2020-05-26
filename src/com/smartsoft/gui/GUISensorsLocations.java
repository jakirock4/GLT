/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

//import com.sun.prism.paint.Color;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.SensoreBasicsDetails;
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
public class GUISensorsLocations  extends JFrame implements ActionListener{
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private ArrayList<Sensor> myNetWork;

    /**
    * Ventana donde se visualiza el reporte de la información de ubicación de los sensores.
    */

    public GUISensorsLocations (ArrayList<Sensor> myNetWork){
        this.myNetWork = myNetWork;
        this.frame=new JFrame ("Ubicación de sensores");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(java.awt.Color.WHITE);
        //this.frame.setSize(600,400);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        frame.setLayout(null);
        this.frame.setVisible(true);
        
        this.ConstruirTabla();
    }
   
    private void ConstruirTabla(){
        String[] columnNames = {"IDNodo","Pox","Poy", "R","Capacidad de batería","Ubicación real","Ubicación central"};
        //agregar poz
        
        Object[][] data = new Object [this.myNetWork.size()][7];
        
        ArrayList<SensoreBasicsDetails> NodesLocationsList = new ArrayList<SensoreBasicsDetails>();
        for(Sensor node : this.myNetWork)
        {
            SensoreBasicsDetails Sensorinfo = new SensoreBasicsDetails();
            Sensorinfo.setNodeID(String.valueOf(node.getID()));
            Sensorinfo.setRealLocation(node.getPox() + "," + node.getPoy());
            Sensorinfo.setCenterLocation((node.getPox() + node.getR()) + "," + (node.getPoy() + node.getR()));
            Sensorinfo.setPox((int) node.getPox());
            Sensorinfo.setBatteryCapacity(node.getBatteryCapacity());
            Sensorinfo.setPoy((int) node.getPoy());
            Sensorinfo.setR(node.getR());

            //Sensorinfo.OverlappingNodes = FindOverlappingNodesString(node);
            //if (node.TreeBasedParentSensor == null) { Sensorinfo.ParentID = "root"; } else { Sensorinfo.ParentID = node.TreeBasedParentSensor.ID.ToString(); }
            //Sensorinfo.Children = FindChildern(node);
           // Sensorinfo.RoutingPath = FindPath(node);
            NodesLocationsList.add(Sensorinfo);

        }
        
        int i = 0;
        for(SensoreBasicsDetails sen : NodesLocationsList){
            data[i][0] = sen.getNodeID();
            data[i][1] = sen.getPox();
            data[i][2] = sen.getPoy();
            data[i][3] = sen.getR();
            data[i][4] = sen.getBatteryCapacity();
            data[i][5] = sen.getRealLocation();
            data[i][6] = sen.getCenterLocation();
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
