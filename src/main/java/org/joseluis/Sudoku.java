package org.joseluis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sudoku {
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

    int[][] sudokuMatriz =
    {{1, 0, 2, 5, 6, 7, 0, 4, 8},
        {5, 4, 0, 3, 8, 9, 2, 1, 7},
        {9, 7, 8, 0, 0, 0, 6, 0, 5},
        {2, 6, 4, 0, 1, 8, 0, 5, 3},
        {7, 1, 5, 6, 3, 0, 8, 9, 0},
        {3, 0, 9, 4, 0, 5, 1, 0, 6},
        {0, 0, 7, 1, 2, 3, 4, 6, 9},
        {6, 0, 1, 0, 5, 0, 3, 8, 2},
        {4, 2, 3, 8, 9, 0, 5, 7, 0}
    };

    JFrame frame = new JFrame("Sudoku");

    JLabel textLabel = new JLabel("Sudoku");

    JPanel textPanel = new JPanel();
    JPanel tableroPanel = new JPanel();
    JPanel botonesPanel = new JPanel();

    JButton numeroSeleccionado = null;

    Sudoku(){

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

        tableroPanel.setLayout(new GridLayout(9, 9));
        generarCasillas();
        frame.add(tableroPanel, BorderLayout.CENTER);

        botonesPanel.setLayout(new GridLayout(1, 9));
        setupBotones();
        frame.add(botonesPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    void generarCasillas(){
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
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
                tableroPanel.add(casilla);

                casilla.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Casilla casilla = (Casilla) e.getSource();
                        int fila = casilla.fila;
                        int columna = casilla.columna;
                        if (numeroSeleccionado != null) {
                            if(comprobarSudoku(fila, columna, Integer.parseInt(numeroSeleccionado.getText()))){
                                // Numero repetido
                                casilla.setBackground(Color.orange);
                            }else{
                                // Numero correcto
                                casilla.setBackground(Color.white);
                            }
                            casilla.setText(numeroSeleccionado.getText());
                            sudokuMatriz[fila][columna] = Integer.parseInt(numeroSeleccionado.getText());
                        }
                    }
                });
            }
        }
    }

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

    boolean comprobarSudoku(int fila, int columna, int numero){
        return  comprobarFila(fila, numero) &&
            comprobarColumna(columna, numero) &&
            comprobarCaja(fila, columna, numero);
    }

    boolean comprobarFila(int fila, int numero){
        for (int c = 0; c < 9; c++) {
            if(sudokuMatriz[fila][c] == numero){
                return true;
            }
        }
        return false;
    }

    boolean comprobarColumna(int columna, int numero){
        for (int f = 0; f < 9; f++) {
            if(sudokuMatriz[f][columna] == numero){
                return true;
            }
        }
        return false;
    }

    boolean comprobarCaja(int fila, int columna, int numero){
        int filaCaja = fila - fila % 3;
        int columnaCaja = columna - columna % 3;

        for (int i = filaCaja; i < filaCaja + 3; i++) {
            for (int j = columnaCaja; j < columnaCaja + 3; j++) {
                if(sudokuMatriz[i][j] == numero){
                    return true;
                }
            }
        }
        return false;
    }
}
