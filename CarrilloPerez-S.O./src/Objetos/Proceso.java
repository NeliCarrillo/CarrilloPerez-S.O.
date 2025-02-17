package Objetos;


public class Proceso{
    private static int contadorID = 0; // Generador de IDs únicos
    private int id;
    private int idProcesador;
    private String nombre;
    private int numeroInstruccionesRestantes;
    private int numerototalInstrucciones;
    private int ciclosParaGenerarExcepcion; // default 0 si no es I/O bound //este siempre sera un valor estatico
    private int ciclosParaSatisfacerExcepcion;
    private String tipo; // "CPU Bound" o "IO Bound"
    private int prioridad;
    private String estado; // Ready, Running, Blocked
    private int PC;
    private int MAR;
    private boolean EScompletada;
    private int ciclosBloqueado;

    // Constructor para los CPU Bound
    public Proceso(String nombre, int numeroInstrucciones, String tipo, int prioridad) {
        this.id = ++contadorID; // Generar un ID único
        this.nombre = nombre;
        this.numeroInstruccionesRestantes = numeroInstrucciones;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = "Ready"; // Todos los procesos inician en Ready
        this.PC = 1;
        this.MAR = PC -1;
    }
    
    //Constructor para los I/O Bound
    public Proceso(String nombre, int numeroInstrucciones, String tipo, int prioridad, int ciclosParaGenerarexcepcion, int ciclosParaSatisfacerexcepcion) {
        EScompletada = false;
        numerototalInstrucciones = numeroInstrucciones;
        this.id = ++contadorID; // Generar un ID único
        this.nombre = nombre;
        this.numeroInstruccionesRestantes = numeroInstrucciones;
        this.ciclosParaGenerarExcepcion= ciclosParaGenerarexcepcion;
        this.ciclosParaSatisfacerExcepcion= ciclosParaSatisfacerexcepcion;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = "Ready"; // Todos los procesos inician en Ready
        this.PC = 1;
        this.MAR = PC -1;
        this.ciclosBloqueado=0;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public boolean irInterrumpiendo(){
        return(this.ciclosParaSatisfacerExcepcion-this.ciclosBloqueado==1 );
    }

    public int getIdProcesador() {
        return idProcesador;
    }

    public void setIdProcesador(int idProcesador) {
        this.idProcesador = idProcesador;
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
        return numeroInstruccionesRestantes;
    }

    public void setNumeroInstrucciones(int numeroInstrucciones) {
        this.numeroInstruccionesRestantes = numeroInstrucciones;
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

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
        this.MAR= PC-1;
    }

    public int getMAR() {
        return MAR;
    }

    public void setMAR(int MAR) {
        this.MAR = MAR;
    }
 
    public String print() {
        String fin = "ID: " + this.id + ", Status: " + this.estado + ", Nombre: " + this.nombre + ", PC: " + this.PC + ", MAR: " + this.MAR;
        return fin;
    }
    
    public boolean NoHaSucedidoInterrupcion(){
        return ((this.numerototalInstrucciones-this.numeroInstruccionesRestantes)<this.ciclosParaGenerarExcepcion);
    }
    
    public boolean finalizadaES(){
        if(ciclosBloqueado==this.ciclosParaSatisfacerExcepcion){
            this.EScompletada=true;
            return true;
        }
        return false;
    }
    
    public boolean desbloqueada(){
        return EScompletada;
    }
    
    public void sumarCicloBloqueado(){
        this.ciclosBloqueado++;
    }

}