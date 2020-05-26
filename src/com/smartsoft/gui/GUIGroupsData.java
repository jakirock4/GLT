/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

//import com.sun.prism.paint.Color;
import com.smartsoft.modules.GroupsDataString;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.SensorsGroup;
import com.smartsoft.modules.Vector;
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
public class GUIGroupsData extends JFrame implements ActionListener{
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private ArrayList<Sensor> myNetWork;
    
    /**
    * Ventana donde se imprimen los grupos de sensores.
    */


    public GUIGroupsData (ArrayList<Sensor> myNetWork){
        this.myNetWork = myNetWork;
        this.frame=new JFrame ("ImprimeGruposdeSensores");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(java.awt.Color.WHITE);
        //this.frame.setSize(1000,400);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        frame.setLayout(null);
        this.frame.setVisible(true);
        
        this.ConstruirTabla();
    }
    
    private void ConstruirTabla(){
        String[] columnNames = {"ID","Vector actual","Grupos", "Cadena de vector vecino","Cadena de vectores filtrados."}; 
        Object[][] data = new Object [this.myNetWork.size()][5];
        
        ArrayList<GroupsDataString> details = new ArrayList<GroupsDataString>();
        for(Sensor sensor : this.myNetWork )
        {
            GroupsDataString gs = new GroupsDataString();
            gs.setID(sensor.getID());
            gs.setGroups(FindMyGroupsString(sensor));
            gs.setCurrentVector(FindVectorString(sensor));
            gs.setNeighborVectosString(FindNeighbor(sensor));
            gs.setFilteredVectosString(FindFilterdVectorsString(sensor));
            details.add(gs);
        }
        
        int i = 0;
        for(GroupsDataString group : details){
            data[i][0] = group.getID();
            data[i][1] = group.getGroups();
            data[i][2] = group.getCurrentVector();
            data[i][3] = group.getNeighborVectosString();
            data[i][4] = group.getFilteredVectosString();
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
    
    private String FindMyGroupsString(Sensor sensor)
    {
        String g="";
        for(SensorsGroup group : sensor.MyGroups)
        {
            String gstring = "{" + group.getID() + ":";
            for(Sensor sen : group.Sensors )
            {
                gstring += sen.getID() + ",";
            }
            gstring += "}";
            g+=gstring;
        }
        return g; 
    }

    private String FindVectorString(Sensor sensor)
    {
        String v = "[" + sensor.Vector.getID() + ":";
        for (Sensor s : sensor.Vector.getMembers())
        {
            v += s.getID()+",";
        }
        v += "]";
        return v;
    }

    private String FindNeighbor(Sensor sensor)
    {
        String str = "";
        for(Vector vector : sensor.Vector.NeighborVectors )
        {
            String v = "["+vector.getID()+":";
            for (Sensor s : vector.getMembers())
            {
                v += s.getID() + ",";
            }
            v += "]";
            str += v;
        }
        return str;
    }

    private String FindFilterdVectorsString(Sensor sensor)
    {
        String str = "";
        for (Vector vector : sensor.Vector.FilterdedVectors)
        {
            String v = "[" + vector.getID() + ":";
            for (Sensor s : vector.getMembers())
            {
                v += s.getID() + ",";
            }
            v += "]";
            str += v;
        }
        return str;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
}
