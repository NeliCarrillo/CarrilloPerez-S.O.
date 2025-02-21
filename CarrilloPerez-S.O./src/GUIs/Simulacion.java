/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;
import EDD.Cola;
import EDD.Nodo;
import static GUIs.CreacionProceso.colaprocesos;
import Objetos.ManejadorProcesos;
import Objetos.EstadoSimulacion;
import Objetos.Procesador;
import Objetos.Proceso;
import Objetos.Semaforo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author nelsoncarrillo
 */
public final class Simulacion extends javax.swing.JFrame {
    
    Cola colaL = new Cola();
    Cola colaRespaldoListos= new Cola();
    Cola colaT;
    Cola colaB;
    Semaforo semf;
    int cicloreloj;
    Procesador cpu1;
    Procesador cpu2;
    Procesador cpu3;
    ManejadorProcesos bios;
    int numcpu;
    String politica;
    boolean huboCambio=false;

    private Simulacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getPolitica() {
        return politica;
    }

    public void setPolitica(String politica) {
        this.politica = politica;
    }

    public int getCicloreloj() {
        return cicloreloj;
    }

    public void setCicloreloj(int cicloreloj) {
        this.cicloreloj = cicloreloj;
    }
    
    public void sumReloj(int i){
        this.reloj.setText("Ciclos Completos: "+i);
    }
    
    /**
     * Creates new form Simulacion
     * @param i
     */
    public Simulacion(String plt , int i,int d) {
        initComponents();
        this.huboCambio=false;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setPolitica(plt);
        this.numcpu=i;
        this.setCicloreloj(d);
        this.semf= new Semaforo();
        this.textoListos(this.getColaL().print());
        this.listos.setEditable(false);
        this.bloqueados.setEditable(false);
        this.terminados.setEditable(false);
        this.procesador1.setEditable(false);
        this.procesador2.setEditable(false);
        this.procesador3.setEditable(false);
        this.colaL=colaprocesos;
        this.colaT = new Cola();
        this.colaB = new Cola();
    }
    
    public boolean empezoYa(){
        return this.cpu1 != null;
    }
    
    public void iniciar(){
        actualizarListos();
        cargarProcesadores();
    }
    
    public Simulacion(String plt, int d, int i,Cola colalistos, Cola colabloq, Cola colaterm,Proceso en1,Proceso en2, Semaforo semf) {
        initComponents();
        this.huboCambio=false;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setPolitica(plt);
        this.semf=semf;
        this.setCicloreloj(d);
        this.bios= new ManejadorProcesos(this);
        this.numcpu=i;
        this.listos.setEditable(false);
        this.bloqueados.setEditable(false);
        this.terminados.setEditable(false);
        this.procesador1.setEditable(false);
        this.procesador2.setEditable(false);
        this.procesador3.setEditable(false);
        this.colaL=colalistos;
        this.colaT = colaterm;
        this.colaB = colabloq;
        actualizarListos();
        this.textoListos(this.getColaL().print());
        this.actualizarBloqueadosR();
        this.actualizarTerminadosR();
        cpu1 = new Procesador(1,semf,this.getCicloreloj(),this);
        cpu2 = new Procesador(2,semf,this.getCicloreloj(),this);
        cpu1.setProcesoActual(en1);
        cpu2.setProcesoActual(en2);
        bios.start();
        cpu1.start();
        cpu2.start();
    }
    
    public Simulacion(String plt, int d, int i,Cola colalistos, Cola colabloq, Cola colaterm,Proceso en1,Proceso en2, Proceso en3, Semaforo semf) {
        initComponents();
        this.huboCambio=false;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setPolitica(plt);
        this.semf=semf;
        this.setCicloreloj(d);
        this.bios= new ManejadorProcesos(this);
        this.numcpu=i;
        this.listos.setEditable(false);
        this.bloqueados.setEditable(false);
        this.terminados.setEditable(false);
        this.procesador1.setEditable(false);
        this.procesador2.setEditable(false);
        this.procesador3.setEditable(false);
        this.colaL=colalistos;
        this.colaT = colaterm;
        this.colaB = colabloq;
        actualizarListos();
        this.textoListos(this.getColaL().print());
        this.actualizarBloqueadosR();
        this.actualizarTerminadosR();
        cpu1 = new Procesador(1,semf,4000,this);
        cpu2 = new Procesador(2,semf,4000,this);
        cpu3 = new Procesador(3,semf,4000,this);
        cpu1.setProcesoActual(en1);
        cpu2.setProcesoActual(en2);
        cpu3.setProcesoActual(en3);
        bios.start();
        cpu1.start();
        cpu2.start();
        cpu3.start();
    }
    
    Simulacion instancia = this;
    
    public void setnNumCpu(int ii){
        this.numcpu=ii;
    }


