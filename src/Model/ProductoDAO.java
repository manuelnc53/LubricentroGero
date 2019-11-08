/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Statement;
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
    private LiquidoDeFrenoDAO liquidoDeFrenoDao = new LiquidoDeFrenoDAO();
    private AceiteDAO aceiteDao  = new AceiteDAO();
    public ProductoDAO(){
        conexion=Conexion.getConnection();
    }
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
    public ArrayList<ProductoModel> ranking() throws SQLException{
        ArrayList<ProductoModel> retorno= new ArrayList();
        Statement st =Conexion.getConnection().createStatement();
        ResultSet resultado;
        resultado = st.executeQuery("SELECT Prod_Nombre, Prod_Marca,cantidad\n" +
                                    "FROM (SELECT ItemVenta_Producto_ID,SUM(ItemVenta_Cantidad) AS cantidad\n" +
                                    "		FROM itemventa\n" +
                                    "		GROUP BY ItemVenta_Producto_ID\n" +
                                    "		ORDER BY cantidad DESC), productos\n" +
                                    " where Prod_ID = ItemVenta_Producto_ID");
        while ( resultado.next() ) {
                
              ProductoModel aux=new ProductoModel();
              aux.setNombre(resultado.getString("Prod_Nombre"));
              aux.setMarca(resultado.getString("Prod_Marca"));
              retorno.add(aux);
        
        
        }
        
        return retorno;
    }
    
    public List dameProductos(){//devuelve una lista con todos los productos en la base de datos
        List<ProductoModel> listaProductos = new ArrayList();
        ResultSet resultado;
        //ProductoModel producto = null ;
        int tipoDeProducto=0;//1: aceite, 2: LiquidoDeFreno,3: Refrigerante
        
        try{
            conexion = Conexion.getConnection();
            consulta= conexion.prepareStatement("SELECT * FROM Productos");
            resultado=consulta.executeQuery();
            
            while(resultado.next()){
                tipoDeProducto=resultado.getInt(10);
                
                switch(tipoDeProducto){//dependiendo del ripo de producto que este guardado en la bd es como se va a instanciar
                    case 1 :AceiteModel producto= new AceiteModel();
                            AceiteModel refAux1 = new AceiteModel();
                            Long id1=resultado.getLong(1);
                            
                            refAux1=aceiteDao.read(id1);
                            
                            producto.setAceite_ID(refAux1.getAceite_ID());
                            producto.setLitros(refAux1.getLitros());
                            producto.setTipo(refAux1.getTipo());
                            producto.setProducto_ID(resultado.getInt(1));
                            producto.setNombre(resultado.getString(2));
                            producto.setMarca(resultado.getString(3));
                            producto.setPrecioCompra(resultado.getFloat(4));
                            producto.setPrecioVenta(resultado.getFloat(5));
                            producto.setDescripcion(resultado.getString(6));
                            producto.setCantidadEnStock(resultado.getInt(7));
                            producto.setOrigen(resultado.getString(9));
                            
                            listaProductos.add(producto);
                             
                             
                    break;
                    case 2 :LiquidoDeFrenoModel producto2= new LiquidoDeFrenoModel();
                            LiquidoDeFrenoModel refAux2 =  new LiquidoDeFrenoModel();//auxiliar que recibe lo que devuelve el dao de liquido de freno al leer
                            Long id2=resultado.getLong(1);
                            refAux2=liquidoDeFrenoDao.read(id2); //recibe el refrigerante con el id pasado por parametro                    
                            producto2=refAux2;
                            producto2.setProducto_ID(resultado.getInt(1));
                            producto2.setNombre(resultado.getString(2));
                            producto2.setMarca(resultado.getString(3));
                            producto2.setPrecioCompra(resultado.getFloat(4));
                            producto2.setPrecioVenta(resultado.getFloat(5));
                            producto2.setDescripcion(resultado.getString(6));
                            producto2.setCantidadEnStock(resultado.getInt(7));
                            producto2.setOrigen(resultado.getString(9));
                           
                            listaProductos.add(producto2);
                    
                    break;
                    case 3 :RefrigeranteModel producto3= new RefrigeranteModel();
                            RefrigeranteModel refAux3 =  new RefrigeranteModel();//auxiliar que recibe lo que devuelve el dao de refrigerante al leer
                            Long id3=resultado.getLong(1);
                            refAux3=refrigeranteDao.read(id3); //recibe el refrigerante con el id pasado por parametro                    
                            producto3=refAux3;
                            producto3.setProducto_ID(resultado.getInt(1));
                            producto3.setNombre(resultado.getString(2));
                            producto3.setMarca(resultado.getString(3));
                            producto3.setPrecioCompra(resultado.getFloat(4));
                            producto3.setPrecioVenta(resultado.getFloat(5));
                            producto3.setDescripcion(resultado.getString(6));
                            producto3.setCantidadEnStock(resultado.getInt(7));
                            producto3.setOrigen(resultado.getString(9));
                            
                            listaProductos.add(producto3);
                    break;
                }
                /*Esto estaba aantes
                producto.setProducto_ID(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setMarca(resultado.getString(3));
                producto.setPrecioCompra(resultado.getFloat(4));
                producto.setPrecioVenta(resultado.getFloat(5));
                producto.setDescripcion(resultado.getString(6));
                producto.setCantidadEnStock(resultado.getInt(7));
                producto.setOrigen(resultado.getString(9));
                //
                System.out.println(producto.getNombre()); 
                System.out.println(producto.getTipo());
                //
                listaProductos.add(producto.clone()); */   
            }
            
        }catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        System.out.println("en el producto dao antes de retornar la lista");
        ProductoModel p=listaProductos.get(0);
        System.out.println(p.getNombre());
        return listaProductos;
    }

   
    
}
