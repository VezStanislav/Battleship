package view;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private Scanner scanner;
    private ConsoleRender consoleRender;
    private ShipPositionValidator positionValidator;
    private CreatePointFromLine createPointFromLine;
    private ShotInputValidator shotValidator;
    public Player(){
        scanner = new Scanner(System.in);
        consoleRender = new ConsoleRender();
        positionValidator = new ShipPositionValidator();
        createPointFromLine = new CreatePointFromLine();
        shotValidator = new ShotInputValidator();
    }

    /**
     * create all player ships
     */
    public void initShips(ArrayList<Ship> playerShips) {
        for (int i = 4; i > 0; i--) {
            for (int j = i - 1; j < 4; j++) {
                Ship ship;
                boolean isCorrect = true;
                int size = i;

                consoleRender.printOpenField(playerShips, null);

                do {
                    if (!isCorrect) {
                        System.out.println("Coordinates don`t correct");
                    }

                    ship = createShip(size);
                } while (!(isCorrect = positionValidator.isCorrected(ship, playerShips, size)));

                playerShips.add(ship);
            }
        }
    }

    /**
     * create the correct ship from the user coordinates from the console
     * @param size number of ship cells
     * @return Ship object
     */
    private Ship createShip(int size) {
        Ship ship = new Ship(size);
        Point point;

        System.out.println("creation of an " + size + "-cells ship");
        for (int i = 0; i < size; i++) {
            do {
                System.out.println("enter coordinates " + (i + 1) + " cell");
                String coordinates = scanner.nextLine();
                point = createPointFromLine.lineToPoint(coordinates);
            } while (point == null);
            ship.addCell(point);
        }
        return ship;
    }
    /**
     * input a line of coordinates from the console and convert it to coordinates, then destroy this cell
     */
    public void makeMove(ArrayList<Ship> compShips, boolean[][] attackedPlayerCells) {
        String line;
        Point movePoint;
        do {
            System.out.println("your move:");
            line = scanner.nextLine();
            movePoint = createPointFromLine.lineToPoint(line);

        } while (!shotValidator.isCorrectedMove(attackedPlayerCells, movePoint));

        attackedPlayerCells[movePoint.getX()][movePoint.getY()] = true;

        for (Ship s :compShips) {
            if(s.cellContains(movePoint.getX(),movePoint.getY())){
                s.destroyCell(movePoint);
            }
        }

    }


}
