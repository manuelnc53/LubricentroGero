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
public class BDModel {
    private static Connection conexion;
    
    private BDModel(){
        
        /**IMPLEMENTAR SINGLETON**/
    }
    
    
    public static Connection getConnection (){
        if (conexion == null){
             new BDModel();
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
