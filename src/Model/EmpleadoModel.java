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
public class EmpleadoModel {
    private String direccion;
    private Date nacimiento;
    private ResponsabilidadModel responsabilidad;
    private String nombre;
    private long cuit;
    private Date fecha_ingreso;
    private ArrayList<OrdenModel> ordenes_generadas;
    private ArrayList<OrdenModel> ordenes_ejecutadas;
    private ArrayList<VentaModel> ventas_realizadas;
    private ArrayList<PedidoModel> pedidos_realizados;
    private ArrayList<CompraModel> compras_realizadas;
    public EmpleadoModel(){
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

    /**
     * @return the edad
     */
    public Date getEdad() {
        return nacimiento;
    }

    /**
     * @param nacimiento the edad to set
     */
    public void setEdad(Date nacimiento){
        this.nacimiento = nacimiento;
    }

    /**
     * @return the responsabilidad
     */
    public ResponsabilidadModel getResponsabilidad() {
        return responsabilidad;
    }

    /**
     * @param responsabilidad the responsabilidad to set
     */
    public void setResponsabilidad(ResponsabilidadModel responsabilidad) {
        this.responsabilidad = responsabilidad;
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
     * @return the fecha_ingreso
     */
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the ordenes_generadas
     */
    public ArrayList<OrdenModel> getOrdenes_generadas() {
        return ordenes_generadas;
    }

    /**
     * @param ordenes_generadas the ordenes_generadas to set
     */
    public void setOrdenes_generadas(ArrayList<OrdenModel> ordenes_generadas) {
        this.ordenes_generadas = ordenes_generadas;
    }

    /**
     * @return the ordenes_ejecutadas
     */
    public ArrayList<OrdenModel> getOrdenes_ejecutadas() {
        return ordenes_ejecutadas;
    }

    /**
     * @param ordenes_ejecutadas the ordenes_ejecutadas to set
     */
    public void setOrdenes_ejecutadas(ArrayList<OrdenModel> ordenes_ejecutadas) {
        this.ordenes_ejecutadas = ordenes_ejecutadas;
    }

    /**
     * @return the ventas_realizadas
     */
    public ArrayList<VentaModel> getVentas_realizadas() {
        return ventas_realizadas;
    }

    /**
     * @param ventas_realizadas the ventas_realizadas to set
     */
    public void setVentas_realizadas(ArrayList<VentaModel> ventas_realizadas) {
        this.ventas_realizadas = ventas_realizadas;
    }

    /**
     * @return the pedidos_realizados
     */
    public ArrayList<PedidoModel> getPedidos_realizados() {
        return pedidos_realizados;
    }

    /**
     * @param pedidos_realizados the pedidos_realizados to set
     */
    public void setPedidos_realizados(ArrayList<PedidoModel> pedidos_realizados) {
        this.pedidos_realizados = pedidos_realizados;
    }

    /**
     * @return the compras_realizadas
     */
    public ArrayList<CompraModel> getCompras_realizadas() {
        return compras_realizadas;
    }

    /**
     * @param compras_realizadas the compras_realizadas to set
     */
    public void setCompras_realizadas(ArrayList<CompraModel> compras_realizadas) {
        this.compras_realizadas = compras_realizadas;
    }

    @Override
    public String toString() {
        return "\nDireccion=" + direccion + "\nNacimiento=" + nacimiento + 
                "\nResponsabilidad=" + responsabilidad +
                "\nNombre=" + nombre + 
                "\nCuit=" + cuit + "\nFecha_ingreso=" + fecha_ingreso + "\nOrdenes_generadas=" 
                + ordenes_generadas + "\nOrdenes_ejecutadas=" + ordenes_ejecutadas + 
                "\nVentas realizadas=" + ventas_realizadas + "\nPedidos realizados=" + pedidos_realizados + 
                "\nCompras_realizadas=" + compras_realizadas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.cuit ^ (this.cuit >>> 32));
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
        final EmpleadoModel other = (EmpleadoModel) obj;
        if (this.cuit != other.cuit) {
            return false;
        }
        return true;
    }
    

    
    @Override
    public EmpleadoModel clone(){
        EmpleadoModel clon = new EmpleadoModel();
        clon.setCuit(cuit);
        clon.setDireccion(direccion);
        clon.setEdad(nacimiento);
        clon.setFecha_ingreso(fecha_ingreso);
        clon.setNombre(nombre);
        clon.setResponsabilidad(responsabilidad);
        return clon;
    }
    
}
