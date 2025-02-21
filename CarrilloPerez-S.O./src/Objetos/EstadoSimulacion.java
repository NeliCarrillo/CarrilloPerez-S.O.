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
    private Cola colaListos;
    private Cola colaTerminados;
    private Cola colaBloqueados;
    private int cicloReloj;
    private int numProcesadores;
    private Proceso en1;
    private Proceso en2;
    private Proceso en3;
    private String politica;
    

    // Constructor2
    public EstadoSimulacion(String politca, Semaforo semaforo, Cola colaL, Cola colaT, Cola colaB, int cicloReloj,int n,Proceso c1,Proceso c2, Proceso c3) {
        this.semaforo = semaforo;
        this.colaListos = colaL;
        this.colaTerminados = colaT;
        this.colaBloqueados = colaB;
        this.cicloReloj = cicloReloj;
        this.numProcesadores=n;
        this.en1=c1;
        this.en2=c2;
        this.en3=c3;
        this.politica=politca;
    }
    
    public EstadoSimulacion(String politca,Semaforo semaforo, Cola colaL, Cola colaT, Cola colaB, int cicloReloj,int n,Proceso c1,Proceso c2) {
        this.semaforo = semaforo;
        this.colaListos = colaL;
        this.colaTerminados = colaT;
        this.colaBloqueados = colaB;
        this.cicloReloj = cicloReloj;
        this.numProcesadores=n;
        this.en1=c1;
        this.en2=c2;
        this.politica=politca;
    }
    
    public EstadoSimulacion(int cpu, int dur){
        this.numProcesadores=cpu;
        this.cicloReloj=dur;
    }

    // Getters y setters
    public Semaforo getSemaforo() {
        return semaforo;
    }
    public String getPolitica(){
        return this.politica;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public Cola getColaListos() {
        return colaListos;
    }

    public void setColaListos(Cola colaListos) {
        this.colaListos = colaListos;
    }

    public Cola getColaTerminados() {
        return colaTerminados;
    }

    public void setColaTerminados(Cola colaTerminados) {
        this.colaTerminados = colaTerminados;
    }

    public Cola getColaBloqueados() {
        return colaBloqueados;
    }

    public void setColaBloqueados(Cola colaBloqueados) {
        this.colaBloqueados = colaBloqueados;
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

    public Proceso getEn1() {
        return en1;
    }

    public void setEn1(Proceso en1) {
        this.en1 = en1;
    }

    public Proceso getEn2() {
        return en2;
    }

    public void setEn2(Proceso en2) {
        this.en2 = en2;
    }

    public Proceso getEn3() {
        return en3;
    }

    public void setEn3(Proceso en3) {
        this.en3 = en3;
    }
    
    
    
    
}