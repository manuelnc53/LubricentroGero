/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author luisr
 */
import Controller.ClienteController;
import Controller.OrdenController;
import Controller.ProductoController;
import Controller.ServicioController;
import Controller.VentaController;
import Model.AceiteModel;
import Model.ClienteModel;
import Model.EmpleadoDAO;
import Model.EmpleadoModel;
import Model.OrdenModel;
import Model.ProductoModel;
import Model.RenglonDeVenta;
import Model.ServicioModel;
import Model.VehiculoDAO;
import Model.VehiculoModel;
import Model.VentaModel;
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
import javax.swing.JDialog;
import javax.swing.JTable;
public class VentaView extends javax.swing.JFrame {

    /**
     * Creates new form VentaView
     */
    Date fechaActual;
    SimpleDateFormat formatoFecha;
    // modelos de tablas y listas utilizadas
    private DefaultTableModel modeloTablaClientes;
    private DefaultTableModel modeloTablaProductos;
    private DefaultTableModel modeloTablaOrdenes;
    private DefaultTableModel modeloTablaVentas;
    private List<ClienteModel> clientes;
    private List<ProductoModel> productos;
    private List<ServicioModel> servicios;
    private List<OrdenModel> ordenes;
    private List<RenglonDeVenta> listaRenglonesVenta;
    //variable para registrar la centa
    private VentaModel venta;
    
    //controladores utilizados
    private ClienteController clienteControlador;
    private ProductoController productoControlador;
    private OrdenController ordenControlador;
    private VentaController ventaControlador;
    //sacar esto cuando el gonza me lo pase por el constructor y cambiar cajerox por cajero
    private EmpleadoModel cajerox;
    //Clinte que se carga en el action listener de agregar luego se pasara a la orden
    private ClienteModel clienteAux;
    
    public VentaView(/*EmpleadoModel cajero*/) throws SQLException, ParseException {
        initComponents();
        cajerox=new EmpleadoModel();//sacar y cambiar en el llamado al controler cuando el gonza me lo pase por el constructor
        //obtengo la fecha actual del sistema
        fechaActual = new Date();
        formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        jTextFieldFecha.setText(formatoFecha.format(fechaActual).toString());
        
        //creo los tableModel con el modelo de las tablas correspondientes
        modeloTablaClientes=(DefaultTableModel)jTableClientesVenta.getModel();
        modeloTablaProductos=(DefaultTableModel)jTableProductosVenta.getModel();
        modeloTablaOrdenes=(DefaultTableModel)jTableOrdenesVenta.getModel();
        modeloTablaVentas=(DefaultTableModel)jTableMostrarRenglonesVenta.getModel();
        
        //seteo las tablas con los modelos que seran llenados en los metodos crear tablas
        jTableClientesVenta.setModel(modeloTablaClientes);
        jTableProductosVenta.setModel(modeloTablaProductos);
        jTableOrdenesVenta.setModel(modeloTablaOrdenes);
        jTableMostrarRenglonesVenta.setModel(modeloTablaVentas);
        
        //instancio el controlador
        clienteControlador = new ClienteController();
        productoControlador = new ProductoController();
        ordenControlador = new OrdenController();
        ventaControlador = new VentaController();
        //listas de elementos a mostrar en la venta
        clientes=clienteControlador.verClientes();
        productos=productoControlador.verProductos();
        listaRenglonesVenta=new ArrayList();
        //carga las tablas con la informacion de las listas
        //Estas tablas se crean ni bien se abre la pantalla
        crearTablaClientes(clientes, modeloTablaClientes);
        crearTablaProductos(productos, modeloTablaProductos);
          
    }
    
