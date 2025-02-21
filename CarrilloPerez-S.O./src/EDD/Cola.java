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
            fin = nuevoNodo;            // Actualizar el fin
        }
        tamaño++;
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
    
    public void setFinal(Nodo frente) {
        this.fin = frente;
    }
    
    // Método para buscar y eliminar el proceso más corto
    public Proceso eliminarMasCorto() {
        if (frente == null) {
            // La cola está vacía
            return null;
        }

        Nodo actual = frente;
        Nodo anterior = null;

        Nodo nodoMasCorto = frente;
        Nodo anteriorMasCorto = null;

        // Buscar el nodo con el proceso más corto
        while (actual != null) {
            if (actual.getElemento().getNumeroInstrucciones() < nodoMasCorto.getElemento().getNumeroInstrucciones()) {
                nodoMasCorto = actual;
                anteriorMasCorto = anterior;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }

        // Eliminar el nodo más corto de la cola
        if (nodoMasCorto == frente) {
            // Si el nodo más corto es el primero
            frente = frente.getSiguiente();
        } else {
            // Si el nodo más corto está en el medio o al final
            anteriorMasCorto.setSiguiente(nodoMasCorto.getSiguiente());
        }

        if (nodoMasCorto == fin) {
            // Si el nodo más corto es el último
            fin = anteriorMasCorto;
        }

        tamaño--; // Reducir el tamaño de la cola
        return nodoMasCorto.getElemento(); // Devolver el proceso eliminado
    }
    
    // Método para buscar y eliminar el proceso más corto
    public Proceso eliminarMayorTasaRespuesta() {
        if (frente == null) {
            // La cola está vacía
            return null;
        }

        Nodo actual = frente;
        Nodo anterior = null;

        Nodo nodoMasCorto = frente;
        Nodo anteriorMasCorto = null;

        // Buscar el nodo con el proceso más corto
        while (actual != null) {
            if (actual.getElemento().getTasaRespuesta() > nodoMasCorto.getElemento().getTasaRespuesta()) {
                nodoMasCorto = actual;
                anteriorMasCorto = anterior;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }

        // Eliminar el nodo más corto de la cola
        if (nodoMasCorto == frente) {
            // Si el nodo más corto es el primero
            frente = frente.getSiguiente();
        } else {
            // Si el nodo más corto está en el medio o al final
            anteriorMasCorto.setSiguiente(nodoMasCorto.getSiguiente());
        }

        if (nodoMasCorto == fin) {
            // Si el nodo más corto es el último
            fin = anteriorMasCorto;
        }

        tamaño--; // Reducir el tamaño de la cola
        return nodoMasCorto.getElemento(); // Devolver el proceso eliminado
    }
    
    
}
