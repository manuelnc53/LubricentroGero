/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author manuel
 */
public class OrdenDAO implements DAO<OrdenModel> {
    private Connection conexion;
    private PreparedStatement consulta;
    public OrdenDAO(){
        conexion= Conexion.getConnection();
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

    public void guardar(OrdenModel orden) throws SQLException {
        consulta = conexion.prepareStatement("INSERT INTO Ordenes (Ord_Fecha_Emision,Ord_Nro_Orden,Ord_Urgencia,Ord_Estado,Ord_Emp_ID,Ord_Clien_ID,Ord_Ve_ID,Ord_Descripcion) VALUES (?,?,?,?,?,?,?,?)");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        consulta.setString(1, dateFormat.format(orden.getFecha_Orden()));
        consulta.setInt(2, (int) orden.getNro_Orden());
        consulta.setString(3, orden.getUrgencia().toString());
        consulta.setString(4,orden.getUrgencia());
        consulta.setInt(5, (int) orden.getEmpleado_cajero().getCuit());
        consulta.setInt(6, (int) orden.getCliente().getCuit_cuil());
        consulta.setString(7,orden.getVehiculo().getPatente());
        consulta.setString(8,orden.getDescripcion());
        consulta.execute();
        for(ServicioModel o: orden.getServicios()){
        consulta = conexion.prepareStatement("INSERT INTO Respecto (Respecto_Orden_ID,Respecto_Servicio_ID) VALUES (?,?)");
        consulta.setInt(1, (int) orden.getNro_Orden());
        consulta.setInt(2, o.getId());
        }
        for(EmpleadoModel o: orden.getEmpleados_mantenimiento()){
        consulta = conexion.prepareStatement("INSERT INTO Ejecuta (Respecto_Orden_ID,Respecto_Servicio_ID) (?,?)");
        consulta.setInt(2, (int) orden.getNro_Orden());
        consulta.setInt(1, (int) o.getCuit());
        }
    }
    
}
