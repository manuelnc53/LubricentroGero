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
    private int aceite_ID;
    private float litros;
    private String tipo;
    
    public AceiteModel(){
     super();
     this.aceite_ID=0;
     this.litros=0;
     this.tipo="";
    } 
    /**
     * @return the acietie_ID
     */
    public int getAceite_ID() {
        return aceite_ID;
    }

    /**
     * @param acietie_ID the acietie_ID to set
     */
    public void setAceite_ID(int acietie_ID) {
        this.aceite_ID = acietie_ID;
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
    public AceiteModel clone(){
        AceiteModel clon = new AceiteModel();
        clon.setAceite_ID(this.aceite_ID);
        clon.setLitros(this.litros);
        clon.setTipo(this.tipo);
       //revisar el clone para poder clonar los atributos del padra aca
        
        return clon;
    }
    
}
