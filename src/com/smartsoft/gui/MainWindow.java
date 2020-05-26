/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.gui;

import com.smartsoft.computations.GetOverlappingNodes;
import com.smartsoft.mobileObject.MobileObject;
import com.smartsoft.db.NetworkTopology;
import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.util.TimerTask;
import java.util.Timer;
import java.util.ArrayList;


import com.smartsoft.model.NetworkImport;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.EdgeLine;
import com.smartsoft.modules.SensorsGroup;
import com.smartsoft.coverage.GridCoverage;
import com.smartsoft.coverage.ZizageCoverage;
import com.smartsoft.groupingOperations.MaximalGrouping;
import com.smartsoft.nt.NTnode;
import com.smartsoft.routing.Algorithms.HierarchicalSpanningTreeRouting;
import com.smartsoft.modules.Line;
import com.smartsoft.routing.Algorithms.HierarchicalSpanningRouting;
import com.smartsoft.routing.Packages.HSTPacket;
/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */

public class MainWindow extends JFrame implements ActionListener{
    
    
    private JFrame frame;
    private JMenu subMenuArchivo;
    private JMenu MenuVista;
    private JMenu MenuCobertura;
    private JMenu MenuEstacionBase;
    private JMenu MenuOtros;
    private JMenu subMenuTiempoS ;
    private JMenuBar menuContenedor;
    private JPanel panel1,panel2, panel3;
    private JMenuItem subMenuAddObjMov;
    private JMenuItem subMenuSimu;
    private JMenuItem subMenuStoSimu;
    private JMenuItem subMenuImportTopo;
    private JMenuItem subMenuSalir;
    private JMenuItem cien;
    private JMenuItem dosc;
    private JMenuItem tresc;
    private JMenuItem cuatroc;
    private JMenuItem cincoc;
    private JMenuItem seisc;
    private JMenuItem sietec;
    private JMenuItem ochoc;
    private JMenuItem nuevec;
    private JMenuItem diezc;
    private JMenuItem subMenuVerID;
    private JMenuItem subMenuVerBateria;
    private JMenuItem subMenuVerBorde;
    private JMenuItem subMenuVerAntena;
    private JMenuItem subMenuCoberRandom;
    private JMenuItem subMenuCoberGrid1;
    private JMenuItem subMenuCoberGrid2;
    private JMenuItem subMenuCoberZigzag;
    
    private JMenuItem subMenuCentral;
    private JMenuItem subMenuTop;
    private JMenuItem subMenuButtom;
    private JMenuItem subMenuLeft;
    private JMenuItem subMenuRigth;
    
    private JMenuItem subMenuOtrosTR;
    //private JMenuItem subMenuOtrosMX; // Visualiza Maxima cobertura 
    private JMenuItem subMenuOtrosRG;  //Imprime la red de grupos
    private JMenuItem subMenuOtrosLN; //Imprime la localización de nodos
    private JMenuItem subMenuOtrosDG; //Imprime datos de grupos
    private JMenuItem subMenuOtrosAEJ; //Impriem arbol de expansion jerarquíca
    private JMenuItem subMenuOtrosVP; //Ver paquetes
    private JMenuItem subMenuOtrosRS; //Imprime resumen de sensor
    private JMenuItem subMenuOtrosAN; // Imprime árbol de notificaciones
    private JMenuItem subMenuOtrosLogN; // imprime log de notificaciones
    //private JMenuItem subMenuOtrosTO;// imprime trayectoria de cada objeto.
    private JMenuItem subMenuOtrosDTo; // dibuja trayectoria de objetos
    
    
    
    private JLabel numTarget;  // número de objeto
    private JLabel speedTarget; // velociddad de objeto
    private JLabel pauseTime; //
    private JLabel numNode; // número de nodo
    private JLabel simulationTime;  // tiempo de simulacion
    private JLabel simulationCounter; // contador de simuacion
    private JLabel propiedades [];
    
    private ArrayList<SensorsGroup> NetWorkGroups = new ArrayList<>();
    private ArrayList<Sensor> myNetWork = new ArrayList<>();
    private ArrayList<MobileObject> MobileObjects = new ArrayList<>();
    private ArrayList<Line> line = new ArrayList<>();
    public ArrayList<HSTPacket> RoutingPackages = new ArrayList<>();
    private ArrayList<NetworkImport> topologias;
    private HierarchicalSpanningRouting HST_RoutingModel; // El HST
    private NetworkTopology importarTopologias;
    
    private GUIAddMobileObjects objeto;
    GUIImportTopology importar;
    
    private Timer tiempo ;
    private TimerTask task;
    private Dibujo simulador;
    private Indicadores indicador;
    private int miliSe = 0;
    private int sumSegun = 0;
    
    public int rootNodeIndex = 192; // s1= 0, s2= 192.
    public double SinkNodeBattryCapacity = 1000000;// jouls.
    public double NodesBattryCapacity = 10; // intial energy
    public double EnergyThreshould = 70;

    public ArrayList<Sensor> getMyNetWork() {
        return myNetWork;
    }

