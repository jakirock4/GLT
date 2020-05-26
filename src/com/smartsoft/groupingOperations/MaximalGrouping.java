/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.groupingOperations;

import java.util.ArrayList;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.SensorsGroup;
import com.smartsoft.modules.Vector;
import com.smartsoft.nt.NotificationTree;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class MaximalGrouping {
    
    private int gid = 0;
       ArrayList<Sensor> Network;
       ArrayList<SensorsGroup> NetWorkGroups = new ArrayList<>();

       public MaximalGrouping(ArrayList<Sensor> _Network) 
       {
           Network = _Network;
       }
/**
 * 
 * @return una lista de grupos de sensores encontrados
 */
       public ArrayList<SensorsGroup> FindGroups()
       {
           //1- Buscar vectores vecinos para todos los sensores.
           FindNeighborVectosForAllSensors();

           //3- Agrupando:
           GroupForAllSensors();
           //4- distribuyendo grupos:
           NetworkGroups();
           return NetWorkGroups;
       }

        private void NetworkGroups()
       {
            int id=0;
            for(Sensor s: Network)
            {
                for(SensorsGroup group : s.MyGroups)
                {
                    if(!isExistedGroup(group,NetWorkGroups))
                    {
                        SensorsGroup g = new SensorsGroup(id++);
                        g.Sensors = group.Sensors;
                        NetWorkGroups.add(g);
                    }
                }
            }

            //// para refix:
            // limpiar
            for (Sensor s : Network)
            {
                s.MyGroups.clear();
            }

            // setear:
            for(SensorsGroup g : NetWorkGroups)
            {
                for(Sensor s : g.Sensors)
                {
                    if(isSensorBelongToGroup(s,g))
                    {
                        s.MyGroups.add(g);
                    }
                }
            }



       }

/**
 * 
 */
        private void GroupForAllSensors()
       {
            for(Sensor sensor : Network)
            {
                GroupForOneSensor(sensor);
                NotificationTree nt = new NotificationTree(sensor);
                sensor.MyNTnode = nt.getNotificationTree();
            }
      
       }
/**
 * Algoritmo de agrupación
 * @param cs Sensor para agrupar en la lista de sensores
 */
       private void GroupForOneSensor(Sensor cs)
       {
          
           Vector cv = cs.Vector;
           // 1- Obtiene vectores filtrados
           for(Vector vec : cv.NeighborVectors)
           {
               Vector FF = FindCVminseV(vec.getID(), cv, vec);
               cv.FilterdedVectors.add(FF);
           }
           // Agrupando.
           for (int i = 0; i < cv.FilterdedVectors.size();i++)
           {
               Vector fi = cv.FilterdedVectors.get(i);
               Sensor si = FindSensorByID(fi.ID, cs.Vector.getMembers());
               if (fi.getMembers().size() == 2) // dos significa que no hay otra superposición con ningún sensor que no sea cs.
               {
                   // agregando
                   SensorsGroup group = new SensorsGroup(gid);
                   group.Sensors = fi.getMembers();
                   if (!isExistedGroup(group, cs.MyGroups))
                   {
                       cs.MyGroups.add(group);
                       gid++;
                      
                   }
               }
               else
               {
                   for (int j = i + 1; j < cv.FilterdedVectors.size(); j++)
                   {
                     
                       Vector fj = cv.FilterdedVectors.get(j);
                        if (IsSensorBelongToVector(si, fj))// superpuesto.
                        {
                            // agregando
                            SensorsGroup group = new SensorsGroup(gid);
                            group.Sensors = FindIntersectionOfTwoVectors(fi, fj);
                            if (!isExistedGroup(group, cs.MyGroups))
                            {
                                cs.MyGroups.add(group);
                                gid++;
                                
                            }
                        }
                   }
               }
           }
           //System.out.println("Tamaño MyGroups: " + cs.MyGroups.size());
       }

/**
 * 
 * @param ID Identificador del sensor
 * @param sensors Lista de sensores
 * @return Regresa el sensor encontrado
 */   
        private Sensor FindSensorByID(int ID, ArrayList<Sensor> sensors)
       {
           Sensor resn = null;
            for(Sensor sensor : sensors)
            {
                if(sensor.getID()==ID)
                {
                    resn = sensor;
                    break;
                }
            }
            return resn;
       }
/**
 * Metodo para buscar la interseccion de dos vectores 
 * @param v1 vector 1 
 * @param v2 vector 2
 * @return retorno de intersección de dos vectores.
 */      
       private ArrayList<Sensor> FindIntersectionOfTwoVectors(Vector v1, Vector v2)
       {
           ArrayList<Sensor> intersectionSensors = new ArrayList<>();
           for (Sensor s_myvector : v1.getMembers())
           {
               for (Sensor s_NeighborVector : v2.getMembers())
               {
                   if (s_myvector.getID() == s_NeighborVector.getID())
                   {
                       intersectionSensors.add(s_myvector);
                   }
               }
           }

           return intersectionSensors;
       }


       private boolean isExistedGroup(SensorsGroup group)
       {
           boolean yes = false;
           for (SensorsGroup g : NetWorkGroups)
           {
               boolean areEqual = IsTwoGroupsAreEqual(g, group);
               if (areEqual) { yes = true; break; }
           }
           return yes;
       }
/**
 * 
 * @param group Grupo de sensores
 * @param Groups Lista de grupo de sensores
 * @return Regresa un booleano verdadero si existe el grupo dentro de la lista, y un falso si no existe
 */
       private boolean isExistedGroup(SensorsGroup group,ArrayList<SensorsGroup>Groups)
       {
           boolean yes = false;
           for (SensorsGroup g : Groups)
           {
               boolean areEqual = IsTwoGroupsAreEqual(g, group);
               if (areEqual) { yes = true; break; }
           }
           return yes;
       }
/**
 * 
 * @param g1 Grupo de sensores 1
 * @param g2 Grupo de sensores 1
 * @return Regresa un booleano verdadero si dos grupos son iguales, y un falso si no lo son
 */
       public boolean IsTwoGroupsAreEqual(SensorsGroup g1, SensorsGroup g2)
       {
           boolean areEqual = true;
           if (g1.Sensors.size() == g2.Sensors.size())
           {
               // compruebe si son iguales.
               for (Sensor s1 : g1.Sensors)
               {
                   boolean isFound = isSensorBelongToGroup(s1, g2);
                   if (isFound == false) // No encontrado
                   {
                       areEqual = false;
                       break;
                   }
               }
           }
           else
           {
               areEqual = false;
           }
           return areEqual;
       }
/**
 * 
 * @param s Sensor
 * @param g Grupo de sensores
 * @return Regresa un booleano verdadero si el sensor pertenece al grupo y falso si no pertenece
 */
       public boolean isSensorBelongToGroup(Sensor s, SensorsGroup g)
       {
           boolean isFound = false;
           for (Sensor s1 : g.Sensors)
           {
               if (s1.getID() == s.getID())
               {
                   isFound = true;
                   break;
               }
           }
           return isFound;
       }
/**
 * eliminar sensores de v si no pertenecen a cv.
 * devuelve solo los sensores que pertenecen a v y pertenecen a cv.
 * @param vid Identificador del sensor v
 * @param cv vector cv
 * @param v  vector v
 * @return devuelve solo los sensores que pertenecen a v y pertenecen a cv.
 */
       private Vector FindCVminseV(int vid,Vector cv, Vector v) 
       {
           Vector rev = new Vector(vid);// no se usa
           ArrayList<Sensor> filterdSensors = new ArrayList<>();
           for (Sensor s : v.getMembers())
           {
               if (IsSensorBelongToVector(s, cv))
               {
                   filterdSensors.add(s);
               }
           }
           rev.Add(filterdSensors);
           return rev;
       }
/**
 * 
 * @param s sensor s
 * @param v sensor v
 * @return  Regresa un booleano verdadero si el sensor pertenece al vector y false si no pertenece
 */       
       private boolean IsSensorBelongToVector(Sensor s, Vector v)
       {
           boolean isbelong = false;
           for (Sensor sensor: v.getMembers())
           {
               if (sensor.getID() == s.getID()) { isbelong = true; break; }
           }
           return isbelong;
       }
/**
 * 
 */
       private void FindNeighborVectosForAllSensors()
       {
           for (Sensor senor : Network)
           {
               FindNeighborVectosForOneSensor(senor);
           }
       }
/**
 * Encuentra vectores vecinos para el sensor actual.
 * @param CurrentSensor Sensor actual
 */
       private void FindNeighborVectosForOneSensor(Sensor CurrentSensor)
       {
           //System.out.println("Members: " + CurrentSensor.Vector.getMembers().size());
           if (CurrentSensor.Vector.getMembers().size() > 0)
           {
               //System.out.println("Es mayor que  cero");
               Vector myvector = CurrentSensor.Vector;
               for (Sensor sensor : myvector.getMembers())
               {
                   if (sensor.getID() != CurrentSensor.getID())
                   {
                       myvector.NeighborVectors.add(sensor.Vector);
                   }
               }
           }
       }
    
}
