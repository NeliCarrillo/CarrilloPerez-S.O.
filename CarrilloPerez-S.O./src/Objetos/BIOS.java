/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import EDD.Cola;
import EDD.Nodo;
import GUIs.Simulacion;
import static Objetos.Procesador.cicloReloj;

/**
 *
 * @author nelsoncarrillo
 */
public class BIOS extends Thread{
    private Simulacion sim;
    private boolean activo; 
    private int ciclos=0;


    public BIOS(Simulacion sim2) {
        this.sim=sim2;
        this.activo=true;
    }
    
    @Override
    public void run() {
        while (activo) {
            try {
                    Thread.sleep(cicloReloj);
                    ciclos++;
                    System.out.println(ciclos);
                    checkIO();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void checkIO() {
        Cola colaB = sim.getBloqueados();
        Nodo actual = colaB.getTop();
        Nodo anterior = null;

        while (actual != null) {
            Proceso p = actual.getElemento();
            p.sumarCicloBloqueado();

            if (p.finalizadaES()) {
                
                // Eliminar el nodo de la cola
                if (anterior == null) {
                    // Si es el primer nodo, actualizamos la cabeza de la cola
                    colaB.setFrente(actual.getSiguiente());
                } else {
                    // Si no es el primer nodo, enlazamos el nodo anterior con el siguiente
                    anterior.setSiguiente(actual.getSiguiente());
                }
                // Actualizamos el nodo actual para continuar el ciclo
                this.sim.aggListos(p);
                actual = actual.getSiguiente();
            } else {
                // Solo avanzamos si no eliminamos el nodo
                anterior = actual;
                actual = actual.getSiguiente();
            }
        }
    }
    
    public void detener() {
        this.activo = false;
    }
    

    public BIOS(Runnable task) {
        super(task);
    }

    public BIOS(ThreadGroup group, Runnable task) {
        super(group, task);
    }

    public BIOS(String name) {
        super(name);
    }

    public BIOS(ThreadGroup group, String name) {
        super(group, name);
    }

    public BIOS(Runnable task, String name) {
        super(task, name);
    }

    public BIOS(ThreadGroup group, Runnable task, String name) {
        super(group, task, name);
    }

    public BIOS(ThreadGroup group, Runnable task, String name, long stackSize) {
        super(group, task, name, stackSize);
    }

    public BIOS(ThreadGroup group, Runnable task, String name, long stackSize, boolean inheritInheritableThreadLocals) {
        super(group, task, name, stackSize, inheritInheritableThreadLocals);
    }
    
}
