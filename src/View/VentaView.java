package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author luisr
 */
import Controller.ClienteController;
import Controller.OrdenController;
import Controller.ProductoController;
import Controller.ServicioController;
import Model.AceiteModel;
import Model.ClienteModel;
import Model.EmpleadoDAO;
import Model.EmpleadoModel;
import Model.OrdenModel;
import Model.ProductoModel;
import Model.ServicioModel;
import Model.VehiculoDAO;
import Model.VehiculoModel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Stack;
import javax.swing.JTable;

public class VentaView extends javax.swing.JFrame {

    /**
     * Creates new form VentaView
     */
    private DefaultTableModel modeloTablaClientes;
    private DefaultTableModel modeloTablaProductos;
    private DefaultTableModel modeloTablaServicios;
    private DefaultTableModel modeloTablaVehiculos;
    private DefaultTableModel modeloTablaVentas;
    private List<ClienteModel> clientes;
    private List<ProductoModel> productos;
    private List<ServicioModel> servicios;
    
    
    private ClienteController clienteControlador;
    private ProductoController productoControlador;
    private ServicioController servicioControlador;
    
    //Clinte que se carga en el action listener de agregar luego se pasara a la orden
    private ClienteModel clienteAux;
    
    public VentaView() {
        initComponents();
        //creo los tableModel con el modelo de las tablas correspondientes
        modeloTablaClientes=(DefaultTableModel)jTableClientesVenta.getModel();
        modeloTablaProductos=(DefaultTableModel)jTableProductosVenta.getModel();
        modeloTablaServicios=(DefaultTableModel)jTableServiciosVenta.getModel();
        modeloTablaVehiculos=(DefaultTableModel) jTableVehiculosVenta.getModel();
        modeloTablaVentas=(DefaultTableModel) jTableMostrarRenglonesVenta.getModel();
        
        
        //seteo las tablas con los modelos que seran llenados aqui
        jTableClientesVenta.setModel(modeloTablaClientes);
        jTableProductosVenta.setModel(modeloTablaProductos);
        jTableServiciosVenta.setModel(modeloTablaServicios);
        jTableVehiculosVenta.setModel(modeloTablaVehiculos);
        //tabla con los renglones de la venta
        jTableMostrarRenglonesVenta.setModel(modeloTablaVentas);
        //instancio el controlador
        clienteControlador = new ClienteController();
        clientes=clienteControlador.clientesBD();
        productoControlador= new ProductoController();
        productos=productoControlador.verProductos();
        servicioControlador=new ServicioController();
        servicios=servicioControlador.serviciosBD();
        crearTablaClientes(clientes, modeloTablaClientes);
        crearTablaProductos(productos, modeloTablaProductos);
        crearTablaServicios(servicios,modeloTablaServicios);
        
        
    }
    
