/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conexion;
import Model.OrdenModel;
import java.util.ArrayList;
import Model.DAO;
import Model.OrdenDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author manuel
 */
public class OrdenController {
    private OrdenModel orden;
    private OrdenDAO ordenDao;
    
    public OrdenController(){
        orden=new OrdenModel();
        ordenDao= new OrdenDAO();
    }
    public List verOrdenes(String idCliente) throws SQLException {
        List<OrdenModel> listado=new ArrayList();
        listado=ordenDao.dameOrdenes(idCliente);
        return listado;
    }
    
    public OrdenModel verOrden(String idOrden){
        OrdenModel ordAux = new OrdenModel();
        ordAux.setNro_Orden(Long.valueOf(idOrden));
        //ordenDao read;
        return ordAux;
    }
}
