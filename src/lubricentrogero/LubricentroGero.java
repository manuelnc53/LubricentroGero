/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lubricentrogero;

import Controller.ListarOrdenesDeTrabajoController;
import View.ListarOrdenesDeTrabajoView;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class LubricentroGero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
        
        ListarOrdenesDeTrabajoController listarOrdenesDeTrabajoController= new ListarOrdenesDeTrabajoController(); 
        try {
            
            listarOrdenesDeTrabajoController.iniciar();
            
            // TODO code application logic here
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }
    
}
