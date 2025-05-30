
package com.mycompany.laberintoalgoritmos;


import java.util.*;

public class Algoritmos {
    
    public Resultado buscarBFS(Laberinto laberinto) {
        long inicio = System.currentTimeMillis();
        // TODO: Implementar BFS
        long fin = System.currentTimeMillis();
        
        return new Resultado(new ArrayList<>(), "BFS", fin - inicio, 0);
    }
    
    public Resultado buscarDijkstra(Laberinto laberinto) {
        long inicio = System.currentTimeMillis();
        // TODO: Implementar Dijkstra
        long fin = System.currentTimeMillis();
        
        return new Resultado(new ArrayList<>(), "Dijkstra", fin - inicio, 0);
    }
    
    public Resultado buscarAStar(Laberinto laberinto) {
        long inicio = System.currentTimeMillis();
        // TODO: Implementar A*
        long fin = System.currentTimeMillis();
        
        return new Resultado(new ArrayList<>(), "A*", fin - inicio, 0);
    }
    
    public List<Resultado> compararAlgoritmos(Laberinto laberinto) {
        List<Resultado> resultados = new ArrayList<>();
        resultados.add(buscarBFS(laberinto));
        resultados.add(buscarDijkstra(laberinto));
        resultados.add(buscarAStar(laberinto));
        return resultados;
    }
    
    private List<Integer> reconstruirRuta(Map<Integer, Integer> padres, int inicio, int fin) {
        List<Integer> ruta = new ArrayList<>();
        // TODO: Implementar reconstrucción de ruta
        return ruta;
    }
    
    private double calcularHeuristica(int actual, int objetivo, int filas, int columnas) {
        // TODO: Implementar heurística Manhattan
        return 0.0;
    }
}
