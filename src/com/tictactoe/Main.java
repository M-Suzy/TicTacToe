package com.tictactoe;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new GameDemo().startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
