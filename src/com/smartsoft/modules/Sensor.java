/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.modules;
import java.util.ArrayList;
import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
//import java.util.Date;
import com.smartsoft.nt.NTnode;
import com.smartsoft.routing.Packages.HSTPacket;
import com.smartsoft.comunicationsMessagesFormate.*;
//import com.smartsoft.modules.HSTCommunicationLog;
//import com.smartsoft.modules.NTCommunicationLog;
//import com.smartsoft.modules.SensorsGroup;
//import com.smartsoft.modules.Vector;
//import com.smartsoft.modules.SensorState;
import com.smartsoft.energy.FirstOrderRadioModel;
import com.smartsoft.mobileObject.MobileObject;
import com.smartsoft.mobileObject.Point;
import com.smartsoft.computations.Operations;
import com.smartsoft.delay.DelayModel;
import com.smartsoft.memory.Message;
import com.smartsoft.memory.SensedObjectRecord;
import com.smartsoft.mobileObject.MobileObject;
import com.smartsoft.routing.Algorithms.HierarchicalSpanningRouting;
/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Sensor {
    
    public ArrayList<NotificationMessage> NMT = new ArrayList<>(); //tabla de mensajes de notificaciones
    public ArrayList<HSTPacket> MyGerenratedPackates = new ArrayList<>();
    public ArrayList<HSTCommunicationLog> HSTOperationsLog = new ArrayList<>();
    public ArrayList<NTCommunicationLog> NTOperationsLog = new ArrayList<>(); //
    public ArrayList<Sensor> Childern = new ArrayList<>();// de enrutamiento
    public ArrayList<SensorsGroup> MyGroups = new ArrayList<>(); //los grupos a los que pertenece el sensor.
    public Vector Vector; // sensores superpuestos: llamado vector en algoritmo de agrupación.
    public ArrayList<MobileObject> MyTrackingObject = new ArrayList<MobileObject>();// los objetos que estoy rastreando en este momento.
    public ArrayList<SensedObjectRecord> SOT = new ArrayList<SensedObjectRecord>();// Tabla de objetos detectados
    public ArrayList<Message> ObjectsRecords = new ArrayList<Message>();// the records of objects.
    FirstOrderRadioModel EnergyModel = new FirstOrderRadioModel();
    public Point CenterLocation;
    public NTnode MyNTnode = new NTnode(this.getID());
    public HierarchicalSpanningRouting HST_RoutingModel; // El HST
    private int r;
    private double dataPackageSize = 2048;// bits
    private double controlPackageSize = 256; // bits.
    private double batteryCapacity; // capacidad de bateria
    private int level;
    private double minEnerygthreshold = 0;
    private double batteryCapacityValue; // este valor cambiara de acuerdo con el uso de la bateria 
    private double residualEnergy; // este valor cambiara de acuerdo con el uso de enegía residual de la bateria
    private int ID;
    private String name;
    private int NodeID;
    private int Pox;
    private int Poy;
    private double batteryPercentage; // porcentaje de la bateria
    private SensorState CurrentState; // estado actual
    private Sensor ParentSensor;
    private Point Position;
    public int ControlPackageSize = 256; // bits.
    public int DataPackageSize = 2048;// bits
    public boolean verID = false;
    public boolean verBateria = false;
    public boolean verRango = false;
    public boolean verBorde = false;
    public boolean verAntena = false;

    public Sensor() {}
    
/**
 * 
 * @return retorna el tamaño del paquete de datos
 */
    public double getDataPackageSize() {
        return dataPackageSize;
    }
/**
 * 
 * @return tamaño de control de paquete
 */
    public double getControlPackageSize() {
        return controlPackageSize;
    }
/**
 * 
 * @return retorna la capacidad de bateria
 */
    public double getBatteryCapacity() {
        return batteryCapacity;
    }
/**
 * 
 * @return retorna el nivel
 */
    public int getLevel() {
        return level;
    }
/**
 * 
 * @return Regresa el umbral mínimo de energía
 */
    public double getMinEnerygthreshold() {
        return minEnerygthreshold;
    }
/**
 * 
 * @return retorna el valor de la capacidad de bateria 
 */
    public double getBatteryCapacityValue() {
        return batteryCapacityValue;
    }
/**
 * 
 * @return retorna la energía residual
 */
    public double getResidualEnergy() {
        return residualEnergy;
        //return batteryCapacityValue;
    }
/**
 * 
 * @return retorna el identificador del sensor
 */
    public int getID() {
        return ID;
    }

    /**
    * 
    * @return retorna el nombre del sensor
    */
    public String getName() {
        return name;
    }
    
    /**
    * 
    * @return retorna el identificador del nodo
    */    

    public int getNodeID() {
        return NodeID;
    }
    
    /**
    * 
    * @return retorna la pocision X
    */ 

    public double getPox() {
        return Pox;
    }
    
    /**
    * 
    * @return retorna la pocision Y
    */ 
    public double getPoy() {
        return Poy;
    }

    /**
    * 
    * @return retorna la pocision
    */ 
    public Point getPosition() {
        return Position;
    }
    
    /**
    * 
    * @return retorna el radio
    */    
    public int getR() {
        return r;
    }
    
    /**
     * 
     * @return Regresa un verdadero si se activo la opción de ver ID del sensor
     */
    public boolean getVerID() {
        return verID;
    }
    /**
     * 
     * @return Regresa un verdadero si se activo la opción de ver Bateria del sensor
     */
    public boolean getVerBateria() {
        return verBateria;
    }
    /**
     * 
     * @return Regresa un verdadero si se activo la opción de ver el rango del sensor
     */
    public boolean getVerRango() {
        return verRango;
    }
    /**
     * 
     * @return Regresa un verdadero si se activo la opción de ver el borde del sensor
     */
    public boolean getVerBorde() {
        return verBorde;
    }

    public boolean getVerAntena() {
        return verAntena;
    }
    
/**
 * 
 * @return reporta el porcentaje de la bateria
 */
    public double getBatteryPercentage() {
        //return batteryPercentage;
        //System.out.println("residualEnergy: " + residualEnergy + " batteryCapacity: " + batteryCapacity + " porcentaje: " + (this.residualEnergy / this.batteryCapacity) * 100);
        return (this.residualEnergy / this.batteryCapacity) * 100;
    }
/**
 * 
 * @return Regresa la información del nodo dentro del arbol de notificaciones
 */
    public NTnode getMyNTnode() {
        this.MyNTnode.setID(this.getID());
        return MyNTnode;
    }
/**
 * 
 * @return Regresa el sensor padre
 */
    public Sensor getParentSensor() {
        return ParentSensor;
    }
/**
 * 
 * @return Regresa el estado actual del sensor
 */
    public SensorState getCurrentState() {
        return CurrentState;
    }

    public Point getCenterLocation() {
        return new Point((int)this.getPox(), (int)this.getPoy());
        //return new Point((int)this.getPox() + (int) this.getR(),(int) this.getPoy() + (int) this.getR());
    }

    /**
     * 
     * @return Lista de objrtos sensados
     */
    public ArrayList<SensedObjectRecord> getSOT() {
        return SOT;
    }

    /**
     * 
     * @return Regresa los objetos que estoy rastreando en este momento.
     */
    public ArrayList<MobileObject> getMyTrackingObject() {
        return MyTrackingObject;
    }

    /**
     * 
     * @return Regresa una lista de registro de comunicacion en el arbol de notificaciones
     */
    public ArrayList<NTCommunicationLog> getNTOperationsLog() {
        return NTOperationsLog;
    }

    /**
     * 
     * @return Regresa el objeto del modelo de ruteo en el arbol de expansión jerarquica
     */
    public HierarchicalSpanningRouting getHST_RoutingModel() {
        return HST_RoutingModel;
    }

    /**
     * 
     * @return Regresa una lista de sensores hijos
     */
    public ArrayList<Sensor> getChildern() {
        return Childern;
    }
    
/**
 * 
 * @param dataPackageSize Variable del tamaño del paquete de datos
 */
    public void setDataPackageSize(double dataPackageSize) {
        this.dataPackageSize = dataPackageSize;
    }
/**
 * 
 * @param controlPackageSize Variable que controla el tamaño del paquete
 */
    public void setControlPackageSize(double controlPackageSize) {
        this.controlPackageSize = controlPackageSize;
    }
/**
 * 
 * @param batteryCapacity Variable para saber la capacidad de la bateria
 */

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        this.residualEnergy = batteryCapacity;// joules. intializing.
        //Prog_batteryCapacityNotation.Value = batteryCapacity;
        //Prog_batteryCapacityNotation.Maximum = batteryCapacity;
    }
