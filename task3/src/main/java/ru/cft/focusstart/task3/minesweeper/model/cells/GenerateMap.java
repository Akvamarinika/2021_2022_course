package ru.cft.focusstart.task3.minesweeper.model.cells;

public interface GenerateMap {
    void init();
    int getValueCell(Position position);
    int getCountItems();
}