    public synchronized void actualizarListos(){
        listos.setText(colaL.print());
    }
    
    public synchronized void actualizarListos(Cola L){
        listos.setText(L.print());
    }
    
    public Cola getColaL(){
        return this.colaL;
        
    }
    
    public Cola getColaRespaldoListos(){
        return this.colaRespaldoListos;
        
    }
    
    public void aggListos(Proceso p){
        colaL.agregar(p);
        listos.setText(colaL.print());
        bloqueados.setText(colaB.print());
    }
    
    
    public void irInterrumpiendo(Proceso p){
        switch (p.getIdProcesador()) {
            case 1:
                cpu1.interrumpir();
                System.out.println("procesador 1 notificado salida");
                break;
            case 2:
                cpu2.interrumpir();
                System.out.println("procesador 2 notificado salida");
                break;
            case 3:
                cpu3.interrumpir();
                System.out.println("procesador 3 notificado salida");
                break;
            default:
                break;
        }
    }
    
    public synchronized void actualizarTerminados(Proceso tu){
        Cola copia = this.colaT.copiar();
        copia.agregar(tu);
        this.terminados.setText(copia.print());
        this.colaT=copia;
    }
    
    public void actualizarTerminadosR(){
        terminados.setText(colaT.print());
    }
    
    
    public synchronized void actualizarListos(Proceso tu){
        Cola copia = this.colaL.copiar();
        copia.agregar(tu);
        this.listos.setText(copia.print());
        this.colaL=copia;
    }
    
    public synchronized void aggRespaldo(Proceso ele){
        this.colaRespaldoListos.agregar(ele);
    }
    public void actualizarBloqueados(Proceso t){
        this.colaB.agregar(t);
        this.bloqueados.setText(colaB.print());
    }
    
    public void actualizarBloqueadosR(){
        bloqueados.setText(colaB.print());
    }
    
    public synchronized void unirColas() {
        // Mientras la cola de respaldo no esté vacía
        while (!colaRespaldoListos.estaVacia()) {
            // Extraer el primer elemento de la cola de respaldo
            Proceso proceso = colaRespaldoListos.obtenerProceso();

            // Agregar el elemento a la cola principal (colaL)
            colaL.agregar(proceso);
        }
        System.out.println(colaL.print());

        // Al final, la cola de respaldo quedará vacía
        // y colaL contendrá todos los elementos de ambas colas
    }
//hola
    
    public synchronized void cambio(){
        this.huboCambio=true;
    }
    
    public boolean hayCambioPend(){
        return this.huboCambio==true;
    }
    
    public synchronized void Fincambio(){
        this.huboCambio=false;
    }
    
    public Cola getBloqueados(){
        return colaB;
    }
    
    
    public void actualizarCiclo(){
        if(this.numcpu>=2){
        cpu1.setCicloReloj(cicloreloj);
        cpu2.setCicloReloj(cicloreloj);
        }
        if(this.numcpu==3){
        cpu3.setCicloReloj(cicloreloj);
        }
    }
    
    private void cargarProcesadores(){
        System.out.println(numcpu);
        if(this.numcpu==3){
            cpu1 = new Procesador(1,semf,this.getCicloreloj(),this);
            cpu1.start();
            cpu2 = new Procesador(2,semf,this.getCicloreloj(),this);
            cpu2.start();
            cpu3 = new Procesador(3,semf,this.getCicloreloj(),this);
            cpu3.start();
        }else if (this.numcpu==2){
            cpu1 = new Procesador(1,semf,this.getCicloreloj(),this);
            cpu1.start();
            cpu2 = new Procesador(2,semf,this.getCicloreloj(),this);
            cpu2.start();
        }
        this.bios=new ManejadorProcesos(this);
        bios.start();
    }
    
    public synchronized Simulacion getInstancia() {
        return instancia;
    }
    
    public synchronized Cola getColaListos(){
        return this.colaL;
    }
    
    public void actualizarTextoArea(String texto,int id) {
        // Asegurarse de que la actualización se realice en el hilo de la interfaz gráfica
        if(id==1){
            javax.swing.SwingUtilities.invokeLater(() -> {
                procesador1.setText(texto); // textAreaProcesador es tu JTextArea
            });
        }else if(id==2){
            javax.swing.SwingUtilities.invokeLater(() -> {
                procesador2.setText(texto); // textAreaProcesador es tu JTextArea
            });
        }else{
            javax.swing.SwingUtilities.invokeLater(() -> {
                procesador3.setText(texto); // textAreaProcesador es tu JTextArea
            });
        }
        
    }
    
    public void detenerHilos() {
        if (cpu1 != null) cpu1.detener();
        if (cpu2 != null) cpu2.detener();
        if (cpu3 != null) cpu3.detener();
        if (bios != null) bios.detener();
    }
    
    public void textoListos(String t){
        this.listos.setText(t);
    }
    
