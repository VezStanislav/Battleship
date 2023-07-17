package aplication;

import view.GameEngine;

public class MainClass {
    public static void main(String[] args) {
        while(true){
            GameEngine gameEngine = new GameEngine();
            gameEngine.initializeGame();
            gameEngine.printFields();
            while(gameEngine.isGameOver()){
                gameEngine.makeMove();
                gameEngine.printFields();
            }
            System.out.println("Game over");
            if(gameEngine.isPlayerWin()){
                System.out.println("Player won");
            }
            else {
                System.out.println("Computer won");
            }
        }
    }
}
