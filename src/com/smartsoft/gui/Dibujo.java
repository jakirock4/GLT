/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;
import java.awt.*;  
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
//import com.smartsoft.mobileObject.Objeto;
import com.smartsoft.mobileObject.MobileObject;
import com.smartsoft.mobileObject.Point;
import com.smartsoft.modules.Line;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.SensorState;
import java.awt.image.BufferStrategy;

/**
 * Dibujando topologias
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Dibujo extends Canvas {
    
    private int x;
    private int y;
    private String id;
    private Boolean dibujarBorde = false;
    public BufferedImage img;
    public BufferedImage imgSensor;
    //private ArrayList<Objeto> objeto;
    private ArrayList<MobileObject> objeto;
    private ArrayList<Sensor> nodos = new ArrayList<>();
    private ArrayList<Line> borde;
    private int estacionBase = 0;
    private boolean verEstacionBase = false;
    private Point point;
    private Color lightBlue= new Color(118,195,230);
    private Color rosa= new Color(235,160,181);
    private Color verde= new Color(48,140,6);
    private Color rojo= new Color(255,38,0);
    private Color amarillo= new Color(255,223,34);
    private Color naranja= new Color(253,128,8);
    
    public Dibujo() {
        this.objeto = null;
        setBackground(Color.WHITE);
        setSize(600, 700);
    }

    public ArrayList<MobileObject> getObjeto() {
        return objeto;
    }

    public ArrayList<Sensor> getNodos() {
        return nodos;
    }

    public Boolean getDibujarBorde() {
        return dibujarBorde;
    }

    public ArrayList<Line> getBorde() {
        return borde;
    }

    public int getEstacionBase() {
        return estacionBase;
    }

    public boolean getVerEstacionBase() {
        return verEstacionBase;
    }

    public void setBorde(ArrayList<Line> borde) {
        this.borde = borde;
    }

    public void setDibujarBorde(Boolean dibujarBorde) {
        this.dibujarBorde = dibujarBorde;
    }

    public void setNodos(ArrayList<Sensor> nodos) {
        this.nodos = nodos;
    }

    public void setObjeto(ArrayList<MobileObject> objeto) {
        this.objeto = objeto;
    }

    public void setEstacionBase(int estacionBase) {
        this.estacionBase = estacionBase;
    }

    public void setVerEstacionBase(boolean verEstacionBase) {
        this.verEstacionBase = verEstacionBase;
    }
    
    @Override
    public void paint(Graphics g){
        Random num = new Random();
        
        createBufferStrategy(2);
        BufferStrategy strategy = getBufferStrategy();
        
         // Renderizar fotograma por fotograma
        do {
            // El siguiente bucle garantiza que el contenido del búfer de dibujo sea coherente en caso de que se 
            // haya recreado la superficie subyacente.
            do {
                // Obtenga un nuevo contexto gráfico cada vez a través del ciclo para asegurarse de que la 
                // estrategia esté validada
                g = strategy.getDrawGraphics();

                try {
                    
                    //Scala
                    g.drawLine(550, 0, 550, 525);
                    g.drawLine(0, 525, 540, 525); 

                    g.drawLine(550,0,535,0);
                    g.drawLine(550,25,540,25);
                    g.drawLine(550,50,535,50);
                    g.drawLine(550,75,540,75);
                    g.drawLine(550,100,535,100);
                    g.drawLine(550,125,540,125);
                    g.drawLine(550,150,535,150);
                    g.drawLine(550,175,540,175);
                    g.drawLine(550,200,535,200);
                    g.drawLine(550,225,540,225);
                    g.drawLine(550,250,535,250);
                    g.drawLine(550,275,540,275);
                    g.drawLine(550,300,535,300);
                    g.drawLine(550,325,540,325);
                    g.drawLine(550,350,535,350);
                    g.drawLine(550,375,540,375);
                    g.drawLine(550,400,535,400);
                    g.drawLine(550,425,540,425);
                    g.drawLine(550,450,535,450);
                    g.drawLine(550,475,540,475);
                    g.drawLine(550,500,535,500);   
                    g.drawLine(550,525,540,525);   

                    g.drawString("0",555,0);
                    g.drawString("25",555,25);
                    g.drawString("50",555,50);
                    g.drawString("75",555,75);
                    g.drawString("100",555,100);
                    g.drawString("125",555,125);
                    g.drawString("150",555,150);
                    g.drawString("175",555,175);
                    g.drawString("200",555,200);
                    g.drawString("225",555,225);
                    g.drawString("250",555,250);
                    g.drawString("275",555,275);
                    g.drawString("300",555,300);
                    g.drawString("325",555,325);
                    g.drawString("350",555,350);
                    g.drawString("375",555,375);
                    g.drawString("400",555,400);
                    g.drawString("425",555,425);
                    g.drawString("450",555,450);
                    g.drawString("475",555,475);
                    g.drawString("500",555,500);
                    g.drawString("525",555,525);

                    g.drawLine(550,525,550,510);
                    g.drawLine(525,525,525,515);
                    g.drawLine(500,525,500,510);
                    g.drawLine(475,525,475,515);
                    g.drawLine(450,525,450,510);
                    g.drawLine(425,525,425,515);
                    g.drawLine(400,525,400,510);
                    g.drawLine(375,525,375,515);
                    g.drawLine(350,525,350,510);
                    g.drawLine(325,525,325,515);
                    g.drawLine(300,525,300,510);
                    g.drawLine(275,525,275,515);
                    g.drawLine(250,525,250,510);
                    g.drawLine(225,525,225,515);
                    g.drawLine(200,525,200,510);
                    g.drawLine(175,525,175,515);
                    g.drawLine(150,525,150,510);
                    g.drawLine(125,525,125,515);
                    g.drawLine(100,525,100,510);
                    g.drawLine(75,525,75,515);
                    g.drawLine(50,525,50,510);
                    g.drawLine(25,525,25,515);
                    g.drawLine(0,525,0,510);

                    g.drawString("500",490,535);
                    g.drawString("450",440,535);
                    g.drawString("400",390,535);
                    g.drawString("350",340,535);
                    g.drawString("300",290,535);
                    g.drawString("250",240,535);
                    g.drawString("200",190,535);
                    g.drawString("150",140,535);
                    g.drawString("100",90,535);
                    g.drawString("50",45,535);
                    g.drawString("0",0,535);

                    g.drawString("5", 590, 240);
                    g.drawString("0", 590, 250);
                    g.drawString("0", 590, 260);
                    g.drawString("M", 590, 280);
                    g.drawString("e", 590, 290);
                    g.drawString("t", 590, 300);
                    g.drawString("r", 590, 310);
                    g.drawString("o", 590, 320);
                    g.drawString("s", 590, 330);   

                    g.drawString("500 Metros ", 250, 550);
                    
                    if(!this.objeto.isEmpty()){
                        for(MobileObject obj : this.objeto){
                            
                            switch(num.nextInt(15)){
                                case 1:{
                                    x = obj.getLocation().getX();
                                    y = obj.getLocation().getY();
                                    point = new Point(x,y);
                                    obj.setLocation(point);
                                    obj.setTrajectory(point);
                                    break;
                                }
                                case 2:{
                                    x = obj.getLocation().getX() + 1;
                                    y = obj.getLocation().getY();
                                    point = new Point(x,y);
                                    obj.setLocation(point);
                                    obj.setTrajectory(point);
                                    break;
                                }
                                case 3:{
                                    x = obj.getLocation().getX() + 1;
                                    y = obj.getLocation().getY() - 1;
                                    point = new Point(x,y);
                                    obj.setLocation(point);
                                    obj.setTrajectory(point);
                                    break;
                                }
                                case 4:{
                                    x = obj.getLocation().getX();
                                    y = obj.getLocation().getY() - 1;
                                    point = new Point(x,y);
                                    obj.setLocation(point);
                                    obj.setTrajectory(point);
                                    break;
                                }
                                case 5:{
                                    x = obj.getLocation().getX() - 1;
                                    y = obj.getLocation().getY() - 1;
                                    point = new Point(x,y);
                                    obj.setLocation(point);
                                    obj.setTrajectory(point);
                                    break;
                                }
                                case 6:{
                                    x = obj.getLocation().getX() - 1;
                                    y = obj.getLocation().getY();
                                    point = new Point(x,y);
                                    obj.setLocation(point);
                                    obj.setTrajectory(point);
                                    break;
                                }
                                case 7:{
                                    x = obj.getLocation().getX() - 1;
                                    y = obj.getLocation().getY() + 1;
                                    point = new Point(x,y);
                                    obj.setLocation(point);
                                    obj.setTrajectory(point);
                                    break;
                                }
                                case 8:{
                                    x = obj.getLocation().getX();
                                    y = obj.getLocation().getY() + 1;
                                    point = new Point(x,y);
                                    obj.setLocation(point);
                                    obj.setTrajectory(point);
                                    break;
                                }
                                case 9:{
                                    x = obj.getLocation().getX() + 1;
                                    y = obj.getLocation().getY() + 1;
                                    point = new Point(x,y);
                                    obj.setLocation(point);
                                    obj.setTrajectory(point);
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.print("");
                    //System.out.println("Aun no existen objetos " + e);
                }

                this.leer();
                int width = (getWidth() - this.img.getWidth()) / 15;
                int height = (getHeight() - this.img.getHeight()) / 15;
                g.setColor(this.lightBlue);
                //g.fillOval(X, Y, WIDTH, HEIGHT);
                //g.fillOval(this.X, this.Y, 50, 50);
                //g.setColor(Color.BLACK);
                //g.drawOval(this.X, this.Y, 50, 50);
                
                
                System.out.print(x);

                try {
                    for(Sensor nodo : this.nodos){
                        x = (int) Math.round(nodo.getPox());
                        y = (int) Math.round(nodo.getPoy());
                        if(nodo.getVerID())
                            id = Integer.toString(nodo.getID());
                        else
                            id = "";

                        if(nodo.getCurrentState() != null){
                            switch(nodo.getCurrentState()){
                                case IDLE:{
                                    g.setColor(Color.RED);
                                    g.fillOval(x, y, 50, 50);
                                    g.setColor(Color.BLACK);
                                    g.drawOval(x, y, 50, 50);
                                    g.drawString(id, x +20, y+20);
                                }
                                break;
                                case ACTIVE:{
                                    g.setColor(this.rosa);
                                    g.fillOval(x, y, 50, 50);
                                    g.setColor(Color.BLACK);
                                    g.drawOval(x, y, 50, 50);
                                    g.drawString(id, x +20, y+20);
                                }
                                break;
                                case SLEEP:{
                                    g.setColor(this.lightBlue);
                                    g.fillOval(x, y, 50, 50);
                                    g.setColor(Color.BLACK);
                                    g.drawOval(x, y, 50, 50);
                                    g.drawString(id, x +20, y+20);
                                }
                                break;
                                    /*
                                default :{
                                    g.drawString(id, x, y);
                                    g.setColor(this.lightBlue);
                                    //g.fillOval(x, y, 50, 50);
                                    g.setColor(Color.BLACK);
                                    g.drawOval(x, y, 50, 50);
                                    g.drawString(id, x +20, y+20);
                                }
                                break;
                                            */
                            }
                        }
                        else{
                            g.setColor(this.lightBlue);
                            g.fillOval(x, y, 50, 50);
                            g.setColor(Color.BLACK);
                            g.drawOval(x, y, 50, 50);
                            g.drawString(id, x +20, y+20);
                        }
                        
                        if(this.verEstacionBase){
                            if(nodo.getID() == this.estacionBase){
                                g.drawImage(this.imgSensor, x, y, width, height, this);
                            }
                        }
                        
                        if(nodo.getVerBateria()){
                            
                            System.out.println("Sensor " + nodo.getID() + " Porcentaje: " + nodo.getBatteryPercentage());
                            
                            if(nodo.getBatteryPercentage()>=0 && nodo.getBatteryPercentage()<=25)
                            {
                                g.setColor(this.rojo);
                                g.fillRect(x + 10, y + 25, 25, 5);
                                g.setColor(Color.BLACK);
                                g.drawRect(x + 10, y+ 25, 25, 5);
                            }

                            if (nodo.getBatteryPercentage() >= 26 && nodo.getBatteryPercentage() <= 50)
                            {
                                g.setColor(this.amarillo);
                                g.fillRect(x + 10, y + 25, 25, 5);
                                g.setColor(Color.BLACK);
                                g.drawRect(x + 10, y+ 25, 25, 5);
                            }

                            if (nodo.getBatteryPercentage() >= 51 && nodo.getBatteryPercentage() <= 75)
                            {
                                g.setColor(this.naranja);
                                g.fillRect(x + 10, y + 25, 25, 5);
                                g.setColor(Color.BLACK);
                                g.drawRect(x + 10, y+ 25, 25, 5);
                            }

                            // full:
                            if (nodo.getBatteryPercentage() >= 76 && nodo.getBatteryPercentage() <= 100)
                            {
                                g.setColor(this.verde);
                                g.fillRect(x + 10, y + 25, 25, 5);
                                g.setColor(Color.BLACK);
                                g.drawRect(x + 10, y+ 25, 25, 5);
                            }
                            
                        }
                        
                    }
                } catch (Exception e) {
                    System.out.print("");
                }

                try {
                    if(!this.objeto.isEmpty()){
                        for(MobileObject obj : this.objeto){
                            g.setColor(null);
                            g.drawImage(this.img, obj.getLocation().getX(), obj.getLocation().getY(), width, height, this);
                        }
                    }
                } catch (Exception e) {
                    System.out.print("");
                    //System.out.println("Aun no existen objetos " + e);
                }

                try {
                    if(!this.borde.isEmpty()){
                        Graphics2D g2D = (Graphics2D) g;
                        g2D.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        for(Line linea : this.borde){
                            g2D.setColor(Color.BLACK);
                            g2D.drawLine(linea.getX1(), linea.getY1(), linea.getX2(), linea.getY2());
                        }
                    }
                } catch (Exception e) {
                    System.out.print("");
                    //System.out.println("Aun no existen borde " + e);
                }

                // Desechar los gráficos
                g.dispose();

                // Repita la representación si se restauraron los contenidos del búfer de dibujo
            } while (strategy.contentsRestored());

            // Mostrar el búfer
            strategy.show();

            // Repita la representación si se perdió el búfer de dibujo
        } while (strategy.contentsLost());

        
    }
    
    public void insert() {
        repaint();
    }
    
    public void probar() {
        System.out.println("Probando objeto");
    }
    
    public void leer() {
        try {
            this.img = ImageIO.read(new File("src/com/smartsoft/gui/pictures/person.png"));
            this.imgSensor = ImageIO.read(new File("src/com/smartsoft/gui/pictures/sensors.png"));
            //this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
            //System.out.println(this.img);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}

