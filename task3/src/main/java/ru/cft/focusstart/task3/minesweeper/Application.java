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


       // ModelGame modelGame = new ModelGame();
        ControllerGame controllerGame = new ControllerGame(mainWindow, settingsWindow.getGameType());
        controllerGame.setHighScoresWindow(highScoresWindow);
        controllerGame.setTimerGame(timerGame);
        controllerGame.startNewGame();
        mainWindow.setVisible(true);

        //WinWindow winWindow = new WinWindow(mainWindow);



        settingsWindow.setGameTypeListener(type -> { // return game type
            settingsWindow.setGameType(type);
            controllerGame.setGameType(type);
        });

        //System.out.println(gameController.getGameType());

        mainWindow.setNewGameMenuAction(e -> {
            controllerGame.startNewGame();
        });

        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());



        mainWindow.setCellListener((x, y, buttonType) -> {
            switch (buttonType) {
                case LEFT_BUTTON -> controllerGame.leftClickOnMouse(new Position(x, y));
                case RIGHT_BUTTON -> controllerGame.rightClickOnMouse(new Position(x, y));
                case MIDDLE_BUTTON -> controllerGame.middleClickOnMouse(new Position(x, y));
            }
        });


//        InputWindow inputWindow = new InputWindow(mainWindow);
//        inputWindow.setExitListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(e);
//            }
//        });


            //RecordNameListener nameListener = x -> System.out.println(x);

        //

    }
}
