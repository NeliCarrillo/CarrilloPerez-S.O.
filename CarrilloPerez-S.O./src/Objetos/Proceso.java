package Objetos;
/**
 *
 * @author nelsoncarrillo
 */
public abstract class Proceso {
    private static int contadorID = 0; // Generador de IDs únicos
    private int id;
    private String nombre;
    private int numeroInstrucciones;
    private String tipo; // "CPU Bound" o "IO Bound"
    private int prioridad;
    private String estado; // Ready, Running, Blocked

    // Constructor
    public Proceso(String nombre, int numeroInstrucciones, String tipo, int prioridad) {
        this.id = ++contadorID; // Generar un ID único
        this.nombre = nombre;
        this.numeroInstrucciones = numeroInstrucciones;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = "Ready"; // Todos los procesos inician en Ready
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroInstrucciones() {
        return numeroInstrucciones;
    }

    public void setNumeroInstrucciones(int numeroInstrucciones) {
        this.numeroInstrucciones = numeroInstrucciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método abstracto para implementar comportamiento específico
    public abstract void ejecutar();
}