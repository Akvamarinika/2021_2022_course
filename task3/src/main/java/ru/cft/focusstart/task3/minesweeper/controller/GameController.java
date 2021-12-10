package ru.cft.focusstart.task3.minesweeper.controller;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.model.Model;
import ru.cft.focusstart.task3.minesweeper.model.StateGame;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.view.*;

@Slf4j
public class GameController implements Controller{
    private final View view;
    private final Model model;

    public GameController(View swingView, Model model) {
        this.view = swingView;
        this.model = model;
    }

    @Override
    public void notifyAboutChangeOfGameType(GameType gameType) {
        model.updateGameTypeInSettingsField(gameType);
    }

    @Override
    public void reportBestPlayer(String name){
        log.info("Получено имя лучшего игрока: {}", name);
        model.setNameBestGamer(name);
    }

    @Override
    public void rightClickOnMouse(Position position) {
        model.toggleFlag(position);
    }

    public void leftOrMiddleClickOnMouse(boolean middle, Position position) {
        if (model.getStateGame() == null){
            model.firstMove (position);
        }

        if (model.getStateGame() == StateGame.PLAYED){

            if (middle){
                model.openCellsIfNumberOfCellEqualsCountFlags(position);
            }

            model.openCells(position);
            model.checkGameState();
        }
    }

    @Override
    public void notifyAboutNewGame() {
        model.resetField();
    }
}