/**
 * 
 * @param level Variable del nivel en el árbol de expansión jerárquica
 */
    public void setLevel(int level) {
        this.level = level;
    }
/**
 * 
 * @param minEnerygthreshold Variable del umbral mínimo de energía 
 */
    public void setMinEnerygthreshold(double minEnerygthreshold) {
        this.minEnerygthreshold = minEnerygthreshold;
    }
/**
 * 
 * @param batteryCapacityValue Valor de la  capacidad de energia
 */
    public void setBatteryCapacityValue(double batteryCapacityValue) {
        this.batteryCapacityValue = batteryCapacityValue;
    }
/**
 * 
 * @param residualEnergy Energia residual 
 */
    public void setResidualEnergy(double residualEnergy) {
        //this.residualEnergy = residualEnergy;
        this.batteryCapacityValue = residualEnergy;
        //Prog_batteryCapacityNotation.Value = residualEnergy;
    }
/**
 * 
 * @param ID Variable del identificador del sensor
 */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
    * 
    * @param name Variable del nombre del sensor
    */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
    * 
    * @param NodeID  Identificador del nodo del sensor
    */

    public void setNodeID(int NodeID) {
        this.NodeID = NodeID;
    }
    
    /**
    * 
    * @param Pox  Pocisión X del sensor
    */

    public void setPox(int Pox) {
        this.Pox = Pox;
    }
    
    /**
    * 
    * @param Poy  Pocisión Y del sensor
    */

    public void setPoy(int Poy) {
        this.Poy = Poy;
    }

    /**
    * 
    * @param Position  Pocisión
    */
    public void setPosition(Point Position) {
        this.Position = Position;
    }
    
    /**
    * 
    * @param r  Radio de alcance del sensor
    */
    public void setR(int r) {
        this.r = r;
    }
    
    /**
    * 
    * @param verID Regresa un verdadero si se activo la opción de ver ID del sensor
    */
   public void setVerID(boolean verID) {
       this.verID = verID;
   }
   /**
    * 
    * @param verBateria Regresa un verdadero si se activo la opción de ver Bateria del sensor
    */
   public void setVerBateria(boolean verBateria) {
       this.verBateria = verBateria;
   }
   /**
    * 
    * @param verRango Regresa un verdadero si se activo la opción de ver el rango del sensor
    */
   public void setVerRango(boolean verRango) {
       this.verRango = verRango;
   }
   /**
    * 
    * @param verBorde Regresa un verdadero si se activo la opción de ver el borde del sensor
    */
   public void setVerBorde(boolean verBorde) {
       this.verBorde = verBorde;
   }

    public void setVerAntena(boolean verAntena) {
        this.verAntena = verAntena;
    }

