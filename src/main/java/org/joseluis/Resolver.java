package org.joseluis;

public class Resolver {

    // Resuelve el sudoku de forma recursiva, el boolean aleatorio se utiliza para la hora de crear un sudoku de forma aleatoria
    static boolean resolverSudoku(int[][] sudoku){
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if(sudoku[f][c] == 0){
                    // Genera numeros y comprueba si son validos en esa posicion
                    for (int n = 1; n < 10; n++) {
                        // Si el numero es valido continua con el siguiente, y si no lo es intenta con otro numero de forma recursiva
                        if(Comprobar.comprobarSudoku(sudoku, f, c, n)){
                            sudoku[f][c] = n;
                            if (resolverSudoku(sudoku)) {
                                return true;
                            } else  {
                                sudoku[f][c] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
