package ru.cft.focusstart.task3.minesweeper.records;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.cft.focusstart.task3.minesweeper.view.GameType;

@Getter
@Setter
@EqualsAndHashCode
public class Gamer {
   private GameType gameType;
   private String name = "Unknown";
   private int time;

    public Gamer(GameType gameType, int time) {
        this.gameType = gameType;
        this.time = time;
    }

    @Override
    public String toString() {
        return gameType + ": " + name + " BestTime: " + time + " sec";
    }
}
