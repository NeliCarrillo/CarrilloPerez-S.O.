/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import Objetos.Proceso;

public class Nodo {
    private Proceso elemento; // El proceso almacenado en el nodo
    private Nodo siguiente;   // Referencia al siguiente nodo

    // Constructor
    public Nodo(Proceso elemento) {
        this.elemento = elemento;
        this.siguiente = null;
    }

    // Getters y Setters
    public Proceso getElemento() {
        return elemento;
    }

    public void setElemento(Proceso elemento) {
        this.elemento = elemento;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}