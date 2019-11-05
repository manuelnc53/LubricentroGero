/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author manuel
 */
public class Conexion {
   // private static Connection conexion;//objeto COnecction
    private String url = "BD_LubricentroGero.db"; //esta variable representa la ruta donde esta almacenada la base de datos   
    private static Connection  conexion;//objeto coneccion
    private Statement st;
    
    
    
    private Conexion(){

        try{
            this.conexion = DriverManager.getConnection("jdbc:sqlite:"+url);//conexion recibe el controlador de sqlite, pasamos la ruta donde está

            if(conexion!=null)
                System.out.println("Conectado\n");

        }catch(SQLException ex){
            System.out.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
        }   
    }
    
   public static void crearConexionBD(){
        if(conexion == null)
            new Conexion();
    }
    
    public static Connection getConnection (){
        if (conexion == null){
            crearConexionBD();
        }
        return conexion;
    }
    
    public void desconectar() throws SQLException{
        if(conexion!=null){
            if(!conexion.isClosed()){
            } else {
                conexion.close();
            }
        }
        /*
        conn=null;
        if (conn==null)
            System.out.println("Conexion terminada");
        */ }}  