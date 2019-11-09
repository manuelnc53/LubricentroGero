/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        orden.setNro_Orden();
        consulta.setString(1, dateFormat.format(orden.getFecha_Orden()));
        consulta.setLong(2, orden.getNro_Orden());
        consulta.setString(3, orden.getUrgencia().toString());
        consulta.setString(4,orden.getEstado().toString());
        consulta.setLong(5,  orden.getEmpleado_cajero().getCuit());
        consulta.setLong(6, orden.getCliente().getCuit_cuil());
        consulta.setString(7,orden.getVehiculo().getPatente());
        consulta.setString(8,orden.getDescripcion());
        consulta.execute();
        for(ServicioModel o: orden.getServicios()){
        consulta = conexion.prepareStatement("INSERT INTO Respecto (Respecto_Orden_ID,Respecto_Servicio_ID) VALUES (?,?)");
        consulta.setLong(1, orden.getNro_Orden());
        consulta.setLong(2, o.getId());
            System.out.println(orden.getNro_Orden()+" "+o.getId());
        consulta.execute();
        }
        for(EmpleadoModel o: orden.getEmpleados_mantenimiento()){
        consulta = conexion.prepareStatement("INSERT INTO Ejecuta (Ejec_Empleado_ID,Ejec_Orden_ID) VALUES (?,?)");
        consulta.setLong(2,  orden.getNro_Orden());
        consulta.setLong(1, o.getCuit());
        consulta.execute();
        }
    }

    long maximoID() throws SQLException {
        consulta = conexion.prepareStatement("SELECT MAX (Ord_Nro_Orden) from Ordenes");
        ResultSet resultado=consulta.executeQuery();
        //System.out.println("resultado:\n\n"+resultado.getInt(1));
        return resultado.getInt(1);
    }
    
}
