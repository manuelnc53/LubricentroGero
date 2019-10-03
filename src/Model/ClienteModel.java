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
public class ClienteModel {
    private ArrayList<String> telefonos_Del_Responsable;
    private long cuit_cuil;
    private String nombre;
    private String direccion;
    private ArrayList<OrdenModel> ordenes;
    private ArrayList<VentaModel> compras;

    public ClienteModel(){
    
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
     * @return the compras
     */
    public ArrayList<VentaModel> getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(ArrayList<VentaModel> compras) {
        this.compras = compras;
    }
 
    /**
     * @return the telefonos_Del_Responsable
     */
    public ArrayList<String> getTelefonos_Del_Responsable() {
        return telefonos_Del_Responsable;
    }

    /**
     * @param telefonos_Del_Responsable the telefonos_Del_Responsable to set
     */
    public void setTelefonos_Del_Responsable(ArrayList<String> telefonos_Del_Responsable) {
        this.telefonos_Del_Responsable = telefonos_Del_Responsable;
    }

    /**
     * @return the cuit_cuil
     */
    public long getCuit_cuil() {
        return cuit_cuil;
    }

    /**
     * @param cuit_cuil the cuit_cuil to set
     */
    public void setCuit_cuil(long cuit_cuil) {
        this.cuit_cuil = cuit_cuil;
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
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
   
    
}
