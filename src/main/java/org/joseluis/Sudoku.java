package org.joseluis;

import java.util.Arrays;
import java.util.Random;

public class Sudoku {

    Sudoku(){
        Interfaz interfaz = new Interfaz();
    }

    // Genera la plantilla vacia de un sudoku, y se intenta resolver con numeros aleatorios
    static int[][] generarSudokuAleatorio(String dificultad){
        int[][] sudokuAleatorio = new int[9][9];
        for (int[] ints : sudokuAleatorio) {
            Arrays.fill(ints, 0);
        }

        // Primero genera de forma aleatoria las "cajas" 1, 5 y 9
        generarSudokuBase(sudokuAleatorio);

        // Despues resuelve el sudoku
        Resolver.resolverSudoku(sudokuAleatorio);

        System.out.println("Sudoku generado:"+ Arrays.deepToString(sudokuAleatorio));

        return ocultarCeldas(sudokuAleatorio, dificultad);
    }

    // Genera de forma aleatoria las "cajas" 1, 5 y 9 que no estan relacionadas ni horizontal ni verticalmente
    private static void generarSudokuBase(int[][] sudokuAleatorio){
        Random random = new Random();
        for (int caja = 0; caja < 9; caja+=3) {
            for (int f = caja; f < caja+3; f++) {
                for (int c = caja; c < caja+3; c++) {
                    int n = (int)(Math.random() * 9) + 1;
                    if(!Comprobar.comprobarCaja(sudokuAleatorio, f, c, n)){
                        sudokuAleatorio[f][c] = n;
                    }else {
                        c--;
                    }
                }
            }
        }
    }

    // Oculta de forma aleatoria celdas dependiendo de la dificultad seleccionada
    private static int[][] ocultarCeldas(int[][] sudoku, String dificultad){
        int numOcultadas;
        switch (dificultad){
            case "facil": numOcultadas = 21;
                break;
            case "normal": numOcultadas = 35;
                break;
            case "dificil": numOcultadas = 50;
                break;
            default: numOcultadas = 0;
        }
        for (int i = 0; i < numOcultadas; i++) {
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
