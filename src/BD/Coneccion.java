/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

/**
 * @author luisr
 */
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.ResultSet;
public class Coneccion {
    String url = "ubricentroGero.db"; //esta variable representa la ruta donde esta almacenada la base de datos   
    Connection connect;//objeto COnecction
    //creamos el metodo connect() que realizara la coneccion con la base de datos
    //connect gestiona la base de datos
    public void connect() throws SQLException{
        try{
            
            connect = DriverManager.getConnection("jdbc:sqlite:"+url);//connect recibe el controlador de sqlite, pasamos la ruta donde está
            if(connect!=null)
                System.out.println("Conectado\n");
            
        }catch(SQLException ex){
            System.out.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
        }
    }
    //creamos el metodo close que cerrara la base de datos
    public void close(){
        try{
            connect.close();
        }catch(SQLException ex){
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE,null,ex);  
            System.out.println("No se pudo cerrar la base de datos");
        }
    }
}
