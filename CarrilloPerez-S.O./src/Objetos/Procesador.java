/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import EDD.Cola;

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
    private int cicloReloj; // Duración del ciclo de reloj en milisegundos

    public Procesador(int id, Cola colaListos, Semaforo semaforo, int cicloReloj) {
        this.id = id;
        this.colaListos = colaListos;
        this.semaforo = semaforo;
        this.activo = true;
        this.ciclosEjecutados = 0;
        this.cicloReloj = cicloReloj; 
    }

    @Override
    public void run() {
        while (activo) {
            try {
                // Intentar obtener un proceso de la cola de listos
                semaforo.adquirir(); // Bloqueo para acceder a la cola de listos
                if (!colaListos.estaVacia()) {
                    procesoActual = colaListos.obtenerProceso(); // Obtener el siguiente proceso
                }
                semaforo.liberar(); // Liberar el semáforo

                if (procesoActual != null) {
                    // Simular la ejecución del proceso
                    ejecutarProceso(procesoActual);
                } else {
                    // Si no hay procesos, el procesador espera un tiempo
                    Thread.sleep(100); // Espera activa simulada
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ejecutarProceso(Proceso proceso) {
        System.out.println("Procesador " + id + " ejecutando proceso: " + proceso.getNombre());
        while (proceso.getNumeroInstrucciones() > 0) {
            try {
                Thread.sleep(cicloReloj); // Simular la ejecución de una instrucción (ajustado al ciclo de reloj)
                proceso.setNumeroInstrucciones(proceso.getNumeroInstrucciones()-1);
                ciclosEjecutados++;
                System.out.println("Procesador " + id + " ejecutó una instrucción. Instrucciones restantes: " + proceso.getNumeroInstrucciones());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Procesador " + id + " terminó el proceso: " + proceso.getNombre());
        procesoActual = null; // Liberar el procesador
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

    public void setCicloReloj(int cicloReloj) {
        this.cicloReloj = cicloReloj; // Permitir cambiar el ciclo de reloj dinámicamente
        System.out.println("Procesador " + id + ": Ciclo de reloj actualizado a " + cicloReloj + " ms.");
    }
}