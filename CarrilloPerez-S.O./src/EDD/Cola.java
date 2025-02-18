package EDD;

import Objetos.Proceso;

public class Cola {
    private Nodo frente; // Primer nodo de la cola
    private Nodo fin;    // Último nodo de la cola
    private int tamaño;  // Número de elementos en la cola

    // Constructor
    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Método para agregar un elemento a la cola
    public synchronized void agregar(Proceso elemento) {
        Nodo nuevoNodo = new Nodo(elemento);
        if (estaVacia()) {
            frente = nuevoNodo; // Si la cola está vacía, el nuevo nodo es el frente
            fin = nuevoNodo;    // y también el fin
        } else {
            fin.setSiguiente(nuevoNodo); // Enlazar el nuevo nodo al final de la cola
            fin = nuevoNodo;            // Actualizar el fin
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
