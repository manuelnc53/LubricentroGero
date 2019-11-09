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

import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;



import java.text.DateFormat;

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
              System.out.println("ACA"); 
              cliente= st3.executeQuery("select ResTel from ClientesTelefonosResponsable where Clien_CUIT_CUIL_Tel="+ resultado.getString("Ord_Clien_ID"));
             
              
              aux.setCliente(new ClienteModel(resultado.getLong("Ord_Clien_ID"),cliente.getString("ResTel")));
              
              
               
              servicios= st3.executeQuery("select distinct Ser_Nombre from Servicios, Respecto where Respecto_Orden_ID="+resultado.getLong("Ord_Nro_Orden")+" and  Respecto_Servicio_ID=Ser_ID");
             System.out.println("--");
              ArrayList<ServicioModel> auxServicios= new ArrayList();
              while(servicios.next()){
                   System.out.println(servicios.getString("Ser_Nombre"));
                   
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
//
    public ArrayList<OrdenModel> dameOrdenes() throws SQLException{
        
        String idCliente="20397508685";
        Date fechaOrd;
        ArrayList<OrdenModel>listaOrdenes = new ArrayList();
        
        ResultSet resultado;
        ResultSet servicios;
        ResultSet mantenimiento;
        ResultSet vehiculo;
        PreparedStatement st;
        PreparedStatement st2;
        PreparedStatement st3;
        PreparedStatement st4;
        
        
        
            conexion = Conexion.getConnection();
            st= conexion.prepareStatement("SELECT * FROM Ordenes WHERE "+idCliente+"=Ord_Clien_ID");
            resultado=st.executeQuery();
            while(resultado.next()){
                OrdenModel ordenAux=new OrdenModel();
                try{
                fechaOrd=new SimpleDateFormat("dd/MM/yyyy").parse(resultado.getString("Ord_Fecha_Emision"));
                ordenAux.setFecha_Orden(fechaOrd);
                }catch(ParseException p){
                    System.out.println("Error de parseo de string a date(orden dao)");
                }
                ordenAux.setNro_Orden(resultado.getInt("Ord_Nro_Orden"));
                ordenAux.setUrgencia(resultado.getString("Ord_Urgencia"));
                if (resultado.getString("Ord_Estado").equals("en_proceso")){
                  ordenAux.setEstado(EstadoModel.EN_PROCESO);
                }else{
                  ordenAux.setEstado(EstadoModel.FINALIZADO);
                }
                //no necesito el cajero
                ordenAux.setDescripcion(resultado.getString("Ord_Descripcion"));
                //consulto para traer los servicios realizados en la orden
                System.out.println("resultado del get long: "+resultado.getLong("Ord_Nro_Orden"));
                //auxiliar string que contiene la id de la orden
                String nrOrden=String.valueOf(resultado.getLong("Ord_Nro_Orden"));
                st2=conexion.prepareStatement("SELECT  Ser_ID,Ser_Nombre,Ser_Precio FROM Servicios,Respecto WHERE Respecto_Orden_ID="+nrOrden+" and  Respecto_Servicio_ID=Ser_ID");
                servicios=st2.executeQuery();
               
                ArrayList<ServicioModel> auxServicios= new ArrayList();
                while(servicios.next()){
                    ServicioModel servicioAux=new ServicioModel();
                    servicioAux.setId(servicios.getLong("Ser_ID"));
                    servicioAux.setNombre(servicios.getString("Ser_Nombre"));
                    
                    servicioAux.setPrecio(servicios.getDouble("Ser_Precio"));
                    auxServicios.add(servicioAux);
                }
                
                
                ordenAux.setServicios(auxServicios);
                st3= conexion.prepareStatement("select distinct Emp_Nombre,Emp_CUIL  from Empleados where Emp_CUIL in (select Ejec_Empleado_ID from Ejecuta where Ejec_Orden_ID="+ nrOrden+ ")");
                mantenimiento=st3.executeQuery();
                ArrayList<EmpleadoModel> auxMantenimiento= new ArrayList();
                while(mantenimiento.next()){
                    EmpleadoModel empAux=new EmpleadoModel();
                    empAux.setCuit(mantenimiento.getLong("Emp_CUIL"));
                    empAux.setNombre(mantenimiento.getString("Emp_Nombre"));
                    auxMantenimiento.add(empAux);
                }
                String patenteAux=resultado.getString("Ord_Ve_ID");
                System.out.println(patenteAux);
                
                st4= conexion.prepareStatement("SELECT * FROM Vehiculos WHERE Ve_Patente= '"+patenteAux+"';");
                vehiculo=st4.executeQuery();
                
                VehiculoModel vAux=new VehiculoModel();
                while(vehiculo.next()){
                    vAux.setPatente(vehiculo.getString("Ve_Patente"));
                }
                ordenAux.setVehiculo(vAux);
                
                ordenAux.setEmpleados_mantenimiento(auxMantenimiento);
                
                listaOrdenes.add(ordenAux);
            }
                 return listaOrdenes;
        
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

    
        
        //SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
        //resultado = st.executeQuery("select * from Ordenes");
        //Date tem;
        //tem=new SimpleDateFormat("dd/MM/yyyy").parse(resultado.getString("Ord_Fecha_Emision"));

   
    
    }