    public int getRootNodeIndex() {
        return rootNodeIndex;
    }

    public void setRootNodeIndex(int rootNodeIndex) {
        this.rootNodeIndex = rootNodeIndex;
    }
    

    public void setMyNetWork(ArrayList<Sensor> myNetWork) {
        this.myNetWork = myNetWork;
    }
    
    /**
    * Ventana Principal del simulador.
    */
    
    public MainWindow(){
/**
 *  Creación del  Panel principal con sus respectivos elementos
 *  como menú, panel de simulación y panel de información
 */    
        this.frame=new JFrame ("Simulador");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setBackground(Color.WHITE);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/com/smartsoft/gui/pictures/IconOption.png")).getImage());
	//this.frame.setBounds(200, 100, 360, 500);
        this.frame.setLayout(null);
	this.frame.setResizable(false);
        //setVisible(true);

        /*
        this.panel1=new JPanel (){
            
            @Override
            public void paint(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.red);
            g.drawOval(100, 100, 15, 25);
            // super.paint(g); //Para cambiar el cuerpo de los métodos generados, elija Herramientas | Plantillas
            }
            
        };
        */
        
        this.panel1 =new JPanel();
        // Creando Paneles
        // Panel de red
        this.panel1.setBackground(Color.WHITE);
        this.panel1.setBounds(0,0,600,700);
        this.frame.add(this.panel1);
        this.panel1.setLayout(null);
        
        this.simulador = new Dibujo();
        this.panel1.add(this.simulador);
        
        
        //Panel información
        this.panel2=new JPanel();
        this.panel2.setBackground(Color.LIGHT_GRAY);
        this.panel2.setBounds(0,0,1000,200);
        this.frame.add(this.panel2);
        this.panel2.setLayout(null);
        
        this.panel3=new JPanel();
        this.panel3.setBackground(Color.WHITE);
        this.panel3.setBounds(0,200,1000,360);
        this.frame.add(this.panel3);
        this.panel3.setLayout(null);
        
        this.indicador = new Indicadores();
        this.panel3.add(this.indicador);
        
        // Etiquetas para panel información
        
        this.numTarget=new JLabel("Número de objetos");
        this.speedTarget=new JLabel("Velocidad de objetos");
        this.pauseTime=new JLabel("Tiempo de pausa");
        this.numNode=new JLabel("Número de nodos");
        this.simulationTime=new JLabel("Tiempo de simulación");
        this.simulationCounter=new JLabel("Contador de simulación");
        
        // agregando etiquetas en panel de información
        this.panel2.add(this.numTarget);
        this.panel2.add(this.speedTarget);
        this.panel2.add(this.pauseTime);
        this.panel2.add(this.numNode);
        this.panel2.add(this.simulationTime);
        this.panel2.add(this.simulationCounter);
        
        this.propiedades = new JLabel[6];
        this.propiedades[0] = this.numTarget;
        this.propiedades[1] = this.speedTarget;
        this.propiedades[2] = this.pauseTime;
        this.propiedades[3] = this.numNode;
        this.propiedades[4] = this.simulationTime;
        this.propiedades[5] = this.simulationCounter;
        
        this.numTarget.setBounds(620,15,170,25);
        this.speedTarget.setBounds(620,40,170,25);
        this.pauseTime.setBounds(620,65,170,25);
        this.numNode.setBounds(620,90,175,25);
        this.simulationTime.setBounds(620,115,200,25);
        this.simulationCounter.setBounds(620,140,250,25);

/**
 * //Creando la barra de menús
 */
        
        this.menuContenedor=new JMenuBar();
        setJMenuBar(this.menuContenedor);
        // Creando menú Archivo
        this.subMenuArchivo=new JMenu("Archivo");
        this.menuContenedor.add(this.subMenuArchivo);
        //Submenu Tiempo simulación
        subMenuTiempoS=new JMenu("Tiempo simulación");
       
        // Creando Item de SubMenu Tiempo simulación
            this.cien=new JMenuItem("100");
            this.subMenuTiempoS.add(cien);
            this.cien.addActionListener(this);
            this.dosc=new JMenuItem("200");
            this.subMenuTiempoS.add(dosc);
            this.dosc.addActionListener(this);
            this.tresc=new JMenuItem("300");
            this.subMenuTiempoS.add(tresc);
            this.tresc.addActionListener(this);
            this.cuatroc=new JMenuItem("400");
            this.subMenuTiempoS.add(cuatroc);
            this.cuatroc.addActionListener(this);
            this.cincoc=new JMenuItem("500");
            this.subMenuTiempoS.add(cincoc);
            this.cincoc.addActionListener(this);
            this.seisc=new JMenuItem("600");
            this.subMenuTiempoS.add(seisc);
            this.seisc.addActionListener(this);
            this.sietec=new JMenuItem("700");
            this.subMenuTiempoS.add(sietec);
            this.sietec.addActionListener(this);
            this.ochoc=new JMenuItem("800");
            this.subMenuTiempoS.add(ochoc);
            this.ochoc.addActionListener(this);
            this.nuevec=new JMenuItem("900");
            this.subMenuTiempoS.add(nuevec);
            this.nuevec.addActionListener(this);
            this.diezc=new JMenuItem("1000");
            this.subMenuTiempoS.add(diezc);
            this.diezc.addActionListener(this);
        //Menu Mostrar
        this.MenuVista=new JMenu("Mostrar");
        this.menuContenedor.add(this.MenuVista);
        // Menu Cobertura
        this.MenuCobertura=new JMenu("Cobertura");
        this.menuContenedor.add(this.MenuCobertura);
        // Menu Estacion Base
        this.MenuEstacionBase=new JMenu("Estacion Base");
        this.menuContenedor.add(this.MenuEstacionBase);
        // Menu Otros
        this.MenuOtros=new JMenu("Otros");
        this.menuContenedor.add(this.MenuOtros);
        
        this.frame.setJMenuBar(this.menuContenedor);
        this.frame.setSize(1000,600);
        this.frame.setLocationRelativeTo(null);
        this.frame.show();


       // Creando Item's de menu Archivo
        this.subMenuAddObjMov = new JMenuItem("Agregar Objetos");
        this.subMenuSimu = new JMenuItem ("Iniciar Simulación");
        this.subMenuStoSimu = new JMenuItem ("Detener Simulación");
        this.subMenuImportTopo = new JMenuItem ("Importar Topología");
        this.subMenuSalir = new JMenuItem("Salir");
        // Creando Items de Menu Mostrar
        this.subMenuVerID=new JMenuItem("Ver ID");
        this.subMenuVerBateria=new JMenuItem("Ver Bateria");
        this.subMenuVerBorde=new JMenuItem("Ver Borde");
        this.subMenuVerAntena=new JMenuItem("Ver Estacion Base");
        
        // Creando Items de Menu Cobertura
        this.subMenuCoberRandom=new JMenuItem ("Random");
        this.subMenuCoberGrid1=new JMenuItem ("Grid 1");
        this.subMenuCoberGrid2=new JMenuItem ("Grid 2");
        this.subMenuCoberZigzag=new JMenuItem ("Zigzag");
        // Creando Items de Menu EstacionBase
        this.subMenuCentral =new JMenuItem ("Central");
        this.subMenuTop =new JMenuItem ("Superior");
        this.subMenuButtom =new JMenuItem ("Inferior");
        this.subMenuLeft =new JMenuItem ("Izquierda");
        this.subMenuRigth =new JMenuItem ("Derecha");
        // Creando Items de Menu Otros
        this.subMenuOtrosTR=new JMenuItem ("Ver reporte Total");
        //this.subMenuOtrosMX=new JMenuItem ("Visualiza ubicaciones máximas cubiertas");
        this.subMenuOtrosRG=new JMenuItem ("Imprimir grupos de red");
        this.subMenuOtrosLN=new JMenuItem ("Imprimir localización de nodos");
        this.subMenuOtrosDG=new JMenuItem ("Imprimir datos de grupos");
        this.subMenuOtrosAEJ=new JMenuItem ("Imprimir árbol de expansión jerarquíca");
        this.subMenuOtrosVP=new JMenuItem ("Vista de paquetes");
        this.subMenuOtrosRS=new JMenuItem ("Imprime resumen de Sensor");
        this.subMenuOtrosAN=new JMenuItem ("Imprime árbol de notificaciones");
        this.subMenuOtrosLogN=new JMenuItem ("Imprime log de notificaciones");
        //this.subMenuOtrosTO=new JMenuItem ("Imprime trayectoria de cada objeto");
        this.subMenuOtrosDTo=new JMenuItem ("Dibuja trayectoria para objetos");

        //Action de Menu Archivo
        this.subMenuAddObjMov.addActionListener(this);
        this.subMenuSimu.addActionListener(this);
        this.subMenuStoSimu.addActionListener(this);
        this.subMenuImportTopo.addActionListener(this);
        this.subMenuSalir.addActionListener(this);
        
        //Action de Menu Mostrar
        this.subMenuVerID.addActionListener(this);
        this.subMenuVerBateria.addActionListener(this);
        this.subMenuVerBorde.addActionListener(this);
        this.subMenuVerAntena.addActionListener(this);
        
        //Action de Menu Cobertura
        this.subMenuCoberRandom.addActionListener(this);
        this.subMenuCoberGrid1.addActionListener(this);
        this.subMenuCoberGrid2.addActionListener(this);
        this.subMenuCoberZigzag.addActionListener(this);
        
        //Action de Menu Estacion Base
        this.subMenuCentral.addActionListener(this);
        this.subMenuTop.addActionListener(this);
        this.subMenuButtom.addActionListener(this);
        this.subMenuLeft.addActionListener(this);
        this.subMenuRigth.addActionListener(this);

        //Action de Menu Otros
        this.subMenuOtrosTR.addActionListener(this);
        //this.subMenuOtrosMX.addActionListener(this);
        this.subMenuOtrosRG.addActionListener(this);
        this.subMenuOtrosLN.addActionListener(this);
        this.subMenuOtrosDG.addActionListener(this);
        this.subMenuOtrosAEJ.addActionListener(this);
        this.subMenuOtrosVP.addActionListener(this);
        this.subMenuOtrosRS.addActionListener(this);
        this.subMenuOtrosAN.addActionListener(this);
        this.subMenuOtrosLogN.addActionListener(this);
        //this.subMenuOtrosTO.addActionListener(this);
        this.subMenuOtrosDTo.addActionListener(this);
        

        //agregando Item al Menú Archivo
        this.subMenuArchivo.add(this.subMenuAddObjMov);
        this.subMenuArchivo.add(this.subMenuSimu);
        this.subMenuArchivo.add(this.subMenuStoSimu);
        this.subMenuArchivo.add(this.subMenuImportTopo);
        this.subMenuArchivo.add(this.subMenuTiempoS);
        this.subMenuArchivo.add(this.subMenuSalir);
        
        //agregando Item a Menu Mostrar
        this.MenuVista.add(this.subMenuVerID);
        this.MenuVista.add(this.subMenuVerBateria);
        this.MenuVista.add(this.subMenuVerBorde);
        this.MenuVista.add(this.subMenuVerAntena);
        
        //agregando Item a Menu Cobertura
        this.MenuCobertura.add(this.subMenuCoberRandom);
        this.MenuCobertura.add(this.subMenuCoberGrid1);
        this.MenuCobertura.add(this.subMenuCoberGrid2);
        this.MenuCobertura.add(this.subMenuCoberZigzag);
        
        //agregando Item a Menu Estacion Base
        this.MenuEstacionBase.add(this.subMenuCentral);
        this.MenuEstacionBase.add(this.subMenuTop);
        this.MenuEstacionBase.add(this.subMenuButtom);
        this.MenuEstacionBase.add(this.subMenuLeft);
        this.MenuEstacionBase.add(this.subMenuRigth);
        
        //agregando Item a Menu Otros
        this.MenuOtros.add(this.subMenuOtrosTR);
        //this.MenuOtros.add(this.subMenuOtrosMX);
        this.MenuOtros.add(this.subMenuOtrosRG);
        this.MenuOtros.add(this.subMenuOtrosLN);
        this.MenuOtros.add(this.subMenuOtrosDG);
        this.MenuOtros.add(this.subMenuOtrosAEJ);
        this.MenuOtros.add(this.subMenuOtrosVP);
        this.MenuOtros.add(this.subMenuOtrosRS);
        this.MenuOtros.add(this.subMenuOtrosAN);
        this.MenuOtros.add(this.subMenuOtrosLogN);
        //this.MenuOtros.add(this.subMenuOtrosTO);
        this.MenuOtros.add(this.subMenuOtrosDTo);
    }
    /**
     * 
     * @param e Eventos(acciones)  que se desenlazan de acuerdo al botón oprimido en los menús del simulador
     * 
     */
    public void actionPerformed(ActionEvent e) {        
        if (e.getSource()==this.subMenuAddObjMov){
            this.myNetWork = this.importar.getSensores();
            this.objeto=new GUIAddMobileObjects (this.simulador, this.propiedades, this.myNetWork);
            this.frame.setVisible(true);
            //JOptionPane.showMessageDialog(this.frame, "Presiono el boton subMenuAddObjMov");
        }
     
        if (e.getSource()==this.subMenuSimu){
            startAnimation();
        }
        if (e.getSource()==this.subMenuStoSimu){
            stopAnimation();
            //JOptionPane.showMessageDialog(this.frame, "Se detuvo la simulación"); // como detener simulación?  System.exit(1); 
        }
        if (e.getSource()==this.subMenuImportTopo){
            //JOptionPane.showMessageDialog(this.frame, "Presiono el boton subMenuImportTopo");
           importarTopologias=new NetworkTopology();
           topologias= importarTopologias.ImportNetworkNames();
           this.importar = new GUIImportTopology (this.simulador, topologias, this.panel1, this.propiedades, this.myNetWork);
        }
        if (e.getSource()==this.subMenuSalir){
            System.exit(0); 
        }
        
        if(e.getSource() == this.cien){
            //this.miliSe = 15 * 1000;
            this.simulationTime.setText("Tiempo de simulación: " + this.cien.getText());
            this.miliSe = Integer.parseInt(this.cien.getText()) * 1000;
        }
        if(e.getSource() == this.dosc){
            this.simulationTime.setText("Tiempo de simulación: " + this.dosc.getText());
            this.miliSe = Integer.parseInt(this.dosc.getText()) * 1000;
        }
        if(e.getSource() == this.tresc){
            this.simulationTime.setText("Tiempo de simulación: " + this.tresc.getText());
            this.miliSe = Integer.parseInt(this.tresc.getText()) * 1000;
        }
        if(e.getSource() == this.cuatroc){
            this.simulationTime.setText("Tiempo de simulación: " + this.cuatroc.getText());
            this.miliSe = Integer.parseInt(this.cuatroc.getText()) * 1000;
        }
        if(e.getSource() == this.cincoc){
            this.simulationTime.setText("Tiempo de simulación: " + this.cincoc.getText());
            this.miliSe = Integer.parseInt(this.cincoc.getText()) * 1000;
        }
        if(e.getSource() == this.seisc){
            this.simulationTime.setText("Tiempo de simulación: " + this.seisc.getText());
            this.miliSe = Integer.parseInt(this.seisc.getText()) * 1000;
        }
        if(e.getSource() == this.sietec){
            this.simulationTime.setText("Tiempo de simulación: " + this.sietec.getText());
            this.miliSe = Integer.parseInt(this.sietec.getText()) * 1000;
        }
        if(e.getSource() == this.ochoc){
            this.simulationTime.setText("Tiempo de simulación: " + this.ochoc.getText());
            this.miliSe = Integer.parseInt(this.ochoc.getText()) * 1000;
        }
        if(e.getSource() == this.nuevec){
            this.simulationTime.setText("Tiempo de simulación: " + this.nuevec.getText());
            this.miliSe = Integer.parseInt(this.nuevec.getText()) * 1000;
        }
        if(e.getSource() == this.diezc){
            this.simulationTime.setText("Tiempo de simulación: " + this.diezc.getText());
            this.miliSe = Integer.parseInt(this.diezc.getText()) * 1000;
        }
        
        if(e.getSource() == this.subMenuCoberRandom){
                this.Coverage_Click(e);
        }
        if(e.getSource() == this.subMenuCoberGrid1){
                this.Coverage_Click(e);
        }
        if(e.getSource() == this.subMenuCoberGrid2){
                this.Coverage_Click(e);
        }
        if(e.getSource() == this.subMenuCoberZigzag){
                this.Coverage_Click(e);
        }
        
        if(e.getSource() == this.subMenuCentral){
                this.base_station_position_Click(e);
        }
        if(e.getSource() == this.subMenuTop){
                this.base_station_position_Click(e);
        }
        if(e.getSource() == this.subMenuButtom){
                this.base_station_position_Click(e);
        }
        if(e.getSource() == this.subMenuLeft){
                this.base_station_position_Click(e);
        }
        if(e.getSource() == this.subMenuRigth){
                this.base_station_position_Click(e);
        }
        
        if(e.getSource() == this.subMenuVerID){
                this.Display_Click(e);
        }
        if(e.getSource() == this.subMenuVerBateria){
                this.Display_Click(e);
        }
        if(e.getSource() == this.subMenuVerBorde){
                this.Display_Click(e);
        }
        if(e.getSource() == this.subMenuVerAntena){
                this.Display_Click(e);
        }
        
        if(e.getSource() == this.subMenuOtrosTR){
                this.btn_other_Menu(e);
        }
        /**if(e.getSource() == this.subMenuOtrosMX){
                this.btn_other_Menu(e);
        }**/
        if(e.getSource() == this.subMenuOtrosRG){
                this.btn_other_Menu(e);
        }
        if(e.getSource() == this.subMenuOtrosLN){
                this.btn_other_Menu(e);
        }
        if(e.getSource() == this.subMenuOtrosDG){
                this.btn_other_Menu(e);
        }
        if(e.getSource() == this.subMenuOtrosAEJ){
                this.btn_other_Menu(e);
        }
        if(e.getSource() == this.subMenuOtrosVP){
                this.btn_other_Menu(e);
        }
        if(e.getSource() == this.subMenuOtrosRS){
                this.btn_other_Menu(e);
        }
        if(e.getSource() == this.subMenuOtrosAN){
                this.btn_other_Menu(e);
        }
        if(e.getSource() == this.subMenuOtrosLogN){
                this.btn_other_Menu(e);
        }
        /**
        if(e.getSource() == this.subMenuOtrosTO){
                this.btn_other_Menu(e);
        }**/
        if(e.getSource() == this.subMenuOtrosDTo){
                this.btn_other_Menu(e);
        }
        
    }
    
