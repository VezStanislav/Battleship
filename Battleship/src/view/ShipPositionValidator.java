package view;

import java.util.ArrayList;
import java.util.Arrays;

import static view.GameEngine.FIELD_SIZE;

public class ShipPositionValidator {
    /**
     *
     * @param ship that will be tested
     * @param ships all the ships
     * @param size number of ship cells
     * @return true - ship is correct, false - ship is incorrect
     */
    public boolean isCorrected(Ship ship, ArrayList<Ship> ships, int size) {
        return isLinear(ship) && isShipPlacementValid(ship, ships) && ship.size() == size;
    }

    /**
     * Check if the ship is placed linearly (horizontally or vertically).
     * @param ship the ship that will be tested
     * @return true if the ship is placed linearly, otherwise false
     */
    private boolean isLinear(Ship ship) {
        int[] xCoordinates = new int[ship.size()];
        int[] yCoordinates = new int[ship.size()];
        boolean xLinear = true;
        boolean yLinear = true;

        Point[] points = ship.getPoints().toArray(new Point[ship.getPoints().size()]);
        for (int i = 0; i < ship.size(); i++) {
            xCoordinates[i] = points[i].getX();
            yCoordinates[i] = points[i].getY();
        }

        Arrays.sort(xCoordinates);
        Arrays.sort(yCoordinates);

        for (int i = 0; i < ship.size() - 1; i++) {
            if (xCoordinates[i + 1] - xCoordinates[i] != 1) {
                xLinear = false;
            }
            if (yCoordinates[i + 1] - yCoordinates[i] != 1) {
                yLinear = false;
            }
        }

        return xLinear || yLinear;
    }

    /**
     * checking the ship for correct placement.
     * @param ship that will be tested
     * @param ships ArrayList<Ship>
     * @return
     */
    private boolean isShipPlacementValid(Ship ship, ArrayList<Ship> ships) {
        boolean[][] occupiedCells = new boolean[FIELD_SIZE][FIELD_SIZE];
        Point[] points = ship.getPoints().toArray(new Point[ship.getPoints().size()]);

        for (Point point : points) {
            if (point.getX() > FIELD_SIZE - 1 || point.getX() < 0
                    || point.getY() > FIELD_SIZE - 1 || point.getY() < 0) {
                return false;
            }
        }

        for (Ship tempShip : ships) {
            Point[] currentShip = tempShip.getPoints().toArray(new Point[tempShip.getPoints().size()]);

            for (Point point : currentShip) {
                occupiedCells[point.getX()][point.getY()] = true;

                if (point.getX() + 1 < FIELD_SIZE) {
                    occupiedCells[point.getX() + 1][point.getY()] = true;
                }
                if (point.getX() - 1 >= 0) {
                    occupiedCells[point.getX() - 1][point.getY()] = true;
                }
                if (point.getY() + 1 < FIELD_SIZE) {
                    occupiedCells[point.getX()][point.getY() + 1] = true;
                }
                if (point.getY() - 1 >= 0) {
                    occupiedCells[point.getX()][point.getY() - 1] = true;
                }
                if (point.getX() + 1 < FIELD_SIZE && point.getY() + 1 < FIELD_SIZE) {
                    occupiedCells[point.getX() + 1][point.getY() + 1] = true;
                }
                if (point.getX() - 1 >= 0 && point.getY() - 1 >= 0) {
                    occupiedCells[point.getX() - 1][point.getY() - 1] = true;
                }
                if (point.getX() - 1 >= 0 && point.getY() + 1 < FIELD_SIZE) {
                    occupiedCells[point.getX() - 1][point.getY() + 1] = true;
                }
                if (point.getX() + 1 < FIELD_SIZE && point.getY() - 1 >= 0) {
                    occupiedCells[point.getX() + 1][point.getY() - 1] = true;
                }

            }
        }

        for (Point point : points) {
            if (occupiedCells[point.getX()][point.getY()]) {
                return false;
            }
        }

        return true;
    }

}
