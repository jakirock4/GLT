/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.energy; 

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class FirstOrderRadioModel {
    
    public double E_elec = 50; // unit: (nJ/bit) //Disipación de energía para hacer funcionar la radio.
    public double Efs = 0.01;// unit( nJ/bit/m^2 ) //Modelo de espacio libre del amplificador transmisor
    public double Emp = 0.0000013; // unit( nJ/bit/m^4) // Modelo multitrayecto de amplificador de transistores
    public double d0; //Distance threshold ( unit m)
/**
 * 
 * @return 
 */
    public double getD0() {
        return Math.sqrt(Efs / Emp);
    }
            
/**
 * Cada nodo del sensor consumirá la siguiente cantidad de energía ETx para transmitir un mensaje de bits 
 * (L = DataLength) sobre la distancia d:
 * @param DataLength el tamaño del paquete
 * @param overDistance distancia de transmisión
 * @return Regresa el Consumo de energia al transmitir un paquete
 */    
    public double Transmit(double DataLength,double overDistance) 
    {
        double E_tx = 0; 
        if(overDistance<=this.getD0())
        {
            E_tx = (DataLength * E_elec) +
                (DataLength * this.Efs * Math.pow(overDistance, 2));
        }
        else if(overDistance>this.getD0())
        {
            E_tx = (DataLength * E_elec) +
                (DataLength * Emp * Math.pow(overDistance, 2));
        }
        return E_tx;
    }
/**
 * 
 * @param DataLength el tamaño del paquete
 * @return Regresa la cantidad de energia al recibir un paquete
 */    
    public double Receive(double DataLength)
    {
        double ERx = DataLength * this.E_elec;
        return ERx;
    }

/**
 * 
 * @return Regresa el Estado de reposo
 */
    public double SleepState()
    {
        return 0.000000006;
    }
/**
 * 
 * @return Regresa la cantidad de estado inactivo
 */
    public double IdleState() 
    {
        return 0.00022;
    }
    
}
