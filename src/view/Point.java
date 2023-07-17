package view;

import java.util.Objects;

public class Point {
    private int x,y;
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }
    public Point(){}
    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void setPosition(Point point){
        this.x = point.getX();
        this.y = point.getY();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return this.x == point.getX() && this.y == point.getY();
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
