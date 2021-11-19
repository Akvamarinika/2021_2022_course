package ru.cft.focusstart.task3.minesweeper.model.specificfields;

import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.model.field.StateCell;

public interface GenerateField {
    void init();
    StateCell getValueCell(Position position);
    int getCountItems();
}
