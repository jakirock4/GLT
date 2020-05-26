/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

//import com.sun.prism.paint.Color;
import com.smartsoft.modules.NTCommunicationLog;
import com.smartsoft.modules.Sensor;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
public class GUINotificationsLog extends JFrame implements ActionListener{
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private ArrayList<Sensor> myNetWork;

    /**
    * Ventana donde se visualizan la información de las notificaciones.
    */

    public GUINotificationsLog (ArrayList<Sensor> myNetWork){
        this.myNetWork = myNetWork;
        this.frame=new JFrame ("Log de notificaciones");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(java.awt.Color.WHITE);
        //this.frame.setSize(1000,400);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        frame.setLayout(null);
        this.frame.setVisible(true);
        
        this.ConstruirTabla();
    }
    
    private void ConstruirTabla(){
        String[] columnNames = {"SensorID","Fecha","Energía utilizada en (nanoJoule)", "Mensaje","Energía utilizada en Julios","Energía residual de batería","Distancia M", "Insertado", "Eliminado"};
        
        this.table = new JTable(PrintLog(), columnNames);
        this.scrollPane = new JScrollPane(this.table);
        this.table.setFillsViewportHeight(true);
        
        this.frame.setLayout(new BorderLayout());
        this.frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
        this.frame.add(this.scrollPane, BorderLayout.CENTER);
        this.frame.pack();

    }
    
    private Object[][] PrintLog()
    {
        DecimalFormat df1 = new DecimalFormat("#.#######");
        DecimalFormat df2 = new DecimalFormat("#.########");
        ArrayList<NTCommunicationLog> Log = new ArrayList<NTCommunicationLog>();
        for (Sensor sensor : this.myNetWork)
        {
            Log.addAll(sensor.NTOperationsLog);
        }
        
        Object[][] data = new Object [Log.size()][9];
        
        int i = 0;
        for(NTCommunicationLog log : Log){
            data[i][0] = log.getSensorID();
            data[i][1] = log.getTime();
            data[i][2] = log.getUsedEnergy_Nanojoule();
            data[i][3] = log.getMessage();
            data[i][4] = df2.format(log.getUsedEnergy_Joule());
            data[i][5] = log.getRemaimBatteryEnergy_Joule();
            data[i][6] = log.getDistance_M();
            data[i][7] = log.getIsInsert();
            data[i][8] = log.getIsDelete();
            i++;
        }
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
}
