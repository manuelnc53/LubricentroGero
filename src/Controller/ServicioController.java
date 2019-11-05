/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClienteDAO;
import Model.ClienteModel;
import Model.Conexion;
import Model.ServicioModel;
import java.util.ArrayList;
import Model.DAO;
import Model.ServicioDAO;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ServicioController {
    public ServicioModel servicio;
    private ServicioDAO servicioDao;
    public ServicioController(){
        servicio= new ServicioModel();
        servicioDao= new ServicioDAO();
    }

    public List verServicios(){
        List<ServicioModel> listado=new ArrayList();
        listado=servicioDao.dameServicios();
        return listado;
    }
    

    public List serviciosBD(){
        List<ServicioModel> listado=new ArrayList();
        listado=servicioDao.dameServicios();
        return listado;
    }

    public ServicioModel buscarPorID(long ID,List<ServicioModel> lista){
    ServicioModel aux = null;
    for(ServicioModel o:lista){
        if(o.getId()==ID)
            aux=o;
        }
    return aux;
    }

}
