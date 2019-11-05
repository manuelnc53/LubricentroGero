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
public class AceiteDAO implements DAO<AceiteModel> {
    private Connection conexion;
    private PreparedStatement consulta;
    public AceiteDAO(){
        conexion = Conexion.getConnection();
    }

    @Override
    public boolean create(AceiteModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AceiteModel read(Long id) {
        ResultSet resultado = null ;
        AceiteModel aceite = new AceiteModel();
        String idAux=String.valueOf(id);
        try{
            
            consulta= conexion.prepareStatement("SELECT * FROM Aceites WHERE Ac_ID="+idAux);
            
            resultado=consulta.executeQuery();
            
        }catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        try {
            aceite.setAceite_ID(resultado.getInt(1));
            aceite.setLitros(resultado.getFloat(2));
            aceite.setTipo(resultado.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(RefrigeranteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return aceite.clone();
    }

    @Override
    public boolean update(AceiteModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
