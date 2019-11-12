/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author luisr
 */
public class RenglonDeVenta {


    private int tipoDeIntem;//1:producto 2:servicio 3:agregado Manualmente
    private String iD;
    private String descripcion;
    private String cantidad;
    private String precioUnitario;
    private String importe;

    
    public boolean equals (RenglonDeVenta ren){
        if(ren.getiD().equals(this.getiD()) 
                && ren.getDescripcion().equals(this.getDescripcion()) && ren.getCantidad().equals(this.getCantidad())
                && ren.getPrecioUnitario().equals(this.getPrecioUnitario()) && ren.getImporte().equals(this.getImporte())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return the tipoDeIntem
     */
    public int getTipoDeIntem() {
        return tipoDeIntem;
    }

    /**
     * @param tipoDeIntem the tipoDeIntem to set
     */
    public void setTipoDeIntem(int tipoDeIntem) {
        this.tipoDeIntem = tipoDeIntem;
    }

    /**
     * @return the iD
     */
    public String getiD() {
        return iD;
    }

    /**
     * @param iD the iD to set
     */
    public void setiD(String iD) {
        this.iD = iD;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cantidad
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioUnitario
     */
    public String getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return the importe
     */
    public String getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(String importe) {
        this.importe = importe;
    }
    
    @Override
    public String toString() {
        return "RenglonDeVenta{" + "tipoDeIntem=" + tipoDeIntem + ", iD=" + iD + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", importe=" + importe + '}';
    }
    
}