/**
 * 
 * @param batteryPercentage Variable del porcentaje de la bateria
 */
    public void setBatteryPercentage(double batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }    
/**
 * 
 * @param MyNTnode Información del sensor en el árbol de notificaciones
 */
    public void setMyNTnode(NTnode MyNTnode) {
        this.MyNTnode = MyNTnode;
    }
/**
 * 
 * @param ParentSensor Información del sensor padre, dentro del arbol
 */
    public void setParentSensor(Sensor ParentSensor) {
        this.ParentSensor = ParentSensor;
    }
/**
 * 
 * @param CurrentState Información del estado actual
 */
    public void setCurrentState(SensorState CurrentState) {
        this.CurrentState = CurrentState;
    }

    /**
     * 
     * @param SOT Lista de objetos sensados
     */
    public void setSOT(ArrayList<SensedObjectRecord> SOT) {
        this.SOT = SOT;
    }

    /**
     * 
     * @param MyTrackingObject objetos que estoy rastreando en este momento.
     */
    public void setMyTrackingObject(ArrayList<MobileObject> MyTrackingObject) {
        this.MyTrackingObject = MyTrackingObject;
    }
    
    public void addMyTrackingObject(MobileObject object) {
        this.MyTrackingObject.add(object);
    }
    
    /**
     * 
     * @param mensaje Paramentro de mesaje
     */
    public void addObjectsRecords(Message mensaje){
        this.ObjectsRecords.add(mensaje);
    }

    /**
     * 
     * @param HSTOperationsLog Parametro de registro de comunicacióm en el arbol de notificaciones
     */
    public void setHSTOperationsLog(ArrayList<HSTCommunicationLog> HSTOperationsLog) {
        this.HSTOperationsLog = HSTOperationsLog;
    }

    /**
     * 
     * @param HST_RoutingModel Prametro del modlo de ruteo en el Arbol de expanción jerarquica
     */
    public void setHST_RoutingModel(HierarchicalSpanningRouting HST_RoutingModel) {
        this.HST_RoutingModel = HST_RoutingModel;
    }

    /**
     * 
     * @param Childern Lista de sensores hijos
     */
    public void setChildern(ArrayList<Sensor> Childern) {
        this.Childern = Childern;
    }
    
    public void addChildern(Sensor sensor) {
        this.Childern.add(sensor);
    }
    
    
