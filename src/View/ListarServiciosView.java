/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ServicioController;
import Model.EmpleadoModel;
import Model.ServicioModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;

/**
 *
 * @author YOGA
 */
public class ListarServiciosView extends javax.swing.JFrame {
    private List<ServicioModel> servicios;
    private TablaNoEditable modeloGenerico;
    /**
     * Creates new form ListarServiciosView
     */
    
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
    
        
    public ListarServiciosView() {
        initComponents();
        ServicioController controladorS = new ServicioController();
        servicios = controladorS.serviciosBD();
        crearTablaServicios(servicios,modeloGenerico,tablaServicios);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabFinalizarOrden = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        buscadorNombreServicio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));

        tabFinalizarOrden.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabFinalizarOrdenFocusGained(evt);
            }
        });

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
        tablaServicios.setSelectionModel(new ForcedListSelectionModel());
        jScrollPane9.setViewportView(tablaServicios);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Servicios disponibles");

        jLabel7.setText("Busqueda por nombre:");

        buscadorNombreServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscadorNombreServicioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorNombreServicioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscadorNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 470, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
        );

        tabFinalizarOrden.addTab("Seleccionar servicios", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1056, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabFinalizarOrden)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabFinalizarOrden)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscadorNombreServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreServicioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorNombreServicioKeyPressed

    private void buscadorNombreServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorNombreServicioKeyReleased
        List a=filtrarNombreServicio(buscadorNombreServicio.getText(),servicios);
        crearTablaServicios(a,modeloGenerico,tablaServicios);
    }//GEN-LAST:event_buscadorNombreServicioKeyReleased

    private void tabFinalizarOrdenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabFinalizarOrdenFocusGained

    }//GEN-LAST:event_tabFinalizarOrdenFocusGained



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscadorNombreServicio;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane tabFinalizarOrden;
    private javax.swing.JTable tablaServicios;
    // End of variables declaration//GEN-END:variables
}
