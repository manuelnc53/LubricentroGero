/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author manuel
 */
public class Conexion {
    private static Connection conexion;
    
    private Conexion(){
        
        /**IMPLEMENTAR SINGLETON**/
    }
    
    
    public static Connection getConnection (){
        if (conexion == null){
             new Conexion();
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
        */
    }
    
}
