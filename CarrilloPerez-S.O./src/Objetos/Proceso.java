package Objetos;
/**
 *
 * @author nelsoncarrillo
 */
public class Proceso{
    private static int contadorID = 0; // Generador de IDs únicos
    private int id;
    private String nombre;
    private int numeroInstrucciones;
    private int ciclosParaGenerarExcepcion; // default 0 si no es I/O bound //este siempre sera un valor estatico
    private int ciclosParaSatisfacerExcepcion;
    private String tipo; // "CPU Bound" o "IO Bound"
    private int prioridad;
    private String estado; // Ready, Running, Blocked

    // Constructor para los CPU Bound
    public Proceso(String nombre, int numeroInstrucciones, String tipo, int prioridad) {
        this.id = ++contadorID; // Generar un ID único
        this.nombre = nombre;
        this.numeroInstrucciones = numeroInstrucciones;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = "Ready"; // Todos los procesos inician en Ready
    }
    
    //Constructor para los I/O Bound
    public Proceso(String nombre, int numeroInstrucciones, String tipo, int prioridad, int ciclosParaGenerarexcepcion, int ciclosParaSatisfacerexcepcion) {
        this.id = ++contadorID; // Generar un ID único
        this.nombre = nombre;
        this.numeroInstrucciones = numeroInstrucciones;
        this.ciclosParaGenerarExcepcion= ciclosParaGenerarexcepcion;
        this.ciclosParaSatisfacerExcepcion= ciclosParaSatisfacerexcepcion;
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
    
    public int getCiclosParaGenerarExcepcion() {
        return ciclosParaGenerarExcepcion;
    }

    public void setCiclosParaGenerarExcepcion(int ciclosParaGenerarExcepcion) {
        this.ciclosParaGenerarExcepcion = ciclosParaGenerarExcepcion;
    }

    public int getCiclosParaSatisfacerExcepcion() {
        return ciclosParaSatisfacerExcepcion;
    }

    public void setCiclosParaSatisfacerExcepcion(int ciclosParaSatisfacerExcepcion) {
        this.ciclosParaSatisfacerExcepcion = ciclosParaSatisfacerExcepcion;
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
    
    public String print() {
        String fin = "ID: " + this.id + ", Nombre: " + this.nombre + ", Número de Instrucciones: " + this.numeroInstrucciones + ", Tipo: " + this.tipo + ", Prioridad: " + this.prioridad + ", Estado: " + this.estado;
        return fin;
    }

}