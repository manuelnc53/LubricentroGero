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
            //if(conexion!=null)
            //System.out.println(url);

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
        */
    }
    public static void main(String[] args) throws SQLException, Exception {
        // TODO code application logic here
       Connection con = null;
       con = Conexion.getConnection();
        PreparedStatement st=  con.prepareStatement("INSERT INTO Clientes (Clien_CUIT_CUIL,Clien_Nombre,Clien_Direccion) VALUES('28397508685','Pedro','av we')");
        st.execute();
        
        //st.execute("INSERT INTO Clientes (Clien_CUIT_CUIL,Clien_Nombre,Clien_Direccion) VALUES(20397508685,Pedro carlos,av 1234)");
        //st= con.prepareStatement("SELECT FROM * Clientes");
       // ResultSet resultado = st.execute("SELECT FROM * Clientes");
    }
    
}
