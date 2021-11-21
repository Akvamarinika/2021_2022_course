package ru.cft.focusstart.task3.minesweeper.controller;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.model.ModelGame;
import ru.cft.focusstart.task3.minesweeper.model.StateGame;
import ru.cft.focusstart.task3.minesweeper.model.field.Cell;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.model.settings.SettingsField;
import ru.cft.focusstart.task3.minesweeper.records.Gamer;
import ru.cft.focusstart.task3.minesweeper.records.Records;
import ru.cft.focusstart.task3.minesweeper.timer.TimerGame;
import ru.cft.focusstart.task3.minesweeper.view.*;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ControllerGame {
    private HighScoresWindow highScoresWindow;
    private final MainWindow mainWindow;
    private ModelGame modelGame;
    private GameType gameType;
    private TimerGame timerGame;
    private final Records records = new Records();

    public ControllerGame(MainWindow mainWindow, GameType gameType) {
        this.mainWindow = mainWindow;
        this.gameType = gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public void setHighScoresWindow(HighScoresWindow highScoresWindow) {
        this.highScoresWindow = highScoresWindow;
    }

    public void setTimerGame(TimerGame timerGame) {
        this.timerGame = timerGame;
    }

    public void rightClickOnMouse(Position position) {
        Optional<Cell> cellOpt = modelGame.toggleFlag(position);
        cellOpt.ifPresent(this::updateFlag);
        updateViewCountBombs();
    }

    private void updateFlag(Cell cell) {
        Position position = cell.getPosition();
        GameImage image = cell.isFlagged() && cell.isClosed() ? GameImage.MARKED : GameImage.CLOSED;
        mainWindow.setCellImage(position.getX(), position.getY(), image);
    }

    private void updateViewCountBombs() {
        int bombs = modelGame.calcRemainedBombs();
        mainWindow.setBombsCount(bombs);
    }

    public void leftOrMiddleClickOnMouse(boolean middle, Position position) {
        if (modelGame.getStateGame() == null){
            firstMove (position);
        }

        if (modelGame.getStateGame() == StateGame.PLAYED){

            if (middle){
                modelGame.openCellsIfNumberOfCellEqualsCountFlags(position);
            }

            modelGame.openCells(position);
            sendViewListCells();
            sendViewNewStateGame();
        }
    }

    private void firstMove (Position firstPos) {
        timerGame.setMainWindow(mainWindow);
        timerGame.createAndStartTimer();
        timerGame.resetTimer();
        modelGame.setStateGame(StateGame.PLAYED);
        modelGame.init(firstPos);
    }

    private void sendViewListCells() {
        List<Cell> cells = modelGame.getCellsList();

        if (!cells.isEmpty()) {
            updatePlayingField(cells);
            cells.clear();
        }
    }

    private void updatePlayingField(List<Cell> cells) {
        for (Cell cell : cells){
            updatePlayingField(cell);
        }
    }

    private void updatePlayingField(Cell cell) {
        Position position = cell.getPosition();
        String state = cell.getStateCell().name();
        GameImage image = GameImage.valueOf(state);
        mainWindow.setCellImage(position.getX(), position.getY(), image);
    }

    private void sendViewNewStateGame(){ //если ничего не произошло из событий -> играем
        if (modelGame.isWin()){
            timerGame.stopTimer();
            createRecordsWindowIfBestResult();
            createWinWindow();
        }

        if (modelGame.isGameOver()){
            timerGame.stopTimer();
            createLoseWindow();
        }
    }

    private void createRecordsWindowIfBestResult() {
        Gamer newGamer = new Gamer(gameType, timerGame.getTimeSec());

        if (records.isBestGamer(newGamer)) {
            new RecordsWindow(mainWindow, name -> {
                newGamer.setName(name);
                records.addBestGamer(newGamer);
                records.writeJsonInFile();
                log.info("Получено имя победителя: {}", name);
            });
        }
    }

    private void createWinWindow() {
        new WinWindow(mainWindow, e -> startNewGame(), exit -> mainWindow.dispose());
    }

    private void createLoseWindow() {
        new LoseWindow(mainWindow, e -> startNewGame(), exit -> mainWindow.dispose());
    }

    public void startNewGame() {
        this.modelGame = new ModelGame(gameType);
        timerGame.stopTimer();
        loadTableRecords();
        createPlayingField(SettingsField.getWeight(), SettingsField.getHeight(), SettingsField.getListPositions());
        mainWindow.setBombsCount(SettingsField.getCountBombs());
    }

    private void createPlayingField(int weight, int height, List<Position> allPositions){
        mainWindow.createGameField(height, weight);

        for (Position position : allPositions) {
            mainWindow.setCellImage(position.getX(), position.getY(), GameImage.CLOSED);
        }
    }

    private void loadTableRecords() {
        records.readRecordsFromFile();
        List<Gamer> bestGamers = records.getBestGamers();
        log.info("read records: {}", bestGamers);

        for (Gamer gamer : bestGamers){
            switch (gamer.getGameType()) {
                case NOVICE -> highScoresWindow.setNoviceRecord(gamer.getName(), gamer.getTime());
                case MEDIUM -> highScoresWindow.setMediumRecord(gamer.getName(), gamer.getTime());
                case EXPERT -> highScoresWindow.setExpertRecord(gamer.getName(), gamer.getTime());
                default -> {
                    return;
                }
            }
        }
    }

}
