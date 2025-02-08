/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    public void agregar(Proceso elemento) {
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
    public Proceso remover() {
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

    // Método para obtener el elemento del frente sin eliminarlo
    public Proceso obtenerFrente() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return null;
        }
        return frente.getElemento();
    }

    // Método para obtener el tamaño de la cola
    public int getTamaño() {
        return tamaño;
    }

    // Método para recorrer y mostrar los elementos de la cola
    public void recorrer() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }
        Nodo actual = frente;
        System.out.println("Elementos en la cola:");
        while (actual != null) {
            //System.out.println("Proceso ID: " + actual.getElemento().getId() + 
             //                  ", Nombre: " + actual.getElemento().getNombre() +
             //                  ", Estado: " + actual.getElemento().getEstado());
            actual = actual.getSiguiente();
        }
    }
}
