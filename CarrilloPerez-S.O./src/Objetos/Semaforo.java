package Objetos;
/**
 *
 * @author nelsoncarrillo
 */
public class Semaforo {
    private int contador; // Contador del semáforo

    public Semaforo() {
        this.contador = 1;
    }
    
    // Método para adquirir el semáforo (decrementar el contador)
    public synchronized void adquirir() {
        while (contador == 0) {
            try {
                wait(); // Esperar hasta que el semáforo esté disponible
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
                System.out.println("Hilo interrumpido mientras esperaba el semáforo.");
            }
        }
        contador--; // Decrementar el contador
    }

    // Método para liberar el semáforo (incrementar el contador)
    public synchronized void liberar() {
        contador++; // Incrementar el contador
        notify(); // Notificar a un hilo en espera
    }
}
