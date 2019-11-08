/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Observable;
import Model.Observer;
import Model.ProductoDAO;
import Model.ProductoModel;
import View.RankingView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class RankingController implements Observer {
    ProductoDAO productoDAO;
    RankingView rankingView;
    public RankingController(){
        this.productoDAO= new ProductoDAO();
        this.rankingView= new RankingView();
        
        
    }
    public void iniciar() throws SQLException{
        ArrayList<ProductoModel> ranking= new ArrayList();
        ranking = this.productoDAO.ranking();
        this.rankingView.setModel(ranking);
        this.rankingView.setVisible(true);

    }

    @Override
    public void update(Observable o, Object arg) {
       ArrayList<ProductoModel> ranking= new ArrayList();
        try {
            ranking = this.productoDAO.ranking();
        } catch (SQLException ex) {
            Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.rankingView.setModel(ranking);}
    
}
