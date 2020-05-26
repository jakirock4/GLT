/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.model;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class NetworkImport {
    
    private int id;
    private String name;

    public NetworkImport() {
    }
/**
 * 
 * @return Regresa el identtificador de la topologia de red importada
 */
    public int getId() {
        return this.id;
    }
/**
 * 
 * @param id Coloca el identificador de la topologia de red importada
 */
    public void setId(int id) {
        this.id = id;
    }
/**
 * 
 * @return Regresa el nombre de la topologia de red importada
 */
    public String getName() {
        return this.name;
    }
/**
 * 
 * @param name coloca el nombre de la topologia de red importada
 */
    public void setName(String name) {
        this.name = name;
    }
    
}
