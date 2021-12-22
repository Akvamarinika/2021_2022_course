package ru.cft.focusstart.task3.minesweeper.model.field;

public enum StateCell {
    OFF_THE_FIELD,
    EMPTY(0),
    NUM_1(1),
    NUM_2(2),
    NUM_3(3),
    NUM_4(4),
    NUM_5(5),
    NUM_6(6),
    NUM_7(7),
    NUM_8(8),
    BOMB,
    BOMBED,
    NO_BOMB
    ;

    private int valueCell;

    StateCell(int value) {
        this.valueCell = value;
    }

    StateCell(){}

    public int getValueCell() {
        return valueCell;
    }

    public StateCell getNextNumber(){
       return switch (this) {
            case EMPTY -> NUM_1;
            case NUM_1 -> NUM_2;
            case NUM_2 -> NUM_3;
            case NUM_3 -> NUM_4;
            case NUM_4 -> NUM_5;
            case NUM_5 -> NUM_6;
            case NUM_6 -> NUM_7;
            case NUM_7, NUM_8 -> NUM_8;
           default -> OFF_THE_FIELD;
       };
    }


}
