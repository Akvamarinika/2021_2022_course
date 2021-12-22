package ru.cft.focusstart.task3.minesweeper.model;

import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.timer.TimerGame;
import ru.cft.focusstart.task3.minesweeper.view.GameType;

public interface Model {
    StateGame getStateGame();
    void updateGameTypeInSettingsField(GameType gameType);
    void toggleFlag(Position position);
    void openCellsIfNumberOfCellEqualsCountFlags(Position position);
    void openCells(Position position);
    void checkGameState();
    void setTimer(TimerGame timer);
    void resetField();
    void firstMove(Position firstPos);
    void setNameBestGamer(String name);
}
