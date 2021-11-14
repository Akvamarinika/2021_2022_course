package ru.cft.focusstart.task3.minesweeper.model.specificfields;

import ru.cft.focusstart.task3.minesweeper.model.field.Position;

public class FlagField implements GenerateField {
    @Override
    public void init() {

    }

    @Override
    public int getValueCell(Position position) {
        return 0;
    }

    @Override
    public int getCountItems() {
        return 0;
    }
}
