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

     public ClienteModel(long cuit_cuil, String telefono){
         this.telefonos_Del_Responsable=new ArrayList();
         this.cuit_cuil=cuit_cuil;
         this.telefonos_Del_Responsable.add(telefono);
         this.ordenes= new ArrayList();
         this.compras= new ArrayList();

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
   
    public ClienteModel clone(){
        ClienteModel clon = new ClienteModel();
        clon.setCompras(this.compras);
        clon.setCuit_cuil(this.cuit_cuil);
        clon.setDireccion(this.direccion);
        clon.setNombre(this.nombre);
        clon.setOrdenes(this.ordenes);
        clon.setTelefonos_Del_Responsable(this.telefonos_Del_Responsable);
        return clon;
    }

    @Override
    public String toString() {
        return "ClienteModel{" + "telefonos_Del_Responsable=" + telefonos_Del_Responsable + ", cuit_cuil=" + cuit_cuil + ", nombre=" + nombre + ", direccion=" + direccion + ", ordenes=" + ordenes + ", compras=" + compras + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.cuit_cuil ^ (this.cuit_cuil >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClienteModel other = (ClienteModel) obj;
        if (this.cuit_cuil != other.cuit_cuil) {
            return false;
        }
        return true;
    }

    
}
