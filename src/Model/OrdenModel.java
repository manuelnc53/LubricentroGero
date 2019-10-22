/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author manuel
 */
public class OrdenModel {


    private Date fecha_Orden;
    private long nro_Orden;
    private String urgencia;
    private EstadoModel estado;
    private EmpleadoModel empleado_cajero;
    private ArrayList<EmpleadoModel> empleados_mantenimiento;
    private ClienteModel cliente;
    private VehiculoModel vehiculo;
    private ArrayList<ServicioModel> servicios;
    private String descripcion;
    
    
    public OrdenModel(){
        fecha_Orden = new Date();
    }
    
    /**
     * @return the empleado_cajero
     */
    public EmpleadoModel getEmpleado_cajero() {
        return empleado_cajero;
    }

    public OrdenModel(String urgencia, EstadoModel estado, EmpleadoModel empleado_cajero, ArrayList<EmpleadoModel> empleados_mantenimiento, ClienteModel cliente, VehiculoModel vehiculo, ArrayList<ServicioModel> servicios) {
        this.urgencia = urgencia;
        this.estado = estado;
        this.empleado_cajero = empleado_cajero;
        this.empleados_mantenimiento = empleados_mantenimiento;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.servicios = servicios;
    }

    /**
     * @param empleado_cajero the empleado_cajero to set
     */
    public void setEmpleado_cajero(EmpleadoModel empleado_cajero) {
        this.empleado_cajero = empleado_cajero;
    }

    /**
     * @return the empleados_mantenimiento
     */
    public ArrayList<EmpleadoModel> getEmpleados_mantenimiento() {
        return empleados_mantenimiento;
    }

    /**
     * @param empleados_mantenimiento the empleados_mantenimiento to set
     */
    public void setEmpleados_mantenimiento(ArrayList<EmpleadoModel> empleados_mantenimiento) {
        this.empleados_mantenimiento = empleados_mantenimiento;
    }

    /**
     * @return the cliente
     */
    public ClienteModel getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the vehiculo
     */
    public VehiculoModel getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(VehiculoModel vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the servicios
     */
    public ArrayList<ServicioModel> getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(ArrayList<ServicioModel> servicios) {
        this.servicios = servicios;
    }
    /**
     * @return the fecha_Orden
     */
    public Date getFecha_Orden() {
        return fecha_Orden;
    }

    /**
     * @param fecha_Orden the fecha_Orden to set
     */
    public void setFecha_Orden(Date fecha_Orden) {
        this.fecha_Orden = fecha_Orden;
    }

    /**
     * @return the nro_Orden
     */
    public long getNro_Orden() {
        return nro_Orden;
    }

    /**
     * @param nro_Orden the nro_Orden to set
     */
    public void setNro_Orden() throws SQLException {
        OrdenDAO ordenBD = new OrdenDAO();
        this.nro_Orden=ordenBD.maximoID();
        nro_Orden++;
    }

    /**
     * @return the urgencia
     */
    public String getUrgencia() {
        return urgencia;
    }

    /**
     * @param urgencia the urgencia to set
     */
    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    /**
     * @return the estado
     */
    public EstadoModel getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoModel estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "OrdenModel{" + "fecha_Orden=" + fecha_Orden + ", nro_Orden=" + nro_Orden + ", urgencia=" + urgencia + ", estado=" + estado + ", empleado_cajero=" + empleado_cajero + ", empleados_mantenimiento=" + empleados_mantenimiento + ", cliente=" + cliente + ", vehiculo=" + vehiculo + ", servicios=" + servicios + '}';
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    
}
