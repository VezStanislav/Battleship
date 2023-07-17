package view;

import resources.GUIData;

import java.util.ArrayList;

import static resources.GUIData.*;
import static view.GameEngine.FIELD_SIZE;

public class ConsoleRender {
    /**
     * Prints the game fields for both users.
     *
     * @param firstUserShips          - ships of the current user
     * @param firstUserAttackedCells  - array of boolean values representing attacked cells
     * @param secondUserShips         - ships of the other user
     * @param secondUserAttackedCells - array of boolean values representing attacked cells
     */
    public void printFields(ArrayList<Ship> firstUserShips, boolean[][] firstUserAttackedCells,
                            ArrayList<Ship> secondUserShips, boolean[][] secondUserAttackedCells) {
        System.out.println("Your field:");
        printOpenField(firstUserShips, secondUserAttackedCells);

        System.out.println("Opponent's field:");
        printCloseField(secondUserShips, firstUserAttackedCells);
    }

    /**
     * Prints all ships and attacked cells on the field.
     *
     * @param ships         - list of ships
     * @param attackedCells - array of boolean values representing attacked cells
     */
    public void printOpenField(ArrayList<Ship> ships, boolean[][] attackedCells) {
        printField(ships, attackedCells, true);
    }

    /**
     * Prints destroyed ships and attacked cells on the field.
     *
     * @param ships         - list of ships
     * @param attackedCells - array of boolean values representing attacked cells
     */
    private void printCloseField(ArrayList<Ship> ships, boolean[][] attackedCells) {
        printField(ships, attackedCells, false);
    }

    /**
     * Prints the game field with ships and attacked cells.
     *
     * @param ships         - list of ships
     * @param attackedCells - array of boolean values representing attacked cells
     * @param isOpen        - true: shows all ships, false: shows only destroyed ships
     */
    private void printField(ArrayList<Ship> ships, boolean[][] attackedCells, boolean isOpen) {
        printTopBar();

        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(GUIData.yCoordinates[i] + "│");

            for (int j = 0; j < FIELD_SIZE; j++) {
                String output = String.valueOf(GUIData.closeCell);

                if(attackedCells!=null){
                    if (attackedCells[j][i]) {
                        output = fillLine(ANSI_BLACK, GUIData.destroyedCell);
                    }
                }

                for (Ship ship : ships) {
                    if (ship.cellContains(j, i)) {
                        if (isOpen) {
                            output = ship.cellAlive(j, i) ? fillLine(ANSI_BLUE, GUIData.shipCell)
                                    : fillLine(ANSI_RED, GUIData.destroyedShip);
                        } else {
                            output = ship.cellAlive(j, i) ?  output
                                    : fillLine(ANSI_RED, GUIData.destroyedShip);
                        }
                    }
                }

                System.out.print(output + " ");
            }
            System.out.println();
        }
    }

    /**
     * Returns a colored line with a special line REST.
     *
     * @param color - ANSI color code
     * @param line  - character symbol
     * @return colored line string
     */
    private String fillLine(String color, char line) {
        return color + line + GUIData.RESET;
    }

    /**
     * Prints the top bar in the game.
     */
    private void printTopBar() {
        System.out.print(" │");
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(GUIData.xCoordinates[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < FIELD_SIZE + 1; i++) {
            System.out.print("——");
        }
        System.out.println();
    }
}

