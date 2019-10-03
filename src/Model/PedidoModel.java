/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class PedidoModel {
    private long nro_Pedido;
    private float presupuesto;
    private ArrayList<CompraModel> compras;
    private ArrayList<EmpleadoModel> empleados_responsable;
    private ProveedorModel proveedor;
    private ArrayList<ProductoModel> productos;

    public PedidoModel(){
    
    }
    /**
     * @return the productos
     */
    public ArrayList<ProductoModel> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(ArrayList<ProductoModel> productos) {
        this.productos = productos;
    }
    
    /**
     * @return the compras
     */
    public ArrayList<CompraModel> getCompra() {
        return compras;
    }

    /**
     * @param compra the compras to set
     */
    public void setCompra(ArrayList<CompraModel> compra) {
        this.compras = compra;
    }

    /**
     * @return the empleados_responsable
     */
    public ArrayList<EmpleadoModel> getEmpleados_responsable() {
        return empleados_responsable;
    }

    /**
     * @param empleados_responsable the empleados_responsable to set
     */
    public void setEmpleados_responsable(ArrayList<EmpleadoModel> empleados_responsable) {
        this.empleados_responsable = empleados_responsable;
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
     * @return the nro_Pedido
     */
    public long getNro_Pedido() {
        return nro_Pedido;
    }

    /**
     * @param nro_Pedido the nro_Pedido to set
     */
    public void setNro_Pedido(long nro_Pedido) {
        this.nro_Pedido = nro_Pedido;
    }

    /**
     * @return the presupuesto
     */
    public float getPresupuesto() {
        return presupuesto;
    }

    /**
     * @param presupuesto the presupuesto to set
     */
    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }
   
}