/**
 * 
 * @param nodeID Identificador del nodo sensor
 */      
    public Sensor(int nodeID){
        ID = nodeID;
        this.setCurrentState(SensorState.SLEEP);
    }
/**
 * 
 * @param sensor Sensor 
 * @param obj objeto en movimiento
 * @return 
 */    
    private boolean IsNotified(Sensor sensor,MobileObject obj)
    {
        boolean isFound = false; 
        for(NotificationMessage Message : sensor.NMT)
        {
            if(Message.getSource().getID()== sensor.getID())
            {
                if (Message.getMobileObject().getID() == obj.getID())
                {
                    isFound = true;
                    break;
                }
            }
        }
        return isFound;
    }
/**
 * 
 * @param msg Información de mensaje de notificación
 * @param parent  Nodo en el arbol de notificaciones
 */    
    public void BroadcastInsertNotification(NotificationMessage msg, NTnode parent) 
        {
            for (NTnode child : parent.getChildernN())
            {
                msg.setFromSensor(parent.getSensor());
                msg.setToSensor(child.getSensor());

                boolean Abort = IsNotified(child.getSensor(), msg.getMobileObject()); // no repetir la notificación de otro sensor para el mismo objeto
                if (!Abort)
                {
                   
                    child.BroadCastInsertMessage(msg);
                    NTCommunicationLog log = new NTCommunicationLog();
                    log.setIsInsert(true);
                    log.setSensorID(child.getSensor().getID());
                    log.setMessage("Desde:<" + msg.getFromSensor().getID() + ">Para:<" + msg.getToSensor().getID() + ">Info:[" + msg.getData() + "]");
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    log.setTime(timestamp);
                    log.setDistance_M(Operations.DistanceBetweenTwoSensors(parent.getSensor(), child.getSensor()));
                    log.setUsedEnergy_Nanojoule(EnergyModel.Transmit(controlPackageSize, log.getDistance_M()));

                    // configurar la Energía restante de la bateria:
                    double remainEnergy = residualEnergy - log.getUsedEnergy_Joule();
                    residualEnergy = remainEnergy;
                    //log.RemaimBatteryEnergy_Joule = residualEnergy;
                    log.setRemaimBatteryEnergy_Joule(residualEnergy);
                    this.NTOperationsLog.add(log);
                    
                    BroadcastInsertNotification(msg, child);
                }
                else
                {
                    break;
                }

            }
           
        }
