package ru.cft.focusstart.task3.minesweeper.model.field;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position position){
            return this.x == position.x && this.y == position.y;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public String toString() {
        return " x=" + x + " y=" + y;
    }
}
