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

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class GUIMultipleNodes extends JFrame implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JButton Ok;
    
    private JLabel etiqueta1;
    private JLabel etiqueta2;
    JTextField jtextfield = new JTextField();  
    JTextField jtextfield1 = new JTextField();  
    
    /**
    * Ventana donde se visualizan los multiples nodos de red.
    */
   
    public GUIMultipleNodes(){
        this.frame=new JFrame ("Multiples Nodos");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(Color.WHITE);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        //this.frame.setSize(300,250);
        
        
        this.panel=new JPanel();
        this.frame.add(this.panel);
        this.panel.setLayout(new GridLayout(3,2,2,2));
        this.panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
 
        //Creando elementos del Frame
        
        //Etiquetas
        
        this.etiqueta1=new JLabel();
        this.etiqueta1.setText("Número de nodos");
        this.etiqueta1.setSize(new Dimension (100,50));
        this.panel.add(this.etiqueta1);
       // this.etiqueta1.setLocationRelativeTo(null);
        
        // Label para introducir número de nodos y radio
        this.jtextfield.setBounds(new Rectangle(100, 15, 150, 21));
        this.jtextfield.setText(" ");
        this.jtextfield.setHorizontalAlignment(JTextField.LEFT); 
        this.panel.add(this.jtextfield, null);
        
        this.etiqueta2=new JLabel();
        this.etiqueta2.setText("r:"); // radio del nodo
        this.etiqueta2.setSize(new Dimension (200,100));
        this.panel.add(this.etiqueta2);
        
        this.jtextfield1.setBounds(new Rectangle(100, 40, 150, 21));
        this.jtextfield1.setText(" ");
        this.jtextfield1.setHorizontalAlignment(JTextField.LEFT); 
        this.panel.add(this.jtextfield1, null);
        //botón ok
        this.Ok=new JButton("OK");
        Ok.setBounds(50,65,100,30);
        this.panel.add(this.Ok);
        
        this.frame.pack();
        this.frame.setLayout(null);
        this.frame.setVisible(true);
            
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {}
    
}
