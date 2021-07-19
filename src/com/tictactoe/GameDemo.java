package com.tictactoe;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class GameDemo {
    Player player_o;
    Player player_x;


    /**
     * @author: Grigor
     * */
    public void startGame(){
        //initPlayers
        String name1 = "";
        String name2 = "";
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
        while (true) {
            System.out.println(player_x.getName() + ", input move position");
            int i = sc.nextInt();
            int j = sc.nextInt();
            player_x.makeMove(i, j);
            if(checkForWin(i, j, Player.board)) {
                System.out.println(player_x.getName() + " won the game");
                break;
            }
            System.out.println(player_o.getName() + ", input move position");
            i = sc.nextInt();
            j = sc.nextInt();
            player_o.makeMove(i, j);
            if(checkForWin(i, j, Player.board)) {
                System.out.println(player_o.getName() + " won the game");
                break;
            }
        }
    }

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
     * end Game and save the result if needed
     * @author Suzy
     */
    private void endGame(boolean save) throws IOException {
        System.out.println(Player.board.result);
        if(save) saveGame();
    }
    /**
     * saves Game result in gameResults.txt file
     * @author Suzy
     */
    private void saveGame() throws IOException {
        try(FileWriter gameDb = new FileWriter("gameResults.txt")){
            gameDb.write("Player X: "+ player_x.getName() +"\n");
            gameDb.write("Player O: "+ player_o.getName() +"\n"+"Result: "+ Player.board.result);
        } catch(IOException e){
            e.printStackTrace();
        }

    }

}
