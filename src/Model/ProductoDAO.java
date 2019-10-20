/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ProductoDAO implements DAO<ProductoModel> {
    private Connection conexion;
    private PreparedStatement consulta;
    private RefrigeranteDAO refrigeranteDao = new RefrigeranteDAO();
    private LiquidoDeFrenoDAO liquidoDeFrenoDAO = new LiquidoDeFrenoDAO();
    private AceiteDAO aceiteDAO  = new AceiteDAO();
    public ProductoDAO(){}
    @Override
    public boolean create(ProductoModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductoModel read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ProductoModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List dameProductos(){//devuelve una lista con todos los productos en la base de datos
        List<ProductoModel> listaProductos = new ArrayList();
        ResultSet resultado;
        ProductoModel producto = null ;
        int tipoDeProducto=0;//1: aceite, 2: LiquidoDeFreno,3: Refrigerante
        
        try{
            conexion = Conexion.getConnection();
            consulta= conexion.prepareStatement("SELECT * FROM Productos");
            resultado=consulta.executeQuery();
            
            while(resultado.next()){
                tipoDeProducto=resultado.getInt(10);
                switch(tipoDeProducto){//dependiendo del ripo de producto que este guardado en la bd es como se va a instanciar
                    case 1 : producto= new AceiteModel();
                             
                    break;
                    case 2 : producto= new LiquidoDeFrenoModel();
                            
                    
                    break;
                    case 3 : producto= new RefrigeranteModel();
                             RefrigeranteModel refAux =  new RefrigeranteModel();//auxiliar que recibe lo que devuelve el dao de refrigerante al leer
                             Long id=resultado.getLong(1);
                             refAux=refrigeranteDao.read(id); //recibe el refrigerante con el id pasado por parametro                    
                             producto=refAux;    
                    break;
                }
                
                producto.setProducto_ID(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setMarca(resultado.getString(3));
                producto.setPrecioCompra(resultado.getFloat(4));
                producto.setPrecioVenta(resultado.getFloat(5));
                producto.setDescripcion(resultado.getString(6));
                producto.setCantidadEnStock(resultado.getInt(7));
                producto.setOrigen(resultado.getString(9));
                
            listaProductos.add(producto.clone());    
            }
            
        }catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        return listaProductos;
    }
    
}
