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
public class LiquidoDeFrenoModel extends ProductoModel {
    private String dot;
    private float volumen_unidad;
    public LiquidoDeFrenoModel(){
     super();
    }
    /**
     * @return the dot
     */
    public String getDot() {
        return dot;
    }

    /**
     * @param dot the dot to set
     */
    public void setDot(String dot) {
        this.dot = dot;
    }

    /**
     * @return the volumen_unidad
     */
    public float getVolumen_unidad() {
        return volumen_unidad;
    }

    /**
     * @param volumen_unidad the volumen_unidad to set
     */
    public void setVolumen_unidad(float volumen_unidad) {
        this.volumen_unidad = volumen_unidad;
    }
    
    
}
