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
    private String modelo;
    public VehiculoModel(){
    
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

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
  @Override
    public String toString(){
        return this.marca+" "+this.modelo+" "+this.patente+" "+this.tipo_motor;
    }
  @Override
    public VehiculoModel clone(){
    VehiculoModel clon = new VehiculoModel();
    clon.setKilometraje(this.getKilometraje());
    clon.setMarca(this.marca);
    clon.setModelo(modelo);
    clon.setPatente(patente);
    clon.setTipo_motor(tipo_motor);
    return clon;
    }
}
