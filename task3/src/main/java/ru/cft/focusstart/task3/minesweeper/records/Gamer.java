package ru.cft.focusstart.task3.minesweeper.records;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.cft.focusstart.task3.minesweeper.view.GameType;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Gamer {
    GameType gameType;
    String name;
    int time;
}
