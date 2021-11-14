package ru.cft.focusstart.task3.minesweeper.model;

import ru.cft.focusstart.task3.minesweeper.model.settings.SettingsField;
import ru.cft.focusstart.task3.minesweeper.model.specificfields.BombField;
import ru.cft.focusstart.task3.minesweeper.model.specificfields.FlagField;
import ru.cft.focusstart.task3.minesweeper.view.GameType;

public class ModelGame {
    private static GameState gameState;
    private static BombField bombField;
    private static FlagField flagField;
    private static SettingsField settingsField;


    public ModelGame(GameType gameType) {
        ModelGame.settingsField = new SettingsField(gameType);

    }

//    private void generateListPositions(int weight, int height) {
//        listPositions = new ArrayList<>();
//        for (int y = 0; y < height; y++){
//            for (int x = 0; x < weight; x++){
//                listPositions.add(new Position(x, y));
//            }
//        }
//    }

}
