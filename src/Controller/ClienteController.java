/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClienteDAO;
import Model.Conexion;
import Model.ClienteModel;
import java.util.ArrayList;
import Model.DAO;
import java.util.List;
//import sun.applet.Main;

/**
 *
 * @author manuel
 */
public class ClienteController {

    private ClienteModel cliente;
    private ClienteDAO clienteDao;
    public ClienteController(){
        cliente= new ClienteModel();
        clienteDao = new ClienteDAO();
    }
    

    public List verClientes(){
                List<ClienteModel> listado=new ArrayList();
                listado=clienteDao.dameClientes();
        return listado;
    }
    public List clientesBD(){

        List<ClienteModel> listado=new ArrayList();
        listado=clienteDao.dameClientes();
        return listado;
    }

    public ClienteModel buscarPorCuil(long cuil,List<ClienteModel> lista){
    ClienteModel aux = null;
    for(ClienteModel o:lista){
        if(o.getCuit_cuil()==cuil)
            aux=o;
        }
    return aux;
    }

    
           

    
}
