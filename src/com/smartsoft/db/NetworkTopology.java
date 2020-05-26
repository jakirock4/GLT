/*
 * Esta clase de usa para Multiples conexiones a la Base de Datos
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import com.smartsoft.model.NetworkImport;
import com.smartsoft.modules.Sensor;
import com.smartsoft.nt.NTnode;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class NetworkTopology {
    
    Connection cn=null;
    Pool pool= new Pool();
    
   /**
    * Metodo Constructor para inicializar una nueva conexión
   */
    
    public NetworkTopology() {
        try{
            cn = pool.dataSource.getConnection();
            if(cn!=null){
              System.out.println("Conectado");
            }
       } catch (SQLException e){
           
            System.out.println("Error en la conexion: " + e);
       }
    }

/**
 * @param TopologName Nombre de la topogía
 * @return todos los sensores de una topologia
 */    
    public ArrayList<Sensor> getSensors(String TopologName){
        ArrayList<Sensor> sensores = new ArrayList<>();
    	Sensor sensor;
        try{
            PreparedStatement pstm = cn.prepareStatement("SELECT * FROM " + TopologName);
            
            ResultSet res = pstm.executeQuery();
            int i = 1;
            while(res.next()){
                sensor = new Sensor();
                sensor.setNodeID(res.getInt("nodoID"));
                sensor.setID(res.getInt("nodoID"));
                sensor.setPox(res.getInt("pox"));
                sensor.setPoy(res.getInt("poy"));
                sensor.setR(res.getInt("r"));
                sensor.setMyNTnode(new NTnode(res.getInt("nodoID")));
                sensores.add(sensor);
                if(res.getInt("nodoID") == 0){
                    sensor.setBatteryCapacity(1000000); //jouls
                    sensor.setMinEnerygthreshold(0);
                }
                else{
                    sensor.setBatteryCapacity(10); //jouls
                    sensor.setMinEnerygthreshold(70);
                }
                i++;
            }
            res.close(); 
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return sensores;
    }

/**
 * @param sensor sensor Información del sensor encapsulado para guardar
 * @param TopologName Nombre de la topologia de donde pertenece el sensor
 * @return el número de errores al procesar la operacíon
 */
    public int SaveSensor(ImportedSensor sensor,String TopologName){
        int resultado = 0;//no hubo errores de validacion
        try{
            PreparedStatement pstm = cn.prepareStatement(
                                   "insert into "+ TopologName +" (" +
                                   " nodoID," +
                                   " pox," +
                                   " poy," +
                                   " r) " +
                                   " values(?,?,?,?)");
            
            pstm.setInt(1, sensor.getNodeID());
            pstm.setDouble(2, sensor.getPox());
            pstm.setDouble(3, sensor.getPoy());
            pstm.setDouble(4, sensor.getR());
            pstm.executeUpdate();

            pstm = cn.prepareStatement("select last_insert_id()");
            ResultSet res = pstm.executeQuery();
            res.next();
            resultado = res.getInt(1);
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return resultado;
    }

/**
 * 
 * @param sensor Información del sensor encapsulado para actualizar
 * @param TopologName Nombre de la topologia de donde pertenece el sensor
 * @return el número de errores al procesar la operación
 */    
    public int update(ImportedSensor sensor, String TopologName){
        int resultado = 0;
        try{
            PreparedStatement pstm = cn.prepareStatement(
                                       "update " + TopologName +
                                       " set nodoID = ?, " +
                                       " pox = ?," +
                                       " poy = ?," +
                                       " r = ?" +
                                       " where id = ?");
            pstm.setInt(1, sensor.getNodeID());
            pstm.setDouble(2, sensor.getPox());
            pstm.setDouble(3, sensor.getPoy());
            pstm.setDouble(4, sensor.getR());
            pstm.setInt(5, sensor.getId());
            resultado = pstm.executeUpdate();
                
        }catch(SQLException e){
            System.out.println(e);
        }
        return resultado;
    }

/**
 * @param sensor Información del sensor encapsulado para actualizar
 * @param TopologName Nombre de la topologia de donde pertenece el sensor
 * @return el número de errores al procesar la operación
 */    
    public int delete(ImportedSensor sensor, String TopologName){
        int resultado = 0;
        try{
            PreparedStatement pstm = cn.prepareStatement(
                                           "delete from " + TopologName +
                                           " where id = ?");
            
            pstm.setInt(1, sensor.getId());

            resultado = pstm.executeUpdate();
                    
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return resultado;
    }

/**
 * @return Regresa una lista con todos los sensores de la topología a importar
 */    
    public ArrayList<NetworkImport> ImportNetworkNames(){
        ArrayList<NetworkImport> networks = new ArrayList<>();
    	NetworkImport net;
        try{
            PreparedStatement pstm = cn.prepareStatement("show tables");
            
            ResultSet res = pstm.executeQuery();
            int i = 1;
            while(res.next()){
                net = new NetworkImport();
                net.setId(i);
                net.setName(res.getString("Tables_in_glt"));
                networks.add(net);

                i++;
            }
            res.close(); 
            
        }catch(SQLException e){
            System.out.println("Error en la consulta: " + e);
        }
        return networks;
    }
    
}
