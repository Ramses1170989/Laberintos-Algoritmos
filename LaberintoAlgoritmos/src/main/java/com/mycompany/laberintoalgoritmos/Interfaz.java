package com.mycompany.laberintoalgoritmos;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Interfaz extends JFrame {
    
    // Componentes principales
    private Laberinto laberinto;
    private Algoritmos algoritmos;
    
    // Paneles
    private JPanel panelPrincipal;
    private JPanel panelCreacion;
    private JPanel panelAlgoritmos;
    private JPanel panelResultados;
    private JPanel panelVisualizacion;
    
    // Componentes de creación
    private JRadioButton rbAleatorio, rbManual, rbArchivo;
    private ButtonGroup grupoCreacion;
    private JSpinner spinnerFilas, spinnerColumnas;
    private JTextField txtArchivo;
    private JButton btnExaminar, btnCrearLaberinto;
    
    // Componentes de algoritmos
    private JCheckBox cbBFS, cbDijkstra, cbAStar;
    private JSpinner spinnerInicio, spinnerFin;
    private JButton btnResolver;
    
    // Componentes de resultados
    private JTextArea areaResultados;
    private JScrollPane scrollResultados;
    
    // Componentes de visualización
    private JPanel panelLaberinto;
    private JLabel lblEstado;
    
    public Interfaz() {
        this.laberinto = new Laberinto();
        this.algoritmos = new Algoritmos();
        inicializarComponentes();
        configurarVentana();
        configurarEventos();
    }
    
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Crear paneles
        crearPanelCreacion();
        crearPanelAlgoritmos();
        crearPanelResultados();
        crearPanelVisualizacion();
        
        // Agregar paneles al principal
        JPanel panelIzquierdo = new JPanel(new BorderLayout(5, 5));
        panelIzquierdo.add(panelCreacion, BorderLayout.NORTH);
        panelIzquierdo.add(panelAlgoritmos, BorderLayout.CENTER);
        panelIzquierdo.add(panelResultados, BorderLayout.SOUTH);
        
        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelVisualizacion, BorderLayout.CENTER);
        
        // Barra de estado
        lblEstado = new JLabel("Listo para crear laberinto");
        lblEstado.setBorder(BorderFactory.createLoweredBevelBorder());
        panelPrincipal.add(lblEstado, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private void crearPanelCreacion() {
        panelCreacion = new JPanel();
        panelCreacion.setBorder(new TitledBorder("Crear Laberinto"));
        panelCreacion.setLayout(new BoxLayout(panelCreacion, BoxLayout.Y_AXIS));
        panelCreacion.setPreferredSize(new Dimension(300, 200));
        
        // Radio buttons para tipo de creación
        rbAleatorio = new JRadioButton("Generar Aleatorio", true);
        rbManual = new JRadioButton("Crear Manual");
        rbArchivo = new JRadioButton("Cargar desde Archivo");
        
        grupoCreacion = new ButtonGroup();
        grupoCreacion.add(rbAleatorio);
        grupoCreacion.add(rbManual);
        grupoCreacion.add(rbArchivo);
        
        // Panel para dimensiones
        JPanel panelDimensiones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDimensiones.add(new JLabel("Filas:"));
        spinnerFilas = new JSpinner(new SpinnerNumberModel(5, 3, 50, 1));
        panelDimensiones.add(spinnerFilas);
        
        panelDimensiones.add(new JLabel("Columnas:"));
        spinnerColumnas = new JSpinner(new SpinnerNumberModel(5, 3, 50, 1));
        panelDimensiones.add(spinnerColumnas);
        
        // Panel para archivo
        JPanel panelArchivo = new JPanel(new BorderLayout(5, 5));
        txtArchivo = new JTextField();
        txtArchivo.setEnabled(false);
        btnExaminar = new JButton("...");
        btnExaminar.setEnabled(false);
        btnExaminar.setPreferredSize(new Dimension(30, 25));
        
        panelArchivo.add(txtArchivo, BorderLayout.CENTER);
        panelArchivo.add(btnExaminar, BorderLayout.EAST);
        
        // Botón crear
        btnCrearLaberinto = new JButton("Crear Laberinto");
        btnCrearLaberinto.setBackground(new Color(76, 175, 80));
        btnCrearLaberinto.setForeground(Color.WHITE);
        
        // Agregar componentes
        panelCreacion.add(rbAleatorio);
        panelCreacion.add(panelDimensiones);
        panelCreacion.add(Box.createVerticalStrut(5));
        panelCreacion.add(rbManual);
        panelCreacion.add(Box.createVerticalStrut(5));
        panelCreacion.add(rbArchivo);
        panelCreacion.add(panelArchivo);
        panelCreacion.add(Box.createVerticalStrut(10));
        panelCreacion.add(btnCrearLaberinto);
    }
    
    private void crearPanelAlgoritmos() {
        panelAlgoritmos = new JPanel();
        panelAlgoritmos.setBorder(new TitledBorder("Algoritmos de Búsqueda"));
        panelAlgoritmos.setLayout(new BoxLayout(panelAlgoritmos, BoxLayout.Y_AXIS));
        panelAlgoritmos.setPreferredSize(new Dimension(300, 150));
        
        // Checkboxes para algoritmos
        cbBFS = new JCheckBox("BFS (Breadth-First Search)", true);
        cbDijkstra = new JCheckBox("Dijkstra");
        cbAStar = new JCheckBox("A* (A-Star)");
        
        // Panel para puntos inicio/fin
        JPanel panelPuntos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPuntos.add(new JLabel("Inicio:"));
        spinnerInicio = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        panelPuntos.add(spinnerInicio);
        
        panelPuntos.add(new JLabel("Fin:"));
        spinnerFin = new JSpinner(new SpinnerNumberModel(24, 0, 100, 1));
        panelPuntos.add(spinnerFin);
        
        // Botón resolver
        btnResolver = new JButton("Resolver Laberinto");
        btnResolver.setBackground(new Color(33, 150, 243));
        btnResolver.setForeground(Color.WHITE);
        btnResolver.setEnabled(false);
        
        // Agregar componentes
        panelAlgoritmos.add(cbBFS);
        panelAlgoritmos.add(cbDijkstra);
        panelAlgoritmos.add(cbAStar);
        panelAlgoritmos.add(Box.createVerticalStrut(5));
        panelAlgoritmos.add(panelPuntos);
        panelAlgoritmos.add(Box.createVerticalStrut(5));
        panelAlgoritmos.add(btnResolver);
    }
    
    private void crearPanelResultados() {
        panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBorder(new TitledBorder("Resultados"));
        panelResultados.setPreferredSize(new Dimension(300, 200));
        
        areaResultados = new JTextArea(8, 20);
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        areaResultados.setBackground(new Color(248, 248, 248));
        
        scrollResultados = new JScrollPane(areaResultados);
        scrollResultados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        panelResultados.add(scrollResultados, BorderLayout.CENTER);
    }
    
    private void crearPanelVisualizacion() {
        panelVisualizacion = new JPanel(new BorderLayout());
        panelVisualizacion.setBorder(new TitledBorder("Visualización del Laberinto"));
        
        panelLaberinto = new JPanel();
        panelLaberinto.setBackground(Color.WHITE);
        panelLaberinto.setPreferredSize(new Dimension(400, 400));
        
        JScrollPane scrollLaberinto = new JScrollPane(panelLaberinto);
        panelVisualizacion.add(scrollLaberinto, BorderLayout.CENTER);
        
        // Panel de leyenda
        JPanel panelLeyenda = new JPanel(new FlowLayout());
        panelLeyenda.add(crearLeyendaItem("Inicio", Color.GREEN));
        panelLeyenda.add(crearLeyendaItem("Fin", Color.RED));
        panelLeyenda.add(crearLeyendaItem("Camino", Color.BLUE));
        panelLeyenda.add(crearLeyendaItem("Pared", Color.BLACK));
        panelLeyenda.add(crearLeyendaItem("Libre", Color.WHITE));
        
        panelVisualizacion.add(panelLeyenda, BorderLayout.SOUTH);
    }
    
    private JPanel crearLeyendaItem(String texto, Color color) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
        JLabel colorLabel = new JLabel("  ");
        colorLabel.setOpaque(true);
        colorLabel.setBackground(color);
        colorLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        item.add(colorLabel);
        item.add(new JLabel(texto));
        return item;
    }
    
    private void configurarVentana() {
        setTitle("Sistema de Creación y Solución de Laberintos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Icono de la ventana (opcional)
        try {
            // setIconImage(new ImageIcon("icono.png").getImage());
        } catch (Exception e) {
            // Ignorar si no hay icono
        }
    }
    
    private void configurarEventos() {
        // Eventos de radio buttons
        ActionListener radioListener = e -> {
            boolean esArchivo = rbArchivo.isSelected();
            txtArchivo.setEnabled(esArchivo);
            btnExaminar.setEnabled(esArchivo);
            
            boolean esDimensiones = rbAleatorio.isSelected() || rbManual.isSelected();
            spinnerFilas.setEnabled(esDimensiones);
            spinnerColumnas.setEnabled(esDimensiones);
        };
        
        rbAleatorio.addActionListener(radioListener);
        rbManual.addActionListener(radioListener);
        rbArchivo.addActionListener(radioListener);
        
        // Evento botón examinar
        btnExaminar.addActionListener(e -> examinarArchivo());
        
        // Evento botón crear laberinto
        btnCrearLaberinto.addActionListener(e -> crearLaberinto());
        
        // Evento botón resolver
        btnResolver.addActionListener(e -> resolverLaberinto());
    }
    
    private void examinarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv", "txt"));
        
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            txtArchivo.setText(archivo.getAbsolutePath());
        }
    }
    
    private void crearLaberinto() {
        try {
            lblEstado.setText("Creando laberinto...");
            
            if (rbAleatorio.isSelected()) {
                int filas = (Integer) spinnerFilas.getValue();
                int columnas = (Integer) spinnerColumnas.getValue();
                laberinto.crearAleatorio(filas, columnas);
                actualizarEstado("Laberinto aleatorio creado (" + filas + "x" + columnas + ")");
                
            } else if (rbManual.isSelected()) {
                // TODO: Implementar creación manual con diálogo
                mostrarDialogoCreacionManual();
                
            } else if (rbArchivo.isSelected()) {
                String archivo = txtArchivo.getText().trim();
                if (archivo.isEmpty()) {
                    mostrarError("Selecciona un archivo CSV");
                    return;
                }
                laberinto.cargarDesdeArchivo(archivo);
                actualizarEstado("Laberinto cargado desde: " + archivo);
            }
            
            // Actualizar límites de spinners inicio/fin
            int maxNodos = laberinto.getFilas() * laberinto.getColumnas() - 1;
            spinnerInicio.setModel(new SpinnerNumberModel(0, 0, maxNodos, 1));
            spinnerFin.setModel(new SpinnerNumberModel(maxNodos, 0, maxNodos, 1));
            
            btnResolver.setEnabled(true);
            visualizarLaberinto();
            
        } catch (Exception e) {
            mostrarError("Error al crear laberinto: " + e.getMessage());
            lblEstado.setText("Error en creación");
        }
    }
    
    private void mostrarDialogoCreacionManual() {
        JOptionPane.showMessageDialog(this, 
            "Funcionalidad de creación manual en desarrollo.\n" + 
            "Por ahora usa generación aleatoria o carga desde archivo.",
            "En desarrollo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void resolverLaberinto() {
        if (laberinto.getGrafo().isEmpty()) {
            mostrarError("Primero crea un laberinto");
            return;
        }
        
        // Verificar que al menos un algoritmo esté seleccionado
        if (!cbBFS.isSelected() && !cbDijkstra.isSelected() && !cbAStar.isSelected()) {
            mostrarError("Selecciona al menos un algoritmo");
            return;
        }
        
        try {
            lblEstado.setText("Resolviendo laberinto...");
            
            int inicio = (Integer) spinnerInicio.getValue();
            int fin = (Integer) spinnerFin.getValue();
            laberinto.establecerInicioFin(inicio, fin);
            
            List<Resultado> resultados = new ArrayList<>();
            StringBuilder textoResultados = new StringBuilder();
            textoResultados.append("=== RESULTADOS DE BÚSQUEDA ===\n\n");
            
            Resultado mejorResultado = null;
            
            // Ejecutar algoritmos seleccionados
            if (cbBFS.isSelected()) {
                Resultado resultado = algoritmos.buscarBFS(laberinto);
                resultados.add(resultado);
                textoResultados.append(formatearResultado(resultado)).append("\n");
                if (mejorResultado == null || esMejorRuta(resultado, mejorResultado)) {
                    mejorResultado = resultado;
                }
            }
            
            if (cbDijkstra.isSelected()) {
                Resultado resultado = algoritmos.buscarDijkstra(laberinto);
                resultados.add(resultado);
                textoResultados.append(formatearResultado(resultado)).append("\n");
                if (mejorResultado == null || esMejorRuta(resultado, mejorResultado)) {
                    mejorResultado = resultado;
                }
            }
            
            if (cbAStar.isSelected()) {
                Resultado resultado = algoritmos.buscarAStar(laberinto);
                resultados.add(resultado);
                textoResultados.append(formatearResultado(resultado)).append("\n");
                if (mejorResultado == null || esMejorRuta(resultado, mejorResultado)) {
                    mejorResultado = resultado;
                }
            }
            
            // Mostrar resultados
            areaResultados.setText(textoResultados.toString());
            areaResultados.setCaretPosition(0);
            
            // Mostrar la mejor ruta en la visualización
            if (mejorResultado != null && !mejorResultado.obtenerRuta().isEmpty()) {
                Component[] componentes = panelLaberinto.getComponents();
                for (Component comp : componentes) {
                    if (comp instanceof LaberintoPanel) {
                        ((LaberintoPanel) comp).setRutaActual(mejorResultado.obtenerRuta());
                        break;
                    }
                }
            }
            
            actualizarEstado("Laberinto resuelto con " + resultados.size() + " algoritmo(s)");
            
        } catch (Exception e) {
            mostrarError("Error al resolver: " + e.getMessage());
            lblEstado.setText("Error en resolución");
        }
    }
    
    private boolean esMejorRuta(Resultado nuevo, Resultado actual) {
        // Primero verificar si encontró ruta
        if (nuevo.obtenerRuta().isEmpty()) return false;
        if (actual.obtenerRuta().isEmpty()) return true;
        
        // Comparar longitud de ruta (menor es mejor)
        if (nuevo.getLongitudRuta() != actual.getLongitudRuta()) {
            return nuevo.getLongitudRuta() < actual.getLongitudRuta();
        }
        
        // Si tienen la misma longitud, comparar tiempo (menor es mejor)
        return nuevo.getTiempoEjecucion() < actual.getTiempoEjecucion();
    }
    
    private String formatearResultado(Resultado resultado) {
        return String.format(
            "Algoritmo: %s\n" +
            "Tiempo: %d ms\n" +
            "Longitud ruta: %d\n" +
            "Ruta encontrada: %s\n" +
            "------------------------",
            resultado.getAlgoritmo(),
            resultado.getTiempoEjecucion(),
            resultado.getLongitudRuta(),
            resultado.obtenerRuta().isEmpty() ? "No encontrada" : "Sí"
        );
    }
    
    private void visualizarLaberinto() {
        panelLaberinto.removeAll();
        
        if (laberinto.getGrafo().isEmpty()) {
            JLabel lblVacio = new JLabel("No hay laberinto para mostrar", SwingConstants.CENTER);
            lblVacio.setForeground(Color.GRAY);
            panelLaberinto.add(lblVacio);
        } else {
            LaberintoPanel laberintoPanel = new LaberintoPanel(laberinto);
            panelLaberinto.setLayout(new BorderLayout());
            panelLaberinto.add(laberintoPanel, BorderLayout.CENTER);
        }
        
        panelLaberinto.revalidate();
        panelLaberinto.repaint();
    }
    
    // Clase interna para dibujar el laberinto
    private class LaberintoPanel extends JPanel {
        private Laberinto laberinto;
        private int tamañoCelda = 25;
        private List<Integer> rutaActual = new ArrayList<>();
        
        public LaberintoPanel(Laberinto laberinto) {
            this.laberinto = laberinto;
            calcularTamañoOptimo();
            setBackground(Color.WHITE);
        }
        
        public void setRutaActual(List<Integer> ruta) {
            this.rutaActual = ruta != null ? ruta : new ArrayList<>();
            repaint();
        }
        
        private void calcularTamañoOptimo() {
            int filas = laberinto.getFilas();
            int columnas = laberinto.getColumnas();
            
            // Calcular tamaño óptimo basado en el panel disponible
            int anchoDisponible = 400; // Ancho aproximado del panel
            int altoDisponible = 400;  // Alto aproximado del panel
            
            int tamañoPorAncho = anchoDisponible / columnas;
            int tamañoPorAlto = altoDisponible / filas;
            
            tamañoCelda = Math.min(Math.max(Math.min(tamañoPorAncho, tamañoPorAlto), 10), 40);
            
            // Establecer tamaño preferido del panel
            setPreferredSize(new Dimension(
                columnas * tamañoCelda + 1,
                filas * tamañoCelda + 1
            ));
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int filas = laberinto.getFilas();
            int columnas = laberinto.getColumnas();
            
            // Dibujar cuadrícula base
            dibujarCuadricula(g2d, filas, columnas);
            
            // Dibujar conexiones (caminos)
            dibujarConexiones(g2d);
            
            // Dibujar ruta actual si existe
            if (!rutaActual.isEmpty()) {
                dibujarRuta(g2d);
            }
            
            // Dibujar puntos especiales (inicio y fin)
            dibujarPuntosEspeciales(g2d);
            
            g2d.dispose();
        }
        
        private void dibujarCuadricula(Graphics2D g2d, int filas, int columnas) {
            // Fondo de las celdas (por defecto serán paredes)
            g2d.setColor(Color.LIGHT_GRAY);
            for (int fila = 0; fila < filas; fila++) {
                for (int col = 0; col < columnas; col++) {
                    int x = col * tamañoCelda;
                    int y = fila * tamañoCelda;
                    g2d.fillRect(x, y, tamañoCelda, tamañoCelda);
                }
            }
            
            // Dibujar celdas accesibles (nodos del grafo)
            g2d.setColor(Color.WHITE);
            for (Integer nodo : laberinto.getGrafo().keySet()) {
                Point pos = nodoAPosicion(nodo);
                int x = pos.x * tamañoCelda;
                int y = pos.y * tamañoCelda;
                g2d.fillRect(x, y, tamañoCelda, tamañoCelda);
            }
            
            // Dibujar bordes de la cuadrícula
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));
            for (int i = 0; i <= columnas; i++) {
                g2d.drawLine(i * tamañoCelda, 0, i * tamañoCelda, filas * tamañoCelda);
            }
            for (int i = 0; i <= filas; i++) {
                g2d.drawLine(0, i * tamañoCelda, columnas * tamañoCelda, i * tamañoCelda);
            }
        }
        
        private void dibujarConexiones(Graphics2D g2d) {
            g2d.setColor(new Color(200, 200, 200));
            g2d.setStroke(new BasicStroke(2));
            
            for (Map.Entry<Integer, List<Integer>> entrada : laberinto.getGrafo().entrySet()) {
                Integer nodoOrigen = entrada.getKey();
                Point posOrigen = nodoAPosicion(nodoOrigen);
                
                for (Integer nodoDestino : entrada.getValue()) {
                    Point posDestino = nodoAPosicion(nodoDestino);
                    
                    // Dibujar línea entre centros de las celdas
                    int x1 = posOrigen.x * tamañoCelda + tamañoCelda / 2;
                    int y1 = posOrigen.y * tamañoCelda + tamañoCelda / 2;
                    int x2 = posDestino.x * tamañoCelda + tamañoCelda / 2;
                    int y2 = posDestino.y * tamañoCelda + tamañoCelda / 2;
                    
                    g2d.drawLine(x1, y1, x2, y2);
                }
            }
        }
        
        private void dibujarRuta(Graphics2D g2d) {
            if (rutaActual.size() < 2) return;
            
            g2d.setColor(Color.BLUE);
            g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            
            for (int i = 0; i < rutaActual.size() - 1; i++) {
                Point pos1 = nodoAPosicion(rutaActual.get(i));
                Point pos2 = nodoAPosicion(rutaActual.get(i + 1));
                
                int x1 = pos1.x * tamañoCelda + tamañoCelda / 2;
                int y1 = pos1.y * tamañoCelda + tamañoCelda / 2;
                int x2 = pos2.x * tamañoCelda + tamañoCelda / 2;
                int y2 = pos2.y * tamañoCelda + tamañoCelda / 2;
                
                g2d.drawLine(x1, y1, x2, y2);
            }
            
            // Dibujar puntos de la ruta
            g2d.setColor(new Color(0, 0, 255, 150));
            for (Integer nodo : rutaActual) {
                Point pos = nodoAPosicion(nodo);
                int x = pos.x * tamañoCelda + tamañoCelda / 4;
                int y = pos.y * tamañoCelda + tamañoCelda / 4;
                g2d.fillOval(x, y, tamañoCelda / 2, tamañoCelda / 2);
            }
        }
        
        private void dibujarPuntosEspeciales(Graphics2D g2d) {
            // Punto de inicio (verde)
            if (laberinto.getInicio() >= 0) {
                Point posInicio = nodoAPosicion(laberinto.getInicio());
                g2d.setColor(Color.GREEN);
                int x = posInicio.x * tamañoCelda + 3;
                int y = posInicio.y * tamañoCelda + 3;
                g2d.fillOval(x, y, tamañoCelda - 6, tamañoCelda - 6);
                
                // Letra "S" para Start
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, tamañoCelda / 3));
                FontMetrics fm = g2d.getFontMetrics();
                String texto = "S";
                int textoX = posInicio.x * tamañoCelda + (tamañoCelda - fm.stringWidth(texto)) / 2;
                int textoY = posInicio.y * tamañoCelda + (tamañoCelda + fm.getAscent()) / 2;
                g2d.drawString(texto, textoX, textoY);
            }
            
            // Punto final (rojo)
            if (laberinto.getFin() >= 0) {
                Point posFin = nodoAPosicion(laberinto.getFin());
                g2d.setColor(Color.RED);
                int x = posFin.x * tamañoCelda + 3;
                int y = posFin.y * tamañoCelda + 3;
                g2d.fillOval(x, y, tamañoCelda - 6, tamañoCelda - 6);
                
                // Letra "E" para End
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, tamañoCelda / 3));
                FontMetrics fm = g2d.getFontMetrics();
                String texto = "E";
                int textoX = posFin.x * tamañoCelda + (tamañoCelda - fm.stringWidth(texto)) / 2;
                int textoY = posFin.y * tamañoCelda + (tamañoCelda + fm.getAscent()) / 2;
                g2d.drawString(texto, textoX, textoY);
            }
        }
        
        private Point nodoAPosicion(int nodo) {
            int columnas = laberinto.getColumnas();
            int fila = nodo / columnas;
            int columna = nodo % columnas;
            return new Point(columna, fila);
        }
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void actualizarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }
    
    public void ejecutarSistema() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }
}