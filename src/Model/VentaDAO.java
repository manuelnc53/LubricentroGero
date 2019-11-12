/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class VentaDAO implements DAO<VentaModel> {
    private Connection conexion;
    public VentaDAO(){
        conexion=Conexion.getConnection();
    }
    @Override
    public boolean create(VentaModel dato) {
        //para la venta
        String fecha;
        String clienteId,empleadoId;
        String descDescuentoRecargo;
        String idVenta = null;//contendra el id de la venta a registrarse
        ArrayList<ServicioModel> servicios;
        ArrayList<Item_de_VentaModel> itemsDeVenta;
        float importeDescuentoRecargo;
        long idProducto;
        int cantDelItem;
        int cantInsert = 0,cantStock=0,idInsertItem = 0;
        //statement para las consultas
        PreparedStatement controlClavesForaneas;
        PreparedStatement consulta;
        PreparedStatement st;
        PreparedStatement st1;
        PreparedStatement st2;
        PreparedStatement st3;
        PreparedStatement st4;
        PreparedStatement st5;
        ResultSet idVent;//result set que contendra el id de la venta a registrada
        ResultSet cantidadStock;
        ResultSet cantidadItem;
       
        long idServicios;
        SimpleDateFormat formatoFecha;
        formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        fecha=formatoFecha.format(dato.getFecha()).toString();
        clienteId=String.valueOf(dato.getCliente().getCuit_cuil());
        empleadoId=String.valueOf(dato.getEmpleado_responsable().getCuit());
        descDescuentoRecargo = dato.getDescripcion_Descuento_Recargo();
        importeDescuentoRecargo=dato.getImporte_Descuento_Recargo();
        servicios=dato.getServicios();
        itemsDeVenta=dato.getItem_de_venta();
        conexion = Conexion.getConnection();
        System.out.println("en el venta dao para primera consulta \n'"+fecha+"'"+","+clienteId+","+empleadoId+",'"+descDescuentoRecargo+"',"+importeDescuentoRecargo+");");
        try {
            controlClavesForaneas= conexion.prepareStatement("PRAGMA foreign_keys = ON;");
            controlClavesForaneas.execute();
            consulta = conexion.prepareStatement("INSERT INTO Ventas (Venta_Fecha,Venta_Cliente_ID,Venta_Emp_ID,Venta_Descrip_Desc_Recargo,Venta_Importe_Desc_Recargo) VALUES ('"+fecha+"'"+","+clienteId+","+empleadoId+",'"+descDescuentoRecargo+"',"+importeDescuentoRecargo+");");
            System.out.println("dentro del dao venta quiero insertar con los sig valores\n"+fecha+"'"+","+clienteId+","+empleadoId+",'"+descDescuentoRecargo+"',"+importeDescuentoRecargo);
            consulta.executeUpdate();
            st = conexion.prepareStatement("SELECT last_insert_rowid()");
            idVent = st.executeQuery();
            while(idVent.next()){
                idVenta=String.valueOf(idVent.getInt(1));
                System.out.println("El id de la ultima venta registrada, dentro del dao venta:"+idVenta);
            }
            if(!servicios.isEmpty()){
                for(ServicioModel s:servicios){
                    idServicios=s.getId();
                    st1=conexion.prepareStatement("INSERT INTO Posee(Posee_Venta_ID,Posee_Servicio_ID)VALUES("+idVenta+","+idServicios+");");
                    st1.executeUpdate();
                }
            }
            if(!itemsDeVenta.isEmpty()){
                for(Item_de_VentaModel i:itemsDeVenta){
                    st5=conexion.prepareStatement("SELECT MAX(ItemVenta_ID) FROM ItemVenta");
                    cantidadItem=st5.executeQuery();
                    while(cantidadItem.next()){
                        cantStock=cantidadItem.getInt(1);
                        idInsertItem = cantStock+1;
                    }
                    idProducto=i.getProducto().getProducto_ID();
                    cantDelItem=i.getCantidad();
                    st2=conexion.prepareStatement("INSERT INTO ItemVenta(ItemVenta_ID,ItemVenta_Producto_ID,ItemVenta_Venta_ID,ItemVenta_Cantidad)VALUES("+idInsertItem+","+idProducto+","+idVenta+","+cantDelItem+");");
                    st2.executeUpdate();
                    st3=conexion.prepareStatement("SELECT Prod_Cant_En_Stock FROM Productos WHERE Prod_ID="+idProducto+";");
                    cantidadStock=st3.executeQuery();
                    while(cantidadStock.next()){
                        cantStock=cantidadStock.getInt("Prod_Cant_En_Stock");
                        cantInsert=cantStock-cantDelItem;
                    }
                    st4=conexion.prepareStatement("UPDATE Productos SET Prod_Cant_En_Stock= "+cantInsert+" WHERE Prod_ID="+idProducto);
                    st4.executeUpdate();
                }
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;     
    }

    @Override
    public VentaModel read(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(VentaModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
