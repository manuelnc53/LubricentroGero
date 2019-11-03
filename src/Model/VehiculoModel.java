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
public class VehiculoModel {

    
  private String tipo_motor;
    private String patente;
    private long kilometraje;
    private String marca;
    private ArrayList<OrdenModel> ordenes;
    public VehiculoModel(){
    
    }
    public VehiculoModel(String patente){
        this.patente=patente;
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
     * @return the tipo_motor
     */
    public String getTipo_motor() {
        return tipo_motor;
    }

    /**
     * @param tipo_motor the tipo_motor to set
     */
    public void setTipo_motor(String tipo_motor) {
        this.tipo_motor = tipo_motor;
    }

    /**
     * @return the patente
     */
    public String getPatente() {
        return patente;
    }

    /**
     * @param patente the patente to set
     */
    public void setPatente(String patente) {
        this.patente = patente;
    }

    /**
     * @return the kilometraje
     */
    public long getKilometraje() {
        return kilometraje;
    }

    /**
     * @param kilometraje the kilometraje to set
     */
    public void setKilometraje(long kilometraje) {
        this.kilometraje = kilometraje;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
  
    
}
