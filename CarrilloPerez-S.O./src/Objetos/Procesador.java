package Objetos;
import EDD.Cola;
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
    private Cola colaListos; // Cola de procesos listos compartida
    private Semaforo semaforo; // Semáforo personalizado para garantizar exclusión mutua
    private boolean activo; // Indica si el procesador está activo
    private int ciclosEjecutados; // Contador de ciclos ejecutados por este procesador
    static int cicloReloj; // Duración del ciclo de reloj en milisegundos
    private Simulacion simulacion;
    private boolean interrupcionPendiente=false;

    public Procesador(int id, Cola colaListos, Semaforo semaforo, int cicloReloj,Simulacion simulacion) {
        this.id = id;
        this.colaListos = colaListos;
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
                // Intentar obtener un proceso de la cola de listos
                semaforo.adquirir(); // Bloqueo para acceder a la cola de listos
                if (!colaListos.estaVacia()) {
                    procesoActual = colaListos.obtenerProceso(); // Obtener el siguiente proceso
                    procesoActual.setEstado("Running");
                    this.simulacion.actualizarListos();
                }
                semaforo.liberar(); // Liberar el semáforo

                if (procesoActual != null) {
                    // Simular la ejecución del proceso
                    ejecutarProceso(procesoActual);
                } else {
                    // Si no hay procesos, el procesador espera un tiempo
                    Thread.sleep(cicloReloj); // Espera activa simulada
                    ciclosEjecutados++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    private void ejecutarProceso(Proceso proceso) {
        if("CPU Bound".equals(proceso.getTipo())||proceso.desbloqueada()){
            System.out.println("Procesador " + id + " ejecutando proceso: " + proceso.getNombre());
            while (proceso.getNumeroInstrucciones() > 0) {
                try {
                    if (interrupcionPendiente) 
                        manejarInterrupcion();
                    this.simulacion.getInstancia().actualizarTextoArea("ID: "+ this.procesoActual.getId()+", STATUS: "+this.procesoActual.getEstado()+", Nombre: "+this.procesoActual.getNombre()+", PC: "+this.procesoActual.getPC()+", MAR: "+this.procesoActual.getMAR(),this.id);
                    Thread.sleep(cicloReloj); // Simular la ejecución de una instrucción (ajustado al ciclo de reloj)
                    proceso.setNumeroInstrucciones(proceso.getNumeroInstrucciones()-1);
                    ciclosEjecutados++;
                    //this.simulacion.cicloCompleto();
                    this.procesoActual.setPC(this.procesoActual.getPC()+1);
                    System.out.println("Procesador " + id + " ejecutó una instrucción. Instrucciones restantes: " + proceso.getNumeroInstrucciones());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Procesador " + id + " terminó el proceso: " + proceso.getNombre());
            this.simulacion.getInstancia().actualizarTextoArea("Procesador tomado por \nel Sistema Operativo...",this.id);
            procesoActual.setEstado("Finished");
            this.simulacion.actualizarTerminados(procesoActual);
            procesoActual = null; // Liberar el procesador
            try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            if(!proceso.desbloqueada()){
                proceso.setIdProcesador(this.id);
                System.out.println("Procesador " + id + " ejecutando proceso: " + proceso.getNombre());
                while (proceso.NoHaSucedidoInterrupcion()) {
                    try {
                        this.simulacion.getInstancia().actualizarTextoArea("ID: "+ this.procesoActual.getId()+", STATUS: "+this.procesoActual.getEstado()+", Nombre: "+this.procesoActual.getNombre()+", PC: "+this.procesoActual.getPC()+", MAR: "+this.procesoActual.getMAR(),this.id);
                        Thread.sleep(cicloReloj); // Simular la ejecución de una instrucción (ajustado al ciclo de reloj)
                        proceso.setNumeroInstrucciones(proceso.getNumeroInstrucciones()-1);
                        ciclosEjecutados++;
                        this.procesoActual.setPC(this.procesoActual.getPC()+1);
                        System.out.println("Procesador " + id + " ejecutó una instrucción. Instrucciones restantes: " + proceso.getNumeroInstrucciones());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //System.out.println("Procesador " + id + " terminó el proceso: " + proceso.getNombre());
                this.simulacion.getInstancia().actualizarTextoArea("Procesador tomado por \nel Sistema Operativo...",this.id);
                procesoActual.setEstado("Blocked");
                this.simulacion.actualizarBloqueados(procesoActual);
                procesoActual = null; // Liberar el procesador
                try { //este es para que salga la vaina de que el sistema operativo toma el procesador
                    Thread.sleep(cicloReloj); // Pausa de 500 ms para que el mensaje sea visible
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }

    public void detener() {
        this.activo = false;
    }

    public int getCiclosEjecutados() {
        return ciclosEjecutados;
    }

    public boolean estaActivo() {
        return activo;
    }

    public int getCicloReloj() {
        return cicloReloj;
    }
    
    private void manejarInterrupcion() {
        System.out.println("Procesador " + id + " manejando interrupción...");
        this.simulacion.getInstancia().actualizarTextoArea("S.O. Interrupción manejada por el procesador " + id, this.id);

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
    
    public synchronized void setCicloReloj(int cicloReloj) {
        this.cicloReloj = cicloReloj; // Permitir cambiar el ciclo de reloj dinámicamente
        System.out.println("Procesador " + id + ": Ciclo de reloj actualizado a " + cicloReloj + " ms.");
    }
       
}