    /**
     * Devuelve una lista con todos los empleados que tienen en su composicion el patron de la variable nombre
     * @param nombre nombre del cliente buscado
     * @param clientes la lista de los empleados
     * @return List<ClienteModel> que cumple con tener el patron de la palabra.
     */
    private static List filtrarNombre(String nombre,List<ClienteModel> clientes) { //Utilizo lenguaje regular aqui.
        nombre=nombre.toLowerCase();
        List<ClienteModel> filtrados=new ArrayList<ClienteModel>();
        String regex = ".*"+nombre+".*";
        Pattern patron = Pattern.compile(regex);
        Matcher m ;
        for(ClienteModel o: clientes){
            
             m  = patron.matcher(o.getNombre().toLowerCase());
             if(m.find()){
                 filtrados.add(o);
             }
        }
        return filtrados;
    }
    private static List filtrarNombreProductos(String nombre,List<ProductoModel> productos) { //Utilizo lenguaje regular aqui.
        nombre=nombre.toLowerCase();
        List<ProductoModel> filtrados=new ArrayList<ProductoModel>();
        String regex = ".*"+nombre+".*";
        Pattern patron = Pattern.compile(regex);
        Matcher m ;
        for(ProductoModel o: productos){
            System.out.println(o.getNombre().toLowerCase()+":"+nombre);
             m  = patron.matcher(o.getNombre().toLowerCase());
             if(m.find()){
                 filtrados.add(o);
             }
        }
        return filtrados;
    }
    
    
    private static void crearTablaClientes(List<ClienteModel> clientesParaTabla,DefaultTableModel modeloTablaClientes){
        //creo un arreglo de String para cargar el modelo
        String []datos=new String[3] ;
        int i;
        //limpia la tabla antes
        for(i=0;i<modeloTablaClientes.getRowCount();i++){
            modeloTablaClientes.removeRow(i);
            i=i-1;
        }
        //carga el renglos datos con la informacion de clientes
        for(ClienteModel o : clientesParaTabla){
            datos[0]=String.valueOf(o.getCuit_cuil());
            datos[1]=o.getNombre();
            datos[2]=o.getDireccion();
            
            modeloTablaClientes.addRow(datos);
        }
    }
    
    private static void crearTablaProductos(List<ProductoModel> productosParaTabla,DefaultTableModel modeloTablaProductos){
        String []datos=new String[6];
        int i;
        //limpia la tabla antes
        for(i=0;i<modeloTablaProductos.getRowCount();i++){
            modeloTablaProductos.removeRow(i);
            i=i-1;
        }
        
        
        for(ProductoModel o : productosParaTabla){

            datos[0]=String.valueOf(o.getProducto_ID());
            datos[1]=o.getNombre();
            datos[2]=o.getMarca();
            datos[3]=o.getDescripcion();
            datos[4]=String.valueOf(o.getPrecioVenta());
            datos[5]=String.valueOf(o.getCantidadEnStock());
            
            modeloTablaProductos.addRow(datos);
        }
    }
    
