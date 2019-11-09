/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lubricentrogero;

import Model.EmpleadoDAO;
import Model.EmpleadoModel;
import Model.ResponsabilidadModel;
import Model.VehiculoDAO;
import Model.VehiculoModel;
import View.PrincipalViewDosPuntoCero;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author manuel
 */
public class LubricentroGero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException, CloneNotSupportedException {
        PrincipalViewDosPuntoCero ventana = new PrincipalViewDosPuntoCero();
        ventana.setVisible(true);
    }
    
}
