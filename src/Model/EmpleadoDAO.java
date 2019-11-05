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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author manuel
 */
public class EmpleadoDAO implements DAO<EmpleadoModel> {
    private Connection conexion;
    private PreparedStatement consulta;
    public EmpleadoDAO(){
    
    }
    @Override
    public boolean create(EmpleadoModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpleadoModel read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(EmpleadoModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List dameEmpleados() throws SQLException, ParseException, CloneNotSupportedException{
        List <EmpleadoModel> empleados = new ArrayList();
        try{
        conexion = Conexion.getConnection();
        consulta = conexion.prepareStatement("SELECT * FROM Empleados");
        ResultSet bdEmpleados=consulta.executeQuery();
        EmpleadoModel auxiliar=new EmpleadoModel();
        String res;
        Date fecha;
        ResponsabilidadModel responsabilidad=ResponsabilidadModel.CAJERO;
        while(bdEmpleados.next()){
            auxiliar.setDireccion(bdEmpleados.getString(1));
            System.out.println(bdEmpleados.getString(1));
            fecha=new SimpleDateFormat("dd/MM/yyyy").parse(bdEmpleados.getString(2));
            auxiliar.setEdad((Date) fecha.clone());
            res=bdEmpleados.getString(3);
            switch(res){
                case "CAJERO":
                    responsabilidad=ResponsabilidadModel.CAJERO;
                    break;
                case "ENCARGADO":
                    responsabilidad=ResponsabilidadModel.ENCARGADO;
                    break;
                case "MANTENIMIENTO":
                    responsabilidad=ResponsabilidadModel.MANTENIMIENTO;
                    break;
                
        }
            auxiliar.setResponsabilidad(responsabilidad);
            auxiliar.setNombre(bdEmpleados.getString(4));
            auxiliar.setCuit(bdEmpleados.getInt(5));
            fecha=new SimpleDateFormat("dd/MM/yyyy").parse(bdEmpleados.getString(6));
            auxiliar.setFecha_ingreso((Date) fecha.clone());
            empleados.add((EmpleadoModel) auxiliar.clone());
        }
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        return empleados;
    }
    
}
