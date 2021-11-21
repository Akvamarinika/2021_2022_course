package ru.cft.focusstart.task3.minesweeper;

import ru.cft.focusstart.task3.minesweeper.controller.ControllerGame;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.timer.TimerGame;
import ru.cft.focusstart.task3.minesweeper.view.HighScoresWindow;
import ru.cft.focusstart.task3.minesweeper.view.MainWindow;
import ru.cft.focusstart.task3.minesweeper.view.SettingsWindow;

public class Application {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);
        TimerGame timerGame = new TimerGame();

        ControllerGame controllerGame = new ControllerGame(mainWindow, settingsWindow.getGameType());
        controllerGame.setHighScoresWindow(highScoresWindow);
        controllerGame.setTimerGame(timerGame);
        controllerGame.startNewGame();
        mainWindow.setVisible(true);

        startGameListeners(mainWindow, controllerGame, settingsWindow, highScoresWindow);

    }

    public static void startGameListeners(MainWindow mainWindow, ControllerGame controller, SettingsWindow settings, HighScoresWindow scoresWindow){
        settings.setGameTypeListener(type -> {
            settings.setGameType(type);
            controller.setGameType(type);
        });

        mainWindow.setNewGameMenuAction(e -> controller.startNewGame());
        mainWindow.setSettingsMenuAction(e -> settings.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> scoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());

        mainWindow.setCellListener((x, y, buttonType) -> {
            switch (buttonType) {
                case LEFT_BUTTON -> controller.leftOrMiddleClickOnMouse(false, new Position(x, y));
                case RIGHT_BUTTON -> controller.rightClickOnMouse(new Position(x, y));
                case MIDDLE_BUTTON -> controller.leftOrMiddleClickOnMouse(true, new Position(x, y));
            }
        });
    }
}
