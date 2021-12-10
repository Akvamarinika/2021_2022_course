package ru.cft.focusstart.task3.minesweeper.view.windows;

import ru.cft.focusstart.task3.minesweeper.view.ButtonType;

public interface CellEventListener {
    void onMouseClick(int x, int y, ButtonType buttonType);
}
