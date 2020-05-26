/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;


//import com.sun.prism.paint.Color;
import com.smartsoft.modules.HSTCommunicationLog;
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
public class GUIPrintHST extends JFrame implements ActionListener{
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private ArrayList<Sensor> myNetWork;
    /**
    * Ventana donde se visualiza el reporte del arbol de expansión jerarquico.
    */


    public GUIPrintHST (ArrayList<Sensor> myNetWork){
        this.myNetWork = myNetWork;
        this.frame =new JFrame("Imprime Árbol de expansión jerarquíca");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(java.awt.Color.WHITE);
        //this.frame.setSize(1000,400);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        frame.setLayout(null);
        this.frame.setVisible(true);
        
        this.ConstruirTabla();
    }
    
    private void ConstruirTabla(){
        String[] columnNames = {"SensorID","Tiempo", "Operación","EnergíaUsada(nanoJulios)","Mensaje", "EnergiaUsada(Julio)", "Energía de batería restante(Julio)", "Distancia M", "Envíado", "Recibido"};
        
        this.table = new JTable(this.PrintLog(), columnNames);
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
        ArrayList<HSTCommunicationLog> Log = new ArrayList<HSTCommunicationLog>();
        for (Sensor sensor : this.myNetWork)
        {
            Log.addAll(sensor.HSTOperationsLog);
        }
        
        Object[][] data = new Object [Log.size()][10];
        
        int i = 0;
        for(HSTCommunicationLog log : Log){
            data[i][0] = log.getSensorID();
            data[i][1] = log.getTime();
            data[i][2] = log.getOperation();
            data[i][3] = log.getUsedEnergy_Nanojoule();
            data[i][4] = log.getMessage();
            data[i][5] = df1.format(log.getUsedEnergy_Joule());
            data[i][6] = log.getRemaimBatteryEnergy_Joule();
            //data[i][6] = df2.format(log.getRemaimBatteryEnergy_Joule() - log.getUsedEnergy_Joule());
            data[i][7] = log.getDistance_M();
            data[i][8] = log.isIsSend();
            data[i][9] = log.isIsReceive();
            i++;
        }
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
}
