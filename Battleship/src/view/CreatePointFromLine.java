package view;

import resources.GUIData;

public class CreatePointFromLine {
    /**
     * this method convert line with 2 symbols to Point object
     * @param line is a string like "e3", "F8", "a0"
     * @return Point object
     */
    public Point lineToPoint(String line) {
        if (line.length() != 2) {
            return null;
        }

        line = line.toUpperCase();

        int x = -1;
        int y = line.charAt(1) - '0';

        for (int i = 0; i < GUIData.xCoordinates.length; i++) {
            if (GUIData.xCoordinates[i] == line.charAt(0)) {
                x = i;
                break;
            }
        }

        if (x < 0 || x > GUIData.xCoordinates.length - 1 || y < 0 || y > GUIData.yCoordinates.length - 1) {
            return null;
        }

        return new Point(x, y);
    }
}
