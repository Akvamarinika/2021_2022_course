package ru.cft.focusstart.task3.minesweeper.model.cells;

public enum CellState {
    CLOSED("-"),
    MARKED("F"),
    EMPTY("0"),
    NUM_1("1"),
    NUM_2("2"),
    NUM_3("3"),
    NUM_4("4"),
    NUM_5("5"),
    NUM_6("6"),
    NUM_7("7"),
    NUM_8("8"),
    BOMB("*"),
    NO_BOMB("X")
    ;

    private final String symbolState;

    CellState(String symbolState) {
        this.symbolState = symbolState;
    }

    public String getSymbolState() {
        return symbolState;
    }
}
