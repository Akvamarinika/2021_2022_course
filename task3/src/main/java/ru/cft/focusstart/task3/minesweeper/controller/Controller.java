package ru.cft.focusstart.task3.minesweeper.controller;

import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.view.GameType;

public interface Controller {
    void rightClickOnMouse(Position position);
    void leftOrMiddleClickOnMouse(boolean middle, Position position);
    void notifyAboutNewGame();
    void notifyAboutChangeOfGameType(GameType gameType);
    void reportBestPlayer(String name);
}
