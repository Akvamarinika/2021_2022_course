package ru.cft.focusstart.task3.minesweeper.model;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.model.field.Cell;
import ru.cft.focusstart.task3.minesweeper.model.field.Field;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.model.field.StateCell;
import ru.cft.focusstart.task3.minesweeper.model.settings.SettingsField;
import ru.cft.focusstart.task3.minesweeper.model.specificfields.BombField;
import ru.cft.focusstart.task3.minesweeper.records.Gamer;
import ru.cft.focusstart.task3.minesweeper.records.JSONRecords;
import ru.cft.focusstart.task3.minesweeper.records.Records;
import ru.cft.focusstart.task3.minesweeper.timer.TimerGame;
import ru.cft.focusstart.task3.minesweeper.view.GameType;
import ru.cft.focusstart.task3.minesweeper.view.View;
import java.util.List;
import java.util.Optional;

@Slf4j
public class GameModel implements Model {
    private final View view;
    private StateGame stateGame;
    private BombField bombField;
    private int countFlags;
    private TimerGame timer;
    private final Records records = new JSONRecords();
    private String bestPlayer = "Anonymous";

    public GameModel(View view) {
        this.view = view;
        SettingsField.init(view.getGameType());
        bombField = new BombField(SettingsField.getCountBombs());
    }

    @Override
    public void updateGameTypeInSettingsField(GameType gameType){
        SettingsField.init(gameType);
    }

    @Override
    public void resetField(){
        stateGame = null;
        timer.stopTimer();
        countFlags = 0;
        SettingsField.init(view.getGameType());
        bombField = new BombField(SettingsField.getCountBombs());

        records.readRecordsFromFile();
        List<Gamer> bestGamers = records.getBestGamers();
        log.info("read records: {}", bestGamers);

        view.displayTableRecords(bestGamers);
        view.createNewPlayingField(SettingsField.getWeight(), SettingsField.getHeight(), SettingsField.getListPositions());
        view.setBombsCount(SettingsField.getCountBombs());
    }

    @Override
    public void firstMove (Position firstPos) {
        timer.createAndStartTimer();
        timer.resetTimer();
        stateGame = StateGame.PLAYED;
        bombField.init(firstPos);
    }

    @Override
    public StateGame getStateGame() {
        return stateGame;
    }

    public void setStateGame(StateGame stateGame) {
        this.stateGame = stateGame;
    }

    public void updateViewCountBombs(){
        int bombs = calcRemainedBombs();
        view.updateViewCountBombs(bombs);
    }

    private int calcRemainedBombs() {
        return bombField.getCountBombs() - countFlags;
    }

    @Override
    public void toggleFlag(Position position) {
        Cell cell = bombField.getCell(position);
        if (isGameOver() || !cell.isClosed()){
            return;
        }

        if (cell.isFlagged()) {
            cell.setFlagged(false);
            countFlags--;
        } else {
            cell.setFlagged(true);
            countFlags++;
            log.info("Flags set: {}", cell);
        }

        view.updateFlag(cell);
        updateViewCountBombs();
    }

    public void setNameBestGamer(String name){
        bestPlayer = name;
    }

    public void checkGameState(){ //если ничего не произошло из событий -> играем
        if (isWin()){
            timer.stopTimer();
            Gamer newGamer = new Gamer(view.getGameType(), timer.getTimeSec());
            ifBestGamerAddedInTableRecords(newGamer);
            view.createWinWindow();
        }

        if (isGameOver()){
            timer.stopTimer();
            view.createLoseWindow();
        }
    }
    
    private void ifBestGamerAddedInTableRecords(Gamer newGamer){
        if (records.isBestGamer(newGamer)) {
            view.createRecordsWindow();
            newGamer.setName(bestPlayer);
            records.addBestGamer(newGamer);
            records.writeJsonInFile();
        }
    }


    private boolean isGameOver() {
        return (stateGame == StateGame.LOST);
    }


    private boolean isWin() {
        if ((stateGame == StateGame.PLAYED) && (bombField.getCountBombs() == bombField.getCountClosedCells())){
            stateGame = StateGame.WINED;
            return true;
        }

        log.info("closed cells: {}", bombField.getCountClosedCells());
        return false;
    }

    @Override
    public void openCells(Position position) {
        Cell cell = bombField.getCell(position);
        if (cell.isFlagged() || !cell.isClosed()){
            return;
        }

        switch (cell.getStateCell()) {
            case EMPTY -> openCellEmptyNeighbors(position);
            case BOMB -> openBombs(position);
            default -> openCell(position);
        }
    }

    private void openCellEmptyNeighbors(Position position) {
        Optional<Cell> cell = bombField.openCell(position);

        cell.ifPresent(view::updatePlayingField);

        for (Position posNeighbor : Field.calcPositionsNeighbors(position)) {
            Cell neighbor = bombField.getCell(posNeighbor);

            if (neighbor.isFlagged()){
                neighbor.setFlagged(false);
                countFlags--;
            }

            view.updatePlayingField(neighbor);
            openCells(posNeighbor);
        }
    }

    private void openBombs(Position posBomb) {
        stateGame = StateGame.LOST;
        Cell bomb = bombField.getCell(posBomb);
        bomb.setStateCell(StateCell.BOMBED);
        view.updatePlayingField(bomb);

        for (Position position : SettingsField.getListPositions()) {
            StateCell stateCell = bombField.getStateCell(position);

            if (stateCell == StateCell.BOMB) {
                Cell cell = bombField.openBombThatExploded(position);
                view.updatePlayingField(cell);
            } else {
                Optional<Cell> cell = bombField.setStateNobombWhenFlagIsWrong(position);
                cell.ifPresent(view::updatePlayingField);
            }

        }
    }

    private void openCell(Position position){
        Optional<Cell> cellOpt = bombField.openCell(position);
        cellOpt.ifPresent(view::updatePlayingField);
    }

    @Override
    public void openCellsIfNumberOfCellEqualsCountFlags(Position position) {
        Cell cell = bombField.getCell(position);
        int countFlags = bombField.calcNumberOfFlagsAroundCell(position);

        if ((bombField.getStateCell(position) != StateCell.BOMB) && (countFlags == bombField.getStateCell(position).getValueCell()) && !cell.isClosed()) {
            view.updatePlayingField(cell);

            for (Position posNeighbor : Field.calcPositionsNeighbors(position)){
                openCells(posNeighbor);
            }
        }
    }

    @Override
    public void setTimer(TimerGame timer) {
        this.timer = timer;
    }
}
