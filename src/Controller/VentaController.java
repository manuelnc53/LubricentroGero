/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClienteModel;
import Model.Conexion;
import Model.VentaModel;
import java.util.ArrayList;
import Model.DAO;
import Model.EmpleadoModel;
import Model.Item_de_VentaModel;
import Model.ProductoModel;
import Model.RenglonDeVenta;
import Model.ServicioModel;
import Model.VentaDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author manuel
 */
public class VentaController  {
    private VentaModel venta;
    private VentaDAO ventaDao;
    public VentaController(){
        venta= new VentaModel();
        ventaDao=new VentaDAO();
    }
    
    public void registrarVenta(List<RenglonDeVenta> listaRenglonesVenta,Date fecha,EmpleadoModel cajero,ClienteModel cliente){
        VentaModel venta = new VentaModel();
        Item_de_VentaModel item;
        ServicioModel servicio;
        ArrayList<Item_de_VentaModel> itemsVenta = new ArrayList();
        ArrayList<ServicioModel> serviciosVenta = new ArrayList();
        ProductoModel prod;
        int tipo;
        boolean exitoDeVenta;
        
        for(RenglonDeVenta ren:listaRenglonesVenta){
            tipo=ren.getTipoDeIntem();
            switch(tipo){
                case 1:
                        item = new Item_de_VentaModel();
                        prod = new ProductoModel();
                        prod.setProducto_ID(Integer.valueOf(ren.getiD()));
                        item.setCantidad(Integer.valueOf(ren.getCantidad()));
                        item.setProducto(prod);
                        itemsVenta.add(item);
                        break;
                case 2:        
                        servicio = new ServicioModel();
                        servicio.setId(Long.valueOf(ren.getiD()));
                        serviciosVenta.add(servicio);
                        break;
                case 3: 
                        venta.setDescripcion_Descuento_Recargo(ren.getDescripcion());
                        venta.setImporte_Descuento_Recargo(Float.valueOf(ren.getImporte()));
            }   
        }
        venta.setCliente(cliente);
        venta.setEmpleado_responsable(cajero);
        venta.setFecha(fecha);
        venta.setItem_de_venta(itemsVenta);
        venta.setServicios(serviciosVenta);
        exitoDeVenta=ventaDao.create(venta);
    }
    
}
