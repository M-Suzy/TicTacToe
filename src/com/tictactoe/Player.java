package com.tictactoe;
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
    public void makeMove(int i, int j) {

    }

}
