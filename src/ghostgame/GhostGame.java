/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ghostgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author river
 */
public final class GhostGame extends JFrame {

    public String[][] espacio_juego = new String[6][6];
    public Player jugador1;
    public Player jugador2;
    public Player jugador_actual = null;
    public JFrame interfaz;
    public JPanel pantalla;
    public JButton[][] casillas = new JButton[6][6];
    public String[][] tablero = new String[6][6];
    public static final int tamaño = 6;
    public String dificultad = "NORMAL";
    public String modo_juego = "ALEATORIO";
    public static Player[] jugadores = new Player[100];
    public static int jugadores_registrados = 0;
    private boolean fantasmas_colocados = false;
    public static GhostGame juego = new GhostGame();
    public static menu_principal menu = new menu_principal();

    public GhostGame() {
        jugadores();
        iniciar_tablero();
    }
    
    public void jugadores() {
        jugadores[0] = new Player("Juanito_01", "12345");
        jugadores[0].setUsername("Juanito_01");
        jugadores[0].setPuntos(34);
        
        jugadores[1] = new Player("MariaJ_22", "abcde");
        jugadores[1].setUsername("MariaJ_22");
        jugadores[1].setPuntos(48);
        
        jugadores[2] = new Player("Carlos_Gamer", "qwerty");
        jugadores[2].setUsername("Carlos_Gamer");
        jugadores[2].setPuntos(27);
        
        jugadores[3] = new Player("Sofia_23", "password");
        jugadores[3].setUsername("Sofia_23");
        jugadores[3].setPuntos(41);
        
        jugadores[4] = new Player("Felipe123", "98765");
        jugadores[4].setUsername("Felipe123");
        jugadores[4].setPuntos(36);
        
        jugadores[5] = new Player("AnaM_11", "11111");
        jugadores[5].setUsername("AnaM_11");
        jugadores[5].setPuntos(44);
        
        jugadores[6] = new Player("Luis_99", "zxcvbn");
        jugadores[6].setUsername("Luis_99");
        jugadores[6].setPuntos(50);
        
        jugadores[7] = new Player("Pedro_87", "abc123");
        jugadores[7].setUsername("Pedro_87");
        jugadores[7].setPuntos(32);
        
        jugadores[8] = new Player("Laura_G", "qwertz");
        jugadores[8].setUsername("Laura_G");
        jugadores[8].setPuntos(28);
        
        jugadores[9] = new Player("Javier_44", "asdfg");
        jugadores[9].setUsername("Javier_44");
        jugadores[9].setPuntos(25);
    }
    
