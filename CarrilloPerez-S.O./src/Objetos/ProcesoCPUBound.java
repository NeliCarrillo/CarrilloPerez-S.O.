/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author nelsoncarrillo
 */
public class ProcesoCPUBound extends Proceso {

    // Constructor
    public ProcesoCPUBound(String nombre, int numeroInstrucciones, int prioridad) {
        super(nombre, numeroInstrucciones, "CPU Bound", prioridad);
    }

    // Implementación del método ejecutar
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando proceso CPU Bound: " + getNombre());
        // Aquí puedes simular la ejecución de instrucciones
    }
}