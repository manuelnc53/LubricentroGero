/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conexion;
import Model.ProductoModel;
import java.util.ArrayList;
import Model.DAO;
import Model.ProductoDAO;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ProductoController  {
    private ProductoModel producto;
    private ProductoDAO productoDao;
    public ProductoController(){
        producto= new ProductoModel();
        productoDao=new ProductoDAO();
    }
    
    /*public String[] consultarProducto(int id){
        
    }*/

    public List verProductos() {
        List<ProductoModel> listado=new ArrayList();
        productoDao = new ProductoDAO();
        listado=productoDao.dameProductos();
        return listado;
    }
}
