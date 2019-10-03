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
public enum ResponsabilidadModel {
    ENCARGADO(1),CAJERO(2),MANTENIMIENTO(3);
 private int responsabilidad;
    private ResponsabilidadModel(int responsabilidad){
        this.responsabilidad=responsabilidad;
    
    }
    /**
     * @return the responsabilidad
     */
    public int getResponsabilidad() {
        return responsabilidad;
    }

    /**
     * @param responsabilidad the responsabilidad to set
     */
    public void setResponsabilidad(int responsabilidad) {
        this.responsabilidad = responsabilidad;
    }
   
    
}
