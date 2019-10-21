/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lubricentrogero;

import Model.Conexion;
import View.VentaView;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author manuel
 */
public class LubricentroGero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VentaView ventaView=new VentaView();
        ventaView.setVisible(true);
    }
    
}
