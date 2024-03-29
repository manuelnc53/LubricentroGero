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
public class ServicioModel {
    private String nombre;
    private String descripcion;
    private double precio;
    private ArrayList<OrdenModel> ordenes;
    private ArrayList<VentaModel> ventas;
    
    
    public ServicioModel(){
    }

    /**
     * @return the ordenes
     */
    public ArrayList<OrdenModel> getOrdenes() {
        return ordenes;
    }

    /**
     * @param ordenes the ordenes to set
     */
    public void setOrdenes(ArrayList<OrdenModel> ordenes) {
        this.ordenes = ordenes;
    }

    /**
     * @return the ventas
     */
    public ArrayList<VentaModel> getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(ArrayList<VentaModel> ventas) {
        this.ventas = ventas;
    }
    
 
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
