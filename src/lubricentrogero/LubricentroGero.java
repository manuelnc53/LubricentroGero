/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lubricentrogero;


import Controller.ListarOrdenesDeTrabajoController;
import Controller.RankingController;
import View.ListarOrdenesDeTrabajoView;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Conexion;
import View.VentaView;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;


import Model.EmpleadoDAO;
import Model.EmpleadoModel;
import Model.ResponsabilidadModel;
import Model.VehiculoDAO;
import Model.VehiculoModel;
import View.RankingView;
import View.ViewCrearOrden;

import java.util.List;

/**
 *
 * @author manuel
 */
public class LubricentroGero {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */

    public static void main(String[] args) throws SQLException, ParseException {
        // TODO code application logic here
        RankingController rankingController= new RankingController();
         try{
        rankingController.iniciar();}
         catch(Exception e){
         
        // System.out.println(e);
         
         }
        try{
        VentaView ventaView=new VentaView();
        ventaView.setVisible(true);
        }catch(Exception e){
        }
        ListarOrdenesDeTrabajoController listarOrdenesDeTrabajoController= new ListarOrdenesDeTrabajoController(); 
        try {
            
            listarOrdenesDeTrabajoController.iniciar();
            
            // TODO code application logic here
        } catch (SQLException ex) {
            System.out.println("Error "+ ex);
        }
        
        
        
        
        try {
            EmpleadoModel emp =new EmpleadoModel();
            List a;
            EmpleadoDAO bd = new EmpleadoDAO();
            a=bd.dameEmpleados();
            System.out.println(a);
            
            
        } catch (CloneNotSupportedException ex) {
          //  Logger.getLogger(LubricentroGero.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    
}
