package ru.cft.focusstart.task3.minesweeper;

import ru.cft.focusstart.task3.minesweeper.controller.Controller;
import ru.cft.focusstart.task3.minesweeper.controller.GameController;
import ru.cft.focusstart.task3.minesweeper.model.GameModel;
import ru.cft.focusstart.task3.minesweeper.model.Model;
import ru.cft.focusstart.task3.minesweeper.timer.TimerGame;
import ru.cft.focusstart.task3.minesweeper.view.SwingView;
import ru.cft.focusstart.task3.minesweeper.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new SwingView();

        TimerGame timerGame = new TimerGame();
        timerGame.setView(view);

        Model model = new GameModel(view);
        model.setTimer(timerGame);

        Controller controller = new GameController(view, model);
        controller.notifyAboutNewGame();

        view.setController(controller);
        view.notifyControllerAboutEventsView();
    }
}
