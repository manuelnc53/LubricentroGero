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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ClienteDAO implements DAO<ClienteModel> {
    //private Conexion conexion;
    private Connection conexion;
    private PreparedStatement consulta;
    public ClienteDAO(){
        conexion=Conexion.getConnection();
    }

    @Override
    public boolean create(ClienteModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClienteModel read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ClienteModel dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List dameClientes(){
        List<ClienteModel> listaClientes = new ArrayList();//lista q contendra los clientes para la ventana de ventas
        ResultSet resultado;
        ClienteModel cliente= new ClienteModel();
        try{
            
            consulta=conexion.prepareStatement("SELECT * FROM Clientes");
            PreparedStatement consultaAux ;//tendra la consulta para traer los telefonos de los responsables
            ResultSet resultadoAux;//tendra los telefonos de los responsables
            List<String>telefonos= new ArrayList();
            resultado=consulta.executeQuery();
            String idAux;
            while(resultado.next()){

                cliente.setCuit_cuil(resultado.getLong("Clien_CUIT_CUIL"));
                cliente.setNombre(resultado.getString("Clien_Nombre"));
                cliente.setDireccion(resultado.getString("Clien_Direccion"));

                idAux=String.valueOf(resultado.getInt(1));
                consultaAux = conexion.prepareStatement("SELECT * FROM ClientesTelefonosResponsable WHERE Clien_CUIT_CUIL_Tel ="+idAux);
                resultadoAux=consultaAux.executeQuery();
                while(resultadoAux.next()){
                    telefonos.add(resultadoAux.getString(2));
                }
                cliente.setTelefonos_Del_Responsable((ArrayList<String>) telefonos);//agrego los telefonos al cliente
                listaClientes.add(cliente.clone());
            }
        }catch(SQLException e){
            System.out.println("No se pudo realizar la consulta");
        }
    return listaClientes;    
    }
    
}
