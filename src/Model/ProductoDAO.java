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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Date fecha= new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
        String a=formatoFecha.format(fecha).toString();
        resultado = st.executeQuery("SELECT Prod_Nombre, Prod_Marca,cantidad\n" +
                                    "FROM (SELECT ItemVenta_Producto_ID,SUM(ItemVenta_Cantidad) AS cantidad\n" +
                                    "		FROM  (SELECT  ItemVenta_Producto_ID,ItemVenta_Cantidad\n" +
"							FROM itemventa, ventas\n" +
"							WHERE Venta_Fecha LIKE '%"+a.substring(2,a.length())+"' AND Venta_ID=ItemVenta_Venta_ID)\n" +
                                    "		GROUP BY ItemVenta_Producto_ID\n" +
                                    "		ORDER BY cantidad DESC), productos\n" +
                                    " where Prod_ID = ItemVenta_Producto_ID");
        while ( resultado.next() ) {
                
              ProductoModel aux=new ProductoModel();
              aux.setNombre(resultado.getString("Prod_Nombre"));
              aux.setMarca(resultado.getString("Prod_Marca"));
              aux.setCantidadEnStock(resultado.getInt("cantidad"));
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
                tipoDeProducto=resultado.getInt("Prod_Especificacion");
                
                switch(tipoDeProducto){//dependiendo del ripo de producto que este guardado en la bd es como se va a instanciar
                    case 1 :AceiteModel producto= new AceiteModel();
                            AceiteModel refAux1 = new AceiteModel();
                            Long id1=resultado.getLong(1);
                            
                            producto.setAceite_ID(resultado.getInt("Prod_ID"));
                            producto.setLitros(resultado.getFloat("Prod_Litros"));
                            //producto.setTipo("Prod_Especificacion"); el tipo de aceite esta en la descripcion del producto
                            producto.setProducto_ID(resultado.getInt("Prod_ID"));
                            producto.setNombre(resultado.getString("Prod_Nombre"));
                            producto.setMarca(resultado.getString("Prod_Marca"));
                            producto.setPrecioCompra(resultado.getFloat("Prod_Precio_Compra"));
                            producto.setPrecioVenta(resultado.getFloat("Prod_Precio_Venta"));
                            producto.setDescripcion(resultado.getString("Prod_Descripcion_Detalle"));
                            producto.setCantidadEnStock(resultado.getInt("Prod_Cant_En_Stock"));
                            producto.setCatidadMinimaEnStock(resultado.getInt("Prod_Cant_Mini_Stock"));
                            producto.setOrigen(resultado.getString("Prod_Origen"));
                           
                            listaProductos.add(producto);
                             
                             
                    break;
                    case 2 :LiquidoDeFrenoModel producto2= new LiquidoDeFrenoModel();
                            LiquidoDeFrenoModel refAux2 =  new LiquidoDeFrenoModel();//auxiliar que recibe lo que devuelve el dao de liquido de freno al leer
                            Long id2=resultado.getLong(1);
                            //refAux2=liquidoDeFrenoDao.read(id2); //recibe el refrigerante con el id pasado por parametro                    
                            producto2=refAux2;
                            producto2.setProducto_ID(resultado.getInt("Prod_ID"));
                            producto2.setNombre(resultado.getString("Prod_Nombre"));
                            producto2.setMarca(resultado.getString("Prod_Marca"));
                            producto2.setPrecioCompra(resultado.getFloat("Prod_Precio_Compra"));
                            producto2.setPrecioVenta(resultado.getFloat("Prod_Precio_Venta"));
                            producto2.setDescripcion(resultado.getString("Prod_Descripcion_Detalle"));
                            producto2.setCantidadEnStock(resultado.getInt("Prod_Cant_En_Stock"));
                            producto2.setCatidadMinimaEnStock(resultado.getInt("Prod_Cant_Mini_Stock"));
                            producto2.setOrigen(resultado.getString("Prod_Origen"));
                           
                            listaProductos.add(producto2);
                    
                    break;
                    case 3 :RefrigeranteModel producto3= new RefrigeranteModel();
                            RefrigeranteModel refAux3 =  new RefrigeranteModel();//auxiliar que recibe lo que devuelve el dao de refrigerante al leer
                            Long id3=resultado.getLong(1);
                            //refAux3=refrigeranteDao.read(id3); recibe el refrigerante con el id pasado por parametro                    
                            //producto3=refAux3;
                            producto3.setProducto_ID(resultado.getInt("Prod_ID"));
                            producto3.setNombre(resultado.getString("Prod_Nombre"));
                            producto3.setMarca(resultado.getString("Prod_Marca"));
                            producto3.setPrecioCompra(resultado.getFloat("Prod_Precio_Compra"));
                            producto3.setPrecioVenta(resultado.getFloat("Prod_Precio_Venta"));
                            producto3.setDescripcion(resultado.getString("Prod_Descripcion_Detalle"));
                            producto3.setCantidadEnStock(resultado.getInt("Prod_Cant_En_Stock"));
                            producto3.setCatidadMinimaEnStock(resultado.getInt("Prod_Cant_Mini_Stock"));
                            producto3.setOrigen(resultado.getString("Prod_Origen"));
                            
                            listaProductos.add(producto3);
                    break;
                }
               
            }
            
        }catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
        System.out.println("en el producto dao antes de retornar la lista");
        //ProductoModel p=listaProductos.get(0);
        //System.out.println(p.getNombre());
        return listaProductos;
    }
  
}
