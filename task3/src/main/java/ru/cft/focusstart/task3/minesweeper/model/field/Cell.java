package ru.cft.focusstart.task3.minesweeper.model.field;

public class Cell {
    Position position;
    boolean isClosed = true;
    boolean isFlagged = false;
    StateCell stateCell;

    public Cell(Position position) {
        this.position = position;
        this.stateCell = StateCell.EMPTY;
    }

    public Position getPosition() {
        return position;
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

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "position=" + position +
                ", isClosed=" + isClosed +
                ", isFlagged=" + isFlagged +
                ", stateCell=" + stateCell +
                '}';
    }
}
