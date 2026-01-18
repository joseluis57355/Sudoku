package org.joseluis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Sudoku {

    Sudoku(){
        Interfaz interfaz = new Interfaz(generarSudokuAleatorio());
    }

    // Genera la plantilla vacia de un sudoku, y se intenta resolver con numeros aleatorios
    private int[][] generarSudokuAleatorio(){
        int[][] sudokuAleatorio = new int[9][9];
        for (int[] ints : sudokuAleatorio) {
            Arrays.fill(ints, 0);
        }
        // Se intenta resolver el sudoku de forma aleatoria y si tarda demasiado se vuelve a intentar
        if (!Resolver.resolverSudoku(sudokuAleatorio, true)) {
            System.out.println("No consiguio generar sudoku, intentando nuevamente");
            generarSudokuAleatorio();
        }else{
            // Si se genera el sudoku correctamente se devuelve el sudoku completo
            System.out.println("Sudoku generado:"+ Arrays.deepToString(sudokuAleatorio));
        }
        return ocultarCeldas(sudokuAleatorio);
    }

    // Oculta de forma aleatoria 21 celdas del sudoku
    private int[][] ocultarCeldas(int[][] sudoku){
        for (int i = 0; i < 21; i++) {
            int fila = (int)(Math.random() * 9) ;
            int columna = (int)(Math.random() * 9) ;
            if(sudoku[fila][columna] != 0){
                sudoku[fila][columna] = 0;
            }else {
                i--;
            }
        }
        return sudoku;
    }

}
