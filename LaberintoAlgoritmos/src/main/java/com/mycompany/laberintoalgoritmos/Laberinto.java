/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laberintoalgoritmos;

/**
 *
 * @author Ramse
 */
import java.util.*;
import java.io.*;

public class Laberinto {
    private Map<Integer, List<Integer>> grafo;
    private int filas;
    private int columnas;
    private int inicio;
    private int fin;
    
    public Laberinto() {
        this.grafo = new HashMap<>();
    }
    
    public void crearAleatorio(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        // TODO: Implementar generación aleatoria
    }
    
    public void crearManual() {
        Scanner scanner = new Scanner(System.in);
        // TODO: Implementar creación manual
    }
    
    public void cargarDesdeArchivo(String archivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        // TODO: Implementar lectura desde CSV
    }
    
    public void establecerInicioFin(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }
    
    public List<Integer> obtenerVecinos(int nodo) {
        return grafo.getOrDefault(nodo, new ArrayList<>());
    }
    
    public boolean esValido() {
        // TODO: Validar que el laberinto sea solucionable
        return true;
    }
    
    // Getters
    public Map<Integer, List<Integer>> getGrafo() { return grafo; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public int getInicio() { return inicio; }
    public int getFin() { return fin; }
}