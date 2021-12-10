package ru.cft.focusstart.task3.minesweeper.records;

import java.util.List;

public interface Records {
    void addBestGamer(Gamer newGamer);
    void writeJsonInFile();
    boolean isBestGamer(Gamer otherGamer);
    void readRecordsFromFile();
    List<Gamer> getBestGamers();
}
