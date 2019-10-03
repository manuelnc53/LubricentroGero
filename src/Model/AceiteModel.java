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
public class AceiteModel extends ProductoModel {
    private float litros;
    private String tipo;
    public AceiteModel(){
     super();
    }
    /**
     * @return the litros
     */
    public float getLitros() {
        return litros;
    }

    /**
     * @param litros the litros to set
     */
    public void setLitros(float litros) {
        this.litros = litros;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
    
}
