/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author manuel
 */
public class VentaModel {
    private Date fecha;
    private float monto;
    private ArrayList<ServicioModel> servicios;
    private ArrayList<Item_de_VentaModel> item_de_venta;
    private ClienteModel cliente;
    private EmpleadoModel empleado_responsable;
    
    
    public VentaModel(){
    
    
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
     * @return the item_de_venta
     */
    public ArrayList<Item_de_VentaModel> getItem_de_venta() {
        return item_de_venta;
    }

    /**
     * @param item_de_venta the item_de_venta to set
     */
    public void setItem_de_venta(ArrayList<Item_de_VentaModel> item_de_venta) {
        this.item_de_venta = item_de_venta;
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
     * @return the empleado_responsable
     */
    public EmpleadoModel getEmpleado_responsable() {
        return empleado_responsable;
    }

    /**
     * @param empleado_responsable the empleado_responsable to set
     */
    public void setEmpleado_responsable(EmpleadoModel empleado_responsable) {
        this.empleado_responsable = empleado_responsable;
    }
    
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the monto
     */
    public float getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }
    
}