    private static void crearTablaServicios(List<ServicioModel> serviciosParaTabla,DefaultTableModel modeloTablaServicios){
        String []datos=new String[4];
        int i;
        //limpia la tabla antes
        for(i=0;i<modeloTablaServicios.getRowCount();i++){
            modeloTablaServicios.removeRow(i);
            i=i-1;
        }
        for(ServicioModel o : serviciosParaTabla){
            datos[0]=String.valueOf(o.getId());
            datos[1]=String.valueOf(o.getNombre());
            datos[2]=String.valueOf(o.getDescripcion());
            datos[3]=String.valueOf(o.getPrecio());
            
            modeloTablaServicios.addRow(datos);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneVenta = new javax.swing.JPanel();
        jTabbedPaneSelecionVenta = new javax.swing.JTabbedPane();
        jPanelClientesVenta = new javax.swing.JPanel();
        jScrollPaneClientesTablaVenta = new javax.swing.JScrollPane();
        jTableClientesVenta = new javax.swing.JTable();
        jTextFieldBuscadorTablaCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanelProductosVenta = new javax.swing.JPanel();
        jScrollPaneProductosTablaVenta = new javax.swing.JScrollPane();
        jTableProductosVenta = new javax.swing.JTable();
        jComboBoxFiltrarPor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBuscadorTablaProductos = new javax.swing.JTextField();
        jPanelServiciosVenta = new javax.swing.JPanel();
        jScrollPaneServiciosTablaVenta = new javax.swing.JScrollPane();
        jTableServiciosVenta = new javax.swing.JTable();
        jPanelVehiculosVenta = new javax.swing.JPanel();
        jScrollPaneVehiculosTablaVenta = new javax.swing.JScrollPane();
        jTableVehiculosVenta = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanelMostrarRenglonesVenta = new javax.swing.JPanel();
        jScrollPaneMostrarRenglonesVenta = new javax.swing.JScrollPane();
        jTableMostrarRenglonesVenta = new javax.swing.JTable();
        jTextFieldNombreCliente = new javax.swing.JTextField();
        jLabelNombreCliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButtonAgregarTablaRenglonesVenta = new javax.swing.JButton();
        jTextFieldCantidadVenta = new javax.swing.JTextField();
        jLabelCantidadVenta = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPaneSelecionVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTableClientesVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CUIT_CUIL", "Nombre", "Direccion"
            }
        ));
        jScrollPaneClientesTablaVenta.setViewportView(jTableClientesVenta);

        jTextFieldBuscadorTablaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscadorTablaClienteKeyReleased(evt);
            }
        });

        jLabel3.setText("Busqueda por nombre:");

        javax.swing.GroupLayout jPanelClientesVentaLayout = new javax.swing.GroupLayout(jPanelClientesVenta);
        jPanelClientesVenta.setLayout(jPanelClientesVentaLayout);
        jPanelClientesVentaLayout.setHorizontalGroup(
            jPanelClientesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesVentaLayout.createSequentialGroup()
                .addGroup(jPanelClientesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClientesVentaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBuscadorTablaCliente))
                    .addComponent(jScrollPaneClientesTablaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelClientesVentaLayout.setVerticalGroup(
            jPanelClientesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClientesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscadorTablaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(53, 53, 53)
                .addComponent(jScrollPaneClientesTablaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPaneSelecionVenta.addTab("Clientes", jPanelClientesVenta);

        jTableProductosVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Marca", "Descripcion", "PrecioVenta", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneProductosTablaVenta.setViewportView(jTableProductosVenta);
        if (jTableProductosVenta.getColumnModel().getColumnCount() > 0) {
            jTableProductosVenta.getColumnModel().getColumn(0).setResizable(false);
            jTableProductosVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        jComboBoxFiltrarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nombre", "marca" }));

        jLabel1.setText("Buscar por:");

        jTextFieldBuscadorTablaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscadorTablaProductosActionPerformed(evt);
            }
        });
        jTextFieldBuscadorTablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscadorTablaProductosKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelProductosVentaLayout = new javax.swing.GroupLayout(jPanelProductosVenta);
        jPanelProductosVenta.setLayout(jPanelProductosVentaLayout);
        jPanelProductosVentaLayout.setHorizontalGroup(
            jPanelProductosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductosVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProductosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneProductosTablaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addGroup(jPanelProductosVentaLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelProductosVentaLayout.createSequentialGroup()
                        .addComponent(jComboBoxFiltrarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jTextFieldBuscadorTablaProductos)))
                .addContainerGap())
        );
        jPanelProductosVentaLayout.setVerticalGroup(
            jPanelProductosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProductosVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelProductosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFiltrarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBuscadorTablaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jScrollPaneProductosTablaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneSelecionVenta.addTab("Productos", jPanelProductosVenta);

        jTableServiciosVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneServiciosTablaVenta.setViewportView(jTableServiciosVenta);
        if (jTableServiciosVenta.getColumnModel().getColumnCount() > 0) {
            jTableServiciosVenta.getColumnModel().getColumn(0).setResizable(false);
            jTableServiciosVenta.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPanelServiciosVentaLayout = new javax.swing.GroupLayout(jPanelServiciosVenta);
        jPanelServiciosVenta.setLayout(jPanelServiciosVentaLayout);
        jPanelServiciosVentaLayout.setHorizontalGroup(
            jPanelServiciosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServiciosVentaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPaneServiciosTablaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelServiciosVentaLayout.setVerticalGroup(
            jPanelServiciosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelServiciosVentaLayout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(jScrollPaneServiciosTablaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        jTabbedPaneSelecionVenta.addTab("Servicios", jPanelServiciosVenta);

        jTableVehiculosVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneVehiculosTablaVenta.setViewportView(jTableVehiculosVenta);

        jLabel4.setText("Buscara");

        jTextField3.setText("jTextField3");

        javax.swing.GroupLayout jPanelVehiculosVentaLayout = new javax.swing.GroupLayout(jPanelVehiculosVenta);
        jPanelVehiculosVenta.setLayout(jPanelVehiculosVentaLayout);
        jPanelVehiculosVentaLayout.setHorizontalGroup(
            jPanelVehiculosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVehiculosVentaLayout.createSequentialGroup()
                .addGroup(jPanelVehiculosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVehiculosVentaLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPaneVehiculosTablaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVehiculosVentaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanelVehiculosVentaLayout.setVerticalGroup(
            jPanelVehiculosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVehiculosVentaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelVehiculosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jScrollPaneVehiculosTablaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        jTabbedPaneSelecionVenta.addTab("Vehiculos", jPanelVehiculosVenta);

        jTableMostrarRenglonesVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cantidad", "Producto", "Descripcion", "Precio", "Importe"
            }
        ));
        jScrollPaneMostrarRenglonesVenta.setViewportView(jTableMostrarRenglonesVenta);

        jLabelNombreCliente.setText("Cliente");

        jLabel2.setText("Fecha");

        jButton3.setText("Eliminar de la venta");

        javax.swing.GroupLayout jPanelMostrarRenglonesVentaLayout = new javax.swing.GroupLayout(jPanelMostrarRenglonesVenta);
        jPanelMostrarRenglonesVenta.setLayout(jPanelMostrarRenglonesVentaLayout);
        jPanelMostrarRenglonesVentaLayout.setHorizontalGroup(
            jPanelMostrarRenglonesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMostrarRenglonesVentaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabelNombreCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(163, 163, 163))
            .addGroup(jPanelMostrarRenglonesVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMostrarRenglonesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneMostrarRenglonesVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanelMostrarRenglonesVentaLayout.setVerticalGroup(
            jPanelMostrarRenglonesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMostrarRenglonesVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMostrarRenglonesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombreCliente)
                    .addComponent(jLabel2))
                .addGap(45, 45, 45)
                .addComponent(jScrollPaneMostrarRenglonesVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addComponent(jButton3))
        );

        jButtonAgregarTablaRenglonesVenta.setText("agregar");
        jButtonAgregarTablaRenglonesVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAgregarTablaRenglonesVentaMouseClicked(evt);
            }
        });
        jButtonAgregarTablaRenglonesVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarTablaRenglonesVentaActionPerformed(evt);
            }
        });

        jTextFieldCantidadVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldCantidadVenta.setText("0");

        jLabelCantidadVenta.setText("   Cantidad");
        jLabelCantidadVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelCantidadVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton1.setText("Registrar Venta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jTabbedPaneVentaLayout = new javax.swing.GroupLayout(jTabbedPaneVenta);
        jTabbedPaneVenta.setLayout(jTabbedPaneVentaLayout);
        jTabbedPaneVentaLayout.setHorizontalGroup(
            jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneSelecionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonAgregarTablaRenglonesVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldCantidadVenta))
                    .addComponent(jLabelCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jPanelMostrarRenglonesVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTabbedPaneVentaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jTabbedPaneVentaLayout.setVerticalGroup(
            jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                .addGroup(jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jButtonAgregarTablaRenglonesVenta)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCantidadVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTabbedPaneSelecionVenta)
                            .addComponent(jPanelMostrarRenglonesVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(36, 36, 36)
                .addGroup(jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPaneVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscadorTablaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorTablaClienteKeyReleased
        // TODO add your handling code here:
        
        List filtrado=filtrarNombre(jTextFieldBuscadorTablaCliente.getText(),clientes);
        System.out.println(filtrado);
        crearTablaClientes(filtrado,modeloTablaClientes);
    }//GEN-LAST:event_jTextFieldBuscadorTablaClienteKeyReleased

    private void jButtonAgregarTablaRenglonesVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarTablaRenglonesVentaActionPerformed
        
        //0:tab cliente 1:tab productos 2:tab servicios 3:tab vehiculos
        int tab;
        tab=jTabbedPaneSelecionVenta.getSelectedIndex();
        switch (tab){
                case 0: String nombre=(String) jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),1);
                        jTextFieldNombreCliente.setText(nombre);
                        clienteAux=new ClienteModel();
                        String a=(String) jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),0);//variable auxiliar
                        clienteAux.setCuit_cuil(Integer.valueOf(a));
                        clienteAux.setNombre((String) jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),1));
                        clienteAux.setDireccion((String) jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),2));
                        break;
                
                case 1: String idProducto,nombreProducto,cantidad,Descripcion,producto,descripcion,precio,stock,importe="";
                        String [] datos= new String[6];
                        idProducto=(String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),0);
                        nombreProducto= (String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),1);//getSelecteRow retorna -1 si no selecciono una fila
                        descripcion = (String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),4);
                        precio = (String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),5);
                        importe=(String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),5);
                        datos[0]=idProducto;
                        datos[1]="0";
                        datos[2]=nombreProducto;
                        datos[3]=descripcion;
                        datos[4]=precio;
                        datos[5]="";
                        modeloTablaVentas.addRow(datos);
                        
                        break;
                case 2:       
                        
                
        }               
        
    }//GEN-LAST:event_jButtonAgregarTablaRenglonesVentaActionPerformed

    private void jButtonAgregarTablaRenglonesVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAgregarTablaRenglonesVentaMouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jButtonAgregarTablaRenglonesVentaMouseClicked

    private void jTextFieldBuscadorTablaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorTablaProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscadorTablaProductosActionPerformed

    private void jTextFieldBuscadorTablaProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorTablaProductosKeyReleased
        // TODO add your handling code here:
        if(jComboBoxFiltrarPor.getSelectedIndex()==0){
            List filtrado=filtrarNombreProductos(jTextFieldBuscadorTablaProductos.getText(),productos);
            System.out.println(filtrado);
            crearTablaProductos(filtrado,modeloTablaProductos);
        }
    }//GEN-LAST:event_jTextFieldBuscadorTablaProductosKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAgregarTablaRenglonesVenta;
    private javax.swing.JComboBox<String> jComboBoxFiltrarPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelCantidadVenta;
    private javax.swing.JLabel jLabelNombreCliente;
    private javax.swing.JPanel jPanelClientesVenta;
    private javax.swing.JPanel jPanelMostrarRenglonesVenta;
    private javax.swing.JPanel jPanelProductosVenta;
    private javax.swing.JPanel jPanelServiciosVenta;
    private javax.swing.JPanel jPanelVehiculosVenta;
    private javax.swing.JScrollPane jScrollPaneClientesTablaVenta;
    private javax.swing.JScrollPane jScrollPaneMostrarRenglonesVenta;
    private javax.swing.JScrollPane jScrollPaneProductosTablaVenta;
    private javax.swing.JScrollPane jScrollPaneServiciosTablaVenta;
    private javax.swing.JScrollPane jScrollPaneVehiculosTablaVenta;
    private javax.swing.JTabbedPane jTabbedPaneSelecionVenta;
    private javax.swing.JPanel jTabbedPaneVenta;
    private javax.swing.JTable jTableClientesVenta;
    private javax.swing.JTable jTableMostrarRenglonesVenta;
    private javax.swing.JTable jTableProductosVenta;
    private javax.swing.JTable jTableServiciosVenta;
    private javax.swing.JTable jTableVehiculosVenta;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextFieldBuscadorTablaCliente;
    private javax.swing.JTextField jTextFieldBuscadorTablaProductos;
    private javax.swing.JTextField jTextFieldCantidadVenta;
    private javax.swing.JTextField jTextFieldNombreCliente;
    // End of variables declaration//GEN-END:variables
}
