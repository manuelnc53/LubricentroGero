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
import java.util.Date;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ServicioDAO implements DAO<ServicioModel> {
    private Connection conexion;
    private PreparedStatement consulta;
    public ServicioDAO(){
        conexion = Conexion.getConnection();
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
    
    //A estos daos les falta obtener las relaciones foreigns ya que hasta ahora no lo necesito, probablemente manu
    //si necesite hacer el dao de servicios completo, es decir, un select que traiga la lista de ordenes asociadas.
    public List dameServicios() throws SQLException, CloneNotSupportedException{
        List <ServicioModel> servicios = new ArrayList();
        try{
        consulta = conexion.prepareStatement("SELECT * FROM Servicios");
        ResultSet bdServicios=consulta.executeQuery();
        ServicioModel auxiliar=new ServicioModel();
        String res;
        Date fecha;
        while(bdServicios.next()){
            auxiliar.setId(bdServicios.getInt(1));
            auxiliar.setNombre(bdServicios.getString(2));
            auxiliar.setDescripcion(bdServicios.getString(3));
            auxiliar.setPrecio(bdServicios.getFloat(4));
            servicios.add((ServicioModel) auxiliar.clone());
        }
        }
                catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        return servicios;
    }
    
}
