package ru.cft.focusstart.task3.minesweeper.model.field;

import ru.cft.focusstart.task3.minesweeper.model.specificfields.statescell.Closed;
import ru.cft.focusstart.task3.minesweeper.model.specificfields.statescell.IState;
import ru.cft.focusstart.task3.minesweeper.model.specificfields.statescell.Opening;

public class Cell {
    Position position;
    boolean isClosed = true;
    boolean isFlagged = false;
    StateCell stateCell;

//    IState state;

    public Cell(Position position) {
        this.position = position;
        this.stateCell = StateCell.EMPTY;
    }

//    public void setState(IState state) {
//        this.state = state;
//    }
//
//    public void changeState() {
//        StateCell cellState = stateCell;
//        if (state instanceof Closed){
//            setStateCell(new Opening());
//        }
//    }

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
}
