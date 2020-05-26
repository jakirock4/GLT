/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

//import com.sun.prism.paint.Color;
import com.smartsoft.modules.HSTCommunicationLog;
import com.smartsoft.modules.NTCommunicationLog;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.TotalSensorLog;
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
public class GUISummaryOfSensors extends JFrame implements ActionListener{
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private ArrayList<Sensor> myNetWork;

    /**
    * Ventana donde se visualiza el reporte de resumen del sensor.
    */

    public GUISummaryOfSensors (ArrayList<Sensor> myNetWork){
        this.myNetWork = myNetWork;
        this.frame=new JFrame ("Resumen de sensor");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(java.awt.Color.WHITE);
        //this.frame.setSize(1000,400);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        frame.setLayout(null);
        this.frame.setVisible(true);
        
        this.ConstruirTabla();
    }
    
    private void ConstruirTabla(){
        DecimalFormat formato = new DecimalFormat("#.########");
        String[] columnNames = {
                                "ID nodo",
                                "Energía residual en Joules",
                                "Porcentaje de energía residual",
                                "Energía total utilizada para HST (Julios)",
                                "Energía total utilizada para NT (Julios)",
                                "Operación de datos enviados",
                                "Operación de datos recibidos",
                                "Total de operaciones",
                                "Notificaciones insertadas",
                                "Notificaciones Borradas",
                                "Total de notificaciones"
        };
        
        ArrayList<TotalSensorLog> logs = new ArrayList<TotalSensorLog>();

        for (Sensor sensor : this.myNetWork)
        {
            TotalSensorLog l = GetLogForSensor(sensor);
            l.setResidualEnergyJouls(sensor.getResidualEnergy());
            l.setResidualEnergyPercentage(sensor.getBatteryPercentage());
            logs.add(l);
        }
        
        Object[][] data = new Object [logs.size()][11];
        
        int i = 0;
        for(TotalSensorLog log : logs){
            data[i][0] = log.getNodeID();
            data[i][1] = log.getResidualEnergyJouls();
            data[i][2] = log.getResidualEnergyPercentage();
            data[i][3] = log.getTotalUsedEnergyJouleForHST();
            data[i][4] = formato.format(log.getTotalUsedEnergyJouleForNT());
            data[i][5] = log.getSendDataOperations();
            data[i][6] = log.getReciveDataOperations();
            data[i][7] = log.getTotalOperations();
            data[i][8] = log.getInsertNotifications();
            data[i][9] = log.getDeleteNotifcations();
            data[i][10] = log.getTotalNotifications();
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
    
    private TotalSensorLog GetLogForSensor(Sensor sensor)
    {
        TotalSensorLog logs = new TotalSensorLog();
        int NodeID = sensor.getID();
        double TotalEnergyHST = 0;
        int SendDateOperations = 0;
        int ReciveDateOperations = 0;


        // HST tree
        for (HSTCommunicationLog log : sensor.HSTOperationsLog)
        {
            TotalEnergyHST += log.getUsedEnergy_Joule();
            if (log.isIsSend()) { SendDateOperations += 1; }
            if (log.isIsReceive()) { ReciveDateOperations += 1; }
        }

        int InsertNotifcations = 0;
        int DeleteNotifcations = 0;
        double TotalEnergyNT = 0; 
        // NT tree
        for (NTCommunicationLog log : sensor.NTOperationsLog)
        {
            TotalEnergyNT += log.getUsedEnergy_Joule();
            if (log.getIsInsert()) { InsertNotifcations += 1; }
            if (log.getIsDelete()) { DeleteNotifcations += 1; }
        }

        logs.setNodeID(NodeID);

        logs.setReciveDataOperations(ReciveDateOperations);
        logs.setSendDataOperations(SendDateOperations);

        logs.setDeleteNotifcations(DeleteNotifcations);
        logs.setInsertNotifications(InsertNotifcations);

        logs.setTotalUsedEnergyJouleForHST(TotalEnergyHST);
        logs.setTotalUsedEnergyJouleForNT(TotalEnergyNT);

        return logs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
}
