package com.tictactoe;

import java.util.Scanner;

/**
 * @author: Grigor
 */
public class Player {
    private String name;
    private char shape; // X or O
    private int score;
    static Board board;
    //we need static board, so that when calling makeMove() method we change the board

    Player(String userName){
        setName(userName);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getShape() {
        return shape;
    }

    public void setShape(char shape) {
        this.shape = shape;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // will change the board
    // after each move prints the board
    public boolean makeMove(int i, int j) {
        if (board.getPosition(i, j) == ' '){
            board.markPosition(i, j, this.shape);
            System.out.println("Board after this move");
            board.printBoard();
            return true;
        }
        System.out.println("position already marked, try another position");
        return false;
    }
}
