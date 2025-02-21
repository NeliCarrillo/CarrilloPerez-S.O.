/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import EDD.Cola;
import Objetos.Proceso;

/**
 *
 * @author nelsoncarrillo
 */
public class CreacionProceso extends javax.swing.JFrame {

    
    public static Cola colaprocesos = new Cola();
    private String tipot="CPU Bound";
    private int numProcesadores;
    private int duracionCiclo;
    private Simulacion sim;
    private Proceso creado;

    
    /**
     * Creates new form CreacionProceso
     */
    public CreacionProceso() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public CreacionProceso(Simulacion si,int neu, int dur) {
        initComponents();
        this.numProcesadores=neu;
        this.duracionCiclo=dur;
        this.sim=si;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        

    }
    
    
    public void clear(){
        this.nombre.setText("");
        this.prioridad.setText("");
        this.qtyInstrucciones.setText("");
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
        nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        qtyInstrucciones = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        prioridad = new javax.swing.JTextField();
        Finalizar = new javax.swing.JButton();
        tipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 93, 147, -1));

        jLabel1.setFont(new java.awt.Font("Beirut", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre del Proceso:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 99, -1, -1));

        jLabel2.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cantidad de Instrucciones:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 140, -1, -1));

        qtyInstrucciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyInstruccionesActionPerformed(evt);
            }
        });
        jPanel1.add(qtyInstrucciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 134, 147, -1));

        jLabel3.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo de Proceso:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 181, -1, -1));

        save.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        save.setText("+ Añadir");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel1.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 276, 91, -1));

        jLabel4.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Prioridad del Proceso:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 219, -1, -1));

        prioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prioridadActionPerformed(evt);
            }
        });
        jPanel1.add(prioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 213, 147, -1));

        Finalizar.setFont(new java.awt.Font("Beirut", 1, 13)); // NOI18N
        Finalizar.setForeground(new java.awt.Color(255, 255, 255));
        Finalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUIsImages/play-button-icon-png-28.png"))); // NOI18N
        Finalizar.setText("OK");
        Finalizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 0), 2));
        Finalizar.setContentAreaFilled(false);
        Finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinalizarActionPerformed(evt);
            }
        });
        jPanel1.add(Finalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 110, 70));

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPU Bound", "I/O Bound" }));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });
        jPanel1.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 175, 147, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUIsImages/background.jpg"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_nombreActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        String nombrel = nombre.getText();
        int inst = Integer.parseInt(this.qtyInstrucciones.getText());
        int prio = Integer.parseInt(this.prioridad.getText());
        clear(); //OJO
        if("CPU Bound".equals(tipot)){
            creado = new Proceso(nombrel,inst,"CPU Bound",prio);
            System.out.println("Se agregó cpu bound.");
            if(this.sim.empezoYa()){
                this.sim.actualizarListos(creado);
            }else{
                colaprocesos.agregar(creado);
            }
        }else if ("I/O Bound".equals(tipot)){
            if(this.sim.empezoYa()){
                            IOBound detalles = new IOBound(this.sim,this,nombrel,inst,prio);
            }else{
                            IOBound detalles = new IOBound(this,nombrel,inst,prio);
            }
        }
        tipot="CPU Bound";
    }//GEN-LAST:event_saveActionPerformed

    private void qtyInstruccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyInstruccionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyInstruccionesActionPerformed

    private void FinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinalizarActionPerformed
        // TODO add your handling code here:
        if(!this.sim.empezoYa()){
            this.sim.iniciar();
            this.dispose();
        }else{
            this.dispose();
        }
    }//GEN-LAST:event_FinalizarActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        tipot = tipo.getSelectedItem().toString();
    }//GEN-LAST:event_tipoActionPerformed

    private void prioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prioridadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prioridadActionPerformed

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
            java.util.logging.Logger.getLogger(CreacionProceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreacionProceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreacionProceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreacionProceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CreacionProceso().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Finalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField prioridad;
    private javax.swing.JTextField qtyInstrucciones;
    private javax.swing.JButton save;
    private javax.swing.JComboBox<String> tipo;
    // End of variables declaration//GEN-END:variables
}
