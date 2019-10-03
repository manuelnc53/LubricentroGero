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
public class CompraModel {
    private float monto;
    private Date fecha;
    private ArrayList<PedidoModel> pedidos;
    private ArrayList<EmpleadoModel> empleados_responsables;
    private ProveedorModel proveedor;

    
    public CompraModel(){
    
    }
    /**
     * @return the pedidos
     */
    public ArrayList<PedidoModel> getPedidos() {
        return pedidos;
    }

    /**
     * @param pedidos the pedidos to set
     */
    public void setPedidos(ArrayList<PedidoModel> pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * @return the empleados_responsables
     */
    public ArrayList<EmpleadoModel> getEmpleados_responsables() {
        return empleados_responsables;
    }

    /**
     * @param empleados_responsables the empleados_responsables to set
     */
    public void setEmpleados_responsables(ArrayList<EmpleadoModel> empleados_responsables) {
        this.empleados_responsables = empleados_responsables;
    }

    /**
     * @return the proveedor
     */
    public ProveedorModel getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(ProveedorModel proveedor) {
        this.proveedor = proveedor;
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
    
    
    
}
