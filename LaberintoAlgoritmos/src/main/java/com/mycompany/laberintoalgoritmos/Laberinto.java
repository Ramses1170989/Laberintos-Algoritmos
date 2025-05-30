
package com.mycompany.laberintoalgoritmos;

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
        this.inicio = -1;
        this.fin = -1;
    }
    
    public void crearAleatorio(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grafo.clear();
        
        // Crear todos los nodos
        int totalNodos = filas * columnas;
        for (int i = 0; i < totalNodos; i++) {
            grafo.put(i, new ArrayList<>());
        }
        
        // Crear conexiones aleatorias (laberinto simple)
        Random random = new Random();
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int nodoActual = fila * columnas + col;
                
                // Conectar con vecino derecho (probabilidad 70%)
                if (col < columnas - 1 && random.nextDouble() < 0.7) {
                    int vecinoDerecha = fila * columnas + (col + 1);
                    grafo.get(nodoActual).add(vecinoDerecha);
                    grafo.get(vecinoDerecha).add(nodoActual);
                }
                
                // Conectar con vecino abajo (probabilidad 70%)
                if (fila < filas - 1 && random.nextDouble() < 0.7) {
                    int vecinoAbajo = (fila + 1) * columnas + col;
                    grafo.get(nodoActual).add(vecinoAbajo);
                    grafo.get(vecinoAbajo).add(nodoActual);
                }
            }
        }
        
        // Asegurar que al menos hay un camino del inicio al fin
        this.inicio = 0;
        this.fin = totalNodos - 1;
        asegurarConectividad();
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
        if (grafo.isEmpty() || inicio < 0 || fin < 0) return false;
        
        // Verificar que inicio y fin estén conectados usando BFS
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(inicio);
        visitados.add(inicio);
        
        while (!cola.isEmpty()) {
            int actual = cola.poll();
            if (actual == fin) return true;
            
            for (int vecino : obtenerVecinos(actual)) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.offer(vecino);
                }
            }
        }
        return false;
    }
    
    private void asegurarConectividad() {
        // Método simple para asegurar que hay camino del inicio al fin
        if (!esValido()) {
            // Crear un camino directo básico
            int actual = inicio;
            while (actual != fin) {
                int siguiente;
                if (actual % columnas < fin % columnas) {
                    // Moverse a la derecha
                    siguiente = actual + 1;
                } else {
                    // Moverse hacia abajo
                    siguiente = actual + columnas;
                }
                
                if (siguiente <= fin) {
                    if (!grafo.get(actual).contains(siguiente)) {
                        grafo.get(actual).add(siguiente);
                    }
                    if (!grafo.get(siguiente).contains(actual)) {
                        grafo.get(siguiente).add(actual);
                    }
                    actual = siguiente;
                } else {
                    break;
                }
            }
        }
    }
    
    // Getters
    public Map<Integer, List<Integer>> getGrafo() { return grafo; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public int getInicio() { return inicio; }
    public int getFin() { return fin; }
}