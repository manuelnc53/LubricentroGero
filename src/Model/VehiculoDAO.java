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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author manuel
 */
public class VehiculoDAO implements DAO<VehiculoModel> {
    private Connection conexion;
    private PreparedStatement consulta;
    public VehiculoDAO(){
       conexion = Conexion.getConnection();
    }
    @Override
    public boolean create(VehiculoModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VehiculoModel read(Long id) {
       ResultSet resultado;
       VehiculoModel vehiculo=new VehiculoModel();
       String idAux=String.valueOf(id);
       try{
           conexion=Conexion.getConnection();
           consulta=conexion.prepareStatement("SELCT * FROM Vehiculos where Ve_Patente="+idAux);
           resultado=consulta.executeQuery();
           vehiculo.setTipo_motor(resultado.getString(2));
           vehiculo.setKilometraje(resultado.getInt(3));
           vehiculo.setMarca(resultado.getString(4));
       }catch(SQLException e){
           System.out.println("No se pudo realizar la consulta");
       }
       return vehiculo;
    }

    @Override
    public boolean update(VehiculoModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List dameVehiculos() throws SQLException, CloneNotSupportedException{
        List <VehiculoModel> vehiculos = new ArrayList();
        try{
        consulta = conexion.prepareStatement("SELECT * FROM Vehiculos");
        ResultSet bdVehiculos=consulta.executeQuery();
        VehiculoModel auxiliar=new VehiculoModel();
        String res;
        while(bdVehiculos.next()){
            auxiliar.setPatente(bdVehiculos.getString(1));
            auxiliar.setTipo_motor(bdVehiculos.getString(2));
            auxiliar.setKilometraje(bdVehiculos.getInt(3));
            auxiliar.setMarca(bdVehiculos.getString(4));
            auxiliar.setModelo(bdVehiculos.getString(5));
            vehiculos.add(auxiliar.clone());
        }
        }
                catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        return vehiculos;
    }
}
