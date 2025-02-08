/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author nelsoncarrillo
 */
public class Semaforo {
    private int permisos; // Número de permisos disponibles

    // Constructor: inicializa el semáforo con un número de permisos
    public Semaforo(int permisos) {
        this.permisos = permisos;
    }

    // Método para adquirir un permiso (bloquea si no hay permisos disponibles)
    public synchronized void adquirir() throws InterruptedException {
        while (permisos <= 0) {
            wait(); // Esperar hasta que haya un permiso disponible
        }
        permisos--; // Reducir el número de permisos disponibles
    }

    // Método para liberar un permiso (notifica a los hilos en espera)
    public synchronized void liberar() {
        permisos++; // Incrementar el número de permisos disponibles
        notify(); // Notificar a un hilo en espera
    }
}
