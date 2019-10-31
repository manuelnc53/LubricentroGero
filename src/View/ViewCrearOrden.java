/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
//Orden
import Controller.ClienteController;
import Controller.EmpleadoController;
import Controller.OrdenController;
import Controller.ServicioController;
import Controller.VehiculoController;
import Model.ClienteModel;
import Model.EmpleadoDAO;
import Model.EmpleadoModel;
import Model.EstadoModel;
import Model.OrdenModel;
import Model.ResponsabilidadModel;
import Model.ServicioDAO;
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
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author YOGA
 */
public class ViewCrearOrden extends javax.swing.JFrame {
    private TablaNoEditable modeloGenerico;
   //private TablaNoEditable modeloEmpleados;
   // private TablaNoEditable modeloEmpleadosSelec;
   // private TablaNoEditable modeloClientes;
   // private TablaNoEditable modeloVehiculos;
   // private TablaNoEditable modeloServicios;
   // private TablaNoEditable modeloServiciosSelec;
    private List<EmpleadoModel> empleados;
    private List<EmpleadoModel> empleadosSelec;
    private List<ClienteModel> clientes;
    private List<VehiculoModel> vehiculos;
    private List<ServicioModel> servicios;
    private List<ServicioModel> serviciosSelec;
    private EmpleadoModel cajero;
    private VehiculoModel vehiculo;
    private ClienteModel cliente;
    //private JLabel costo;
    
    
     /**
     * Devuelve una lista con todos los empleados que tienen en su composicion el patron de la variable nombre
     * @param nombre nombre del empleado buscado
     * @param listaEmpleados la lista de los empleados
     * @return List<Empleados> que cumple con tener el patron de la palabra.
     */
    private static List filtrarNombreEmpleado(String nombre,List<EmpleadoModel> empleados) { //Utilizo lenguaje regular aqui.
        nombre=nombre.toLowerCase();
        List<EmpleadoModel> filtrados=new ArrayList<EmpleadoModel>();
        String regex = ".*"+nombre+".*";
        Pattern patron = Pattern.compile(regex);
        Matcher m ;
        for(EmpleadoModel o: empleados){
             m  = patron.matcher(o.getNombre().toLowerCase());
             if(m.find()){
                 filtrados.add(o);
             }
        }
        return filtrados;
    }
    private static List filtrarNombreCliente(String nombre,List<ClienteModel> clientes) { //Utilizo lenguaje regular aqui.
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

    private static List filtrarPatente(String nombre,List<VehiculoModel> vehiculos) { //Utilizo lenguaje regular aqui.
        nombre=nombre.toLowerCase();
        List<VehiculoModel> filtrados=new ArrayList<VehiculoModel>();
        String regex = ".*"+nombre+".*";
        Pattern patron = Pattern.compile(regex);
        Matcher m ;
        for(VehiculoModel o: vehiculos){
             m  = patron.matcher(o.getPatente().toLowerCase());
             if(m.find()){
                 filtrados.add(o);
             }
        }
        return filtrados;
    }
    private static List filtrarNombreServicio(String nombre,List<ServicioModel> servicios) { //Utilizo lenguaje regular aqui.
        nombre=nombre.toLowerCase();
        List<ServicioModel> filtrados=new ArrayList<ServicioModel>();
        String regex = ".*"+nombre+".*";
        Pattern patron = Pattern.compile(regex);
        Matcher m ;
        for(ServicioModel o: servicios){
             m  = patron.matcher(o.getNombre().toLowerCase());
             if(m.find()){
                 filtrados.add(o);
             }
        }
        return filtrados;
    }

    
    /**
     * Devuelve una lista con todos los empleados que tienen cuil igual al pasado por parametro
     * @param cuil nombre del empleado buscado
     * @param listaEmpleados la lista de los empleados
     * @return EmpleadoModel que tiene el cuil seleccionado.
     */
    private static EmpleadoModel filtrarCuil(String cuil,List<EmpleadoModel> empleados) { //Utilizo lenguaje regular aqui.
        List<EmpleadoModel> filtrados=new ArrayList<EmpleadoModel>();
        long aux = Integer.parseInt(cuil);
        for(EmpleadoModel o: empleados){
             if(aux==o.getCuit()){
                 filtrados.add(o);
             }
        }
        return filtrados.get(0);
    }
    
    
     /**
     * Coloca en el modelo de la tabla de empleados, el modelo con los empleados de la tabla de busqueda
     * @param emp lista con empleados
     * @param modelo el modelo actual de la tabla
     * @return List<Empleados> que cumple con tener el patron de la palabra.
     */

    private static void crearTablaEmpleados(List<EmpleadoModel> emp,TablaNoEditable modelo,JTable tabla){
        modelo=new TablaNoEditable();
        modelo.addColumn("Nombre");
        modelo.addColumn("Direccion");
        modelo.addColumn("Fecha nacimiento");
        modelo.addColumn("Responsabilidad");
        modelo.addColumn("Cuil");
        modelo.addColumn("Fecha Ingreso");
        tabla.setModel(modelo);
        String []datos=new String[6];
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for(EmpleadoModel o : emp){
            datos[0]=o.getNombre();
            datos[1]=o.getDireccion();
            datos[2]=dateFormat.format(o.getEdad());
            datos[3]=o.getResponsabilidad().toString();
            datos[4]=String.valueOf(o.getCuit());
            datos[5]=dateFormat.format(o.getFecha_ingreso());
            modelo.addRow(datos);
        }
    }
    private static void crearTablaClientes(List<ClienteModel> client,TablaNoEditable modelo,JTable tabla){
        modelo=new TablaNoEditable();
        modelo.addColumn("Cuil");
        modelo.addColumn("Nombre");
        modelo.addColumn("Direccion");
        tabla.setModel(modelo);
        String []datos=new String[3];
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for(ClienteModel o : client){
            datos[0]=String.valueOf(o.getCuit_cuil());
            datos[1]=o.getNombre();
            datos[2]=o.getDireccion();
            modelo.addRow(datos);
        }
    }
    
    private static void crearTablaVehiculos(List<VehiculoModel> vehiculo,TablaNoEditable modelo,JTable tabla){
        modelo=new TablaNoEditable();
        modelo.addColumn("Patente");
        modelo.addColumn("Tipo Motor");
        modelo.addColumn("Kilometraje");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        tabla.setModel(modelo);
        String []datos=new String[5];
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for(VehiculoModel o : vehiculo){
            datos[0]=o.getPatente();
            datos[1]=o.getTipo_motor();
            datos[2]=String.valueOf(o.getKilometraje());
            datos[3]=o.getMarca();
            datos[4]=o.getModelo();
            modelo.addRow(datos);
        }
    }
    
    private static void crearTablaServicios(List<ServicioModel> servicio,TablaNoEditable modelo,JTable tabla){
        modelo=new TablaNoEditable();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        tabla.setModel(modelo);
        String []datos=new String[4];
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for(ServicioModel o : servicio){
            datos[0]=String.valueOf(o.getId());
            datos[1]=o.getNombre();
            datos[2]=o.getDescripcion();
            datos[3]=String.valueOf(o.getPrecio());
            modelo.addRow(datos);
        }
    }
    
    
    public ViewCrearOrden(EmpleadoModel cajero) throws SQLException, ParseException, CloneNotSupportedException {
        initComponents();
        //this.costo=costo;
        this.cajero=cajero;
        this.vehiculo= new VehiculoModel();
        this.cliente=new ClienteModel();
        EmpleadoController controlador = new EmpleadoController();
        ClienteController controladorC= new ClienteController();
        VehiculoController controladorV = new VehiculoController();
        ServicioController controladorS = new ServicioController();
        //VehiculoDAO bdVehiculo=new VehiculoDAO();
        empleados=controlador.empleadosBD();
        empleadosSelec=new ArrayList<EmpleadoModel>();
        serviciosSelec = new ArrayList<ServicioModel>();
        vehiculos = controladorV.vehiculosBD();
        servicios = controladorS.serviciosBD();
        clientes = controladorC.clientesBD();
        crearTablaEmpleados(empleados,modeloGenerico,tablaEmpleados);
        crearTablaEmpleados(empleadosSelec,modeloGenerico,tablaEmpleadosSelec);
        crearTablaClientes(clientes,modeloGenerico,tablaClientes);
        crearTablaVehiculos(vehiculos,modeloGenerico,tablaVehiculos);
        crearTablaServicios(servicios,modeloGenerico,tablaServicios);
        crearTablaServicios(serviciosSelec,modeloGenerico,tablaServiciosSelec);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tabFinalizarOrden = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        botonAgregarEmpleados = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buscadorNombreEmpleado = new javax.swing.JTextField();
        botonSacarEmpleados = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEmpleadosSelec = new javax.swing.JTable();
        factorExtraTexto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buscadorNombreCliente = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaVehiculos = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        buscadorNombreVehiculo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        botonAgregarServicios = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        buscadorNombreServicio = new javax.swing.JTextField();
        botonSacarServicios = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaServiciosSelec = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaTextoDatos = new javax.swing.JTextArea();
        botonCrearOrden = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboBoxPrioridad = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaTextoDescripcion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crear orden de trabajo");
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(1056, 600));
        setMinimumSize(new java.awt.Dimension(1056, 600));
        setName("ventanCrearOrden"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1080, 730));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(396, 551));
        jPanel1.setMinimumSize(new java.awt.Dimension(396, 551));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Crear orden de trabajo");

        tabFinalizarOrden.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabFinalizarOrdenFocusGained(evt);
            }
        });

        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaEmpleados);

        botonAgregarEmpleados.setText("Agregar");
        botonAgregarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarEmpleadosActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Seleccione los empleados que ejecutaran los servicio");

        jLabel3.setText("Busqueda por nombre:");

        buscadorNombreEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscadorNombreEmpleadoActionPerformed(evt);
            }
        });
        buscadorNombreEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscadorNombreEmpleadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorNombreEmpleadoKeyReleased(evt);
            }
        });

        botonSacarEmpleados.setText("Sacar");
        botonSacarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSacarEmpleadosActionPerformed(evt);
            }
        });

        tablaEmpleadosSelec.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaEmpleadosSelec);

        factorExtraTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factorExtraTextoActionPerformed(evt);
            }
        });

        jLabel13.setText("Factor extra por cada empleado en porcentaje");

        jLabel14.setText("%");

        jLabel15.setText(" (Ejemplo: 20 significa 20% extra del costo total de los servicios por cada empleado)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscadorNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(factorExtraTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botonAgregarEmpleados)
                                    .addComponent(botonSacarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscadorNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(factorExtraTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(botonAgregarEmpleados)
                        .addGap(77, 77, 77)
                        .addComponent(botonSacarEmpleados)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        tabFinalizarOrden.addTab("Seleccionar empleado/s", jPanel3);

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaClientesMouseEntered(evt);
            }
        });
        tablaClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaClientesKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tablaClientes);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Seleccione el cliente al que se le realizara el servicio");

        jLabel5.setText("Busqueda por nombre:");

        buscadorNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscadorNombreClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorNombreClienteKeyReleased(evt);
            }
        });

        jButton2.setText("Agregar nuevo cliente a la base de datos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscadorNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 514, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscadorNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
        );

        tabFinalizarOrden.addTab("Seleccionar cliente", jPanel7);

        tablaVehiculos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVehiculosMouseClicked(evt);
            }
        });
        tablaVehiculos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaVehiculosKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(tablaVehiculos);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Seleccione el vehiculo al que se le realizara el servicio");

        jLabel6.setText("Busqueda por patente:");

        buscadorNombreVehiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscadorNombreVehiculoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorNombreVehiculoKeyReleased(evt);
            }
        });

        jButton1.setText("Agregar nuevo vehiculo a la base de datos");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscadorNombreVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 514, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscadorNombreVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
        );

        tabFinalizarOrden.addTab("Seleccionar vehiculo", jPanel8);

        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(tablaServicios);

        botonAgregarServicios.setText("Agregar");
        botonAgregarServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarServiciosActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Agregue los servicios que se realizaran al vehiculo");

        jLabel7.setText("Busqueda por nombre:");

        buscadorNombreServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscadorNombreServicioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorNombreServicioKeyReleased(evt);
            }
        });

        botonSacarServicios.setText("Sacar");
        botonSacarServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSacarServiciosActionPerformed(evt);
            }
        });

        tablaServiciosSelec.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(tablaServiciosSelec);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscadorNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botonAgregarServicios)
                                    .addComponent(botonSacarServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscadorNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(botonAgregarServicios)
                        .addGap(77, 77, 77)
                        .addComponent(botonSacarServicios)
                        .addContainerGap())
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        tabFinalizarOrden.addTab("Seleccionar servicios", jPanel9);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Datos de orden:");

        areaTextoDatos.setEditable(false);
        areaTextoDatos.setColumns(20);
        areaTextoDatos.setRows(5);
        areaTextoDatos.setText("Servicios:"+servicios+" \nCliente:"+cliente+"\nVehiculo:"+vehiculo+"\nEmpleado/s:"+empleadosSelec+"\n");
        jScrollPane4.setViewportView(areaTextoDatos);

        botonCrearOrden.setText("Crear orden");
        botonCrearOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearOrdenActionPerformed(evt);
            }
        });

        jLabel1.setText("Prioridad");

        comboBoxPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Muy baja", "Baja", "Normal", "Alta", "Muy alta" }));
        comboBoxPrioridad.setSelectedIndex(2);
        comboBoxPrioridad.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        comboBoxPrioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPrioridadActionPerformed(evt);
            }
        });

        areaTextoDescripcion.setColumns(20);
        areaTextoDescripcion.setRows(5);
        jScrollPane3.setViewportView(areaTextoDescripcion);

        jLabel2.setText("Agregar una observacion:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonCrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonCrearOrden)
                        .addComponent(comboBoxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );

        tabFinalizarOrden.addTab("Finalizar orden", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tabFinalizarOrden)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabFinalizarOrden))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabFinalizarOrdenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabFinalizarOrdenFocusGained
        //No se aplica la arquitectura en capas?
        OrdenController orden = new OrdenController();
        try {
            orden.guardar("h", EstadoModel.EN_PROCESO, cajero, empleadosSelec, cliente, vehiculo, serviciosSelec, null);
        } catch (SQLException ex) {
            Logger.getLogger(ViewCrearOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        float aux=Float.parseFloat(factorExtraTexto.getText());
        aux= (float) (aux * 0.01);
        System.out.println(aux);
        String texto=orden.texto(aux);
        areaTextoDatos.setText(texto);
        //areaTextoDatos.setText("Servicios:"+servicios+" \nCliente:"+cliente+"\nVehiculo:"+vehiculo+"\nEmpleado/s:"+empleadosSelec+"\n");
    }//GEN-LAST:event_tabFinalizarOrdenFocusGained

    private void comboBoxPrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPrioridadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxPrioridadActionPerformed

    private void botonCrearOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearOrdenActionPerformed
        OrdenController controlador = new OrdenController();
        try {
            controlador.guardar(comboBoxPrioridad.getSelectedItem().toString(),EstadoModel.EN_PROCESO,cajero,empleadosSelec,cliente,vehiculo,servicios,
                areaTextoDescripcion.getText());
            controlador.guardarEnBD();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCrearOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonCrearOrdenActionPerformed

    private void buscadorNombreClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreClienteKeyReleased
        List a=filtrarNombreCliente(buscadorNombreCliente.getText(),clientes);
        crearTablaClientes(a,modeloGenerico,tablaClientes);
    }//GEN-LAST:event_buscadorNombreClienteKeyReleased

    private void buscadorNombreClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorNombreClienteKeyPressed

    private void botonSacarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSacarEmpleadosActionPerformed
        String cuil=(String) tablaEmpleadosSelec.getValueAt(tablaEmpleadosSelec.getSelectedRow(),4);
        EmpleadoModel emp=filtrarCuil(cuil,empleadosSelec);
        empleadosSelec.remove(emp);
        crearTablaEmpleados(empleadosSelec,modeloGenerico,tablaEmpleadosSelec);
    }//GEN-LAST:event_botonSacarEmpleadosActionPerformed

    private void buscadorNombreEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreEmpleadoKeyReleased
        List a=filtrarNombreEmpleado(buscadorNombreEmpleado.getText(),empleados);
        crearTablaEmpleados(a,modeloGenerico,tablaEmpleados);
    }//GEN-LAST:event_buscadorNombreEmpleadoKeyReleased

    private void buscadorNombreEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreEmpleadoKeyPressed

    }//GEN-LAST:event_buscadorNombreEmpleadoKeyPressed

    private void botonAgregarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarEmpleadosActionPerformed
        String cuil=(String) tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(),4);
        EmpleadoModel emp =new EmpleadoModel();
        emp=filtrarCuil(cuil,empleados);
        empleadosSelec.add(emp);
        //eliminar los repetidos
        Set<EmpleadoModel> hs = new HashSet<>();
        hs.addAll(empleadosSelec);
        empleadosSelec.clear();
        empleadosSelec.addAll(hs);
        //
        crearTablaEmpleados(empleadosSelec,modeloGenerico,tablaEmpleadosSelec);
    }//GEN-LAST:event_botonAgregarEmpleadosActionPerformed

    private void buscadorNombreVehiculoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreVehiculoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorNombreVehiculoKeyPressed

    private void buscadorNombreVehiculoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreVehiculoKeyReleased
        List a=filtrarPatente(buscadorNombreVehiculo.getText(),vehiculos);
        crearTablaVehiculos(a,modeloGenerico,tablaVehiculos);
    }//GEN-LAST:event_buscadorNombreVehiculoKeyReleased

    private void botonAgregarServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarServiciosActionPerformed
        long ID= Integer.parseInt((String) tablaServicios.getValueAt(tablaServicios.getSelectedRow(),0)) ;
        System.out.println(ID);
        ServicioController controladorS=new ServicioController();
        ServicioModel aux=controladorS.buscarPorID(ID,servicios);
                System.out.println(serviciosSelec);
        serviciosSelec.add(aux);
        //eliminar los repetidos
        Set<ServicioModel> hs = new HashSet<>();
        hs.addAll(serviciosSelec);
        serviciosSelec.clear();
        serviciosSelec.addAll(hs);
        //
        System.out.println(serviciosSelec);
        crearTablaServicios(serviciosSelec,modeloGenerico,tablaServiciosSelec);
    }//GEN-LAST:event_botonAgregarServiciosActionPerformed

    private void buscadorNombreServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreServicioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorNombreServicioKeyPressed

    private void buscadorNombreServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreServicioKeyReleased
        List a=filtrarNombreServicio(buscadorNombreServicio.getText(),servicios);
        crearTablaServicios(a,modeloGenerico,tablaServicios);
    }//GEN-LAST:event_buscadorNombreServicioKeyReleased

    private void botonSacarServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSacarServiciosActionPerformed
        long ID=Integer.parseInt((String) tablaServiciosSelec.getValueAt(tablaServiciosSelec.getSelectedRow(),0)) ;
        ServicioController controladorS = new ServicioController();
        ServicioModel aux=controladorS.buscarPorID(ID, servicios);
        System.out.println(serviciosSelec);
        serviciosSelec.remove(aux);
        System.out.println(serviciosSelec);
        crearTablaServicios(serviciosSelec,modeloGenerico,tablaServiciosSelec);
    }//GEN-LAST:event_botonSacarServiciosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseEntered
        
    }//GEN-LAST:event_tablaClientesMouseEntered

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
       ClienteController controladorC = new ClienteController();
       long aux=Integer.parseInt((String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0)) ;
       System.out.println(aux);
       cliente=controladorC.buscarPorCuil(aux, clientes);
        System.out.println(cliente);
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void tablaVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVehiculosMouseClicked
       VehiculoController controladorV = new VehiculoController();
       String aux=(String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 0) ;
       System.out.println(aux);
       vehiculo=controladorV.buscarPorPatente(aux, vehiculos);
        System.out.println(vehiculo);
    }//GEN-LAST:event_tablaVehiculosMouseClicked

    private void tablaVehiculosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaVehiculosKeyReleased
       VehiculoController controladorV = new VehiculoController();
       String aux=(String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 0) ;
       System.out.println(aux);
       vehiculo=controladorV.buscarPorPatente(aux, vehiculos);
       System.out.println(vehiculo);
    }//GEN-LAST:event_tablaVehiculosKeyReleased

    private void tablaClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaClientesKeyReleased
       ClienteController controladorC = new ClienteController();
       long aux=Integer.parseInt((String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0)) ;
       System.out.println(aux);
       cliente=controladorC.buscarPorCuil(aux, clientes);
       System.out.println(cliente);
    }//GEN-LAST:event_tablaClientesKeyReleased

    private void buscadorNombreEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscadorNombreEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorNombreEmpleadoActionPerformed

    private void factorExtraTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factorExtraTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_factorExtraTextoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewCrearOrden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCrearOrden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCrearOrden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCrearOrden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //Aqui poner valores validos de la bd
                    EmpleadoModel cajero = new EmpleadoModel();
                    Date f = new Date();/*
                    VehiculoModel v = new VehiculoModel();
                    ClienteModel c=new ClienteModel();
                    List <ServicioModel> servicios = new ArrayList<>();
                    JLabel j = new JLabel();
                    v.setKilometraje(1);
                    v.setMarca("1");
                    v.setModelo("1");
                    v.setPatente("1");
                    v.setTipo_motor("1");
                    ServicioModel ser = new ServicioModel();
                    ser.setDescripcion("1");
                    ser.setId(1);
                    ser.setNombre("1");
                    ser.setPrecio(1);
                    servicios.add(ser);
                    c.setCuit_cuil(1);
                    c.setDireccion("1");
                    c.setNombre("1");*/
                    cajero.setCuit(1);
                    cajero.setDireccion("1");
                    cajero.setEdad(f);
                    cajero.setFecha_ingreso(f);
                    cajero.setResponsabilidad(ResponsabilidadModel.CAJERO);
                    cajero.setNombre("Peres Ricardo");
                    
                    new ViewCrearOrden(cajero).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewCrearOrden.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ViewCrearOrden.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(ViewCrearOrden.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTextoDatos;
    private javax.swing.JTextArea areaTextoDescripcion;
    private javax.swing.JButton botonAgregarEmpleados;
    private javax.swing.JButton botonAgregarServicios;
    private javax.swing.JButton botonCrearOrden;
    private javax.swing.JButton botonSacarEmpleados;
    private javax.swing.JButton botonSacarServicios;
    private javax.swing.JTextField buscadorNombreCliente;
    private javax.swing.JTextField buscadorNombreEmpleado;
    private javax.swing.JTextField buscadorNombreServicio;
    private javax.swing.JTextField buscadorNombreVehiculo;
    private javax.swing.JComboBox<String> comboBoxPrioridad;
    private javax.swing.JTextField factorExtraTexto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane tabFinalizarOrden;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tablaEmpleadosSelec;
    private javax.swing.JTable tablaServicios;
    private javax.swing.JTable tablaServiciosSelec;
    private javax.swing.JTable tablaVehiculos;
    // End of variables declaration//GEN-END:variables
}
