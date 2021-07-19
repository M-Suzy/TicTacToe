package com.tictactoe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameDemo {
    Player player_o;
    Player player_x;


    /**
     * @author: Grigor
     * */
    public void startGame() throws IOException {
        //initPlayers
        String name1 = "";
        String name2 = "";
        String save = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Player 1 will start the game");
        System.out.println("Player 1 please enter your name:");
        name1 = sc.nextLine();

        System.out.println("Player 2 please enter your name:");
        name2 = sc.nextLine();

        player_x = new Player(name1);
        player_o = new Player(name2);
        player_x.setShape('x');
        player_o.setShape('o');
        Player.board = new Board();
        //game logic
        System.out.println("couple of rules: 1. indexing starts from 0. first input horizontal index then vertical. ");
        while (sc.hasNext()) {
            System.out.println(player_x.getName() + ", input move position");
            int i=0, j=0;
            while(!sc.hasNextInt()) {
                try {
                    i = sc.nextInt();
                    j = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer");
                    sc.nextLine();
                }
            }
            i = sc.nextInt();
            j = sc.nextInt();
            while (i > 2 || i < 0 || j > 2 || j < 0) {
                System.out.println("invalid input, try again");
                i = sc.nextInt();
                j = sc.nextInt();
            }
            player_x.makeMove(i, j);
            if(checkForWin(i, j, Player.board)) {
                Player.board.result = player_x.getName();
                System.out.println("Do you wanna save game result?");
                save = sc.nextLine();
                if(save=="True")
                    endGame(true);
                else {
                    endGame(false);
                }
                break;
            }
            while(!sc.hasNextInt()) {
                try {
                    i = sc.nextInt();
                    j= sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer");
                    sc.nextLine();
                }
            }
            i = sc.nextInt();
            j = sc.nextInt();
            while (i > 2 || i < 0 || j > 2 || j < 0) {
                System.out.println("invalid input, try again");
                i = sc.nextInt();
                j = sc.nextInt();
            }
            player_o.makeMove(i, j);
            if(checkForWin(i, j, Player.board)) {
                Player.board.result = player_o.getName();
                System.out.println("Do you wanna save game results?");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                save = sc.nextLine();
                if(save.equalsIgnoreCase("Yes")) {
                    endGame(true);
                    System.out.println(save);

                }
                else {
                    endGame(false);
                }
                sc.close();
            }
            else if(checkForDraw()) {
                Player.board.result = "Draw";
                System.out.println("Do you wanna save game results?");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                save = sc.nextLine();
                if (save.equalsIgnoreCase("Yes")) {
                    endGame(true);
                    System.out.println(save);
                }
                else {
                    endGame(false);
                }
                sc.close();
            }
        }
    }

    /**
     * end Game and save the result if needed
     * @param i
     * @param j
     * @param b
     * @author Grigor
     */
    private boolean checkForWin(int i, int j, Board b) {
        char c = b.getPosition(i, j);
        //check row
        boolean checkRow = true;
        for (int t = 0; t < 3; t++) {
            if(b.getPosition(i, t) != c) {
                checkRow = false;
            }
        }
        if(checkRow)
            return true;

        //check column
        boolean checkColumn = true;
        for (int t = 0; t < 3; t++) {
            if(b.getPosition(t, j) != c) {
                checkColumn = false;
            }
        }

        if(checkColumn)
            return true;

        //checkDiagonal
        if((b.getPosition(0, 0) == c && b.getPosition(1, 1) == c && b.getPosition(2, 2) == c) ||
                (b.getPosition(0, 2) == c && b.getPosition(1, 1) == c && b.getPosition(2, 0) == c))
            return true;

        return false;
    }

    /**
     * checking draw: if no cell is empty
     * @author Suzy
     */
     private boolean checkForDraw(){
       return Player.board.hasEmptyCell();
     }

    /**
     * end Game and save the result if needed
     * @param save
     * @author Suzy
     */
    private void endGame(boolean save) throws IOException {
        System.out.println("Result: " + Player.board.result+ " won");
        if(save) saveGame();
    }
    /**
     * saves Game result in gameResults.txt file
     * @author Suzy
     */
    private void saveGame() throws IOException {
        try(BufferedWriter gameDb = new BufferedWriter(new FileWriter("gameResults.txt"))){
            gameDb.write("Player X: "+ player_x.getName() +"\n");
            gameDb.write("Player O: "+ player_o.getName() +"\n"+"Result: "+ Player.board.result+" won");
        } catch(IOException e){
            e.printStackTrace();
        }

    }

}
