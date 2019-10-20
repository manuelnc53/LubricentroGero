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
public class RefrigeranteModel extends ProductoModel {
    private float litros;
    public RefrigeranteModel(){
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
    
    
    public RefrigeranteModel clone(){
        RefrigeranteModel clon= new RefrigeranteModel();
        clon.setLitros(this.litros);
        return clon;
    }
}