    public void startAnimation() {    
           //se inicia la animacion
        if (this.importar != null) {
            if(this.objeto != null){
                if(this.miliSe != 0){
                    
                    HST_RoutingModel = new HierarchicalSpanningRouting(this.myNetWork, this.rootNodeIndex, this.RoutingPackages);
                    //System.out.println("Start Simulacion Childern: " + this.myNetWork.get(0).getChildern());
                    
                    this.tiempo = new Timer();
                    task = new TimerTask() {  
                        long segundos = System.currentTimeMillis();
                        long contando;
                        @Override
                        public void run() {
                            MobileObjects = simulador.getObjeto();
                            contando = System.currentTimeMillis();
                            simulador.repaint();
                            simulationCounter.setText("Contador de simulación: " + ((int) (sumSegun + ((contando - segundos)/ 1000))));
                            
                            switch((int) ((contando - segundos)/ 1000) % 100){
                                case 100:
                                    System.out.println("Round 1 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                                case 200:
                                    System.out.println("Round 2 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                                case 300:
                                    System.out.println("Round 3 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                                    case 400:
                                    System.out.println("Round 4 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                                    case 500:
                                    System.out.println("Round 5 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                                    case 600:
                                    System.out.println("Round 6 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                                    case 700:
                                    System.out.println("Round 7 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                                    case 800:
                                    System.out.println("Round 8 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                                    case 900:
                                    System.out.println("Round 9 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                                    case 1000:
                                    System.out.println("Round 10 Tiempo" + ((contando - segundos)/ 1000));
                                    break;
                            }

                            
                            for(Sensor sensor : myNetWork){
                                sensor.sensando(MobileObjects, (int) (contando - segundos)/ 1000, HST_RoutingModel);
                                sensor.StartTrackingAndReporting(HST_RoutingModel);
                            }

                            /*
                            if (System.currentTimeMillis() - t0 > 10 * 1000) {
                            tiempo.cancel();
                            task.cancel();
                            }*/
                            if (System.currentTimeMillis() - segundos > miliSe) {
                                sumSegun = (int) (sumSegun + ((contando - segundos)/ 1000));
                                tiempo.cancel();
                                task.cancel();
                            }
                        }
                    };
                    this.tiempo.scheduleAtFixedRate(task, 1000, 1000 / 30);
                }
                else{
                    JOptionPane.showMessageDialog(this.frame, "Seleccione Tiempo de simulación");
                }
            }
            else{
                JOptionPane.showMessageDialog(this.frame, "Seleccione Agregar Objetos");
            }
        }
        else{
            JOptionPane.showMessageDialog(this.frame, "Seleccione Importar topologia");
        }
    }

     public void stopAnimation() {
        this.tiempo.cancel();
        this.task.cancel();
        int i = 1;
        /*
        for(MobileObject obj : this.MobileObjects){
            System.out.println("Objeto: " + i);
            for(Point puntos : obj.getTrajectory()){
                System.out.println("X: " + puntos.getX() + " Y: " + puntos.getY());
            }
            i++;
        }
        */
    }
     
     private void Display_Click(ActionEvent e){
         switch (e.getActionCommand()){
                case "Ver ID":{
                    
                    
                    for(Sensor sensor : myNetWork){
                        if(!sensor.getVerID())
                            sensor.setVerID(true);
                        else
                            sensor.setVerID(false);
                    }
                    this.simulador.setNodos(myNetWork);
                    this.simulador.insert();
                    this.panel1.repaint();
                }
                break;
                case "Ver Bateria":{
                    for(Sensor sensor : myNetWork){
                        if(!sensor.getVerBateria())
                            sensor.setVerBateria(true);
                        else
                            sensor.setVerBateria(false);
                    }
                    this.simulador.setNodos(myNetWork);
                    this.simulador.insert();
                    this.panel1.repaint();
                }
                break;
                case "Ver Borde":{
                    if(this.simulador.getBorde() == null || this.simulador.getBorde().isEmpty()){
                        this.DrawForRootNode(this.myNetWork.get(this.rootNodeIndex));
                        this.simulador.setBorde(this.line);
                        this.simulador.insert();
                        this.panel1.repaint();
                    }
                    else{
                        this.line.clear();
                        this.simulador.setBorde(this.line);
                        this.simulador.insert();
                        this.panel1.repaint();
                    }
                }
                break;
                    case "Ver Estacion Base":{
                        if(!this.simulador.getVerEstacionBase()){
                            this.simulador.setVerEstacionBase(true);
                            this.simulador.setEstacionBase(this.rootNodeIndex);
                            this.simulador.insert();
                            this.panel1.repaint();
                        }
                        else{
                            this.simulador.setVerEstacionBase(false);
                            this.simulador.insert();
                            this.panel1.repaint();
                        }
                        
                    }
                    break;
         }
     }
     
     public void base_station_position_Click(ActionEvent e){
         
         System.out.println(this.importar.getNombreTopologia());
         switch (e.getActionCommand()){
                case "Central":{
                    switch(this.importar.getNombreTopologia()){
                        case "grid1":{
                            this.rootNodeIndex = 97;
                        }
                        break;
                        case "grid2":{
                            this.rootNodeIndex = 140;
                        }
                        break;    
                        case "zigzag":{
                            this.rootNodeIndex = 58;
                        }
                        break;
                        case "17NodosRandom":{
                            this.rootNodeIndex = 5;
                        }
                        break;
                        case "100rn":{
                            this.rootNodeIndex = 0;
                        }
                        break;
                        case "418_zig_15m":{
                            this.rootNodeIndex = 208;
                        }
                        break;
                        default:{
                            this.rootNodeIndex = 0;
                        }
                        break;
                    }
                }
                break;
                case "Superior":{
                    switch(this.importar.getNombreTopologia()){
                        case "grid1":{
                            this.rootNodeIndex = 91;
                        }
                        break;
                        case "grid2":{
                            this.rootNodeIndex = 136;
                        }
                        break;    
                        case "zigzag":{
                            this.rootNodeIndex = 11;
                            //this.rootNodeIndex = 0;
                            // X= 0; Y = 37.55
                        }
                        break;
                        case "17NodosRandom":{
                            this.rootNodeIndex = 1;
                        }
                        break;
                            case "100rn":{
                            this.rootNodeIndex = 7;
                        }
                        break;
                        case "418_zig_15m":{
                            this.rootNodeIndex = 17;
                        }
                        break;
                        default:{
                            this.rootNodeIndex = 0;
                        }
                        break;
                    }
                }
                break;
                case "Inferior":{
                    switch(this.importar.getNombreTopologia()){
                        case "grid1":{
                            this.rootNodeIndex = 103;
                        }
                        break;
                        case "grid2":{
                            this.rootNodeIndex = 144;
                        }
                        break;    
                        case "zigzag":{
                            this.rootNodeIndex = 127;
                        }
                        break;
                        case "17NodosRandom":{
                            this.rootNodeIndex = 14;
                        }
                        break;
                            case "100rn":{
                            this.rootNodeIndex = 52;
                        }
                        break;
                        case "418_zig_15m":{
                            this.rootNodeIndex = 398;
                        }
                        break;
                        default:{
                            this.rootNodeIndex = 0;
                        }
                        break;
                    }
                }
                break;
                case "Izquierda":{
                    switch(this.importar.getNombreTopologia()){
                        case "grid1":{
                            this.rootNodeIndex = 6;
                        }
                        break;
                        case "grid2":{
                            this.rootNodeIndex = 5;
                        }
                        break;    
                        case "zigzag":{
                            this.rootNodeIndex = 90;
                        }
                        break;
                        case "17NodosRandom":{
                            this.rootNodeIndex = 12;
                        }
                        break;
                            case "100rn":{
                            this.rootNodeIndex = 42;
                        }
                        break;
                            case "418_zig_15m":{
                            this.rootNodeIndex = 190;
                        }
                        break;
                        default:{
                            this.rootNodeIndex = 0;
                        }
                        break;
                    }
                }
                break;
                case "Derecha":{
                    switch(this.importar.getNombreTopologia()){
                        case "grid1":{
                            this.rootNodeIndex = 175;
                        }
                        break;
                        case "grid2":{
                            this.rootNodeIndex = 95;
                        }
                        break;    
                        case "zigzag":{
                            this.rootNodeIndex = 70;
                        }
                        break;
                        case "17NodosRandom":{
                            this.rootNodeIndex = 11;
                        }
                        break;
                        case "100rn":{
                            this.rootNodeIndex = 1;
                        }
                        break;
                            case "418_zig_15m":{
                            this.rootNodeIndex = 226;
                        }
                        break;
                        default:{
                            this.rootNodeIndex = 0;
                        }
                        break;
                    }
                }
                break;
                
         }
     }
         
    //public void Coverage_Click(ActionEvent e){}
    public void Coverage_Click(ActionEvent e){
        boolean isDelo = true;
            this.myNetWork = this.importar.getSensores();
            // clear all data done before.
            for( Sensor s : myNetWork)
            {
                s.MyGroups.clear();
                if (s.Vector!=null)
                s.Vector.Clear();
            }
            NetWorkGroups.clear();

            // start:
            
            switch (e.getActionCommand())
            {
                case "Grid 1":
                    if (myNetWork.size() > 1)
                    {
                       // rootNodeIndex = 90;
                        // 182 sensors to coverver:500 x 500 m ,sensor r=25., root=90.
                        GridCoverage GridCoverage = new GridCoverage();
                        //GridCoverage.GridCoverage1(myNetWork, Integer.parseInt((25 * 2) * 0.7));
                        Double value = (25 * 2) * 0.7;
                        GridCoverage.GridCoverage1(myNetWork, value.intValue());
                        this.simulador.setNodos(myNetWork);
                        simulador.insert();
                        panel1.repaint();

                        GetOverlappingNodes Verctors = new GetOverlappingNodes(myNetWork);
                        Verctors.GetOverlappingForAllNodes();

                        MaximalGrouping grou = new MaximalGrouping(myNetWork);
                        NetWorkGroups=grou.FindGroups();
                        BuildHierarchicalSpanningTreeRouting();

                    }
                    break;
                    case "Grid 2":
                    if (myNetWork.size() > 1)
                    {
                       // rootNodeIndex = 140;
                        // 181 sensors to coverver:500 x 500 m ,sensor r=25., root=140.
                        GridCoverage GridCoverage = new GridCoverage();
                        //GridCoverage.GridCoverage2(myNetWork, Convert.ToInt16((Sensor.R * 2)));
                        GridCoverage.GridCoverage1(myNetWork, 25 * 2);
                        this.simulador.setNodos(myNetWork);
                        simulador.insert();
                        panel1.repaint();
                        
                        GetOverlappingNodes Verctors = new GetOverlappingNodes(myNetWork);
                        Verctors.GetOverlappingForAllNodes();

                        MaximalGrouping grou = new MaximalGrouping(myNetWork);
                        NetWorkGroups = grou.FindGroups();

                        BuildHierarchicalSpanningTreeRouting();
                    }
                    break;
                        
                    case "Zigzag":
                    if (myNetWork.size() > 1)
                    {
                       // rootNodeIndex = 56;
                        // 138 sensors. to cover 500* 500 , sensor r=25, root 56.
                        ZizageCoverage zig = new ZizageCoverage();
                        zig.coverage(myNetWork, 25 * 2);
                        this.simulador.setNodos(myNetWork);
                        simulador.insert();
                        panel1.repaint();

                        GetOverlappingNodes Verctors = new GetOverlappingNodes(myNetWork);
                        Verctors.GetOverlappingForAllNodes();

                        // grouping 

                        MaximalGrouping grou = new MaximalGrouping(myNetWork);
                        NetWorkGroups=grou.FindGroups();

                        BuildHierarchicalSpanningTreeRouting();
                    }
                    break;
                    case "Random":
                    {
                      //  rootNodeIndex = 0;
                        this.myNetWork = this.importar.resetSensores();
                        this.simulador.setNodos(myNetWork);
                        simulador.insert();
                        panel1.repaint();
                        GetOverlappingNodes Verctors = new GetOverlappingNodes(myNetWork);
                        Verctors.GetOverlappingForAllNodes();

                        MaximalGrouping grou = new MaximalGrouping(myNetWork);
                        NetWorkGroups=grou.FindGroups();
                        //System.out.println("Tamaño NetWorkGroups: " + NetWorkGroups.size());

                        BuildHierarchicalSpanningTreeRouting();
                    }

                    break;
                        
            }
            
    }
    
    private void BuildHierarchicalSpanningTreeRouting()
        {
            ArrayList<NTnode> Tree = new ArrayList<>();
            for (Sensor sensor : this.myNetWork) 
            {
                sensor.getMyNTnode().setID(sensor.getID());
                NTnode n = sensor.getMyNTnode();
                n.setSensor(sensor);
                Tree.add(n); 
            }
            //System.out.println("Tamaño del Tree: " + Tree.size());
            HierarchicalSpanningTreeRouting BuildRoutingTree = new HierarchicalSpanningTreeRouting(Tree, this.rootNodeIndex);
            BuildRoutingTree.BuildTree();
            //System.out.println("Covertura Childern: " + this.myNetWork.get(0).getChildern());


           // get the routing node


        }
    
    private void DrawForRootNode(Sensor root)
    {
        if (root != null)
        {
            if (root.getChildern().size() > 0)
            {
                for (Sensor child : root.getChildern())
                {
                    EdgeLine ed = new EdgeLine();
                    ed.setStartVertex(root);
                    ed.setEndVertex(child);
                    ed.DrawEdge();
                    this.line.add(ed.getEdg());
                    DrawForRootNode(child);
                }
            }
        }
    }
    
    private void btn_other_Menu(ActionEvent e){
        switch (e.getActionCommand())
        {
            case "Ver reporte Total" :{
                GUITotalReport total=new GUITotalReport(this.myNetWork, this.RoutingPackages, this.objeto, this.miliSe, this.NodesBattryCapacity);
                this.frame.setVisible(true);
            }
            break;
            case "Visualiza ubicaciones máximas cubiertas" :{}
            break;
            case "Imprimir grupos de red" :{
                GUIPrintNG grupo=new GUIPrintNG(this.NetWorkGroups);
                this.frame.setVisible(true);
            }
            break;
            case "Imprimir localización de nodos" :{
                GUISensorsLocations sensor= new GUISensorsLocations(this.myNetWork);
                this.frame.setVisible(true);
            }
            break;
            case "Imprimir datos de grupos" :{
                GUIGroupsData grup=new GUIGroupsData(this.myNetWork);
                this.frame.setVisible(true);
            }
            break;
            case "Imprimir árbol de expansión jerarquíca" :{
                GUIPrintHST hst=new GUIPrintHST(this.myNetWork);
                this.frame.setVisible(true);
            }
            break;
            case "Vista de paquetes" :{
                GUIShowPackages pkg=new GUIShowPackages(this.RoutingPackages);
                this.frame.setVisible(true);
            }
            break;
            case "Imprime resumen de Sensor" :{
                GUISummaryOfSensors sen=new GUISummaryOfSensors(this.myNetWork);
                this.frame.setVisible(true);
            }
            break;
            case "Imprime árbol de notificaciones" :{
                GUIPrintNT nt=new GUIPrintNT(this.myNetWork);
                this.frame.setVisible(true);
            }
            break;
            case "Imprime log de notificaciones" :{
                GUINotificationsLog log=new GUINotificationsLog(this.myNetWork);
                this.frame.setVisible(true);
            }
            break;
            case "Imprime trayectoria de cada objeto" :{
                GUITrajectoryForEachObject o=new GUITrajectoryForEachObject(this.objeto.getMobileObjects(), this.myNetWork);
                this.frame.setVisible(true);
            }
            break;
            case "Dibuja trayectoria para objetos" :{
                for(MobileObject m : this.objeto.getMobileObjects()){
                    //System.out.println("Trayectoria: " + m.getTrajectory());
                    GUIDrawTrajectory tr=new GUIDrawTrajectory(m.getTrajectory());
                    this.frame.setVisible(true);
                }
            }
            break;
        }
    }
    
    
    
}
