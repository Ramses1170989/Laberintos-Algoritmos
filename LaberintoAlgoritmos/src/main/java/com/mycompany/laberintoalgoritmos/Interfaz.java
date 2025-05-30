
package com.mycompany.laberintoalgoritmos;


import java.util.*;

public class Interfaz {
    private Laberinto laberinto;
    private Algoritmos algoritmos;
    private Scanner scanner;
    
    public Interfaz() {
        this.laberinto = new Laberinto();
        this.algoritmos = new Algoritmos();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        System.out.println("=== SISTEMA DE LABERINTOS ===");
        System.out.println("1. Crear laberinto aleatorio");
        System.out.println("2. Crear laberinto manual");
        System.out.println("3. Cargar laberinto desde archivo");
        System.out.println("4. Resolver laberinto");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
    }
    
    public void solicitarCreacionLaberinto() {
        // TODO: Implementar solicitud de creación
    }
    
    public List<String> solicitarAlgoritmos() {
        List<String> algoritmos = new ArrayList<>();
        // TODO: Implementar selección de algoritmos
        return algoritmos;
    }
    
    public void mostrarResultados(List<Resultado> soluciones) {
        // TODO: Implementar mostrar resultados
    }
    
    public void ejecutarSistema() {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            // TODO: Implementar switch con opciones
        }
    }
    
    private int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer
            return 0;
        }
    }
    
    private void mostrarLaberinto() {
        // TODO: Implementar visualización del laberinto
    }
}