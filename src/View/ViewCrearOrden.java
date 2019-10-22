/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.OrdenController;
import Model.ClienteModel;
import Model.EmpleadoDAO;
import Model.EmpleadoModel;
import Model.EstadoModel;
import Model.OrdenModel;
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
    private TablaNoEditable modeloEmpleados;
    private TablaNoEditable modeloEmpleadosSelec;
    private List<EmpleadoModel> empleados;
    private List<EmpleadoModel> empleadosSelec;
    private EmpleadoModel cajero;
    private List<ServicioModel> servicios;
    private VehiculoModel vehiculo;
    private ClienteModel cliente;
    
    
     /**
     * Devuelve una lista con todos los empleados que tienen en su composicion el patron de la variable nombre
     * @param nombre nombre del empleado buscado
     * @param listaEmpleados la lista de los empleados
     * @return List<Empleados> que cumple con tener el patron de la palabra.
     */
    private static List filtrarNombre(String nombre,List<EmpleadoModel> empleados) { //Utilizo lenguaje regular aqui.
        nombre.toLowerCase();
        List<EmpleadoModel> filtrados=new ArrayList<EmpleadoModel>();
        String regex = ".*"+nombre+".*";
        Pattern patron = Pattern.compile(regex);
        Matcher m ;
        for(EmpleadoModel o: empleados){
             m  = patron.matcher(o.getNombre());
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
        System.out.println("llegue aqui");
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
    
    public ViewCrearOrden(EmpleadoModel cajero,VehiculoModel vehiculo,List<ServicioModel> servicios,
            ClienteModel cliente,JLabel costo) throws SQLException, ParseException, CloneNotSupportedException {
        initComponents();
        this.cajero=cajero;
        this.vehiculo=vehiculo;
        this.servicios=servicios;
        this.cliente=cliente;
        EmpleadoDAO bdEmpleado=new EmpleadoDAO();
        //VehiculoDAO bdVehiculo=new VehiculoDAO();
        empleados=bdEmpleado.dameEmpleados();
        empleadosSelec=new ArrayList<EmpleadoModel>();
        crearTablaEmpleados(empleados,modeloEmpleados,tablaEmpleados);
        crearTablaEmpleados(empleadosSelec,modeloEmpleadosSelec,tablaEmpleadosSelec);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        botonAgregar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buscadorNombre = new javax.swing.JTextField();
        botonSacar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEmpleadosSelec = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaTextoDatos = new javax.swing.JTextArea();
        botonCrearOrden = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboBoxPrioridad = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crear orden de trabajo");
        setMaximumSize(new java.awt.Dimension(396, 551));
        setMinimumSize(new java.awt.Dimension(396, 551));

        jPanel1.setMaximumSize(new java.awt.Dimension(396, 551));
        jPanel1.setMinimumSize(new java.awt.Dimension(396, 551));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Crear orden de trabajo");

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
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

        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Seleccione empleado/s");

        jLabel3.setText("Busqueda por nombre:");

        buscadorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscadorNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorNombreKeyReleased(evt);
            }
        });

        botonSacar.setText("Sacar");
        botonSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSacarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonAgregar)
                            .addComponent(botonSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscadorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscadorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(botonAgregar)
                        .addGap(77, 77, 77)
                        .addComponent(botonSacar)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", jPanel3);

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1011, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonCrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboBoxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(botonCrearOrden)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
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

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
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
        crearTablaEmpleados(empleadosSelec,modeloEmpleadosSelec,tablaEmpleadosSelec);
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void buscadorNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreKeyPressed

    }//GEN-LAST:event_buscadorNombreKeyPressed

    private void buscadorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreKeyReleased
        List a=filtrarNombre(buscadorNombre.getText(),empleados);
        crearTablaEmpleados(a,modeloEmpleados,tablaEmpleados);
    }//GEN-LAST:event_buscadorNombreKeyReleased

    private void botonSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSacarActionPerformed
        String cuil=(String) tablaEmpleadosSelec.getValueAt(tablaEmpleadosSelec.getSelectedRow(),4);
        EmpleadoModel emp=filtrarCuil(cuil,empleadosSelec);
        empleadosSelec.remove(emp);
        crearTablaEmpleados(empleadosSelec,modeloEmpleadosSelec,tablaEmpleadosSelec);
    }//GEN-LAST:event_botonSacarActionPerformed

    private void comboBoxPrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPrioridadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxPrioridadActionPerformed

    private void botonCrearOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearOrdenActionPerformed
        OrdenController controlador = new OrdenController();
        try {
            controlador.guardar(comboBoxPrioridad.getSelectedItem().toString(),EstadoModel.EN_PROCESO,cajero,empleadosSelec,cliente,vehiculo,servicios);
        } catch (SQLException ex) {
            Logger.getLogger(ViewCrearOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonCrearOrdenActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
    //Aplicar el controller
    OrdenController orden = new OrdenController();
    orden.guardarServicios(servicios);
    String texto="Cliente: "+cliente+"\nCajero: "+cajero+"\nServicios: ";
    ServicioDAO ser = new ServicioDAO();
    List <ServicioModel> serviciosbd = null;
        try {
            serviciosbd = ser.dameServicios();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCrearOrden.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ViewCrearOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    orden.guardarServicios(serviciosbd);
    for(ServicioModel o: serviciosbd){
        texto = texto+o.getNombre()+" ";
    }
    texto=texto+"\nVehiculo: "+vehiculo+"\nEmpleado/s: ";
    for(EmpleadoModel o: empleadosSelec){
        texto = texto+o.getNombre()+", ";
    }
    texto=texto+"\nCosto: ";
    float costo=orden.costoOrden(empleadosSelec);
    texto=texto+costo;
    areaTextoDatos.setText(texto);
//areaTextoDatos.setText("Servicios:"+servicios+" \nCliente:"+cliente+"\nVehiculo:"+vehiculo+"\nEmpleado/s:"+empleadosSelec+"\n");
    }//GEN-LAST:event_jTabbedPane1FocusGained

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
                    EmpleadoModel cajero = new EmpleadoModel();
                    VehiculoModel v = new VehiculoModel();
                    ClienteModel c=new ClienteModel();
                    List <ServicioModel> servicios = new ArrayList<>();
                    JLabel j = new JLabel();
                    new ViewCrearOrden(cajero,v,servicios,c,j).setVisible(true);
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
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonCrearOrden;
    private javax.swing.JButton botonSacar;
    private javax.swing.JTextField buscadorNombre;
    private javax.swing.JComboBox<String> comboBoxPrioridad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tablaEmpleadosSelec;
    // End of variables declaration//GEN-END:variables
}
