package Objetos;
import GUIs.Simulacion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nelsoncarrillo
 */
public class Procesador extends Thread {
    private int id; // Identificador único del procesador
    private Proceso procesoActual; // Proceso que está ejecutando actualmente
    private Semaforo semaforo; // Semáforo personalizado para garantizar exclusión mutua
    private volatile boolean activo; // Indica si el procesador está activo
    private int ciclosEjecutados; // Contador de ciclos ejecutados por este procesador
    static int cicloReloj; // Duración del ciclo de reloj en milisegundos
    private Simulacion simulacion;
    private boolean interrupcionPendiente=false;
    private boolean llegoNuevo = false;

    public Procesador(int id, Semaforo semaforo, int cicloReloj,Simulacion simulacion) {
        this.id = id;
        this.semaforo = semaforo;
        this.activo = true;
        this.ciclosEjecutados = 0;
        Procesador.cicloReloj = cicloReloj; 
        this.simulacion = simulacion;
    }

    @Override
    public void run() {
        while (activo) {
            try {
                if(procesoActual==null){
                    semaforo.adquirir(); // Bloqueo para acceder a la cola de listos
                    if (!this.simulacion.getColaL().estaVacia()) {
                        if("FCFS".equals(this.simulacion.getPolitica())||"RR".equals(this.simulacion.getPolitica())){
                            procesoActual = this.simulacion.getColaL().obtenerProceso(); // Obtener el siguiente proceso
                            procesoActual.setEstado("Running");
                            this.simulacion.textoListos(this.simulacion.getColaL().print());
                        }else if("SPN".equals(this.simulacion.getPolitica())||"SRT".equals(this.simulacion.getPolitica())){
                            procesoActual = this.simulacion.getColaL().eliminarMasCorto(); // Obtener el siguiente proceso
                            procesoActual.setEstado("Running");
                            this.simulacion.textoListos(this.simulacion.getColaL().print());
                        }else if("HRRN".equals(this.simulacion.getPolitica())){
                            procesoActual = this.simulacion.getColaL().eliminarMayorTasaRespuesta(); // Obtener el siguiente proceso
                            procesoActual.setEstado("Running");
                            this.simulacion.textoListos(this.simulacion.getColaL().print());
                        }
                        
                    }
                    semaforo.liberar(); // Liberar el semáforo
                }
                if (procesoActual != null) {
                    // Simular la ejecución del proceso
                    ejecutarProceso(procesoActual);
                } else {
                    // Si no hay procesos, el procesador espera un tiempo
                    if (interrupcionPendiente) 
                        manejarInterrupcion();
                    this.simulacion.getInstancia().actualizarTextoArea("Espera activa\nProcesador tomado por \nel Sistema Operativo...",this.id);
                    Thread.sleep(cicloReloj); // Espera activa simulada
                    ciclosEjecutados++;
                    if(this.simulacion.hayCambioPend())
                        this.gestionarCambio(procesoActual);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    private void ejecutarProceso(Proceso proceso) {
        if("RR".equals(this.simulacion.getPolitica())){
            int ciclosPasados = 0;
            if("CPU Bound".equals(proceso.getTipo())||proceso.desbloqueada()){
                while (proceso.getNumeroInstrucciones() > 0 && !this.simulacion.hayCambioPend()&&ciclosPasados <5) {
                    try {
                        if (interrupcionPendiente) 
                            manejarInterrupcion();
                        this.simulacion.getInstancia().actualizarTextoArea("ID: "+ this.procesoActual.getId()+"\nSTATUS: "+this.procesoActual.getEstado()+"\nNombre: "+this.procesoActual.getNombre()+"\nPC: "+this.procesoActual.getPC()+", MAR: "+this.procesoActual.getMAR(),this.id);
                        Thread.sleep(cicloReloj); // Simular la ejecución de una instrucción (ajustado al ciclo de reloj)
                        proceso.setNumeroInstrucciones(proceso.getNumeroInstrucciones()-1);
                        ciclosEjecutados++;
                        
                        this.procesoActual.setPC(this.procesoActual.getPC()+1);
                        System.out.println("Procesador " + id + " ejecutó una instrucción. Instrucciones restantes: " + proceso.getNumeroInstrucciones());
                        ciclosPasados++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(procesoActual.getNumeroInstrucciones()==0){
                    System.out.println("Procesador " + id + " terminó el proceso: " + proceso.getNombre());
                    this.simulacion.getInstancia().actualizarTextoArea("Eligiendo proceso\nProcesador tomado por \nel Sistema Operativo...",this.id);
                    procesoActual.setEstado("Finished");
                    this.simulacion.actualizarTerminados(this.procesoActual);
                    procesoActual = null; // Liberar el procesador
                    try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                        Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(this.simulacion.hayCambioPend()){
                    this.gestionarCambio(procesoActual);
                }
                if(procesoActual!=null&&ciclosPasados==5&&procesoActual.getNumeroInstrucciones()!=0){
                    this.simulacion.getInstancia().actualizarTextoArea("Exceso de quantum\nProcesador tomado por \nel Sistema Operativo...",this.id);
                    procesoActual.setEstado("Ready");
                    this.simulacion.aggListos(proceso);
                    procesoActual=null;
                    try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                        Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                if(!proceso.desbloqueada()){
                    proceso.setIdProcesador(this.id);
                    while (proceso.NoHaSucedidoInterrupcion() && !this.simulacion.hayCambioPend()&&ciclosPasados <5) {
                        try {
                            this.simulacion.getInstancia().actualizarTextoArea("ID: "+ this.procesoActual.getId()+"\nSTATUS: "+this.procesoActual.getEstado()+"\nNombre: "+this.procesoActual.getNombre()+"\nPC: "+this.procesoActual.getPC()+", MAR: "+this.procesoActual.getMAR(),this.id);
                            Thread.sleep(cicloReloj); // Simular la ejecución de una instrucción (ajustado al ciclo de reloj)
                            proceso.setNumeroInstrucciones(proceso.getNumeroInstrucciones()-1);
                            ciclosEjecutados++;
                            ciclosPasados++;
                            this.procesoActual.setPC(this.procesoActual.getPC()+1);
                            System.out.println("Procesador " + id + " ejecutó una instrucción. Instrucciones restantes: " + proceso.getNumeroInstrucciones());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(procesoActual!=null&&ciclosPasados==5&&procesoActual.getCiclosParaGenerarExcepcion()!=5){
                        this.simulacion.aggListos(proceso);
                        this.simulacion.getInstancia().actualizarTextoArea("Exceso de quantum\nProcesador tomado por \nel Sistema Operativo...",this.id);
                        procesoActual.setEstado("Ready");
                        try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                            Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        procesoActual=null;
                    }else{
                        //System.out.println("Procesador " + id + " terminó el proceso: " + proceso.getNombre());
                        this.simulacion.getInstancia().actualizarTextoArea("Enviando a bloqueados\nProcesador tomado por \nel Sistema Operativo...",this.id);
                        procesoActual.setEstado("Blocked");
                        this.simulacion.actualizarBloqueados(procesoActual);
                        procesoActual = null; // Liberar el procesador
                        try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                            Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(this.simulacion.hayCambioPend()){
                        this.gestionarCambio(procesoActual);
                    }
                }
            }
        }else if ("FCFS".equals(this.simulacion.getPolitica())||"SPN".equals(this.simulacion.getPolitica())||"HRRN".equals(this.simulacion.getPolitica())){
            if("CPU Bound".equals(proceso.getTipo())||proceso.desbloqueada()){
                //System.out.println("Procesador " + id + " ejecutando proceso: " + proceso.getNombre());
                while (proceso.getNumeroInstrucciones() > 0 && !this.simulacion.hayCambioPend()) {
                    try {
                        if (interrupcionPendiente) 
                            manejarInterrupcion();
                        this.simulacion.getInstancia().actualizarTextoArea("ID: "+ this.procesoActual.getId()+"\nSTATUS: "+this.procesoActual.getEstado()+"\nNombre: "+this.procesoActual.getNombre()+"\nPC: "+this.procesoActual.getPC()+", MAR: "+this.procesoActual.getMAR(),this.id);
                        Thread.sleep(cicloReloj); // Simular la ejecución de una instrucción (ajustado al ciclo de reloj)
                        proceso.setNumeroInstrucciones(proceso.getNumeroInstrucciones()-1);
                        ciclosEjecutados++;
                        this.procesoActual.setPC(this.procesoActual.getPC()+1);
                        System.out.println("Procesador " + id + " ejecutó una instrucción. Instrucciones restantes: " + proceso.getNumeroInstrucciones());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(!this.simulacion.hayCambioPend()){
                    System.out.println("Procesador " + id + " terminó el proceso: " + proceso.getNombre());
                    this.simulacion.getInstancia().actualizarTextoArea("Eligiendo proceso\nProcesador tomado por \nel Sistema Operativo...",this.id);
                    procesoActual.setEstado("Finished");
                    this.simulacion.actualizarTerminados(procesoActual);
                    procesoActual = null; // Liberar el procesador
                    try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                    Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
                if(this.simulacion.hayCambioPend()){
                        this.gestionarCambio(procesoActual);
                }
                
            }else{
                if(!proceso.desbloqueada()){
                    proceso.setIdProcesador(this.id);
                    while (proceso.NoHaSucedidoInterrupcion() && !this.simulacion.hayCambioPend()) {
                        try {
                            if (interrupcionPendiente) 
                                manejarInterrupcion();
                            this.simulacion.getInstancia().actualizarTextoArea("ID: "+ this.procesoActual.getId()+"\nSTATUS: "+this.procesoActual.getEstado()+"\nNombre: "+this.procesoActual.getNombre()+"\nPC: "+this.procesoActual.getPC()+", MAR: "+this.procesoActual.getMAR(),this.id);
                            Thread.sleep(cicloReloj); // Simular la ejecución de una instrucción (ajustado al ciclo de reloj)
                            proceso.setNumeroInstrucciones(proceso.getNumeroInstrucciones()-1);
                            ciclosEjecutados++;
                            this.procesoActual.setPC(this.procesoActual.getPC()+1);
                            System.out.println("Procesador " + id + " ejecutó una instrucción. Instrucciones restantes: " + proceso.getNumeroInstrucciones());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(!this.simulacion.hayCambioPend()){
                        this.simulacion.getInstancia().actualizarTextoArea("Enviando a bloqueados\nProcesador tomado por \nel Sistema Operativo...",this.id);
                        procesoActual.setEstado("Blocked");
                        this.simulacion.actualizarBloqueados(procesoActual);
                        procesoActual = null; // Liberar el procesador
                        try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                        Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }
                    if(this.simulacion.hayCambioPend()){
                        this.gestionarCambio(procesoActual);
                    }
                    
                }
            }
        }else{ //SI ES SRT
            if("CPU Bound".equals(proceso.getTipo())||proceso.desbloqueada()){
                //System.out.println("Procesador " + id + " ejecutando proceso: " + proceso.getNombre());
                while (llegoNuevo==false&&proceso.getNumeroInstrucciones() > 0 && !this.simulacion.hayCambioPend()) {
                    try {
                        if (interrupcionPendiente) 
                            manejarInterrupcion();
                        this.simulacion.getInstancia().actualizarTextoArea("ID: "+ this.procesoActual.getId()+"\nSTATUS: "+this.procesoActual.getEstado()+"\nNombre: "+this.procesoActual.getNombre()+"\nPC: "+this.procesoActual.getPC()+", MAR: "+this.procesoActual.getMAR(),this.id);
                        Thread.sleep(cicloReloj); // Simular la ejecución de una instrucción (ajustado al ciclo de reloj)
                        proceso.setNumeroInstrucciones(proceso.getNumeroInstrucciones()-1);
                        ciclosEjecutados++;
                        this.procesoActual.setPC(this.procesoActual.getPC()+1);
                        System.out.println("Procesador " + id + " ejecutó una instrucción. Instrucciones restantes: " + proceso.getNumeroInstrucciones());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(!this.simulacion.hayCambioPend()&&llegoNuevo==false){
                    System.out.println("Procesador " + id + " terminó el proceso: " + proceso.getNombre());
                    this.simulacion.getInstancia().actualizarTextoArea("Eligiendo proceso\nProcesador tomado por \nel Sistema Operativo...",this.id);
                    procesoActual.setEstado("Finished");
                    this.simulacion.actualizarTerminados(procesoActual);
                    procesoActual = null; // Liberar el procesador
                    try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                    Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
                if(this.simulacion.hayCambioPend()){
                        this.gestionarCambio(procesoActual);
                }
                if(llegoNuevo){
                                semaforo.adquirir();
                                verificar();
                                semaforo.liberar();
                            }
                
            }else{ 
                if(!proceso.desbloqueada()){
                    proceso.setIdProcesador(this.id);
                    while (false==llegoNuevo&&proceso.NoHaSucedidoInterrupcion() && !this.simulacion.hayCambioPend()) {
                        try {
                            if (interrupcionPendiente) 
                                manejarInterrupcion();
                            this.simulacion.getInstancia().actualizarTextoArea("ID: "+ this.procesoActual.getId()+"\nSTATUS: "+this.procesoActual.getEstado()+"\nNombre: "+this.procesoActual.getNombre()+"\nPC: "+this.procesoActual.getPC()+", MAR: "+this.procesoActual.getMAR(),this.id);
                            Thread.sleep(cicloReloj); // Simular la ejecución de una instrucción (ajustado al ciclo de reloj)
                            proceso.setNumeroInstrucciones(proceso.getNumeroInstrucciones()-1);
                            ciclosEjecutados++;
                            this.procesoActual.setPC(this.procesoActual.getPC()+1);
                            System.out.println("Procesador " + id + " ejecutó una instrucción. Instrucciones restantes: " + proceso.getNumeroInstrucciones());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(!this.simulacion.hayCambioPend()&&llegoNuevo==false){
                        this.simulacion.getInstancia().actualizarTextoArea("Enviando a bloqueados\nProcesador tomado por \nel Sistema Operativo...",this.id);
                        procesoActual.setEstado("Blocked");
                        this.simulacion.actualizarBloqueados(procesoActual);
                        procesoActual = null; // Liberar el procesador
                        try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                        Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }
                    if(this.simulacion.hayCambioPend()){
                        this.gestionarCambio(procesoActual);
                    }
                    if(llegoNuevo){
                                semaforo.adquirir();
                                verificar();
                                semaforo.liberar();
                            }
                    
                }
            }
        }
            
    }

    public void detener() {
        this.activo = false;
    }
    
    private void verificar(){
        if(this.simulacion.getColaL().getFinal().getElemento().getNumeroInstrucciones()<procesoActual.getNumeroInstrucciones()){
            Proceso aux = procesoActual;
            procesoActual = this.simulacion.getColaL().obtenerUltimo();
            this.simulacion.aggListos(aux);
        }
        this.simulacion.getInstancia().actualizarTextoArea("SRT verifica\nInterrupción manejada por el S.O en el procesador " + id, this.id);

        try {
            Thread.sleep(cicloReloj); // Simular el tiempo de manejo de la interrupción
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.llegoNuevo=false;
    }
    public int getCiclosEjecutados() {
        return ciclosEjecutados;
    }

    public boolean estaActivo() {
        return activo;
    }
    
    public void seAgrego(){
        this.llegoNuevo=true;
    }

    public int getCicloReloj() {
        return cicloReloj;
    }
    
    private void manejarInterrupcion() {
        System.out.println("Procesador " + id + " manejando interrupción...");
        this.simulacion.getInstancia().actualizarTextoArea("Interrupción manejada por el S.O en el procesador " + id, this.id);

        try {
            Thread.sleep(cicloReloj); // Simular el tiempo de manejo de la interrupción
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        interrupcionPendiente = false; // Reiniciar la bandera de interrupción
    }
    
    public void interrumpir(){
        this.interrupcionPendiente=true;
    }

    public void setProcesoActual(Proceso procesoActual) {
        this.procesoActual = procesoActual;
    }

    public synchronized Proceso getProcesoActual() {
        return procesoActual;
    }
    
    public synchronized void setCicloReloj(int cicloReloj) {
        this.cicloReloj = cicloReloj; // Permitir cambiar el ciclo de reloj dinámicamente
        System.out.println("Procesador " + id + ": Ciclo de reloj actualizado a " + cicloReloj + " ms.");
    }
    
    public void gestionarCambio(Proceso proceso){
        try {
            if(procesoActual!=null){
            this.simulacion.getInstancia().actualizarTextoArea("Interrupción del SO\nCambio de política",this.id);
            procesoActual.setEstado("Ready");
            semaforo.adquirir();
            this.simulacion.aggListosppio(proceso);
            procesoActual=null;
            semaforo.liberar();
            Thread.sleep(cicloReloj);
            this.simulacion.Fincambio();}
        } catch (InterruptedException ex) {
            Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
}