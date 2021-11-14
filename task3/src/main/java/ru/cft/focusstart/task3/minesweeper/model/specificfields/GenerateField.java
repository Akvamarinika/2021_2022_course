package ru.cft.focusstart.task3.minesweeper.model.specificfields;

import ru.cft.focusstart.task3.minesweeper.model.field.Position;

public interface GenerateField {
    void init();
    int getValueCell(Position position);
    int getCountItems();
}
