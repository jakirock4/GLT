/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.db;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * Clase que gestiona las multiples conexiones
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class Pool {
/**
 * Metodo para crear multiconexiones
 * Conexión con la base de datos.
 */ 
    public DataSource dataSource;

    //public String db = "GLT";
    //public String user = "root";
    //public String pass = "AGEjaFUT";
    public String db = "glt";
    public String user = "root";
    public String pass = "";
    public String url = "jdbc:mysql://localhost/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    /**
     * Inicializa el DataSoruce
    */

    public Pool(){
        inicializaDataSource();
    }
    
    /**
     * Configura los datos para habilitar una conexión
    */

    private void inicializaDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
        //basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(pass);
        basicDataSource.setUrl(url);
        basicDataSource.setMaxActive(50);
        dataSource = basicDataSource;

    }
 
}
