package com.tictactoe;

import java.util.Arrays;

public class Board {
    private char[][] table = new char[3][3];
    String result;

    public Board() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(table[i], ' ');
        }
    }
    public void markPosition(int i, int j, char m){
       if(table[i][j]==' '){
           table[i][j] = m;
       }
       else System.out.println("position is already occupied");
    }

    public void printBoard() {
        for (char[] i : table) {
            for (int j = 0; j < 3; j++) {
                if (j < 2)
                    System.out.print(" " + i[j] + " |");
                else System.out.println(" " + i[j]);
            }
            System.out.println("---|---|---");
        }
        System.out.println("Result: " + result);
    }
}
