package org.joseluis;

public class Comprobar {

    // Comprueba si el numero dentro de la fila y columna del sudoku es valido, devuelve true si el numero es correcto y no se repite.
    static boolean comprobarSudoku(int[][] sudoku, int fila, int columna, int numero){
        return  !comprobarFila(sudoku, fila, numero) &&
                !comprobarColumna(sudoku, columna, numero) &&
                !comprobarCaja(sudoku, fila, columna, numero);
    }

    // Devuelve true si el numero se repite en la fila
    private static boolean comprobarFila(int[][] sudoku, int fila, int numero){
        for (int c = 0; c < 9; c++) {
            if(sudoku[fila][c] == numero){
                return true;
            }
        }
        return false;
    }

    // Devuelve true si el numero se repite en la columna
    private static boolean comprobarColumna(int[][] sudoku, int columna, int numero){
        for (int f = 0; f < 9; f++) {
            if(sudoku[f][columna] == numero){
                return true;
            }
        }
        return false;
    }

    // Devuelve true si el numero se repite en la caja
    private static boolean comprobarCaja(int[][] sudoku, int fila, int columna, int numero){
        int filaCaja = fila - fila % 3;
        int columnaCaja = columna - columna % 3;

        for (int i = filaCaja; i < filaCaja + 3; i++) {
            for (int j = columnaCaja; j < columnaCaja + 3; j++) {
                if(sudoku[i][j] == numero){
                    return true;
                }
            }
        }
        return false;
    }
}
