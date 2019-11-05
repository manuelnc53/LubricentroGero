/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class LiquidoDeFrenoDAO implements DAO<LiquidoDeFrenoModel> {
    private Connection conexion;
    private PreparedStatement consulta;
    public LiquidoDeFrenoDAO(){
        conexion = Conexion.getConnection();
    }
    @Override
    public boolean create(LiquidoDeFrenoModel dato) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LiquidoDeFrenoModel read(Long id) {
         ResultSet resultado = null ;
        LiquidoDeFrenoModel liquidoDeFreno = new LiquidoDeFrenoModel();
        String idAux=String.valueOf(id);
        try{
            
            consulta= conexion.prepareStatement("SELECT * FROM LiquidoFrenos WHERE LF_ID="+idAux);
            resultado=consulta.executeQuery();
        
        }catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        try {
            liquidoDeFreno.setVolumen_unidad(resultado.getFloat(2));
        } catch (SQLException ex) {
            Logger.getLogger(RefrigeranteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return liquidoDeFreno.clone();         
    }

    @Override
    public boolean update(LiquidoDeFrenoModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
