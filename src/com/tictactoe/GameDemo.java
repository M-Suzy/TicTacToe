package com.tictactoe;

import java.io.FileWriter;
import java.io.IOException;

public class GameDemo {
    static Board board;
    Player player_o;
    Player player_x;


    /**
     * @author: Grigor
     * */
    public void startGame(){
        //initPlayers
        //game logic
        //after each move printBoard();
    }

    /**
     * end Game and save the result if needed
     * @author Suzy
     */
    private void endGame(boolean save) throws IOException {
        System.out.println(board.result);
        if(save) saveGame();
    }
    /**
     * saves Game result in gameResults.txt file
     * @author Suzy
     */
    private void saveGame() throws IOException {
        try(FileWriter gameDb = new FileWriter("gameResults.txt")){
            gameDb.write("Player X: "+ player_x.getName() +"\n");
            gameDb.write("Player O: "+ player_o.getName() +"\n"+"Result: "+board.result);
        } catch(IOException e){
            e.printStackTrace();
        }

    }

}
