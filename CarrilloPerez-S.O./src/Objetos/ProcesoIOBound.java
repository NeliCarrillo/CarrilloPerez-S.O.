/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author nelsoncarrillo
 */
public class ProcesoIOBound extends Proceso {
    private int ciclosExcepcion; // Ciclos para generar una solicitud de E/S
    private int ciclosSatisfaccion; // Ciclos para completar la solicitud de E/S

    // Constructor
    public ProcesoIOBound(String nombre, int numeroInstrucciones, int prioridad, int ciclosExcepcion, int ciclosSatisfaccion) {
        super(nombre, numeroInstrucciones, "IO Bound", prioridad);
        this.ciclosExcepcion = ciclosExcepcion;
        this.ciclosSatisfaccion = ciclosSatisfaccion;
    }

    // Getters y Setters
    public int getCiclosExcepcion() {
        return ciclosExcepcion;
    }

    public void setCiclosExcepcion(int ciclosExcepcion) {
        this.ciclosExcepcion = ciclosExcepcion;
    }

    public int getCiclosSatisfaccion() {
        return ciclosSatisfaccion;
    }

    public void setCiclosSatisfaccion(int ciclosSatisfaccion) {
        this.ciclosSatisfaccion = ciclosSatisfaccion;
    }

    // Implementación del método ejecutar
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando proceso IO Bound: " + getNombre());
        System.out.println("Ciclos para excepción: " + ciclosExcepcion + ", Ciclos para satisfacción: " + ciclosSatisfaccion);
        // Aquí puedes simular la ejecución de instrucciones y solicitudes de E/S
    }
}
