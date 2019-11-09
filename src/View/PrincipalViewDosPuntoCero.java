package View;

import Controller.EmpleadoController;
import Model.EmpleadoModel;
import Model.ResponsabilidadModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YOGA
 */
public class PrincipalViewDosPuntoCero extends javax.swing.JFrame {
    private EmpleadoModel cajero;
    /**
     * Creates new form PrincipalViewDosPuntoCero
     */
    public PrincipalViewDosPuntoCero() throws SQLException, ParseException, CloneNotSupportedException {
        initComponents();
        Collection c = null;
        cajero = new EmpleadoModel();
        EmpleadoController controlador = new EmpleadoController();
        java.util.List<EmpleadoModel> empleados;
        empleados=controlador.empleadosBD();
        EmpleadoModel cajeroEncontrado = null;
        for(EmpleadoModel o: empleados){
            if(o.getResponsabilidad()==ResponsabilidadModel.CAJERO)
                cajeroEncontrado=o;
        }
        //Iterator iter = empleados.iterator();
       // EmpleadoModel first = (EmpleadoModel) iter.next();
        cajero = cajeroEncontrado;
        System.out.println(cajero);
        
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
        rankingMensualBoton = new javax.swing.JButton();
        listarServiciosBoton = new javax.swing.JButton();
        listarVentasBoton = new javax.swing.JButton();
        crearOrdenBoton = new javax.swing.JButton();
        registrarVentaBoton = new javax.swing.JButton();
        verOrdenesBoton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(480, 300));
        setResizable(false);
        setSize(new java.awt.Dimension(520, 300));

        jPanel1.setMaximumSize(new java.awt.Dimension(900, 900));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel1.setRequestFocusEnabled(false);

        rankingMensualBoton.setText("Ranking mensual de ventas");
        rankingMensualBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankingMensualBotonActionPerformed(evt);
            }
        });

        listarServiciosBoton.setText("Listar servicios");
        listarServiciosBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarServiciosBotonActionPerformed(evt);
            }
        });

        listarVentasBoton.setText("Listar ventas");

        crearOrdenBoton.setText("Crear orden de trabajo");
        crearOrdenBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearOrdenBotonActionPerformed(evt);
            }
        });

        registrarVentaBoton.setText("Registrar Venta");

        verOrdenesBoton.setText("Ver ordenes de trabajo");

        jLabel1.setText("Registrar datos:");

        jLabel2.setText("Listar datos:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rankingMensualBoton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(registrarVentaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(69, 69, 69)
                                    .addComponent(crearOrdenBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(377, 377, 377)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(listarServiciosBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(listarVentasBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(174, 174, 174))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(verOrdenesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rankingMensualBoton)
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrarVentaBoton)
                    .addComponent(crearOrdenBoton))
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listarServiciosBoton)
                    .addComponent(listarVentasBoton))
                .addGap(18, 18, 18)
                .addComponent(verOrdenesBoton)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rankingMensualBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankingMensualBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rankingMensualBotonActionPerformed

    private void crearOrdenBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearOrdenBotonActionPerformed
        JFrame estaVentana = this;
        try {
            ViewCrearOrden ventana;
            ventana = new ViewCrearOrden(cajero,this);
            WindowListener exitListener = new WindowAdapter() {

    @Override
    public void windowClosing(WindowEvent e) {
                ventana.setVisible(false);
                estaVentana.setVisible(true);
                    }
                };
            ventana.addWindowListener(exitListener);
            this.setVisible(false);
            ventana.setAlwaysOnTop(true);
            ventana.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_crearOrdenBotonActionPerformed

    private void listarServiciosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarServiciosBotonActionPerformed
        JFrame estaVentana = this;
        ListarServiciosView ventana;
        ventana = new ListarServiciosView();
        WindowListener exitListener = new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                ventana.setVisible(false);
                estaVentana.setVisible(true);
            }
        };
        ventana.addWindowListener(exitListener);
        this.setVisible(false);
        ventana.setAlwaysOnTop(true);
        ventana.setVisible(true);
    }//GEN-LAST:event_listarServiciosBotonActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PrincipalViewDosPuntoCero().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(PrincipalViewDosPuntoCero.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearOrdenBoton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton listarServiciosBoton;
    private javax.swing.JButton listarVentasBoton;
    private javax.swing.JButton rankingMensualBoton;
    private javax.swing.JButton registrarVentaBoton;
    private javax.swing.JButton verOrdenesBoton;
    // End of variables declaration//GEN-END:variables
}
