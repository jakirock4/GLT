/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.smartsoft.model.NetworkImport;
import com.smartsoft.db.NetworkTopology;
import com.smartsoft.modules.Sensor;

/**
 * Importación de topologías (mandar a llamar)
 * llamada de Frame con los nombres de las topologías existentes y poder 
 * utilizar dentro de la simulación.
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class GUIImportTopology extends JFrame implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JPanel panelMain;
    private JLabel id[];
    private JLabel nombre [];
    private JButton importar [];
    private JButton borrar [];
    private JLabel propiedades [];
    private int total = 0;
    private String nombreTopologia;
    private Dibujo simulador;
    private ArrayList<Sensor> sensores;
    private ArrayList<Sensor> myNetWork;
    //private JLabel etiqueta1;
    //private JLabel etiqueta2;
    
    /**
    * Ventana donde se visualizan las topogias de red.
    * @param topologias Nomre de las topologuias
    */
    
    public GUIImportTopology(Dibujo simulador, ArrayList<NetworkImport> topologias,JPanel panelMain, JLabel[] propiedades, ArrayList<Sensor> myNetWork){
        this.simulador = simulador;
        this.panelMain = panelMain;
        this.propiedades = propiedades;
        this.myNetWork = myNetWork;
        this.total = topologias.size();
        this.frame =new JFrame ("Topologías");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(Color.WHITE);   
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        
        this.panel=new JPanel();
        this.frame.add(this.panel);
        this.panel.setLayout(new GridLayout(this.total,4,2,2));
        this.panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        this.id=new JLabel[topologias.size()];
        this.nombre=new JLabel[topologias.size()];
        this.importar=new JButton[topologias.size()];
        this.borrar=new JButton [topologias.size()];
        
        
        int i=0;
        for(NetworkImport top:topologias){
            this.id[i]=new JLabel(Integer.toString(top.getId()));
            this.nombre[i]=new JLabel(top.getName());
            this.importar[i]=new JButton("Importar");
            this.importar[i].addActionListener(this);
            this.borrar[i]=new JButton("Borrar");
            this.borrar[i].addActionListener(this);
            this.panel.add(this.id[i]);
            this.panel.add(this.nombre[i]);
            this.panel.add(this.importar[i]);
            this.panel.add(this.borrar[i]);
            i++;  
        }
        
        //this.frame.setSize(450,315);
        this.frame.pack();
        this.frame.setVisible(true);
        
        this.frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {}
        });
    }
    @Override
    public void actionPerformed(ActionEvent e){
        for (int i = 0; i < this.total; i++) {
            if (e.getSource() == this.importar[i]){
                this.nombreTopologia = this.nombre[i].getText();
                if(nombreTopologia != ""){
                    sensores = new NetworkTopology().getSensors(nombreTopologia);
                    //System.out.println(sensores.get(2).getMyNTnode());
                    propiedades[3].setText("Número de nodos: " + sensores.size());
                    simulador.setNodos(sensores);
                    simulador.insert();
                    panelMain.repaint();
                }
            }
            
            if (e.getSource() == this.borrar[i]){
                this.nombreTopologia = "";
                sensores.clear();
                simulador.setNodos(sensores);
                simulador.insert();
                panelMain.repaint();

            }
        }
    }

    public String getNombreTopologia() {
        return nombreTopologia;
    }

    public ArrayList<Sensor> getSensores() {
        return sensores;
    }
    
    public void setNombreTopologia(String nombreTopologia) {
        this.nombreTopologia = nombreTopologia;
    }

    public void setSensores(ArrayList<Sensor> sensores) {
        this.sensores = sensores;
    }
    
    public ArrayList<Sensor> resetSensores(){
        return new NetworkTopology().getSensors(nombreTopologia);
    }
    
}
