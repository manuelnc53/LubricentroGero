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

/**
 *
 * @author manuel
 */
public class OrdenDAO implements DAO<OrdenModel> {
    private Connection conexion;
    public OrdenDAO(){
        conexion=Conexion.getConnection();
}
    @Override
    public boolean create(OrdenModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //read que me trae la orden pero solo con servicios, no necesito mas que el id de la orden y los servicios
    public OrdenModel read(OrdenModel dato){
    /*
        String nroOrdenAux=String.valueOf(dato.getNro_Orden());
        conexion = Conexion.getConnection();
        //resultsets
        ResultSet resultado;
        ResultSet servicios;
        PreparedStatement consulta;
        PreparedStatement st2;
        consulta=conexion.prepareStatement("SELECT * FROM Ordenes WHERE "+nroOrdenAux+"=Ord_Nro_Orden");
        resultado=consulta.executeQuery();
        while(resultado.next()){
            st2=conexion.prepareStatement("SELECT  Ser_ID,Ser_Nombre,Ser_Precio FROM Servicios,Respecto WHERE Respecto_Orden_ID="+nroOrdenAux+" and  Respecto_Servicio_ID=Ser_ID");
            servicios=st2.executeQuery();
            //auxiliartque contendra los servicios que pertenecen a la ordea buscada
            ArrayList<ServicioModel> auxServicios= new ArrayList();
            while(servicios.next()){
                ServicioModel servicioAux=new ServicioModel();
                servicioAux.setId(servicios.getLong("Ser_ID"));
                servicioAux.setNombre(servicios.getString("Ser_Nombre"));
                servicioAux.setPrecio(servicios.getDouble("Ser_Precio"));
                auxServicios.add(servicioAux);//agrego al array de servicios
            }
            dato.setServicios(auxServicios);
            
        }
     */   
    throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(OrdenModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public ArrayList<OrdenModel> dameOrdenes(String idCliente) throws SQLException{
        
        
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
                System.out.println("mantenimiento en el dao esta cerrado:"+mantenimiento.isClosed());
                while(mantenimiento.next()){
                    EmpleadoModel empAux=new EmpleadoModel();
                    empAux.setCuit(mantenimiento.getLong("Emp_CUIL"));
                    empAux.setNombre(mantenimiento.getString("Emp_Nombre"));
                    auxMantenimiento.add(empAux);
                    System.out.println("En el orden dao los empleados:"+auxMantenimiento.isEmpty());
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
    
        
        //SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
        //resultado = st.executeQuery("select * from Ordenes");
        //Date tem;
        //tem=new SimpleDateFormat("dd/MM/yyyy").parse(resultado.getString("Ord_Fecha_Emision"));
        
        return listaOrdenes;
        
    }
    
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        OrdenDAO ord=new OrdenDAO();
        ArrayList<OrdenModel>listaOrdenes=new ArrayList();
        
        listaOrdenes=ord.dameOrdenes("20397508685");
        for(OrdenModel o:listaOrdenes){
            System.out.println(o);
        } 
    }

    @Override
    public OrdenModel read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
