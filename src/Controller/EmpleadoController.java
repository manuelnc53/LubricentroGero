/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conexion;
import Model.EmpleadoModel;
import java.util.ArrayList;
import Model.DAO;
import Model.OrdenDAO;

/**
 *
 * @author manuel
 */
public class EmpleadoController  {
    private EmpleadoModel empleado;
    private OrdenDAO ordenBD;
    public EmpleadoController(){
        empleado= new EmpleadoModel();
        ordenBD=new OrdenDAO();
    }
   
}
