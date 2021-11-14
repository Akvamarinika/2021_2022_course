package ru.cft.focusstart.task3.minesweeper.model.field;

public class Cell {
    Position position;
    boolean isClosed = true;
    StateCell stateCell;

    public Cell(Position position, StateCell stateCell) {
        this.position = position;
        this.stateCell = stateCell;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public StateCell getStateCell() {
        return stateCell;
    }

    public void setStateCell(StateCell stateCell) {
        this.stateCell = stateCell;
    }
}
