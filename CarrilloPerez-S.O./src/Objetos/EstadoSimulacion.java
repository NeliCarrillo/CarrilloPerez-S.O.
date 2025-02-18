/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import EDD.Cola;

/**
 *
 * @author nelsoncarrillo
 */
public class EstadoSimulacion {
    private Semaforo semaforo;
    private Cola colaL;
    private Cola colaT;
    private Cola colaB;
    private int cicloReloj;
    private int numProcesadores;
    private Proceso en1;
    private Proceso en2;
    private Proceso en3;
    

    // Constructor2
    public EstadoSimulacion(Semaforo semaforo, Cola colaL, Cola colaT, Cola colaB, int cicloReloj,int n,Proceso c1,Proceso c2, Proceso c3) {
        this.semaforo = semaforo;
        this.colaL = colaL;
        this.colaT = colaT;
        this.colaB = colaB;
        this.cicloReloj = cicloReloj;
        this.numProcesadores=n;
        this.en1=c1;
        this.en2=c2;
        this.en3=c3;
    }

    // Getters y setters
    public Semaforo getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public Cola getColaL() {
        return colaL;
    }

    public void setColaL(Cola colaL) {
        this.colaL = colaL;
    }

    public Cola getColaT() {
        return colaT;
    }

    public void setColaT(Cola colaT) {
        this.colaT = colaT;
    }

    public Cola getColaB() {
        return colaB;
    }

    public void setColaB(Cola colaB) {
        this.colaB = colaB;
    }

    public int getCicloReloj() {
        return cicloReloj;
    }

    public void setCicloReloj(int cicloReloj) {
        this.cicloReloj = cicloReloj;
    }

    public int getNumProcesadores() {
        return numProcesadores;
    }

    public void setNumProcesadores(int numProcesadores) {
        this.numProcesadores = numProcesadores;
    }
    
    
}