/**
 * 
 * @param ToTargetSensor sensor objetivo
 * @param pck Paquete del arbol de expansión jerarquica
 */    
    public void SendData(Sensor ToTargetSensor, HSTPacket pck)
    {
        try{
            //this.Dispatcher.BeginInvoke(DispatcherPriority.Normal, (ThreadStart)delegate()
            //{
                if (ToTargetSensor != null)
                {
                    if (ToTargetSensor.getID() != this.getID())
                    {
                        if (residualEnergy > 0)
                        {
                            DelayModel delayModel = new DelayModel(this, ToTargetSensor);
                            pck.Delay += delayModel.getDelay();

                            pck.RoutingPath += this.getID() + ">";
                            HSTCommunicationLog log = new HSTCommunicationLog();
                            log.setIsSend(true);
                            log.setSensorID(this.getID());
                            log.setMessage(pck.MessageContent);
                            log.setOperation("Enviar datos para:" + ToTargetSensor.getID());
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            log.setTime(timestamp);
                            log.setDistance_M(Operations.DistanceBetweenTwoSensors(this, ToTargetSensor));
                            log.setUsedEnergy_Nanojoule(EnergyModel.Transmit(dataPackageSize, log.getDistance_M()));
                            
                            // configurar la Energía restante de la bateria:
                            double remainEnergy = residualEnergy - log.getUsedEnergy_Joule();
                            residualEnergy = remainEnergy;
                            //System.out.println("Despues residual: " + residualEnergy + " UsedEnergy_Joule: " + log.UsedEnergy_Joule);
                            //log.RemaimBatteryEnergy_Joule = residualEnergy;
                            log.setRemaimBatteryEnergy_Joule(residualEnergy);
                            this.HSTOperationsLog.add(log);
                        }
                        else
                        {
                            //this.Ellipse_Sensing_range.Fill = Brushes.Brown; // nodo muerto fuera
                            System.out.println("Fuera de nodo"); //nodo muerto fuera

                        }
                    }
                //}
                //else
                //{

                //}
            //});
            }
        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }

    }
