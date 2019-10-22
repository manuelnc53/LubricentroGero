/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.OrdenDAO;
import View.ListarOrdenesDeTrabajoView;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class ListarOrdenesDeTrabajoController {
    OrdenDAO ordenDAO;
    ListarOrdenesDeTrabajoView listarOrdenesDeTrabajoView; 
    
    public ListarOrdenesDeTrabajoController (){
        
        ordenDAO= new OrdenDAO();
        listarOrdenesDeTrabajoView= new ListarOrdenesDeTrabajoView();
    }
    
    public void iniciar() throws SQLException{
        ArrayList<String[]> ordenes= new ArrayList();
        ordenes = this.ordenDAO.getAll();
        
       /* ordenes.addColumn("N°");
        ordenes.addColumn("Urgencia");
        ordenes.addColumn("Estado");
        ordenes.addColumn("Fecha");
        ordenes.addColumn( "Emisor");
        ordenes.addColumn("Servicio");
        ordenes.addColumn("Patente");
        ordenes.addColumn("Telefono");
        */
        
        this.listarOrdenesDeTrabajoView.setModel(ordenes);
        this.listarOrdenesDeTrabajoView.setVisible(true);
    
    
    }
    public void terminar(){
    
    
    }
    
}
