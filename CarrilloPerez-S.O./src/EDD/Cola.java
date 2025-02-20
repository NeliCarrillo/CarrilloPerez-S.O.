package EDD;

import Objetos.Proceso;

public class Cola {
    private Nodo frente =null; // Primer nodo de la cola
    private Nodo fin= null;    // Último nodo de la cola
    private int tamaño=0;  // Número de elementos en la cola
    private String resultado="";

    // Constructor
    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
        this.resultado="";
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Método para agregar un elemento a la cola
    public synchronized void agregar(Proceso elemento) {
        //this.resultado="";
        //Nodo salvoguardo = fin;
        Nodo nuevoNodo = new Nodo(elemento);
        if (estaVacia()) {
            frente = nuevoNodo; // Si la cola está vacía, el nuevo nodo es el frente
            fin = nuevoNodo;    // y también el fin
        } else {
            fin.setSiguiente(nuevoNodo); // Enlazar el nuevo nodo al final de la cola
            //System.out.println("conexion desde .  "+fin.getElemento().getId()+" . a   "+fin.getSiguiente().getElemento().getId());
            //salvoguardo = fin;
            fin = nuevoNodo;            // Actualizar el fin
        }
        tamaño++;
        /*Nodo salvo = frente;
        while(salvo.getSiguiente()!=null){
            System.out.println("El siguiente de "+salvo.getElemento().getId()+"es"+salvo.getSiguiente().getElemento().getId());
            resultado += salvo.getElemento().print()+"\n"+salvo.getSiguiente().getElemento().print()+"\n";
            salvo=salvo.getSiguiente();
        }
        while(salvoguardo.getSiguiente()!=null){
                        System.out.println("\nsalvog \nEl siguiente de "+salvoguardo.getElemento().getId()+"es"+salvoguardo.getSiguiente().getElemento().getId());
            resultado += salvoguardo.getElemento().print()+"\n"+salvoguardo.getSiguiente().getElemento().print()+"\n";
            salvoguardo=salvoguardo.getSiguiente();
        }
        System.out.println("\nIniciolol "+frente.getElemento().getId()+"\nFin "+fin.getElemento().getId()+"\n\n"+resultado);
        */
    }
    
    public synchronized void agregarPpio(Proceso el){
        Nodo nuevoNodo = new Nodo(el);
        if (estaVacia()) {
            frente = nuevoNodo; // Si la cola está vacía, el nuevo nodo es el frente
            fin = nuevoNodo;    // y también el fin
        } else {
            nuevoNodo.setSiguiente(frente);
            frente = nuevoNodo;            // Actualizar el fin
        }
        tamaño++;
    }

    // Método para eliminar un elemento de la cola
    public Proceso obtenerProceso() {
        if (estaVacia()) {
            System.out.println("La cola está vacía. No se puede remover un elemento.");
            return null;
        }
        Proceso elemento = frente.getElemento(); // Obtener el elemento del frente
        frente = frente.getSiguiente();         // Mover el frente al siguiente nodo
        if (frente == null) {                   // Si la cola queda vacía
            fin = null;
        }
        tamaño--;
        return elemento;
    }

    // Método para obtener el tamaño de la cola
    public int getTamaño() {
        return tamaño;
    }
    
    public Cola copiar(){
        Cola r = new Cola();
        Nodo actual = this.frente;
        while(actual!=null){
            r.agregar(actual.getElemento());
            actual=actual.getSiguiente();
        }
        return r;
    }
    
    public String print() {
        String resultado = ""; // Inicializamos un String vacío

        // Usamos un ciclo for para recorrer la cola
        Nodo actual = frente; // Comenzamos desde el nodo frente
        while(actual!=null) {
                Proceso proceso = actual.getElemento(); // Obtener el proceso del nodo actual
                resultado += proceso.print()+"\n";
                actual = actual.getSiguiente(); // Mover al siguiente nodo
        }

        return resultado; // Devolver el resultado como un String
    }
    
    public Nodo getTop(){
        return this.frente;
    }
    
    public Nodo getFinal(){
        return this.fin;
    }

    public void setFrente(Nodo frente) {
        this.frente = frente;
    }
    
}