/**
 * 
 * @param FromSourceSensor Sensor que envia el mensaje
 * @param pck Paquete que envia el mensaje
 */    
    public void ReceiveData(Sensor FromSourceSensor, HSTPacket pck)
    {
        try
        {
            //this.Dispatcher.BeginInvoke(DispatcherPriority.Normal, (ThreadStart)delegate()
            //{
                if (FromSourceSensor != null)
                {
                    if (FromSourceSensor.getID() != this.getID())
                    {
                        if (residualEnergy > 0)
                        {
                            pck.RoutingPath += this.ID + "|";
                            DelayModel delayModel = new DelayModel(this, FromSourceSensor);
                            pck.Delay += delayModel.getDelay();
                            pck.HopsCount += 1;
                            HSTCommunicationLog log = new HSTCommunicationLog();
                            log.setIsReceive(true);
                            log.setSensorID(this.getID());
                            log.setMessage(pck.getMessageContent());
                            log.setOperation("Recibir datos de:" + FromSourceSensor.getID());
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            log.setTime(timestamp);
                            log.setDistance_M(Operations.DistanceBetweenTwoSensors(this, FromSourceSensor));
                            log.setUsedEnergy_Nanojoule(EnergyModel.Receive(dataPackageSize));

                            // configurar la energia restante de la bateria:
                            double remainEnergy = residualEnergy - log.getUsedEnergy_Joule();
                            residualEnergy = remainEnergy;
                            //log.RemaimBatteryEnergy_Joule = residualEnergy;
                            log.setRemaimBatteryEnergy_Joule(residualEnergy);
                            this.HSTOperationsLog.add(log);
                            //this.Ellipse_Sensing_range.Fill = Brushes.Gray;// nodo muerto
                        }
                        else
                        {
                            System.out.println("Fuera de nodo"); // Nodo fuera de linea.
                        }
                    }
                }

           // });
        }
        catch 
            (Exception exp)
        {
            System.out.println(exp);
        }
    }
    
    public void sensando(ArrayList<MobileObject> objetos, int time, HierarchicalSpanningRouting HST_RoutingModel){
        try
            {
                for (MobileObject mobj : objetos)
                {
                    Point mobilObjeCntr = mobj.getCenterLocation();
                    if (isWithinMyRange(mobj))
                    {
                         if (!isInMyNMT(mobj)) // no. notification from other nodes.
                         {
                             Message message = new Message();
                             message.MessageContent = "Sensor:" + this.ID + " Objecto " + mobj.getID() + "(" + mobilObjeCntr.getX() + "," + mobilObjeCntr.getY() + ")";
                             message.setIsSent(false);
                             message.setNodeID(this.ID);
                             message.setObjectID(mobj.getID()); 
                             message.setObjectLocation(new Point(mobilObjeCntr.getX(), mobilObjeCntr.getY())); 
                             message.setTime(time);
                             // is not in my sensed list.
                             if (!IsInMySOT(mobj))
                             {
                                 // add
                                 SensedObjectRecord sensedRecord = new SensedObjectRecord();
                                 sensedRecord.setMobileObject(mobj);
                                 sensedRecord.messageTableAdd(message);
                                 this.SOT.add(sensedRecord);

                                 // send notifications:
                                 NotificationMessage insertNotificationMessage = new NotificationMessage();
                                 insertNotificationMessage.setMobileObject(mobj);
                                 insertNotificationMessage.setNotificationType(Notification.Insert);
                                 insertNotificationMessage.setSource(this);
                                 insertNotificationMessage.setData("Obj " + mobj.getID() + " Dentro del sensor " + this.ID);
                                 this.NMT.add(insertNotificationMessage);
                                 //this.MyNTnode.setSensor(this);
                                 BroadcastInsertNotification(insertNotificationMessage, this.getMyNTnode());
                                 ActivationZoon(this.getMyNTnode());

                                 // Informe de seguimiento:
                                 Sensor ProxNode = SelectProxyNodeAccordingToObjectsNumber(mobj);
                                 if(ProxNode!=null)
                                 {
                                     ProxNode.setCurrentState(SensorState.ACTIVE);
                                     ProxNode.addMyTrackingObject(mobj);
                                     ProxNode.StartTrackingAndReporting(HST_RoutingModel);
                                     
                                 }
                             }// end if (!IsInMySOT(mobj))
                             else // // is in my SOT
                             {
                                 // find my SensedObjectsMemory ref:
                                 UpdateSOT(mobj).messageTableAdd(message);
                             }
                         }//if (!isInMyNMT(mobj))
                    } // if (isWithinMyRange(mobj))
                    else
                    {
                        // broadcastDeleteMessage.
                        // buscar Mensaje de notificación por objeto.
                        NotificationMessage deletNotificationMessage = GetNotificationMessageForObject(mobj);
                        if(deletNotificationMessage!=null)
                        {
                            deletNotificationMessage.setNotificationType(Notification.Delete);
                            deletNotificationMessage.Data = "Obj " + mobj.getID() + " fuera del Sensor " + this.ID;
                            // el objeto estaba en el rango y luego se quiere eliminarlo.
                            BroadcastDeleteNotification(deletNotificationMessage, this.getMyNTnode());
                            DeActivatation(this.getMyNTnode());
                            this.NMT.remove(deletNotificationMessage);// eliminarlo.
                        }
                    }

                }
            }
            catch (Exception exp)
            {
                System.out.println("Primer catch: " + exp);
            }
        
    }
    
    public void StartTrackingAndReporting(HierarchicalSpanningRouting HST_RoutingModel)
        {
            ArrayList<MobileObject> FinishedTrackedObjects = new ArrayList<MobileObject>();

            for(MobileObject obj : MyTrackingObject )
            {
                
                SensedObjectRecord sotRecord = getSOTforObject(obj);// get SOT
                
                if (sotRecord != null)
                {
                    if (sotRecord.getMessageTable().size() > 0)
                    {
                        HSTPacket hstPackaet = new HSTPacket();
                        hstPackaet.MessageContent ="";
                        for (Message message : sotRecord.getMessageTable())
                        {
                            if (!message.getIsSent())
                            {
                               
                                message.setIsSent(true);
                                hstPackaet.MessageContent += message.getMessageContent() + "%";
                                //hstPackaet.setMessageContent(message.getMessageContent() + "%");
                                this.addObjectsRecords(message);
                            }
                        }
                        // send the packtes.
                        if (hstPackaet.MessageContent != "")
                        {
                            hstPackaet.setHopsCount(0);
                            hstPackaet.setSourceNode(this.ID);
                            HST_RoutingModel.DataReport(this, hstPackaet);
                            this.MyGerenratedPackates.add(hstPackaet);
                        }

                        // remove ost:
                       if(sotRecord.getIsReported())
                       {
                           if (!isWithinMyRange(obj))
                           {
                               SOT.remove(sotRecord);
                               FinishedTrackedObjects.add(obj);
                           }
                           
                       }
                    }
                }
                // create routing:
            }

            for (MobileObject Obj : FinishedTrackedObjects)
            {
                this.getMyTrackingObject().remove(Obj);
                //MainWindow.txt_Motion.AppendText("**Node:" + this.ID + "--> removed:" + Obj.ID + "\r\n");
            }

            if (this.getMyTrackingObject().size() == 0) 
            {
                //this.StopTracking(); lo comente
                this.setCurrentState(SensorState.SLEEP);
                //MainWindow.txt_Motion.AppendText("##Node:" + this.ID + " is stopped \r\n");
            }
        }
    
    public void BroadcastDeleteNotification(NotificationMessage msg, NTnode parent) 
        {
            for (NTnode child : parent.getChildernN())
            {
                msg.setFromSensor(parent.getSensor());
                msg.setToSensor(child.getSensor());
                
                child.BroadCastDeleteMessage(msg);

                NTCommunicationLog log = new NTCommunicationLog();
                log.setIsDelete(true);
                log.setSensorID(child.getSensor().getID());
                log.setMessage("Desde:<" + msg.getFromSensor().getID() + ">Para:<" + msg.getToSensor().getID() + ">Datos:[" + msg.getData() + "]");
                
                log.setTime(new Timestamp(System.currentTimeMillis()));
                log.setDistance_M(Operations.DistanceBetweenTwoSensors(parent.getSensor(), child.getSensor()));
                log.setUsedEnergy_Nanojoule(EnergyModel.Transmit(this.ControlPackageSize, log.getDistance_M()));

                // set the remain battery Energy:
                double remainEnergy = residualEnergy - log.getUsedEnergy_Joule();
                residualEnergy = remainEnergy;
                //log.RemaimBatteryEnergy_Joule = residualEnergy;
                log.setRemaimBatteryEnergy_Joule(residualEnergy);
                this.NTOperationsLog.add(log);

                BroadcastDeleteNotification(msg, child);

            }

        }
    
    public boolean isWithinMyRange(MobileObject m)
        {
            boolean re = false;
            int dx = (this.getCenterLocation().getX() - m.getCenterLocation().getX());
            dx *= dx;
            int dy = (this.getCenterLocation().getY() - m.getCenterLocation().getY());
            dy *= dy;

            if (dx + dy <= 25 * 25)
            {
                re = true;
             
            }
            else
            {
                re = false;
            }

            return re;
        }
    
    public boolean isInMyNMT(MobileObject mobj)
        {
            boolean re = false;
            for (NotificationMessage message : NMT)
            {
                if (message.getSource().getID() != this.ID)
                {
                    if (message.getMobileObject().getID() == mobj.getID())
                    {
                        re = true;
                    }
                }
            }
            return re;
        }
    
    private boolean IsInMySOT(MobileObject mid)
    {
        boolean No = false;
        for(SensedObjectRecord m : this.SOT)
        {
            if (mid == m.getMobileObject()) No = true;
        }
        return No;
    }
    
    /**
     * Convierte el nodo a estado IDEL
     * @param node Nodo sensor dentro del arbol de notificaciones
     */
        public void ActivationZoon(NTnode node)
        {
            try
            {
                if (node != null)
                {
                    node.getSensor().setCurrentState(SensorState.IDLE);
                }
                for (NTnode n : node.getChildernN())
                {
                    ActivationZoon(n);
                }
            }catch (Exception exp) {
                System.out.println("Segundo Catch" + exp);
            }
           
        }
        
    private Sensor SelectProxyNodeAccordingToObjectsNumber(MobileObject mobj) 
    {
        Sensor re = null;
        ArrayList<NotificationMessage> Nformobj = GetNotificationFor(mobj);
        if (Nformobj != null)
        {
            if (Nformobj.size() == 1) { re = Nformobj.get(0).Source; }
            else
            {
                // select the maximum energ node.
                int myTrackObjectsCout = Nformobj.get(0).getSource().getMyTrackingObject().size();
                re = Nformobj.get(0).getSource();
                for (NotificationMessage n : Nformobj)
                {
                    int mytrackObjectCout = n.getSource().getMyTrackingObject().size();
                    if(mytrackObjectCout<myTrackObjectsCout)
                    {
                        re = n.getSource();
                    }
                }
            }
        }
        return re;
    }
    
    /**
     * Obtenga los registros de notificación para el objeto mobj.
     * @param mobj Objeto en movimiento
     * @return 
     */
        private ArrayList<NotificationMessage> GetNotificationFor(MobileObject mobj)
        {
            ArrayList<NotificationMessage> re = new ArrayList<NotificationMessage>();
            for (NotificationMessage N : NMT)
            {
                if(N.getMobileObject()==mobj)
                {
                    re.add(N);
                }
            }
            if (re.size() == 0) re = null;
            return re;
        }
        
        /**
         * 
         * @param mobj Objeto en movimiento
         * @return Regresa un boleano si el objeto fue registrado
         */
        public SensedObjectRecord UpdateSOT(MobileObject mobj)
        {
            SensedObjectRecord re = null;
            for(SensedObjectRecord m : SOT)
            {
                if(mobj == m.getMobileObject())
                {
                    re = m;
                }
            }
            return re;
        }
        
        /**
         * Significaba que el objeto salía del alcance.
         * @param mobj Objeto en movimiento
         * @return Regresa un booleano si el mensaje fe notificado
         */
        public NotificationMessage GetNotificationMessageForObject(MobileObject mobj)
        {
            NotificationMessage re = null;
            for(NotificationMessage message : this.NMT)
            {
                if(message.getMobileObject().getID()==mobj.getID() && message.getSource().getID() == this.ID)
                {
                    re = message;
                }
            }
            return re;
        }
        
        /**
         * 
         * @param node Convierte los nodos en modo Sleep
         */
        public void DeActivatation(NTnode node)
        {
           try
           {
                if (node != null)
                {
                    node.getSensor().setCurrentState(SensorState.SLEEP);
                }
                for (NTnode n : node.getChildernN())
                {
                    DeActivatation(n);
                }
           }catch (Exception exp) {
                System.out.println("Tercer Catch" + exp);
            }

        }
        
        private SensedObjectRecord getSOTforObject(MobileObject obj)
        {
            SensedObjectRecord sot = null;
            for(SensedObjectRecord sott : SOT)
            {
                if(sott.getMobileObject()==obj)
                {
                    sot = sott;
                }
            }
            return sot;
        }
        
        public String GetNotificationString(NTnode node)
        {
            String str = "";
            if(node!=null)
            {
                str = node.getID() + "→";
            }
            for(NTnode n : node.getChildernN())
            {
                if (node.getChildernN().size() > 1) { str += "\r\n>"; }
                str += GetNotificationString(n);
               
            }
            return str;
        }
}