    public static void ordenar_jugadores() {
        int n = GhostGame.jugadores.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (jugadores[j] != null && jugadores[j + 1] != null && jugadores[j].getPuntos() < jugadores[j + 1].getPuntos()) {
                    Player temp = jugadores[j];
                    jugadores[j] = jugadores[j + 1];
                    jugadores[j + 1] = temp;
                }
            }
        }
    }
    
    public void eliminar_cuenta() {
        if (jugador1 != null) {
            jugador1.setUsername("");
            jugador1.setPuntos(0);
        }
    }

    public Player inicio_sesion(String username, String password) {
        for (int i = 0; i < jugadores_registrados; i++) {
            if (jugadores[i].getUsername().equals(username) && jugadores[i].getPassword().equals(password)) {
                jugador1 = jugadores[i];
                return jugadores[i];
            }
        }
        System.out.println("Error: Username o contraseña incorrectos.");
        return null;
    }
    
    public void crear_jugador(String username, String password) {
        if (jugadores_registrados >= jugadores.length) {
            return;
        }

        jugadores[jugadores_registrados] = new Player(username, password);
        jugadores_registrados++;
    }

    public boolean usuario_existente(String username) {
        for (int i = 0; i < jugadores_registrados; i++) {
            if (jugadores[i].getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public Player jugador2(String username) {
        for (int i = 0; i < jugadores_registrados; i++) {
            if (jugadores[i].getUsername().equals(username) && !username.equalsIgnoreCase(String.valueOf(jugador1.getUsername()))) {
                jugador2 = jugadores[i];
                return jugadores[i];
            } else if (username.equalsIgnoreCase(String.valueOf(jugador1.getUsername()))){
                JOptionPane.showMessageDialog(this, "No se puede ingresar el nombre del jugador actual.");
            }
        }
        return null;
    }

    public void iniciar_tablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                espacio_juego[i][j] = "";
            }
        }
    }
    
    public void reiniciar_tablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                espacio_juego[i][j] = "";
                casillas[i][j] = new JButton();
                casillas[i][j].setText("");
                casillas[i][j].setBackground(Color.getHSBColor(32, 81, 38));
            }
        }
        jugador1.resetFantasmas();
        jugador2.resetFantasmas();
    }

    public void crear_tablero() {
        interfaz = new JFrame("Juego Ghosts");
        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        interfaz.setLocation(450, 100);
        pantalla = new JPanel(new GridLayout(tamaño, tamaño));
        casillas = new JButton[tamaño][tamaño];

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                casillas[i][j] = new JButton();
                casillas[i][j].setBackground(Color.getHSBColor(32, 81, 38));
                casillas[i][j].setPreferredSize(new Dimension(100, 100));
                pantalla.add(casillas[i][j]);
            }
        }

        interfaz.add(pantalla);
        interfaz.pack();
        interfaz.setVisible(true);
        if (modo_juego.equals("ALEATORIO")) {
            fantasmas_aleatorio();
        } else if (modo_juego.equals("MANUAL")) {
            fantasmas_manual();
        }
    }

    public void iniciar_juego() {
        reiniciar_tablero();
        jugador_actual = jugador1;
        crear_tablero();
        actualizar_tablero_visibilidad();
        inicializar_fantasmas();
        detectar_fantasmas();
    }

    public void movimiento() {
        if (jugador_actual == null) {
            JOptionPane.showMessageDialog(null, "Error: Jugador actual no está inicializado.");
            return;
        }

        int filaInicial, columnaInicial;

        while (!game_over()) {
            while (true) {
                try {
                    filaInicial = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila del fantasma que desea mover (0-5):"));
                    columnaInicial = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la columna del fantasma que desea mover (0-5):"));
                    
                    if (!coordenada_valida(filaInicial, columnaInicial)) {
                        JOptionPane.showMessageDialog(null, "Coordenadas fuera de rango. Intente nuevamente.");
                        continue;
                    }

                    String fantasma = espacio_juego[filaInicial][columnaInicial];
                    if (!fantasma.equals("FBueno1") && !fantasma.equals("FMalo1") && !fantasma.equals("FBueno2") && !fantasma.equals("FMalo2") && !fantasma.equals("FTrampa1") && !fantasma.equals("FTrampa2")) {
                        JOptionPane.showMessageDialog(null, "No hay un fantasma válido en esa posición. Intente nuevamente.");
                        continue;
                    }

                    if (!esFantasmaDeJugador(filaInicial, columnaInicial, jugador_actual)) {
                        JOptionPane.showMessageDialog(null, "Ese fantasma no te pertenece. Intente nuevamente.");
                        continue;
                    }

                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese números válidos para las coordenadas.");
                }
            }

            int filaDestino, columnaDestino;
            while (true) {
                try {
                    filaDestino = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila de destino (0-5):"));
                    columnaDestino = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la columna de destino (0-5):"));

                    if (!coordenada_valida(filaDestino, columnaDestino)) {
                        JOptionPane.showMessageDialog(null, "Coordenadas fuera de rango. Intente nuevamente.");
                        continue;
                    }
                    
                    if (Math.abs(filaDestino - filaInicial) > 1 || Math.abs(columnaDestino - columnaInicial) > 1) {
                        JOptionPane.showMessageDialog(null, "El movimiento solo puede ser de una casilla en horizontal o vertical.");
                        continue;
                    }

                    String fantasmaEnDestino = espacio_juego[filaDestino][columnaDestino];

                    if (!fantasmaEnDestino.equals("")) {
                        if (esFantasmaDeJugador(filaDestino, columnaDestino, jugador_actual)) {
                            JOptionPane.showMessageDialog(null, "No puedes capturar tus propios fantasmas. Intente nuevamente.");
                            continue;
                        }

                        if (fantasmaEnDestino.equals("FBueno1") || fantasmaEnDestino.equals("FMalo1") || fantasmaEnDestino.equals("FBueno2") || fantasmaEnDestino.equals("FMalo2") && !fantasmaEnDestino.equals("FTrampa1") && !fantasmaEnDestino.equals("FTrampa2")) {
                            String atacante = espacio_juego[filaInicial][columnaInicial];
                            if (atacante.equals("FMalo1") && (fantasmaEnDestino.equals("FBueno2") || fantasmaEnDestino.equals("FMalo2") || fantasmaEnDestino.equals("FTrampa2"))) {
                                JOptionPane.showMessageDialog(null, "Fantasma capturado por " + jugador1.getUsername());
                                capturarFantasma(filaDestino, columnaDestino);
                            } else if (atacante.equals("FMalo2") && (fantasmaEnDestino.equals("FBueno1") || fantasmaEnDestino.equals("FMalo1") || fantasmaEnDestino.equals("FTrampa1"))) {
                                JOptionPane.showMessageDialog(null, "Fantasma capturado por " + jugador2.getUsername());
                                capturarFantasma(filaDestino, columnaDestino);
                            } else {
                                JOptionPane.showMessageDialog(null, "Solo los fantasmas adecuados pueden capturar.");
                                continue;
                            }
                        }
                    }

                    if (filaInicial != filaDestino && columnaInicial != columnaDestino) {
                        JOptionPane.showMessageDialog(null, "Los fantasmas solo pueden moverse en línea recta (horizontal o vertical).");
                        continue;
                    }

                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese números válidos para las coordenadas.");
                }
            }

            String fantasma = espacio_juego[filaInicial][columnaInicial];
            espacio_juego[filaDestino][columnaDestino] = fantasma;
            espacio_juego[filaInicial][columnaInicial] = "";

            if (fantasma.equals("FBueno1") || fantasma.equals("FBueno2")) {
                casillas[filaDestino][columnaDestino].setText("F");
            } else if (fantasma.equals("FMalo1") || fantasma.equals("FMalo2")) {
                casillas[filaDestino][columnaDestino].setText("F");
            } else if (fantasma.equals("FTrampa1") || fantasma.equals("FTrampa2")) {
                casillas[filaDestino][columnaDestino].setText("F");
            }

            casillas[filaDestino][columnaDestino].setBackground(casillas[filaInicial][columnaInicial].getBackground());
            casillas[filaInicial][columnaInicial].setText("");
            casillas[filaInicial][columnaInicial].setBackground(Color.getHSBColor(32, 81, 38));

            JOptionPane.showMessageDialog(null, "Movimiento realizado con éxito.");
            cambio_turno();
        }
    }

    public void capturarFantasma(int fila, int columna) {
        espacio_juego[fila][columna] = "";
        casillas[fila][columna].setText("");
        casillas[fila][columna].setBackground(Color.getHSBColor(32, 81, 38));
    }
    
    public void fantasmas_aleatorio() {
        int contadorJugador1 = 0;
        int contadorJugador2 = 0;
        int fila = 0;
        int columna = 0;
        int fantasmas = 0;
        
        if (dificultad.equals("GENIUS")) {
            fantasmas = 2;
            while (contadorJugador1 < fantasmas) {
                do {
                    fila = (int) (Math.random() * 2);
                    columna = (int) (Math.random() * 6);
                } while ((fila == 0 && columna == 0) || (fila == 0 && columna == 5));

                if (espacio_juego[fila][columna].equals("")) {
                    tablero[fila][columna] = (contadorJugador1 % 2 == 0) ? "F1" : "F1";
                    casillas[fila][columna].setText(contadorJugador1 % 2 == 0 ? "F" : "F");
                    casillas[fila][columna].setBackground(contadorJugador1 % 2 == 0 ? Color.GREEN : Color.RED);
                    espacio_juego[fila][columna] = contadorJugador1 % 2 == 0 ? "FBueno1" : "FMalo1";
                    contadorJugador1++;
                }
            }
            
            for (int i = 0; i < 4; i++) {
                do {
                    fila = (int) (Math.random() * 2);
                    columna = (int) (Math.random() * 6);
                } while ((fila == 0 && columna == 0) || (fila == 0 && columna == 5) || !espacio_juego[fila][columna].equals(""));

                tablero[fila][columna] = "FTrampa1";
                casillas[fila][columna].setText("F");
                casillas[fila][columna].setBackground(Color.GRAY);
                espacio_juego[fila][columna] = "FTrampa1";
            }
            
            while (contadorJugador2 < fantasmas) {
                do {
                    fila = 4 + (int) (Math.random() * 2);
                    columna = (int) (Math.random() * 6);
                } while ((fila == 5 && columna == 5) || (fila == 5 && columna == 0));

                if (espacio_juego[fila][columna].equals("")) {
                    tablero[fila][columna] = (contadorJugador2 % 2 == 0) ? "F2" : "F2";
                    casillas[fila][columna].setText(contadorJugador2 % 2 == 0 ? "F" : "F");
                    casillas[fila][columna].setBackground(contadorJugador2 % 2 == 0 ? Color.GREEN : Color.RED);
                    espacio_juego[fila][columna] = contadorJugador2 % 2 == 0 ? "FBueno2" : "FMalo2";
                    contadorJugador2++;
                }
            }
            
            for (int i = 0; i < 4; i++) {
                do {
                    fila = 4 + (int) (Math.random() * 2);
                    columna = (int) (Math.random() * 6);
                } while ((fila == 5 && columna == 5) || (fila == 5 && columna == 0) || !espacio_juego[fila][columna].equals(""));

                tablero[fila][columna] = "FTrampa2";
                casillas[fila][columna].setText("F");
                casillas[fila][columna].setBackground(Color.GRAY);
                espacio_juego[fila][columna] = "FTrampa2";
            }
            
        } else {
            if (dificultad.equals("NORMAL")) {
                fantasmas = 8;
            } else if (dificultad.equals("EXPERT")) {
                fantasmas = 4;
            }

            while (contadorJugador1 < fantasmas) {
                do {
                    fila = (int) (Math.random() * 2);
                    columna = (int) (Math.random() * 6);
                } while ((fila == 0 && columna == 0) || (fila == 0 && columna == 5));

                if (espacio_juego[fila][columna].equals("")) {
                    tablero[fila][columna] = (contadorJugador1 % 2 == 0) ? "F1" : "F1";
                    casillas[fila][columna].setText(contadorJugador1 % 2 == 0 ? "F" : "F");
                    casillas[fila][columna].setBackground(contadorJugador1 % 2 == 0 ? Color.GREEN : Color.RED);
                    espacio_juego[fila][columna] = contadorJugador1 % 2 == 0 ? "FBueno1" : "FMalo1";
                    contadorJugador1++;
                }
            }

            while (contadorJugador2 < fantasmas) {
                do {
                    fila = 4 + (int) (Math.random() * 2);
                    columna = (int) (Math.random() * 6);
                } while ((fila == 5 && columna == 5) || (fila == 5 && columna == 0));

                if (espacio_juego[fila][columna].equals("")) {
                    tablero[fila][columna] = (contadorJugador2 % 2 == 0) ? "F2" : "F2";
                    casillas[fila][columna].setText(contadorJugador2 % 2 == 0 ? "F" : "F");
                    casillas[fila][columna].setBackground(contadorJugador2 % 2 == 0 ? Color.GREEN : Color.RED);
                    espacio_juego[fila][columna] = contadorJugador2 % 2 == 0 ? "FBueno2" : "FMalo2";
                    contadorJugador2++;
                }
            }
        }        
        fantasmas_colocados = true;
        actualizar_tablero_visibilidad();
        movimiento();
    }

    public void fantasmas_manual() {
        int contadorJugador1 = 0;
        int contadorJugador2 = 0;
        int fila = 0;
        int columna = 0;
        int fantasmas = 0;

        if (dificultad.equals("GENIUS")) {
            fantasmas = 2;
            while (contadorJugador1 < fantasmas) {
                do {
                    String inputFila = JOptionPane.showInputDialog("Jugador 1: Introduce la fila (0-1) para colocar un fantasma:");
                    String inputColumna = JOptionPane.showInputDialog("Jugador 1: Introduce la columna (0-5) para colocar un fantasma:");

                    try {
                        fila = Integer.parseInt(inputFila);
                        columna = Integer.parseInt(inputColumna);
                    } catch (NumberFormatException e) {
                        continue;
                    }

                } while ((fila < 0 || fila > 1) || (columna < 0 || columna > 5) || (fila == 0 && columna == 0) || (fila == 0 && columna == 5) || !espacio_juego[fila][columna].equals(""));

                tablero[fila][columna] = (contadorJugador1 % 2 == 0) ? "F1" : "F1";
                casillas[fila][columna].setText(contadorJugador1 % 2 == 0 ? "F" : "F");
                casillas[fila][columna].setBackground(contadorJugador1 % 2 == 0 ? Color.GREEN : Color.RED);
                espacio_juego[fila][columna] = contadorJugador1 % 2 == 0 ? "FBueno1" : "FMalo1";
                contadorJugador1++;
            }

            for (int i = 0; i < 4; i++) {
                do {
                    String inputFila = JOptionPane.showInputDialog("Jugador 1: Introduce la fila (0-1) para colocar un fantasma trampa:");
                    String inputColumna = JOptionPane.showInputDialog("Jugador 1: Introduce la columna (0-5) para colocar un fantasma trampa:");

                    try {
                        fila = Integer.parseInt(inputFila);
                        columna = Integer.parseInt(inputColumna);
                    } catch (NumberFormatException e) {
                        continue;
                    }

                } while ((fila < 0 || fila > 1) || (columna < 0 || columna > 5) || (fila == 0 && columna == 0) || (fila == 0 && columna == 5) || !espacio_juego[fila][columna].equals(""));

                tablero[fila][columna] = "FTrampa1";
                casillas[fila][columna].setText("F");
                casillas[fila][columna].setBackground(Color.GRAY);
                espacio_juego[fila][columna] = "FTrampa1";
            }

            while (contadorJugador2 < fantasmas) {
                do {
                    String inputFila = JOptionPane.showInputDialog("Jugador 2: Introduce la fila (4-5) para colocar un fantasma:");
                    String inputColumna = JOptionPane.showInputDialog("Jugador 2: Introduce la columna (0-5) para colocar un fantasma:");

                    try {
                        fila = Integer.parseInt(inputFila);
                        columna = Integer.parseInt(inputColumna);
                    } catch (NumberFormatException e) {
                        continue;
                    }

                } while ((fila < 4 || fila > 5) || (columna < 0 || columna > 5) || (fila == 5 && columna == 5) || (fila == 5 && columna == 0) || !espacio_juego[fila][columna].equals(""));

                tablero[fila][columna] = (contadorJugador2 % 2 == 0) ? "F2" : "F2";
                casillas[fila][columna].setText(contadorJugador2 % 2 == 0 ? "F" : "F");
                casillas[fila][columna].setBackground(contadorJugador2 % 2 == 0 ? Color.GREEN : Color.RED);
                espacio_juego[fila][columna] = contadorJugador2 % 2 == 0 ? "FBueno2" : "FMalo2";
                contadorJugador2++;
            }

            for (int i = 0; i < 4; i++) {
                do {
                    String inputFila = JOptionPane.showInputDialog("Jugador 2: Introduce la fila (4-5) para colocar un fantasma trampa:");
                    String inputColumna = JOptionPane.showInputDialog("Jugador 2: Introduce la columna (0-5) para colocar un fantasma trampa:");

                    try {
                        fila = Integer.parseInt(inputFila);
                        columna = Integer.parseInt(inputColumna);
                    } catch (NumberFormatException e) {
                        continue;
                    }

                } while ((fila < 4 || fila > 5) || (columna < 0 || columna > 5) || (fila == 5 && columna == 5) || (fila == 5 && columna == 0) || !espacio_juego[fila][columna].equals(""));

                tablero[fila][columna] = "FTrampa2";
                casillas[fila][columna].setText("F");
                casillas[fila][columna].setBackground(Color.GRAY);
                espacio_juego[fila][columna] = "FTrampa2";
            }

        } else {
            if (dificultad.equals("NORMAL")) {
                fantasmas = 8;
            } else if (dificultad.equals("EXPERT")) {
                fantasmas = 4;
            }

            while (contadorJugador1 < fantasmas) {
                do {
                    String inputFila = JOptionPane.showInputDialog("Jugador 1: Introduce la fila (0-1) para colocar un fantasma:");
                    String inputColumna = JOptionPane.showInputDialog("Jugador 1: Introduce la columna (0-5) para colocar un fantasma:");
                    
                    try {
                        fila = Integer.parseInt(inputFila);
                        columna = Integer.parseInt(inputColumna);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    
                } while ((fila < 0 || fila > 1) || (columna < 0 || columna > 5) || (fila == 0 && columna == 0) || (fila == 0 && columna == 5) || !espacio_juego[fila][columna].equals(""));

                if (espacio_juego[fila][columna].equals("")) {
                    tablero[fila][columna] = (contadorJugador1 % 2 == 0) ? "F1" : "F1";
                    casillas[fila][columna].setText(contadorJugador1 % 2 == 0 ? "F" : "F");
                    casillas[fila][columna].setBackground(contadorJugador1 % 2 == 0 ? Color.GREEN : Color.RED);
                    espacio_juego[fila][columna] = contadorJugador1 % 2 == 0 ? "FBueno1" : "FMalo1";
                    contadorJugador1++;
                }
            }

            while (contadorJugador2 < fantasmas) {
                do {
                    String inputFila = JOptionPane.showInputDialog("Jugador 2: Introduce la fila (4-5) para colocar un fantasma:");
                    String inputColumna = JOptionPane.showInputDialog("Jugador 2: Introduce la columna (0-5) para colocar un fantasma:");

                    try {
                        fila = Integer.parseInt(inputFila);
                        columna = Integer.parseInt(inputColumna);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    
                } while ((fila < 4 || fila > 5) || (columna < 0 || columna > 5) || (fila == 5 && columna == 5) || (fila == 5 && columna == 0) || !espacio_juego[fila][columna].equals(""));

                if (espacio_juego[fila][columna].equals("")) {
                    tablero[fila][columna] = (contadorJugador2 % 2 == 0) ? "F2" : "F2";
                    casillas[fila][columna].setText(contadorJugador2 % 2 == 0 ? "F" : "F");
                    casillas[fila][columna].setBackground(contadorJugador2 % 2 == 0 ? Color.GREEN : Color.RED);
                    espacio_juego[fila][columna] = contadorJugador2 % 2 == 0 ? "FBueno2" : "FMalo2";
                    contadorJugador2++;
                }
            }
        }
        fantasmas_colocados = true;
        actualizar_tablero_visibilidad();
        movimiento();
    }

    public void actualizar_tablero_visibilidad() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                String contenido = espacio_juego[i][j];

                if (contenido == null || contenido.isEmpty()) {
                    casillas[i][j].setBackground(Color.getHSBColor(32, 81, 38));
                    casillas[i][j].setText("");
                } else if (jugador_actual.equals(jugador1)) {
                    if (contenido.equals("FBueno2") || contenido.equals("FMalo2") || contenido.equals("FTrampa2")) {
                        casillas[i][j].setBackground(Color.WHITE);
                        casillas[i][j].setText("F");
                    } else if (contenido.equals("FBueno1")) {
                        casillas[i][j].setBackground(Color.GREEN);
                        casillas[i][j].setText("F");
                    } else if (contenido.equals("FMalo1")) {
                        casillas[i][j].setBackground(Color.RED);
                        casillas[i][j].setText("F");
                    } else if (contenido.equals("FTrampa1")) {
                        casillas[i][j].setBackground(Color.GRAY);
                        casillas[i][j].setText("F");
                    }
                } else if (jugador_actual.equals(jugador2)) {
                    if (contenido.equals("FBueno1") || contenido.equals("FMalo1") || contenido.equals("FTrampa1")) {
                        casillas[i][j].setBackground(Color.WHITE);
                        casillas[i][j].setText("F");
                    } else if (contenido.equals("FBueno2")) {
                        casillas[i][j].setBackground(Color.GREEN);
                        casillas[i][j].setText("F");
                    } else if (contenido.equals("FMalo2")) {
                        casillas[i][j].setBackground(Color.RED);
                        casillas[i][j].setText("F");
                    } else if (contenido.equals("FTrampa2")) {
                        casillas[i][j].setBackground(Color.GRAY);
                        casillas[i][j].setText("F");
                    }
                }
            }
        }
    }

    public void inicializar_fantasmas() {
        for (int i = 0; i < 4; i++) {
            espacio_juego[5][i] = (i % 2 == 0) ? "FBueno1" : "FMalo1";
            jugador1.agregarFantasma(5, i);
        }

        for (int i = 0; i < 4; i++) {
            espacio_juego[0][i] = (i % 2 == 0) ? "FBueno2" : "FMalo2";
            jugador2.agregarFantasma(0, i);
        }
    }

    public void mostrar_fantasmas(Player jugador) {
        String mensaje = "Fantasmas de " + jugador.getUsername() + ":\n";
        for (int i = 0; i < jugador.cantidad_fantasmas; i++) {
            mensaje += "Fila: " + jugador.fantasmas[i][0] + ", Columna: " + jugador.fantasmas[i][1] + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void detectar_fantasmas() {
        jugador1.resetFantasmas();
        jugador2.resetFantasmas();

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                String fantasma = espacio_juego[i][j];
                if (fantasma.equals("FBueno1") || fantasma.equals("FMalo1") || fantasma.equals("FTrampa1")) {
                    jugador1.agregarFantasma(i, j);
                } else if (fantasma.equals("FBueno2") || fantasma.equals("FMalo2") || fantasma.equals("FTrampa2")) {
                    jugador2.agregarFantasma(i, j);
                }
            }
        }
    }

    public boolean esFantasmaDeJugador(int fila, int columna, Player jugador) {
        if (!coordenada_valida(fila, columna)) {
            return false;
        }

        String contenido = espacio_juego[fila][columna];
        if (jugador == jugador1 && (contenido.equals("FBueno1") || contenido.equals("FMalo1") || contenido.equals("FTrampa1"))) {
            return true;
        } else if (jugador == jugador2 && (contenido.equals("FBueno2") || contenido.equals("FMalo2") || contenido.equals("FTrampa2"))) {
            return true;
        }

        return false;
    }

    public void mostrar_tablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(espacio_juego[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void turno_jugador() {
        mostrar_fantasmas(jugador_actual);
    }

    public boolean fantasma_valido(int fila, int columna) {
        return !espacio_juego[fila][columna].equals("");
    }

    public boolean coordenada_valida(int fila, int columna) {
        return fila >= 0 && fila < tamaño && columna >= 0 && columna < tamaño;
    }

    public boolean movimiento_valido(int fila, int columna, int filaDestino, int columnaDestino) {
        if (filaDestino < 0 || filaDestino >= 6 || columnaDestino < 0 || columnaDestino >= 6) {
            return false;
        }

        if (!tablero[filaDestino][columnaDestino].isEmpty()) {
            return false;
        }

        return (Math.abs(fila - filaDestino) == 1 && columna == columnaDestino)
                || (Math.abs(columna - columnaDestino) == 1 && fila == filaDestino);
    }

    public void cambio_turno() {
        if (jugador_actual == jugador1) {
            jugador_actual = jugador2;
        } else {
            jugador_actual = jugador1;
        }
        JOptionPane.showMessageDialog(null, "Es el turno de: " + jugador_actual.getUsername());
        actualizar_tablero_visibilidad();
    }

    public boolean game_over() {
        if (!fantasmas_colocados) {
            return false;
        } else {
            int fantasmas_buenos_j1 = contar_fantasmas_buenos(jugador2);
            int fantasmas_buenos_j2 = contar_fantasmas_buenos(jugador1);
            if (fantasmas_buenos_j1 == 0) {
                String resultado = jugador1.getUsername() + " gana al capturar todos los fantasmas buenos de " + jugador2.getUsername();
                JOptionPane.showMessageDialog(this, resultado);
                jugador1.añadir_log(resultado);
                jugador1.setPuntos(jugador1.getPuntos()+1);
                interfaz.dispose();
                menu.setVisible(true);
                return true;
            }
            if (fantasmas_buenos_j2 == 0) {
                String resultado = jugador2.getUsername() + " gana al capturar todos los fantasmas buenos de " + jugador1.getUsername();
                JOptionPane.showMessageDialog(this, resultado);
                jugador2.añadir_log(resultado);
                jugador2.setPuntos(jugador2.getPuntos()+1);
                interfaz.dispose();
                menu.setVisible(true);
                return true;
            }

            int fantasmas_malos_j1 = contar_fantasmas_malos(jugador1);
            int fantasmas_malos_j2 = contar_fantasmas_malos(jugador2);
            if (fantasmas_malos_j1 == 0) {
                String resultado = jugador2.getUsername() + " gana al eliminar todos los fantasmas malos de " + jugador1.getUsername();
                JOptionPane.showMessageDialog(this, resultado);
                jugador2.añadir_log(resultado);
                jugador2.setPuntos(jugador2.getPuntos()+1);
                interfaz.dispose();
                menu.setVisible(true);
                return true;
            }
            if (fantasmas_malos_j2 == 0) {
                String resultado = jugador1.getUsername() + " gana al eliminar todos los fantasmas malos de " + jugador2.getUsername();
                JOptionPane.showMessageDialog(this, resultado);
                jugador1.añadir_log(resultado);
                jugador1.setPuntos(jugador1.getPuntos()+1);
                interfaz.dispose();
                menu.setVisible(true);
                return true;
            }

            if (espacio_juego[0][0].equals("FBueno2")) {
                String resultado = jugador2.getUsername() + " gana al llevar un fantasma bueno a la salida de " + jugador1.getUsername();
                JOptionPane.showMessageDialog(this, resultado);
                jugador2.añadir_log(resultado);
                jugador2.setPuntos(jugador2.getPuntos()+1);
                interfaz.dispose();
                menu.setVisible(true);
                return true;
            }
            if (espacio_juego[0][5].equals("FBueno2")) {
                String resultado = jugador2.getUsername() + " gana al llevar un fantasma bueno a la salida de " + jugador1.getUsername();
                JOptionPane.showMessageDialog(this, resultado);
                jugador2.añadir_log(resultado);
                jugador2.setPuntos(jugador2.getPuntos()+1);
                interfaz.dispose();
                menu.setVisible(true);
                return true;
            }
            if (espacio_juego[5][0].equals("FBueno1")) {
                String resultado = jugador1.getUsername() + " gana al llevar un fantasma bueno a la salida de " + jugador2.getUsername();
                JOptionPane.showMessageDialog(this, resultado);
                jugador1.añadir_log(resultado);
                jugador1.setPuntos(jugador1.getPuntos()+1);
                interfaz.dispose();
                menu.setVisible(true);
                return true;
            }
            if (espacio_juego[5][5].equals("FBueno1")) {
                String resultado = jugador1.getUsername() + " gana al llevar un fantasma bueno a la salida de " + jugador2.getUsername();
                JOptionPane.showMessageDialog(this, resultado);
                jugador1.añadir_log(resultado);
                jugador1.setPuntos(jugador1.getPuntos()+1);
                interfaz.dispose();
                menu.setVisible(true);
                return true;
            }
            return false;
        }
    }

    public int contar_fantasmas_buenos(Player jugador) {
        int count = 0;
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if ((jugador == jugador1 && espacio_juego[i][j].equals("FBueno1")) || (jugador == jugador2 && espacio_juego[i][j].equals("FBueno2"))) {
                    count++;
                }
            }
        }
        return count;
    }

    public int contar_fantasmas_malos(Player jugador) {
        int count = 0;
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if ((jugador == jugador1 && espacio_juego[i][j].equals("FMalo1")) || (jugador == jugador2 && espacio_juego[i][j].equals("FMalo2"))) {
                    count++;
                }
            }
        }
        return count;
    }

    public void mostrar_resultado() {
        System.out.println("El juego ha terminado. Resultados:");
    }
}