    public void unirColaListos(){
        if(!this.colaRespaldoListos.estaVacia()){
            while(!this.colaRespaldoListos.estaVacia()){
                Proceso ele = this.colaRespaldoListos.obtenerProceso();
                this.colaL.agregar(ele);
            }
        }
    }
    
    public void aggListosppio(Proceso p){
        colaL.agregarPpio(p);
        listos.setText(colaL.print());
    }
    
    
    public void guardarEstado(String rutaArchivo) {
        /*detenerHilos(); // Detener los hilos antes de guardar
        unirColaListos();
        if(this.numcpu==3){
            Proceso p1 = cpu1.getProcesoActual();
            Proceso p2 = cpu2.getProcesoActual();
            Proceso p3 = cpu3.getProcesoActual();

            EstadoSimulacion estado = new EstadoSimulacion(this.getPolitica(),this.semf,this.colaL, this.colaT, this.colaB, this.cicloreloj,this.numcpu,p1,p2,p3);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter(rutaArchivo)) {
                gson.toJson(estado, writer); // Serializar el objeto contenedor
                System.out.println("Estado de la simulación guardado en: " + rutaArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Proceso p1 = cpu1.getProcesoActual();
            Proceso p2 = cpu2.getProcesoActual();

            EstadoSimulacion estado = new EstadoSimulacion(this.getPolitica(),this.semf, this.colaL, this.colaT, this.colaB, this.cicloreloj,this.numcpu,p1,p2);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter(rutaArchivo)) {
                gson.toJson(estado, writer); // Serializar el objeto contenedor
                System.out.println("Estado de la simulación guardado en: " + rutaArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        EstadoSimulacion estado = new EstadoSimulacion(this.numcpu,this.cicloreloj);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(estado, writer); // Serializar el objeto contenedor
            System.out.println("Estado de la simulación guardado en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bloqueados = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listos = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        terminados = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        procesador1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        procesador2 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        procesador3 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        reloj = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bloqueados.setColumns(20);
        bloqueados.setRows(5);
        jScrollPane1.setViewportView(bloqueados);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 252, 293, 150));

        listos.setColumns(20);
        listos.setRows(5);
        jScrollPane2.setViewportView(listos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 75, 293, 150));

        terminados.setColumns(20);
        terminados.setRows(5);
        jScrollPane3.setViewportView(terminados);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 436, 293, 150));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cola Procesos Listos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 52, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cola Procesos Bloqueados");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 231, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cola Procesos Terminados");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 413, -1, -1));

        panel.setBackground(new java.awt.Color(204, 255, 204));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        procesador1.setColumns(20);
        procesador1.setRows(5);
        jScrollPane5.setViewportView(procesador1);

        panel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 75, 218, 117));

        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 38, 246, 270));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        procesador2.setColumns(20);
        procesador2.setRows(5);
        jScrollPane6.setViewportView(procesador2);

        jPanel3.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 78, -1, 119));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 38, 251, 270));

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        procesador3.setColumns(20);
        procesador3.setRows(5);
        jScrollPane4.setViewportView(procesador3);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 67, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 250, 250));

        jLabel4.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Procesador 1");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 15, 90, -1));

        jLabel5.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Procesador 2");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 15, -1, -1));

        jLabel6.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Procesador 3");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jButton1.setText("Configurar Planificación");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 355, 251, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Algoritmo Actual:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 332, -1, -1));

        jButton2.setText("Configurar Ciclo de Reloj");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 446, 251, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Número de Ciclo:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 423, -1, -1));

        reloj.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        reloj.setForeground(new java.awt.Color(255, 255, 255));
        reloj.setText("Ciclos Completos: 0");
        jPanel1.add(reloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 501, -1, -1));

        exit.setBackground(new java.awt.Color(153, 0, 0));
        exit.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setText("X");
        exit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 6));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 80, -1));

        jButton3.setFont(new java.awt.Font("Beirut", 0, 13)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUIsImages/addprocess.png"))); // NOI18N
        jButton3.setText("Añadir Proceso");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 190, 50));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUIsImages/1600-x-900-black-m14o1oxj88ccjrbs-2.jpg"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Politicas cpol = new Politicas(this);
        cpol.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        CicloReloj cambio = new CicloReloj(this);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        this.guardarEstado("test//simulacion.json");
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        CreacionProceso n = new CreacionProceso(this,this.numcpu,this.cicloreloj);
        n.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea bloqueados;
    private javax.swing.JButton exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea listos;
    private javax.swing.JPanel panel;
    private javax.swing.JTextArea procesador1;
    private javax.swing.JTextArea procesador2;
    private javax.swing.JTextArea procesador3;
    private javax.swing.JLabel reloj;
    private javax.swing.JTextArea terminados;
    // End of variables declaration//GEN-END:variables
}
