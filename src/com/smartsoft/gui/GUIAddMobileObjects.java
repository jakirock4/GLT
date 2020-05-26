/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;
import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import com.smartsoft.mobileObject.Objeto;
import com.smartsoft.mobileObject.Point;
import com.smartsoft.mobileObject.MobileObject;
import com.smartsoft.modules.Sensor;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */

//Frame para agregar objetos moviles
public class GUIAddMobileObjects  extends JFrame implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JButton OK;
    private JLabel etiqueta1;
    private JLabel etiqueta2;
    private JLabel etiqueta3;
    private JLabel etiqueta4;
    private JLabel etiqueta5;
    private int numObj = 0;
    private int distriMov = 0;
    private Double pause = 0.0;
    private String distriRango = "";
    private int velocidad = 0;
    private int setpsPerPexil = 1;
    private double Pause_Time;
    private Dibujo simulador;
    private JLabel propiedades [];
    private ArrayList<MobileObject> mobileObjects;
    private ArrayList<Sensor> myNetWork;
    //private JLabel etiqueta6;
    //private JLabel etiqueta7;
    private JTextField jtextfield = new JTextField();  
    private JTextField jtextfield1 = new JTextField(); 
    private JTextField jtextfield2 = new JTextField(); 
    
    private javax.swing.JComboBox<String> jComboBox1, jComboBox2;
    
    public GUIAddMobileObjects (Dibujo simulador, JLabel[] propiedades, ArrayList<Sensor> myNetWork){
        this.myNetWork = myNetWork;
        this.simulador = simulador;
        this.propiedades = propiedades;
        this.frame=new JFrame("Agregar objetos");
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setBackground(Color.WHITE);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
        //this.frame.setSize(300,250);

        //crear elementos para el frame
        //Etiquetas para numero de nodos, distribucion, rango, pausa y velocidad
        
        this.panel=new JPanel();
        this.frame.add(this.panel);
        this.panel.setLayout(new GridLayout(6,2,2,2));
        this.panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        this.etiqueta1=new JLabel();
        this.etiqueta1.setText("Número de objetos");
        this.panel.add(this.etiqueta1);
        this.etiqueta1.setSize(new Dimension (120,50));
        
        // Label para introducir número de nodos, distribución
        //Rango, pausa y velocidad al agregar objetos
        this.jtextfield.setBounds(new Rectangle(170, 15, 100, 21));
        this.jtextfield.setHorizontalAlignment(JTextField.LEFT); 
        this.jtextfield.setText("1");
        this.panel.add(this.jtextfield, null);
        
        this.etiqueta2=new JLabel();
        this.etiqueta2.setText("Distribución de movimiento");
        this.panel.add(this.etiqueta2);
        this.etiqueta2.setSize(new Dimension (200,100));
        
        //menu's para distribucion de movimiento y rango de movimiento
        this.jComboBox1=new javax.swing.JComboBox<>();
        this.jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String []{"fair","loaded"}));
        this.jComboBox1.setBounds(new Rectangle(170, 45, 100, 21));
        this.panel.add(this.jComboBox1);
        
        this.etiqueta3=new JLabel();
        this.etiqueta3.setText("Distribución de rango");
        this.panel.add(this.etiqueta3);
        this.etiqueta3.setSize(new Dimension (300,150)); 
        
        this.jComboBox2=new javax.swing.JComboBox<>();
        this.jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String []{"Limtado","Ilimitado"}));
        this.jComboBox2.setBounds(new Rectangle(170, 70, 100, 21));
        this.panel.add(this.jComboBox2);
        
        this.etiqueta4=new JLabel();
        this.etiqueta4.setText("Pausa (seg)"); // radio del nodo
        this.panel.add(this.etiqueta4);
        this.etiqueta4.setSize(new Dimension (400,200));
        
        this.jtextfield1.setBounds(new Rectangle(170, 95, 100, 21));
        this.jtextfield1.setText("1");
        this.jtextfield1.setEditable(false);
        jtextfield1.setHorizontalAlignment(JTextField.LEFT); 
        this.panel.add(this.jtextfield1, null);
        
        this.etiqueta5=new JLabel();
        this.etiqueta5.setText("Velocidad m/s");
        this.panel.add(this.etiqueta5);
        this.etiqueta5.setSize(new Dimension (500,250));
        
        this.jtextfield2.setBounds(new Rectangle(170, 120, 100, 21));
        this.jtextfield2.setText("8");
        //jtextfield2.setEditable(false);
        this.jtextfield2.setHorizontalAlignment(JTextField.LEFT); 
        this.panel.add(this.jtextfield2, null);
        
        
        //boton OK
        this.OK=new JButton("OK");
        this.OK.setBounds(50,150,100,30);
        this.OK.addActionListener(this);
        this.panel.add(this.OK);
        
        this.frame.pack();
        this.frame.setLayout(null);
        this.frame.setVisible(true);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.OK){
            this.mobileObjects = new ArrayList<>();
            this.numObj = Integer.parseInt(this.jtextfield.getText().trim());
            this.distriMov = Integer.parseInt(this.jtextfield1.getText().trim());
            this.velocidad = Integer.parseInt(this.jtextfield2.getText().trim());
            this.distriRango = this.jComboBox1.getSelectedItem().toString().trim();
            this.Pause_Time = (double)this.setpsPerPexil/this.velocidad;
            this.pause = this.Pause_Time;
            //System.out.println(this.numObj + " " + this.distriMov + " " + this.velocidad + " " + this.distriRango + " " + this.pause);
            if(this.numObj != 0){
                double rd = 1;
                Random rnd = new Random();
                double x = 0;
                double y = 0;
                for (int i = 0; i < this.numObj; i++) {
                    // randomize
                    rd = i * (System.currentTimeMillis());
                    int minX = (int) rd * 15 % 500;
                    int maxX = (int) rd * 110 % 450;
                    if (minX < maxX) { x = rnd.nextInt((maxX - minX) + 1) + minX; } else { x = rnd.nextInt((minX - maxX) + 1) + minX; }
                    int minY = (int )rd * 22 % 450;
                    int maxY = (int)rd * 34 % 450;
                    if (minX < maxX) { y = rnd.nextInt((maxY - minY) + 1) + minY; } else { y = rnd.nextInt((minY - maxY) + 1) + minY; }
                    //MobileObject m = new MobileObject(i,new Point(rnd.nextInt(30) + 40,rnd.nextInt(30) + 130));
                    MobileObject m = new MobileObject(i,new Point(200,144));
                    //MobileObject m = new MobileObject(i,new Point(0,0));
                    //MobileObject m = new MobileObject(i,new Point(250,250));
                    //MobileObject m = new MobileObject(i,new Point(400,50));
                    //MobileObject m = new MobileObject(i,new Point(50,400));
                    //this.objetos.add(new Objeto(i,new Point(rnd.nextInt(30) + 50, rnd.nextInt(30) + 250)));
                    m.setPause_Time(this.Pause_Time);
                    this.mobileObjects.add(m);
                }
                this.simulador.setObjeto(this.mobileObjects);
                this.simulador.insert();
            }
            this.propiedades[0].setText("Número de objetos: " + this.numObj);
            this.propiedades[1].setText("Velocidad de objetos: " + this.velocidad);
            this.propiedades[2].setText("Tiempo de pausa: " + this.Pause_Time);
            this.frame.setVisible(false);
        }
    }

    public int getNumObj() {
        return numObj;
    }

    public int getDistriMov() {
        return distriMov;
    }

    public String getDistriRango() {
        return distriRango;
    }

    public Double getPause() {
        return pause;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public ArrayList<MobileObject> getMobileObjects() {
        return mobileObjects;
    }

    public void setNumObj(int numObj) {
        this.numObj = numObj;
    }

    public void setDistriMov(int distriMov) {
        this.distriMov = distriMov;
    }

    public void setDistriRango(String distriRango) {
        this.distriRango = distriRango;
    }

    public void setPause(Double Pause) {
        this.pause = Pause;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setMobileObjects(ArrayList<MobileObject> mobileObjects) {
        this.mobileObjects = mobileObjects;
    }

    
}
