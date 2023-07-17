package view;

public class ShotInputValidator {
    /**
     * @param   attackedCells - array of boolean values representing attacked cells
     * @param   point - attacked cell
     * @return  true - this cell is unharmed, false - the cell has experienced previous attacks.
     */
    public boolean isCorrectedMove(boolean[][] attackedCells, Point point) {
        if (point == null) {
            return false;
        }
        return !attackedCells[point.getX()][point.getY()];
    }
}
