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
 *hola 
 * @author nelsoncarrillo
 */
public class ManejadorProcesos extends Thread{
    private Simulacion sim;
    private boolean activo; 
    private int ciclos=0;


    public ManejadorProcesos(Simulacion sim2) {
        this.sim=sim2;
        this.activo=true;
    }
    
    @Override
    public void run() {
        while (activo) {
            try {
                    checkListos();
                    checkIO();
                    Thread.sleep(cicloReloj);
                    ciclos++;
                    this.sim.sumReloj(ciclos);
                    System.out.println(ciclos);
                    
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
            if(p.irInterrumpiendo()){
                    this.sim.irInterrumpiendo(p);
            }
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
                p.setEstado("Ready");
                this.sim.aggListos(p);
                actual = actual.getSiguiente();
                
            } else {
                // Solo avanzamos si no eliminamos el nodo
                
                anterior = actual;
                actual = actual.getSiguiente();
            }
            
        }
    }
    
    private void checkListos(){
        Cola colaL = sim.getColaListos();
        Nodo actual = colaL.getTop();
        while(actual!=null){
            actual.getElemento().sumarEspera();
            actual=actual.getSiguiente();
        }
    }
    
    public void detener() {
        this.activo = false;
    }
    

    public ManejadorProcesos(Runnable task) {
        super(task);
    }

    public ManejadorProcesos(ThreadGroup group, Runnable task) {
        super(group, task);
    }

    public ManejadorProcesos(String name) {
        super(name);
    }

    public ManejadorProcesos(ThreadGroup group, String name) {
        super(group, name);
    }

    public ManejadorProcesos(Runnable task, String name) {
        super(task, name);
    }

    public ManejadorProcesos(ThreadGroup group, Runnable task, String name) {
        super(group, task, name);
    }

    public ManejadorProcesos(ThreadGroup group, Runnable task, String name, long stackSize) {
        super(group, task, name, stackSize);
    }

    public ManejadorProcesos(ThreadGroup group, Runnable task, String name, long stackSize, boolean inheritInheritableThreadLocals) {
        super(group, task, name, stackSize, inheritInheritableThreadLocals);
    }
    
}
