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
public class ProveedorModel {
    private String nombreDelResponsable;
    private String nombre_Empresa;
    private long cuit;
    private ArrayList<String> telefonos;
    private ArrayList<CompraModel> compras;
    private ArrayList<PedidoModel> pedidos;

    public ProveedorModel(){
    
    }
    /**
     * @return the compras
     */
    public ArrayList<CompraModel> getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(ArrayList<CompraModel> compras) {
        this.compras = compras;
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
     * @return the nombreDelResponsable
     */
    public String getNombreDelResponsable() {
        return nombreDelResponsable;
    }

    /**
     * @param nombreDelResponsable the nombreDelResponsable to set
     */
    public void setNombreDelResponsable(String nombreDelResponsable) {
        this.nombreDelResponsable = nombreDelResponsable;
    }

    /**
     * @return the nombre_Empresa
     */
    public String getNombre_Empresa() {
        return nombre_Empresa;
    }

    /**
     * @param nombre_Empresa the nombre_Empresa to set
     */
    public void setNombre_Empresa(String nombre_Empresa) {
        this.nombre_Empresa = nombre_Empresa;
    }

    /**
     * @return the cuit
     */
    public long getCuit() {
        return cuit;
    }

    /**
     * @param cuit the cuit to set
     */
    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    /**
     * @return the telefonos
     */
    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    /**
     * @param telefonos the telefonos to set
     */
    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }
    
    
}
