/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

import com.smartsoft.memory.TimeInterval;
import com.smartsoft.modules.Sensor;
import com.smartsoft.mobileObject.Point;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class GUIDrawTrajectory extends JFrame implements ActionListener {
    
    private JFrame frame;
    private JPanel panel;
    private int _ObjectID;
    private ArrayList<Sensor> _MyNetwork;
    private ArrayList<TimeInterval> TimeIntervals;
    private ArrayList<Point> Trajectory;
    
/**
 * Ventana donde se dibuja la trayectoria del objeto.
 */    
        public GUIDrawTrajectory(ArrayList<Point> Trajectory){
            this.frame= new JFrame("Dibuja trayectoria para objetos");  
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.frame.getContentPane().setBackground(Color.WHITE);
            this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
            
            this.Trajectory = Trajectory;
            this.frame.add(new DibujoTrayectory(this.Trajectory));  
            
            this.frame.setLayout(null);  
            this.frame.setSize(500,600);
            this.frame.setVisible(true); 
        }
    
    @Override
    public void actionPerformed(ActionEvent e) {}

}
