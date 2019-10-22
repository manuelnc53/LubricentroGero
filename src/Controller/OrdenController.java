/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClienteModel;
import Model.Conexion;
import Model.OrdenModel;
import java.util.ArrayList;
import Model.DAO;
import Model.EmpleadoModel;
import Model.EstadoModel;
import Model.OrdenDAO;
import Model.ServicioModel;
import Model.VehiculoModel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author manuel
 */
public class OrdenController {
    private OrdenModel orden;
    private OrdenDAO ordenBD;
    private static float tazaAumento=(float) 0.2;
    public OrdenController(){
        orden=new OrdenModel();
        ordenBD=new OrdenDAO();
    }
    
    
    
    public void setOrden(long nroOrden){
        
    }

    public void guardar(String urgencia, EstadoModel estadoModel, EmpleadoModel cajero,
        List<EmpleadoModel> empleadosSelec, ClienteModel cliente,
        VehiculoModel vehiculo, List<ServicioModel> servicios,String descripcion) throws SQLException {
        orden.setCliente(cliente);
        orden.setUrgencia(urgencia);
        orden.setEmpleado_cajero(cajero);
        orden.setEstado(estadoModel);
        orden.setEmpleados_mantenimiento((ArrayList<EmpleadoModel>) empleadosSelec);
        orden.setVehiculo(vehiculo);
        orden.setServicios((ArrayList<ServicioModel>) servicios);
        orden.setDescripcion("hola");
        orden.setDescripcion(descripcion);
    }

    
    public void guardarEnBD() throws SQLException{
        ordenBD.guardar(orden);
    }
    
    public  float costoOrden(){
        float aux=0,aumento=1;
        for(ServicioModel o: orden.getServicios()){
            aux=(float) (aux+o.getPrecio());
        }
        /*System.out.println("llegue aqui");
        for(EmpleadoModel o: mantenimiento){
            aumento=(float) (aumento+tazaAumento);
        }*/
        aumento=aumento+(float) (orden.getEmpleados_mantenimiento().size()*tazaAumento);
        if(aumento==1)
            aux=0;
        else
        {aumento=aumento-tazaAumento;
            aux=aux*aumento;
        }
        return aux;
    }

    public void guardarServicios(List<ServicioModel> servicios) {
        orden.setServicios((ArrayList<ServicioModel>) servicios);
    }

    public String texto() {
    String texto="Cliente: "+orden.getCliente().getNombre()+"\nCajero: "
            +orden.getEmpleado_cajero().getNombre()+"\nServicios: ";
    for(ServicioModel o: orden.getServicios()){
        texto = texto+o.getNombre()+", ";
    }
    texto=texto+"\nVehiculo: "+orden.getVehiculo().getModelo()+"\nEmpleado/s: ";
    for(EmpleadoModel o: orden.getEmpleados_mantenimiento()){
        texto = texto+o.getNombre()+", ";
    }
    texto=texto+"\nCosto: ";
    float costo=this.costoOrden();
    texto=texto+costo;  
    return texto;
    
    }
}
