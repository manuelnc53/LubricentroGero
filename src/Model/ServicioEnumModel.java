/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author manuel
 */
public enum ServicioEnumModel {
    SI(true), NO(false);
    private boolean servicio;
    private ServicioEnumModel(boolean servicio){
        this.servicio=servicio;
    }
    /**
     * @return the servicio
     */
    public boolean isServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(boolean servicio) {
        this.servicio = servicio;
    }
    
    
}
