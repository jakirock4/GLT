/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;


import com.smartsoft.mobileObject.MobileObject;
import com.smartsoft.modules.Sensor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class GUITrajectoryForEachObject extends JFrame implements ActionListener {
    private JFrame frame;
    private ArrayList<MobileObject> objetos;
    private ArrayList<Sensor> myNetWork;
    
    /**
    * Ventana donde se visualiza la trayectoria por cada objeto.
    */
    
    public GUITrajectoryForEachObject(ArrayList<MobileObject> objetos,ArrayList<Sensor> myNetWork){
        this.objetos = objetos;
        this.myNetWork = myNetWork;
        System.out.print(objetos.size());
        this.frame=new JFrame("Imprime tayectoria por cada objeto");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(Color.WHITE);
        this.frame.setSize(300,200);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        frame.setLayout(null);
        this.frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
}
