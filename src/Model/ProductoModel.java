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
public class ProductoModel {

    private int producto_ID;
    private String nombre;
    private String marca;
    private float precioCompra;
    private float precioVenta;
    private String descripcion;
    private int cantidadEnStock;
    private int catidadMinimaEnStock;
    private String origen;
    private ArrayList<Item_de_VentaModel> venta;
    private ArrayList<PedidoModel> pedido;
    public ProductoModel(){
    
    }
    /**
     * @return the venta
     */
    public ArrayList<Item_de_VentaModel> getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(ArrayList<Item_de_VentaModel> venta) {
        this.venta = venta;
    }

    /**
     * @return the pedido
     */
    public ArrayList<PedidoModel> getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(ArrayList<PedidoModel> pedido) {
        this.pedido = pedido;
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
     * @return the precioCompra
     */
    public float getPrecioCompra() {
        return precioCompra;
    }

    /**
     * @param precioCompra the precioCompra to set
     */
    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    /**
     * @return the precioVenta
     */
    public float getPrecioVenta() {
        return precioVenta;
    }

    /**
     * @param precioVenta the precioVenta to set
     */
    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
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
     * @return the cantidadEnStock
     */
    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    /**
     * @param cantidadEnStock the cantidadEnStock to set
     */
    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    /**
     * @return the producto_ID
     */
    public int getProducto_ID() {
        return producto_ID;
    }

    /**
     * @param producto_ID the producto_ID to set
     */
    public void setProducto_ID(int producto_ID) {
        this.producto_ID = producto_ID;
    }
    /**
     * @return the catidadMinimaEsStock
     */
    public int getCatidadMinimaEnStock() {
        return catidadMinimaEnStock;
    }

    /**
     * @param catidadMinimaEsStock the catidadMinimaEsStock to set
     */
    public void setCatidadMinimaEnStock(int catidadMinimaEsStock) {
        this.catidadMinimaEnStock = catidadMinimaEsStock;
    }
    public ProductoModel clone(){
        ProductoModel clon = new ProductoModel();
        
        clon.setCantidadEnStock(this.cantidadEnStock);
        clon.setDescripcion(this.descripcion);
        clon.setMarca(this.marca);
        clon.setNombre(this.nombre);
        clon.setOrigen(this.origen);
        clon.setPedido(this.pedido);
        clon.setPrecioCompra(this.precioCompra);
        clon.setPrecioVenta(this.precioVenta);
        clon.setProducto_ID(this.producto_ID);
        clon.setVenta(this.venta);
        return clon;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.producto_ID;
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
        final ProductoModel other = (ProductoModel) obj;
        if (this.producto_ID != other.producto_ID) {
            return false;
        }
        return true;
    }
    
}
