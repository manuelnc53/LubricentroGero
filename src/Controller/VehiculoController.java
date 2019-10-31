/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conexion;
import Model.VehiculoModel;
import java.util.ArrayList;
import Model.DAO;
import Model.VehiculoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author manuel
 */
public class VehiculoController  {
    private VehiculoModel vehiculo;
    private VehiculoDAO vehiculoBD;
    public VehiculoController(){
        vehiculo= new VehiculoModel();
        vehiculoBD = new VehiculoDAO();
    }
    
public List vehiculosBD() throws SQLException, CloneNotSupportedException{
        List<VehiculoModel> listado=new ArrayList();
        listado=vehiculoBD.dameVehiculos();
        return listado;
    }

public VehiculoModel buscarPorPatente(String patente,List<VehiculoModel> lista){
    VehiculoModel aux = null;
    for(VehiculoModel o:lista){
        if(o.getPatente().equals(patente))
            aux=o;
    }
    return aux;
}
    
}