    /**
     * Devuelve una lista con todos los empleados que tienen en su composicion el patron de la variable nombre
     * @param nombre nombre del cliente buscado
     * @param clientes la lista de los empleados
     * @return List<ClienteModel> que cumple con tener el patron de la palabra.
     */
    private List filtrarNombre(String nombre,List<ClienteModel> clientes) { //Utilizo lenguaje regular aqui.
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
    private List filtrarNombreProductos(String nombre,List<ProductoModel> productos) { //Utilizo lenguaje regular aqui.
        nombre=nombre.toLowerCase();
        List<ProductoModel> filtrados=new ArrayList<ProductoModel>();
        String regex = ".*"+nombre+".*";
        Pattern patron = Pattern.compile(regex);
        Matcher m ;
        for(ProductoModel o: productos){
            //System.out.println(o.getNombre().toLowerCase()+":"+nombre);
             m  = patron.matcher(o.getNombre().toLowerCase());
             if(m.find()){
                 filtrados.add(o);
             }
        }
        return filtrados;
    }
    
    
    private  void crearTablaClientes(List<ClienteModel> clientesParaTabla,DefaultTableModel modeloTablaClientes){
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
    
    private  void crearTablaProductos(List<ProductoModel> productosParaTabla,DefaultTableModel modeloTablaProductos){
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
    
    //sacar el estatic . Preguntar al gonza
    private void crearTablaOrdenes(List<OrdenModel>ordenesParaTabla,DefaultTableModel modeloTablaOrdenes,String idCliente){
        String []datos=new String[6];
        String empleados="";
        int i;
        SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
        for(i=0;i<modeloTablaOrdenes.getRowCount();i++){
            modeloTablaOrdenes.removeRow(i);
            i=i-1;
        }
        for(OrdenModel o: ordenesParaTabla){
            datos[0]=String.valueOf(o.getNro_Orden());
            datos[1]=o.getDescripcion();
            datos[2]=fechaAux.format(o.getFecha_Orden());
            datos[3]=o.getVehiculo().getPatente();
            for(EmpleadoModel e: o.getEmpleados_mantenimiento()){
                System.out.println("En el crear ordenes while:"+e.getNombre());
                empleados=empleados+"\n"+e.getNombre();
            }
            System.out.println("En el crar ordenes:"+empleados);
            datos[4]=empleados.toString();
            datos[5]=o.getUrgencia();
            
            modeloTablaOrdenes.addRow(datos);
        }
    }
    
    private void crearTablaVentas(List<RenglonDeVenta> renglonesVenta,DefaultTableModel modeloTablaVentas){
        String []datos=new String[5];
        int i;
        SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
        for(i=0;i<modeloTablaVentas.getRowCount();i++){//limpia la tabla ventas
            modeloTablaVentas.removeRow(i);
            i=i-1;
        }
        for( RenglonDeVenta r : renglonesVenta ){
            datos[0]=r.getiD();
            datos[1]=r.getDescripcion();
            datos[2]=r.getCantidad();
            datos[3]=r.getPrecioUnitario();
            datos[4]=r.getImporte();
            modeloTablaVentas.addRow(datos);
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

        jDialogAgregarManualmente = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionjDialogAgregarManualmente = new javax.swing.JTextArea();
        costojDialogAgregarManualmente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        aceptarjDialogAgregarManualmente = new javax.swing.JButton();
        cancelarjDialogAgregarManualmente = new javax.swing.JButton();
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
        jPanelMostrarRenglonesVenta = new javax.swing.JPanel();
        jScrollPaneMostrarRenglonesVenta = new javax.swing.JScrollPane();
        jTableMostrarRenglonesVenta = new javax.swing.JTable();
        jTextFieldNombreCliente = new javax.swing.JTextField();
        jLabelNombreCliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextFieldFecha = new javax.swing.JTextField();
        jTextFieldTotalVenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButtonAgregarTablaRenglonesVenta = new javax.swing.JButton();
        jLabelCantidadVenta = new javax.swing.JLabel();
        jButtonRegistrarVenta = new javax.swing.JButton();
        jScrollPaneServiciosTablaVenta = new javax.swing.JScrollPane();
        jTableOrdenesVenta = new javax.swing.JTable();
        jTextFieldCantidadVenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        descripcionjDialogAgregarManualmente.setColumns(20);
        descripcionjDialogAgregarManualmente.setRows(5);
        jScrollPane1.setViewportView(descripcionjDialogAgregarManualmente);

        jLabel4.setText("Agregar Manualmente");

        jLabel5.setText("Descripcion");

        jLabel6.setText("Costo");

        aceptarjDialogAgregarManualmente.setText("Aceptar");
        aceptarjDialogAgregarManualmente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarjDialogAgregarManualmenteActionPerformed(evt);
            }
        });

        cancelarjDialogAgregarManualmente.setText("Cancelar");

        javax.swing.GroupLayout jDialogAgregarManualmenteLayout = new javax.swing.GroupLayout(jDialogAgregarManualmente.getContentPane());
        jDialogAgregarManualmente.getContentPane().setLayout(jDialogAgregarManualmenteLayout);
        jDialogAgregarManualmenteLayout.setHorizontalGroup(
            jDialogAgregarManualmenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAgregarManualmenteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAgregarManualmenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogAgregarManualmenteLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAgregarManualmenteLayout.createSequentialGroup()
                        .addGroup(jDialogAgregarManualmenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDialogAgregarManualmenteLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(aceptarjDialogAgregarManualmente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelarjDialogAgregarManualmente))
                            .addComponent(jScrollPane1)
                            .addGroup(jDialogAgregarManualmenteLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(costojDialogAgregarManualmente, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29))))
        );
        jDialogAgregarManualmenteLayout.setVerticalGroup(
            jDialogAgregarManualmenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAgregarManualmenteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jDialogAgregarManualmenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costojDialogAgregarManualmente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogAgregarManualmenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarjDialogAgregarManualmente)
                    .addComponent(cancelarjDialogAgregarManualmente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPaneSelecionVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTableClientesVenta = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int collIndex){return false;}};
        jTableClientesVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CUIT_CUIL", "Nombre", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableClientesVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesVentaMouseClicked(evt);
            }
        });
        jScrollPaneClientesTablaVenta.setViewportView(jTableClientesVenta);
        if (jTableClientesVenta.getColumnModel().getColumnCount() > 0) {
            jTableClientesVenta.getColumnModel().getColumn(0).setResizable(false);
            jTableClientesVenta.getColumnModel().getColumn(1).setResizable(false);
            jTableClientesVenta.getColumnModel().getColumn(2).setResizable(false);
        }

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelClientesVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClientesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPaneClientesTablaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addGroup(jPanelClientesVentaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBuscadorTablaCliente)))
                .addContainerGap())
        );
        jPanelClientesVentaLayout.setVerticalGroup(
            jPanelClientesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesVentaLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanelClientesVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscadorTablaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneClientesTablaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPaneSelecionVenta.addTab("Clientes", jPanelClientesVenta);

        jTableProductosVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Marca", "Descripcion", "PrecioVenta", "Stock", "Cant.Min"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProductosVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableProductosVentaKeyTyped(evt);
            }
        });
        jScrollPaneProductosTablaVenta.setViewportView(jTableProductosVenta);
        if (jTableProductosVenta.getColumnModel().getColumnCount() > 0) {
            jTableProductosVenta.getColumnModel().getColumn(0).setResizable(false);
            jTableProductosVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableProductosVenta.getColumnModel().getColumn(1).setResizable(false);
            jTableProductosVenta.getColumnModel().getColumn(2).setResizable(false);
            jTableProductosVenta.getColumnModel().getColumn(3).setResizable(false);
            jTableProductosVenta.getColumnModel().getColumn(4).setResizable(false);
            jTableProductosVenta.getColumnModel().getColumn(5).setResizable(false);
            jTableProductosVenta.getColumnModel().getColumn(5).setPreferredWidth(60);
            jTableProductosVenta.getColumnModel().getColumn(6).setResizable(false);
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
                    .addGroup(jPanelProductosVentaLayout.createSequentialGroup()
                        .addComponent(jScrollPaneProductosTablaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelProductosVentaLayout.createSequentialGroup()
                        .addGroup(jPanelProductosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProductosVentaLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanelProductosVentaLayout.createSequentialGroup()
                                .addComponent(jComboBoxFiltrarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jTextFieldBuscadorTablaProductos)))
                        .addGap(10, 10, 10))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPaneProductosTablaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTabbedPaneSelecionVenta.addTab("Productos", jPanelProductosVenta);

        jPanelMostrarRenglonesVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableMostrarRenglonesVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripcion", "Cantidad", "PrecioUnit", "Importe"
            }
        ));
        jScrollPaneMostrarRenglonesVenta.setViewportView(jTableMostrarRenglonesVenta);
        if (jTableMostrarRenglonesVenta.getColumnModel().getColumnCount() > 0) {
            jTableMostrarRenglonesVenta.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jPanelMostrarRenglonesVenta.add(jScrollPaneMostrarRenglonesVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 590, 240));

        jTextFieldNombreCliente.setEditable(false);
        jPanelMostrarRenglonesVenta.add(jTextFieldNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 270, -1));

        jLabelNombreCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombreCliente.setText("Cliente");
        jPanelMostrarRenglonesVenta.add(jLabelNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 60, 20));

        jLabel2.setText("Fecha");
        jPanelMostrarRenglonesVenta.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, -1, 20));

        jButton2.setText("Agregar Manualmente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanelMostrarRenglonesVenta.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 190, -1));

        jButton3.setText("Eliminar de la venta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanelMostrarRenglonesVenta.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, 190, -1));

        jButton4.setText("Limpiar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanelMostrarRenglonesVenta.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 190, -1));

        jTextFieldFecha.setEditable(false);
        jPanelMostrarRenglonesVenta.add(jTextFieldFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 90, -1));

        jTextFieldTotalVenta.setEditable(false);
        jPanelMostrarRenglonesVenta.add(jTextFieldTotalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 80, -1));

        jLabel7.setText("Total:");
        jPanelMostrarRenglonesVenta.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 40, 20));

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

        jLabelCantidadVenta.setText("   Cantidad");
        jLabelCantidadVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelCantidadVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButtonRegistrarVenta.setText("Registrar Venta");
        jButtonRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarVentaActionPerformed(evt);
            }
        });

        jTableOrdenesVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripcion", "Fecha de Emision", "Patente", "Empleados", "Urgencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableOrdenesVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOrdenesVentaMouseClicked(evt);
            }
        });
        jScrollPaneServiciosTablaVenta.setViewportView(jTableOrdenesVenta);
        if (jTableOrdenesVenta.getColumnModel().getColumnCount() > 0) {
            jTableOrdenesVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jTabbedPaneVentaLayout = new javax.swing.GroupLayout(jTabbedPaneVenta);
        jTabbedPaneVenta.setLayout(jTabbedPaneVentaLayout);
        jTabbedPaneVentaLayout.setHorizontalGroup(
            jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonRegistrarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTabbedPaneVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneServiciosTablaVenta)
                    .addComponent(jTabbedPaneSelecionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAgregarTablaRenglonesVenta)
                            .addComponent(jTextFieldCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelMostrarRenglonesVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jTabbedPaneVentaLayout.setVerticalGroup(
            jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                .addGroup(jTabbedPaneVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jButtonAgregarTablaRenglonesVenta)
                        .addGap(13, 13, 13)
                        .addComponent(jLabelCantidadVenta)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(226, 226, 226)
                        .addComponent(jLabel8))
                    .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelMostrarRenglonesVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172)
                        .addComponent(jButtonRegistrarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jTabbedPaneVentaLayout.createSequentialGroup()
                        .addComponent(jTabbedPaneSelecionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneServiciosTablaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        getContentPane().add(jTabbedPaneVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAgregarTablaRenglonesVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarTablaRenglonesVentaActionPerformed
        
        //0:tab cliente 1:tab productos 2:tab servicios 3:tab vehiculos
        int tab;
        tab=jTabbedPaneSelecionVenta.getSelectedIndex();
        switch(tab){
                case 0: String nombre=(String) jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),1);
                        jTextFieldNombreCliente.setText(nombre);
                        clienteAux=new ClienteModel();
                        String a=(String) jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),0);//variable auxiliar
                        clienteAux.setCuit_cuil(Long.valueOf(a));
                        clienteAux.setNombre((String) jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),1));
                        clienteAux.setDireccion((String) jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),2));
                        break;
                
                case 1: String idProducto,nombreProducto,cantidad,Descripcion,producto,descripcion,precio,stock,importe="";
                        RenglonDeVenta renAuxi=new RenglonDeVenta();
                        String [] datos= new String[6];
                       // System.out.println((String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),0));
                        idProducto=(String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),0);
                        nombreProducto= (String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),1);//getSelecteRow retorna -1 si no selecciono una fila
                        descripcion = (String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),4);
                        precio = (String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),5);
                        importe=(String) jTableProductosVenta.getValueAt(jTableProductosVenta.getSelectedRow(),5);
                        int cant;
                        Float pre;
                        renAuxi.setTipoDeIntem(1);//1 porque es un producto
                        renAuxi.setiD(idProducto);
                        renAuxi.setDescripcion(descripcion);
                        renAuxi.setCantidad(jTextFieldCantidadVenta.getText());
                        cant=Integer.valueOf(jTextFieldCantidadVenta.getText());
                        pre=Float.valueOf(precio);
                        renAuxi.setPrecioUnitario(precio);
                        renAuxi.setImporte(String.valueOf(cant*pre));
                        listaRenglonesVenta.add(renAuxi);//agrego el renglo a la lista unica de renglones
                        crearTablaVentas(listaRenglonesVenta, modeloTablaVentas);//creo la tabla
                     
                        break;
      
        }               
        
    }//GEN-LAST:event_jButtonAgregarTablaRenglonesVentaActionPerformed

    private void jButtonAgregarTablaRenglonesVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAgregarTablaRenglonesVentaMouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jButtonAgregarTablaRenglonesVentaMouseClicked

    private void jButtonRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarVentaActionPerformed
        // para registrar la venta
     
        
        ventaControlador.registrarVenta(listaRenglonesVenta, fechaActual, cajerox, clienteAux);
    }//GEN-LAST:event_jButtonRegistrarVentaActionPerformed

    private void jTextFieldBuscadorTablaProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorTablaProductosKeyReleased
        // TODO add your handling code here:
        if(jComboBoxFiltrarPor.getSelectedIndex()==0){
            List filtrado=filtrarNombreProductos(jTextFieldBuscadorTablaProductos.getText(),productos);
            System.out.println(filtrado);
            crearTablaProductos(filtrado,modeloTablaProductos);
        }
    }//GEN-LAST:event_jTextFieldBuscadorTablaProductosKeyReleased

    private void jTextFieldBuscadorTablaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorTablaProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscadorTablaProductosActionPerformed

    private void jTextFieldBuscadorTablaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorTablaClienteKeyReleased
        // TODO add your handling code here:

        List filtrado=filtrarNombre(jTextFieldBuscadorTablaCliente.getText(),clientes);
        System.out.println(filtrado);
        crearTablaClientes(filtrado,modeloTablaClientes);
    }//GEN-LAST:event_jTextFieldBuscadorTablaClienteKeyReleased

    private void jTableClientesVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesVentaMouseClicked
        // TODO add your handling code here:
       
        try {
            ordenes=ordenControlador.verOrdenes(jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),0).toString());
        } catch (SQLException ex) {
            Logger.getLogger(VentaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        crearTablaOrdenes(ordenes,modeloTablaOrdenes,jTableClientesVenta.getValueAt(jTableClientesVenta.getSelectedRow(),0).toString());
    }//GEN-LAST:event_jTableClientesVentaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //jDialogAgregarManualmente =new JDialog(this,"Agregar Manualmente",true);
        jDialogAgregarManualmente.setVisible(true);
        jDialogAgregarManualmente.setSize(400, 400);
        jDialogAgregarManualmente.setLocation(600,300);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTableProductosVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableProductosVentaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableProductosVentaKeyTyped

    private void jTableOrdenesVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrdenesVentaMouseClicked
        // TODO add your handling code here:
        long idAux;
        idAux=Long.valueOf(jTableOrdenesVenta.getValueAt(jTableOrdenesVenta.getSelectedRow(),0).toString());
        System.out.println("dentro del evento de la tabla ordenes:"+idAux);
        ArrayList<ServicioModel> serviciosAux=new ArrayList();
        RenglonDeVenta ren;//se cargara con servicios leidos de la orden
        String idString=(String)jTableOrdenesVenta.getValueAt(jTableOrdenesVenta.getSelectedRow(),0);//variable auxiliar
        long id=Long.valueOf(idString);
        for(OrdenModel o:ordenes){
            if(id==o.getNro_Orden()){
            serviciosAux=o.getServicios();
            for(ServicioModel s: serviciosAux){
                ren = new RenglonDeVenta();
                ren.setTipoDeIntem(2);//Es un servicio(2)
                ren.setiD(String.valueOf(s.getId()));
                ren.setDescripcion(s.getNombre());
                ren.setCantidad("1");
                ren.setPrecioUnitario(String.valueOf(s.getPrecio()));
                ren.setImporte(String.valueOf(s.getPrecio()));
                System.out.println(ren);
                listaRenglonesVenta.add(ren);
                
            }}
            
        }
        //cargo un servicio
        crearTablaVentas(listaRenglonesVenta, modeloTablaVentas);
    }//GEN-LAST:event_jTableOrdenesVentaMouseClicked

    private void aceptarjDialogAgregarManualmenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarjDialogAgregarManualmenteActionPerformed
        // TODO add your handling code here:
        RenglonDeVenta renAux = new RenglonDeVenta();
        String des = descripcionjDialogAgregarManualmente.getText();
        String costo = costojDialogAgregarManualmente.getText();
        renAux.setDescripcion(des);
        renAux.setImporte(costo);
        renAux.setTipoDeIntem(3);// 3 : por ser un descuento o recargo
        listaRenglonesVenta.add(renAux);
        crearTablaVentas(listaRenglonesVenta, modeloTablaVentas);
    }//GEN-LAST:event_aceptarjDialogAgregarManualmenteActionPerformed

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
                //new VentaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarjDialogAgregarManualmente;
    private javax.swing.JButton cancelarjDialogAgregarManualmente;
    private javax.swing.JTextField costojDialogAgregarManualmente;
    private javax.swing.JTextArea descripcionjDialogAgregarManualmente;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAgregarTablaRenglonesVenta;
    private javax.swing.JButton jButtonRegistrarVenta;
    private javax.swing.JComboBox<String> jComboBoxFiltrarPor;
    private javax.swing.JDialog jDialogAgregarManualmente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCantidadVenta;
    private javax.swing.JLabel jLabelNombreCliente;
    private javax.swing.JPanel jPanelClientesVenta;
    private javax.swing.JPanel jPanelMostrarRenglonesVenta;
    private javax.swing.JPanel jPanelProductosVenta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneClientesTablaVenta;
    private javax.swing.JScrollPane jScrollPaneMostrarRenglonesVenta;
    private javax.swing.JScrollPane jScrollPaneProductosTablaVenta;
    private javax.swing.JScrollPane jScrollPaneServiciosTablaVenta;
    private javax.swing.JTabbedPane jTabbedPaneSelecionVenta;
    private javax.swing.JPanel jTabbedPaneVenta;
    private javax.swing.JTable jTableClientesVenta;
    private javax.swing.JTable jTableMostrarRenglonesVenta;
    private javax.swing.JTable jTableOrdenesVenta;
    private javax.swing.JTable jTableProductosVenta;
    private javax.swing.JTextField jTextFieldBuscadorTablaCliente;
    private javax.swing.JTextField jTextFieldBuscadorTablaProductos;
    private javax.swing.JTextField jTextFieldCantidadVenta;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldNombreCliente;
    private javax.swing.JTextField jTextFieldTotalVenta;
    // End of variables declaration//GEN-END:variables
}