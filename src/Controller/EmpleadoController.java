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
import Model.EmpleadoDAO;
import Model.OrdenDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author manuel
 */
public class EmpleadoController  {
    private EmpleadoModel empleado;
    private EmpleadoDAO empleadoBD;
    public EmpleadoController(){
        empleado= new EmpleadoModel();
        empleadoBD=new EmpleadoDAO();
    }

    public List<EmpleadoModel> empleadosBD() throws SQLException, ParseException, CloneNotSupportedException {
        return empleadoBD.dameEmpleados();
    }
   
}
