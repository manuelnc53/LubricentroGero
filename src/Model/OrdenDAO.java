/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class OrdenDAO implements DAO<OrdenModel> {
    private Conexion conexion;
    public OrdenDAO(){
        

}
    @Override
    public boolean create(OrdenModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrdenModel read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(OrdenModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public ArrayList<String[]> getAll() throws SQLException{
        
        ArrayList<String[]> retorno = new ArrayList();
       
        
        
        Statement st =Conexion.getConnection().createStatement();
        ResultSet resultado;
        
        resultado = st.executeQuery("select * from Ordenes");
            while ( resultado.next() ) {
                 String[] aux= new String[7];
                aux[0]=resultado.getString("Ord_Fecha_Emision");
                aux[1]=resultado.getString("Ord_Nro_Orden");
                aux[2]=resultado.getString("Ord_Urgencia");
                aux[3]=resultado.getString("Ord_Estado");
                aux[4]=resultado.getString("Ord_Emp_ID");
                aux[5]=resultado.getString("Ord_Clien_ID");
                aux[6]=resultado.getString("Ord_Ve_ID");
                
                
             
           retorno.add(aux);
            }
            
        return retorno;
        
    }
}
