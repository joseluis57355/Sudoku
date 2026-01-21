package org.joseluis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz {
    static class Casilla extends JButton {
        int fila;
        int columna;
        Casilla(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }
    }

    int Ancho = 600;
    int Alto = 650;


    JFrame frame = new JFrame("Sudoku");

    JLabel textLabel = new JLabel("Sudoku");

    JPanel textPanel = new JPanel();
    JPanel tableroPanel = new JPanel();
    JPanel botonesPanel = new JPanel();

    JButton numeroSeleccionado = null;

    // Genera la interfaz a partir de una plantilla de sudoku generada
    Interfaz() {
        frame.setSize(Ancho, Alto);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Sudoku");

        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        tableroPanel.setLayout(new GridLayout(1, 3));

        JButton botonFacil = new  JButton("Facil");
        JButton botonNormal = new  JButton("Normal");
        JButton botonDificil = new  JButton("Dificil");

        //TODO refinar botones
        botonFacil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarPartida("facil");
            }
        });

        botonNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarPartida("normal");
            }
        });

        botonDificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarPartida("dificil");
            }
        });

        tableroPanel.add(botonFacil);
        tableroPanel.add(botonNormal);
        tableroPanel.add(botonDificil);
        frame.add(tableroPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    void empezarPartida(String dificultad) {
        int[][] sudokuMatriz = Sudoku.generarSudokuAleatorio(dificultad);
        tableroPanel.removeAll();
        tableroPanel.setLayout(new GridLayout(9, 9));
        generarCasillas(sudokuMatriz);
        frame.add(tableroPanel, BorderLayout.CENTER);

        botonesPanel.setLayout(new GridLayout(1, 9));
        setupBotones();
        frame.add(botonesPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Genera cada casilla con su numero correspondiente en la matriz, y cada vez que se actualizan las casillas en blanco se revisa si es valido
    void generarCasillas(int[][] sudokuMatriz){
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                Casilla casilla = crearCasilla(sudokuMatriz, f, c);
                tableroPanel.add(casilla);

                casilla.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Casilla casilla = (Casilla) e.getSource();
                        int fila = casilla.fila;
                        int columna = casilla.columna;
                        if (numeroSeleccionado != null) {
                            if(Comprobar.comprobarSudoku(sudokuMatriz, fila, columna, Integer.parseInt(numeroSeleccionado.getText()))){
                                // Numero correcto
                                casilla.setBackground(Color.white);
                            }else{
                                // Numero repetido
                                casilla.setBackground(Color.orange);
                            }
                            casilla.setText(numeroSeleccionado.getText());
                            sudokuMatriz[fila][columna] = Integer.parseInt(numeroSeleccionado.getText());
                        }
                    }
                });
            }
        }
    }

    // Da los estilos y propiedades a cada casilla
    private static Casilla crearCasilla(int[][] sudokuMatriz, int f, int c) {
        Casilla casilla = new Casilla(f, c);
        int casillaValor = sudokuMatriz[f][c];
        if (casillaValor != 0) {
            casilla.setFont(new Font("Times New Roman", Font.BOLD, 30));
            casilla.setBackground(Color.lightGray);
            casilla.setText(String.valueOf(casillaValor));
        } else {
            casilla.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            casilla.setBackground(Color.white);
        }
        if((f == 2 && c == 2) || (f == 2 && c == 5) || (f == 5 && c == 2) ||  (f == 5 && c == 5)) {
            casilla.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.black));
        }else if (f == 2 || f == 5) {
            casilla.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.black));
        }else if (c == 2 || c == 5) {
            casilla.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 5, Color.black));
        }else{
            casilla.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        casilla.setFocusable(false);
        return casilla;
    }

    // Agrega botones en la parte inferior para introducir valores en las casillas en blanco
    void setupBotones(){
        for (int i = 1; i < 10; i++) {
            JButton boton = new JButton();
            boton.setFont(new Font("Times New Roman", Font.BOLD, 30));
            boton.setText(String.valueOf(i));
            boton.setFocusable(false);
            boton.setBackground(Color.white);
            botonesPanel.add(boton);

            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton boton = (JButton)e.getSource();
                    if(numeroSeleccionado != null){
                        numeroSeleccionado.setBackground(Color.white);
                    }
                    numeroSeleccionado = boton;
                    numeroSeleccionado.setBackground(Color.lightGray);
                }
            });
        }
    }
}
