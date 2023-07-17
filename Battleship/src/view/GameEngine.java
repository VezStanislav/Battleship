package view;

import java.util.*;

public class GameEngine {
    private final int N_SHIPS = 10;
    public static final int FIELD_SIZE = 10;
    private boolean playerTurn;
    private ArrayList<Ship> playerShips;
    private ArrayList<Ship> compShips;
    private boolean[][] attackedPlayerCells;
    private boolean[][] attackedCompCells;
    private ConsoleRender consoleRender;
    private AIPlayer computer;
    private Player player;

    public GameEngine() {
        playerTurn = true;

        playerShips = new ArrayList<>(N_SHIPS);
        compShips = new ArrayList<>(N_SHIPS);

        consoleRender = new ConsoleRender();

        computer = new AIPlayer();
        player = new Player();

        attackedPlayerCells = new boolean[FIELD_SIZE][FIELD_SIZE];
        attackedCompCells = new boolean[FIELD_SIZE][FIELD_SIZE];
    }

    /**
     * initialize player ships and computer ships
     */
    public void initializeGame() {
        player.initShips(playerShips);
        computer.initShips(compShips);
    }

    /**
     * make move player or computer
     */
    public void makeMove() {
        if (playerTurn) {
            player.makeMove(compShips,attackedPlayerCells);
        } else {
            computer.makeMove(playerShips,attackedCompCells);
        }
        playerTurn = !playerTurn;
    }

    /**
     * check all ships for undestroyed
     * @param ships the ArrayList of ships to check
     * @return true if all ships are undestroyed, otherwise false
     */
    private boolean shipsAlive(ArrayList<Ship> ships){
        for (Ship s : ships) {
            if(s.shipAlive()){
                 return true;
            }
        }
        return false;
    }

    /**
     *checking the game at the end, if all the ships of the computer
     *or the player have been destroyed, the game is over
     * @return boolean value
     */
    public boolean isGameOver() {
        return shipsAlive(playerShips) && shipsAlive(compShips);
    }

    public boolean isPlayerWin(){
        return shipsAlive(playerShips);
    }

    /**
     * print player field and computer field
     */
    public void printFields() {
        consoleRender.printFields(playerShips, attackedPlayerCells, compShips, attackedCompCells);
    }

    public void printShipsInfo() {
        System.out.println("Comp ships:");
        for (Ship s : compShips) {
            System.out.println(s);
        }
        System.out.println("Player ships:");
        for (Ship s : playerShips) {
            System.out.println(s);
        }
    }
}
