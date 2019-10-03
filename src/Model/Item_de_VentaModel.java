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
public class Item_de_VentaModel {
    private int cantidad;
    private ServicioEnumModel servicio;
    private ArrayList<VentaModel> venta;
    private ProductoModel producto;
    public Item_de_VentaModel(){
    }
    
    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the servicio
     */
    public ServicioEnumModel getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(ServicioEnumModel servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the venta
     */
    public ArrayList<VentaModel> getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(ArrayList<VentaModel> venta) {
        this.venta = venta;
    }

    /**
     * @return the producto
     */
    public ProductoModel getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }
    
}
