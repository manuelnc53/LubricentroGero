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
    public List verOrdenes() throws SQLException {
        List<OrdenModel> listado=new ArrayList();
        listado=ordenDao.dameOrdenes();
        return listado;
    }
}
