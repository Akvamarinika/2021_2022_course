package ru.cft.focusstart.task3.minesweeper.view;

import ru.cft.focusstart.task3.minesweeper.controller.Controller;
import ru.cft.focusstart.task3.minesweeper.model.field.Cell;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.records.Gamer;

import java.util.List;

public interface View {
    void updateFlag(Cell cell);
    void updateViewCountBombs(int bombs);
    void updatePlayingField(Cell cell);
    void displayTableRecords(List<Gamer> bestGamers);
    void createRecordsWindow();
    void createWinWindow();
    void createLoseWindow();
    void createNewPlayingField(int weight, int height, List<Position> allPositions);
    void setBombsCount(int bombs);
    GameType getGameType();

    void setController(Controller controller);
    void setTimerValue(int timeSeconds);
    void notifyControllerAboutEventsView();

}
