package view;

import java.util.ArrayList;
import java.util.Random;

import static view.GameEngine.FIELD_SIZE;

public class AIPlayer {
    private Random random;
    private ShotInputValidator shotValidator;
    private ShipPositionValidator positionValidator;

    public AIPlayer() {
        random = new Random();
        shotValidator = new ShotInputValidator();
        positionValidator = new ShipPositionValidator();
    }

    /**
     * selecting a random undestroyed cell to destroy.
     */
    public void makeMove(ArrayList<Ship> playerShips, boolean[][] attackedCompCells) {
        int x, y;
        Point movePoint;
        do {
            x = random.nextInt(FIELD_SIZE - 1);
            y = random.nextInt(FIELD_SIZE - 1);
            movePoint = new Point(x, y);
        } while (!shotValidator.isCorrectedMove(attackedCompCells, movePoint));
        System.out.println("comp move:" + movePoint.getX() + " ," + movePoint.getY());

        for (Ship s : playerShips) {
            if (s.cellContains(movePoint.getX(), movePoint.getY())) {
                s.destroyCell(movePoint);
            }
        }

        attackedCompCells[movePoint.getX()][movePoint.getY()] = true;
    }

    /**
     * create computer ships with random coordinates
     */
    public void initShips(ArrayList<Ship> compShips) {
        for (int i = 4; i > 0; i--) {
            for (int j = i - 1; j < 4; j++) {
                Ship ship;
                int size = i;

                do {
                    ship = createShip(size);
                } while (!positionValidator.isCorrected(ship, compShips, size));
                compShips.add(ship);
            }
        }

    }

    /**
     * create the correct ship from random coordinates
     *
     * @param size number of ship cells
     * @return Ship object
     */
    public Ship createShip(int size) {
        Ship ship = new Ship(size);
        random = new Random();
        int x, y;
        boolean direction, coordinateX;
        x = random.nextInt(FIELD_SIZE - 1);
        y = random.nextInt(FIELD_SIZE - 1);
        direction = random.nextBoolean();
        coordinateX = random.nextBoolean();

        for (int i = 0; i < size; i++) {
            ship.addCell(x, y);
            if (coordinateX) {
                x += (direction) ? 1 : -1;
            } else {
                y += (direction) ? 1 : -1;
            }
        }

        return ship;
    }
}
