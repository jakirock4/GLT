/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

import com.smartsoft.modules.HSTCommunicationLog;
import com.smartsoft.modules.NTCommunicationLog;
import com.smartsoft.modules.Sensor;
import com.smartsoft.routing.Packages.HSTPacket;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class GUITotalReport extends JFrame implements ActionListener{
    
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private GUIAddMobileObjects objeto;
    private ArrayList<Sensor> myNetWork;
    private ArrayList<HSTPacket> RoutingPackages;
    private int miliSe = 0;
    private double NodesBattryCapacity;
    private double EnergyUsedJControlPackagesTO = 0;
    // HST
    private double EnergyUsedJDataRoutingPackagesTo = 0; ;
    // generated packages:
    private int gneratedRoutingPackagesCount = 0;
    // hops and delay:
    private double avergaeDelay = 0;
    private double averageHops = 0;
    
    /**
    * Ventana donde se visualiza el reporte total del simulador.
    */
    
    public GUITotalReport(ArrayList<Sensor> myNetWork, ArrayList<HSTPacket> RoutingPackages, GUIAddMobileObjects objeto, int miliSe, double NodesBattryCapacity){
        this.myNetWork = myNetWork;
        this.RoutingPackages = RoutingPackages;
        this.objeto = objeto;
        this.miliSe = miliSe;
        this.NodesBattryCapacity = NodesBattryCapacity;
        
        this.frame=new JFrame("Ver Reporte Total");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.frame.getContentPane().setBackground(Color.WHITE);
        //this.frame.setSize(800,300);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        this.frame.setLayout(null);
        this.frame.setVisible(true);
        
        this.ConstruirTabla();
    }
    
    private void ConstruirTabla(){
        String[] columnNames = {"Par","Val"};
        
        for(HSTPacket packet : this.RoutingPackages)
        {
            avergaeDelay += packet.Delay;
            averageHops += packet.HopsCount;
        }
        avergaeDelay /= this.RoutingPackages.size();
        averageHops /= this.RoutingPackages.size();
        
        // NT and HST
        ArrayList<HSTCommunicationLog> hstLogs = new ArrayList<HSTCommunicationLog>(); 
        ArrayList<NTCommunicationLog> NTLogS = new ArrayList<NTCommunicationLog>();
        
        for (Sensor sensor : this.myNetWork)
        {
            NTLogS.addAll(sensor.getNTOperationsLog());
            hstLogs.addAll(sensor.HSTOperationsLog);
        }
        
        for(NTCommunicationLog ntlog : NTLogS)
        {
            this.EnergyUsedJControlPackagesTO += ntlog.getUsedEnergy_Joule();
        }
        
        for(HSTCommunicationLog hstlog : hstLogs )
        {
            this.EnergyUsedJDataRoutingPackagesTo += hstlog.getUsedEnergy_Joule();
        }
        
        for (Sensor sn : this.myNetWork)
        {
            this.gneratedRoutingPackagesCount += sn.MyGerenratedPackates.size();
        }
        
        Object[][] data = {
            {"Número de Nodos", new Integer(myNetWork.size())},
            {"Tiempo de simulación", new Integer(miliSe/1000)},
            {"Energía inicial (J)", new Double(NodesBattryCapacity)},
            {"Modelo de energía", "Modelo de primer orden"},
            {"Campo de detección", "525 * 525"},
            {"Rango de comunicación", this.myNetWork.get(0).getR() * 2},
            {"Número de objetos", new Integer(this.objeto.getNumObj())},
            {"Velocidad de los objetos", new Integer(this.objeto.getVelocidad())},
            {"Tiempo de pausa", new Double(this.objeto.getPause())},
            {"Tamaño del paquete de datos (bit)", this.myNetWork.get(0).getDataPackageSize()},
            {"Tamaño del paquete de control (bit)", this.myNetWork.get(0).getControlPackageSize()},
            {"Control de conteo de paquetes", NTLogS.size()},
            {"Recuento de paquetes de enrutamiento", gneratedRoutingPackagesCount},
            {"Recuerdo de paquetes de enrutamiento entregados con éxito.", this.RoutingPackages.size()},
            {"Energía utilizada para paquetes de enrutamiento de datos", this.EnergyUsedJDataRoutingPackagesTo},
            {"Energía utilizada para los paquetes de control (J)", this.EnergyUsedJControlPackagesTO},
            {"Saltos promedio", this.averageHops},
            {"Retrasos promedio", this.avergaeDelay},
            {"Energía total utilizada (J)", EnergyUsedJDataRoutingPackagesTo + EnergyUsedJControlPackagesTO}
        };
        
        this.table = new JTable(data, columnNames);    
        
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(370);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(160);
        
        this.table.setFillsViewportHeight(true);
        //this.scrollPane = new JScrollPane(this.table);
        
        
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.table, BorderLayout.PAGE_START);
        //this.frame.add(this.scrollPane, BorderLayout.CENTER);
        this.frame.pack();
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {}
    
}
