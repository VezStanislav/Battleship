package view;

import java.util.HashMap;
import java.util.Set;

/**
 * class for convenient control over all cells of the ship.
 */
public class Ship {
    // HashMap with a point and a boolean value indicating whether a cell is alive or dead
    private HashMap<Point, Boolean> cells;

    public Ship(int length) {
        cells = new HashMap<>(length);
    }

    public void addCell(int x, int y) {
        cells.put(new Point(x, y), true);
    }

    public void addCell(Point point) {
        cells.put(point, true);
    }

    public boolean shipAlive() {
        for (Boolean isAlive : cells.values()) {
            if (isAlive) {
                return true;
            }
        }
        return false;
    }

    public boolean cellContains(int x, int y) {
        return cells.containsKey(new Point(x, y));
    }

    public Set<Point> getPoints() {
        return cells.keySet();
    }

    public Boolean cellAlive(int x, int y) {
        return cells.get(new Point(x, y));
    }

    public int size() {
        return cells.size();
    }

    public void destroyCell(Point point) {
        cells.put(point, false);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "cells=" + cells +
                '}';
    }
}
