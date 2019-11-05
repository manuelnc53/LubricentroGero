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
    public ArrayList<OrdenModel> getAll() throws SQLException{
        
        ArrayList<OrdenModel> retorno = new ArrayList();
       
        
        
        Statement st =Conexion.getConnection().createStatement();
        Statement st2 =Conexion.getConnection().createStatement();
        Statement st3 =Conexion.getConnection().createStatement();
        Statement st4 =Conexion.getConnection().createStatement();
        ResultSet resultado;
        ResultSet cajero;
        ResultSet cliente;
        ResultSet servicios;
        ResultSet mantenimiento;

        
        resultado = st.executeQuery("select * from Ordenes");
            while ( resultado.next() ) {
               
                
                OrdenModel aux=new OrdenModel();
                
              aux.setNro_Orden(resultado.getLong("Ord_Nro_Orden"));
              
              aux.setUrgencia(resultado.getString("Ord_Urgencia"));
              aux.setVehiculo( new VehiculoModel(resultado.getString("Ord_Ve_ID")));
              if (resultado.getString("Ord_Estado").equals("en_proceso")){
                  aux.setEstado(EstadoModel.EN_PROCESO);
              }else{
                  aux.setEstado(EstadoModel.FINALIZADO);
              }
              cajero = st2.executeQuery("select Emp_Nombre from Empleados where Emp_CUIL="+ resultado.getString("Ord_Emp_ID"));
             
              aux.setEmpleado_cajero(new EmpleadoModel(resultado.getLong("Ord_Emp_ID"),cajero.getString("Emp_Nombre")));
               
              cliente= st3.executeQuery("select ResTel from ClientesTelefonosResponsable where Clien_CUIT_CUIL_Tel="+ resultado.getString("Ord_Clien_ID"));
              aux.setCliente(new ClienteModel(resultado.getLong("Ord_Clien_ID"),cliente.getString("ResTel")));
              
              
              servicios= st3.executeQuery("select distinct Ser_Nombre from Servicios, Respecto where Respecto_Orden_ID="+resultado.getLong("Ord_Nro_Orden")+" and  Respecto_Servicio_ID=Ser_ID");
             // System.out.println("--");
              ArrayList<ServicioModel> auxServicios= new ArrayList();
              while(servicios.next()){
                   //System.out.println(servicios.getString("Ser_Nombre"));
                   
                   auxServicios.add(new ServicioModel(servicios.getString("Ser_Nombre")));
              
              }
              aux.setServicios(auxServicios);
              
              
              
              mantenimiento= st3.executeQuery("select distinct Emp_Nombre,Emp_CUIL  from Empleados where Emp_CUIL in (select Ejec_Empleado_ID from Ejecuta where Ejec_Orden_ID="+ resultado.getLong("Ord_Nro_Orden")+ ")");
             // System.out.println("--");
              ArrayList<EmpleadoModel> auxMantenimiento= new ArrayList();
              while(mantenimiento.next()){
                   //System.out.println(servicios.getString("Ser_Nombre"));
                   
                   
                   auxMantenimiento.add(new EmpleadoModel(mantenimiento.getLong("Emp_CUIL"),mantenimiento.getString("Emp_Nombre")));
              
              }
              aux.setEmpleados_mantenimiento(auxMantenimiento);
              /**  String[] aux= new String[7];
                aux[0]=resultado.getString("Ord_Fecha_Emision");
                aux[1]=resultado.getString("Ord_Nro_Orden");
                aux[2]=resultado.getString("Ord_Urgencia");
                aux[3]=resultado.getString("Ord_Estado");
                aux[4]=resultado.getString("Ord_Emp_ID");
                aux[5]=resultado.getString("Ord_Clien_ID");
                aux[6]=resultado.getString("Ord_Ve_ID");
                
                
             
           retorno.add(aux);**/
               retorno.add(aux);
            }
            
        return retorno;
        
    }
}
