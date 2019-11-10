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
public class RefrigeranteDAO implements DAO<RefrigeranteModel> {
    private Connection conexion;
    private PreparedStatement consulta;
    public RefrigeranteDAO(){
        conexion = Conexion.getConnection();
    }
    @Override
    public boolean create(RefrigeranteModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RefrigeranteModel read(Long id) {
        ResultSet resultado = null ;
        RefrigeranteModel refrigerante = new RefrigeranteModel();
        String idAux=String.valueOf(id);
        try{
            
            consulta= conexion.prepareStatement("SELECT * FROM Refrigerante WHERE Re_ID="+idAux);
            resultado=consulta.executeQuery();
        
        }catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        try {
            
            refrigerante.setLitros(resultado.getFloat(2));
        } catch (SQLException ex) {
            
            Logger.getLogger(RefrigeranteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return refrigerante.clone();   
    }

    @Override
    public boolean update(RefrigeranteModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
