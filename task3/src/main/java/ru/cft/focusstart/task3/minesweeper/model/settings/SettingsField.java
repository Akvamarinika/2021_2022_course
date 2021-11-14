package ru.cft.focusstart.task3.minesweeper.model.settings;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.view.GameType;
import java.util.ArrayList;

@Slf4j
public class SettingsField {
    private static GameType gameType;
    private static ArrayList<Position> listPositions;
    private static int weight;
    private static int height;
    private static int countBombs;

    public SettingsField(GameType gameType) {
        SettingsField.gameType = gameType;
        determinationSizeOfPlayingField();
    }

    private void generateListPositions(int weight, int height) {
        listPositions = new ArrayList<>();
        for (int y = 0; y < height; y++){
            for (int x = 0; x < weight; x++){
                listPositions.add(new Position(x, y));
            }
        }
    }

    private void determinationSizeOfPlayingField(){
        //                break;
        //            default:
        //                log.warn("Неизвестный тип игры. По умолчанию тип: Novice.");
        switch (gameType) {
            case NOVICE -> {
                countBombs = ParamField.NOVICE.getCountBombs();
                weight = ParamField.NOVICE.getWeight();
                height = ParamField.NOVICE.getHeight();
            }
            case MEDIUM -> {
                countBombs = ParamField.MEDIUM.getCountBombs();
                weight = ParamField.MEDIUM.getWeight();
                height = ParamField.MEDIUM.getHeight();
            }
            case EXPERT -> {
                countBombs = ParamField.EXPERT.getCountBombs();
                weight = ParamField.EXPERT.getWeight();
                height = ParamField.EXPERT.getHeight();
            }
        }
        generateListPositions(weight, height);
    }

    public static ArrayList<Position> getListPositions() {
        return listPositions;
    }

    public static int getWeight() {
        return weight;
    }

    public static int getHeight() {
        return height;
    }

    public static int getCountBombs() {
        return countBombs;
    }
}
