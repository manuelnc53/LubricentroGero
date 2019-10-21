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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ServicioDAO implements DAO<ServicioModel> {
    private Connection conexion;
    private PreparedStatement consulta;
    
    public ServicioDAO(){
        conexion= Conexion.getConnection();
    }
    @Override
    public boolean create(ServicioModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServicioModel read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ServicioModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List dameServicios(){
        List<ServicioModel>listaServicos=new ArrayList();
        ResultSet resultado=null;
        ServicioModel servicio = new ServicioModel();
        try{
            consulta= conexion.prepareStatement("SELECT * FROM Servicios");//preparo la consulta a ser ejecutada
            resultado=consulta.executeQuery();
        }catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        try{
            while(resultado.next()){
                servicio.setId(resultado.getInt(1));
                servicio.setNombre(resultado.getString(2));
                servicio.setDescripcion(resultado.getString(3));
                servicio.setPrecio(resultado.getFloat(4));
                listaServicos.add(servicio.clone());
            }
        }catch(SQLException f){
            System.out.println("La consulta retorno vacio");
        } 
        return listaServicos;
    }
    
    
}
