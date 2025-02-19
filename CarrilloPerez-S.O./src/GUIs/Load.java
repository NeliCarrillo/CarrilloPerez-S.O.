/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import EDD.Cola;
import Objetos.EstadoSimulacion;
import Objetos.Semaforo;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author nelsoncarrillo
 */
public class Load extends javax.swing.JFrame {
    
    private CreacionProceso creacion;

    /**
     * Creates new form Load
     */
    public Load() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.creacion=new CreacionProceso();
    }
    
    public Simulacion restablecerEstado(String rutaArchivo) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(rutaArchivo)) {
            // Deserializar el archivo JSON al objeto EstadoSimulacion
            EstadoSimulacion estado = gson.fromJson(reader, EstadoSimulacion.class);

            // Restaurar los valores en la simulación
            Semaforo semaf = estado.getSemaforo();
            Cola colaL = estado.getColaL();
            Cola colaT = estado.getColaT();
            Cola colaB = estado.getColaB();
            int cicloreloj = estado.getCicloReloj();
            int numcpu = estado.getNumProcesadores();
            
            if(numcpu==2){
                Simulacion sim = new Simulacion(cicloreloj, numcpu,colaL,colaB,colaT,estado.getEn1(),estado.getEn2(),semaf);
                return sim;
            }else{
                Simulacion sim = new Simulacion(cicloreloj,numcpu,colaL,colaB,colaT,estado.getEn1(),estado.getEn2(),estado.getEn3(),semaf);
                return sim;
            }

            
            /*if (this.numcpu >= 2) {
                this.cpu1.setProcesoActual(estado.getEn1());
                this.cpu2.setProcesoActual(estado.getEn2());
            }
            if (this.numcpu >= 3) {
                this.cpu3.setProcesoActual(estado.getEn3());
            }*/
            //this.actualizarListos();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadTXT = new javax.swing.JButton();
        loadP = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loadTXT.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        loadTXT.setText("Cargar Sólo Procesos Guardados");
        loadTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTXTActionPerformed(evt);
            }
        });
        getContentPane().add(loadTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 310, 44));

        loadP.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        loadP.setText("Cargar Nuevos Procesos a los Guardados");
        getContentPane().add(loadP, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 310, 45));

        jButton1.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        jButton1.setText("Iniciar con Nuevos Procesos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 270, 300, 45));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nelson Carrillo");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Luis Pérez");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Beirut", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("UNIVERSIDAD METROPOLITANA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUIsImages/background.jpg"))); // NOI18N
        jLabel1.setText("                  ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadTXTActionPerformed
        // TODO add your handling code here:
        Simulacion sim = this.restablecerEstado("test//simulacion.json");
        //sim.restablecerEstado("test//simulacion.json");
        sim.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_loadTXTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        NumProcesadores n = new NumProcesadores(this);
        n.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Load.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Load.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Load.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Load.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Load().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton loadP;
    private javax.swing.JButton loadTXT;
    // End of variables declaration//GEN-END:variables
}
