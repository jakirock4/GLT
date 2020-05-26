/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.routing.Algorithms;
import java.util.ArrayList;
import com.smartsoft.routing.Packages.HSTPacket;
import com.smartsoft.modules.Sensor;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class HierarchicalSpanningRouting {
    
    
    public ArrayList<HSTPacket> RoutingPackages;
    private ArrayList<Sensor> network;
    private int _rootIndex=0;
    /**
     * 
     * @param _network Lista de sensores
     * @param rootIndex Indice de raiz
     */
    public HierarchicalSpanningRouting(ArrayList<Sensor> _network, int rootIndex, ArrayList<HSTPacket> RoutingPackages)
    {
        network = _network;
        _rootIndex = rootIndex;
        this.RoutingPackages = RoutingPackages;
    }

    public HierarchicalSpanningRouting() {}
    /**
     * 
     * @param Message Mensaje de las operaciones
     */
    public void ForAll(String Message)
    {
        for(Sensor sen : network)
        {
            HSTPacket hstPackaet = new HSTPacket();
            hstPackaet.setMessageContent("test1");
            hstPackaet.setHopsCount(0);
            hstPackaet.setSourceNode(sen.getID());
            sen.MyGerenratedPackates.add(hstPackaet);
            DataReport(sen, hstPackaet); 
        }
    }
    /**
     * 
     * @param ReporterNode Sensor que reporta en el nodo del árbol de expasión jerárquica
     * @param pkg Paquete que envia el senor
     */
    public void DataReport(Sensor ReporterNode, HSTPacket pkg)
    {
        if (ReporterNode.getParentSensor() != null)
        {

            Sensor ParentInHST = ReporterNode.getParentSensor(); // Destino predeterminado en el árbol de estructura jerárquica.
            // checar si DfulateTargetSensorInHST esta bien.
            if (ParentInHST.getBatteryPercentage() > ParentInHST.getMinEnerygthreshold())
            {
            /**
             * Enrutamiento normal a través del árbol de estructura jerárquica
             */

                ReporterNode.SendData(ParentInHST, pkg);// Enviar
                ParentInHST.ReceiveData(ReporterNode, pkg);// Recibir
                DataReport(ParentInHST, pkg);// ecuación recursiva
            }
            else
            {
                // switch:
                 Sensor ParentInNT = SwitchOperation(ReporterNode, ParentInHST,true);
                 ReporterNode.SendData(ParentInNT, pkg);
                 ParentInNT.ReceiveData(ReporterNode, pkg);
                 DataReport(ParentInNT, pkg);

            }
        }
        else // recibido por el nodo receptor (sumidero)
        {
            if (ReporterNode.getID() == _rootIndex)
            {
                //System.out.println("Se entrego");
                pkg.setIsDelivered(true);
                this.RoutingPackages.add(pkg);
            }
            else
            {
                System.out.println("¡La fecha no se pudo entregar!");
            }

        }
    }
    /**
     * 
     * @param ReporterNode  Implementación del algoritmo para el reporte de nodo
     * @param ParentInHST Sensor padre en Árbol de estructura jerárquica
     * @param SwitchToTheSameLevelOnly Boleano verdadero si Cambia al mismo nivel solamente y un falso si no cambia
     * @return Regresa el sensor
     */
    
    private Sensor SwitchOperation(Sensor ReporterNode, Sensor ParentInHST, boolean SwitchToTheSameLevelOnly)
    {
        Sensor NewTargetNode = null;
        // 1- Obtiene el árbol de notificaciones de origen: s (10) = 10 → 9 → 8 → 20
        //2- suponga que 8 está por debajo del umbral.
        // 3- seleccione cualquier nodo que tenga el mismo nivel de 8 solamente (energía máxima)

        /**
         * obtiene el NT/AN (SourceSensor):
         */
        
        ArrayList<Sensor> NTnodesWithSameLevlOfParentInHST = new ArrayList<>();
        GetIDs IDS = new GetIDs(ReporterNode.getMyNTnode());
        ArrayList<Integer> ids = IDS.GetList();
        /**
         * obtiene los sensores
         */
        for(int id : ids) 
        {
            if (id != ReporterNode.getID())
            {
                if (id != ParentInHST.getID())
                {
                    if (id != _rootIndex)
                    {
                        Sensor sensor = network.get(id);
                        if (SwitchToTheSameLevelOnly)
                        {
                            // solo el mismo nivel de objetivo.
                            if (sensor.getLevel() == ParentInHST.getLevel())
                            {
                                NTnodesWithSameLevlOfParentInHST.add(sensor);
                            }
                        }
                        else
                        {
                            // el mismo nivel de fuente y más bajo.
                            if (sensor.getLevel() <= ReporterNode.getLevel())
                            {
                                NTnodesWithSameLevlOfParentInHST.add(sensor);
                            }
                        }
                    }
                }
            }
        }

        // si 
        if(NTnodesWithSameLevlOfParentInHST.size()>0)
        {
            // si solo uno para cambiar de operación
            if(NTnodesWithSameLevlOfParentInHST.size()==1)
            {
                // switch:
                Sensor newParentNode = NTnodesWithSameLevlOfParentInHST.get(0);
                newParentNode.getChildern().add(ReporterNode);
                ReporterNode.setParentSensor(newParentNode);
                ParentInHST.getChildern().remove(ReporterNode);

                NewTargetNode = newParentNode;
                if (newParentNode.getMinEnerygthreshold()>0)
                newParentNode.setMinEnerygthreshold(newParentNode.getMinEnerygthreshold()-1);

                // reporte:
               // this.MainWindow.txt_Motion.AppendText("New Switch Path From:" + SourceSensor.ID + " To:" + newPathNode.ID +"( Old path was to <"+TargetSensorInHST.ID+">)\r\n");

            }
            else
            {
                // Seleccione la energía máxima.

                Sensor newParentNode = NTnodesWithSameLevlOfParentInHST.get(0);
                for(Sensor sen : NTnodesWithSameLevlOfParentInHST)
                {
                    if (sen.getBatteryPercentage() > newParentNode.getBatteryPercentage())
                    {
                        newParentNode = sen;
                    }
                }

                // switch:  do swich operation
                newParentNode.getChildern().add(ReporterNode);
                ReporterNode.setParentSensor(newParentNode);
                ParentInHST.getChildern().remove(ReporterNode);

                NewTargetNode = newParentNode;
                if (newParentNode.getMinEnerygthreshold() > 0)
                newParentNode.setMinEnerygthreshold(newParentNode.getMinEnerygthreshold()-1);

                //this.MainWindow.txt_Motion.AppendText("New Switch Path From:" + SourceSensor.ID + " To:" + newPathNode.ID + "( Old path was to <" + TargetSensorInHST.ID + ">)\r\n");
            }
        }
            /**
             *  No hay otras opciones en el Árbol de notificaciones (AN
             * asi que solo se utiliza el HST (Árbol de estructura jerárquica))
             */
        if (NewTargetNode == null) { NewTargetNode = ParentInHST; }

        return NewTargetNode;
    }
    
}
