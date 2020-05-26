/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class ExportTopology extends JFrame implements ActionListener{
    
    private JFrame frame;
    private JPanel panel;
    private JButton OK;
    private JLabel etiqueta1;

    
    JTextField jtextfield = new JTextField(); 
/**
 * Exportación de topologias para poder usarse en la simulación.
 */    
    public ExportTopology (){
    this.frame=new JFrame("Exportar Topología");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(Color.WHITE);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        //this.frame.setSize(300,110);
        
        this.panel=new JPanel();
        this.frame.add(this.panel);
        this.panel.setLayout(new GridLayout(2,2,2,2));
        this.panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // 
        this.etiqueta1=new JLabel();
        this.etiqueta1.setText("Nombre de red");
        this.panel.add(this.etiqueta1);
        this.etiqueta1.setSize(new Dimension (100,50));
        
        // 
        
        this.jtextfield.setBounds(new Rectangle(100, 15, 150, 21));
        this.jtextfield.setText(" ");
        this.jtextfield.setHorizontalAlignment(JTextField.LEFT); 
        this.panel.add(this.jtextfield, null);
        
        // botón ok
        this.OK=new JButton("OK");
        this.OK.setBounds(50,40,100,30);
        this.OK.setHorizontalAlignment(JButton.CENTER); 
        this.panel.add(this.OK);
        
        this.frame.pack();
        this.frame.setLayout(null);
        this.frame.setVisible(true);
}
    /**
     * posibles acciones que se podrían desencadenar del botón y algunos otros métodos introducidos.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {}
    
}
