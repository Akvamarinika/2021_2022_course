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

    public GameType getGameType() {
        return gameType;
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



    public void leftClickOnMouse(Position position){
        if (modelGame.getStateGame() == null){
            firstMove (position);
        }

        if (modelGame.getStateGame() == StateGame.PLAYED){
            modelGame.openCells(position);
            sendViewListCells();
            sendViewNewStateGame();
        }
    }

    public void rightClickOnMouse(Position position){
        Optional<Cell> cellOpt = modelGame.toggleFlag(position);
        cellOpt.ifPresent(this::updateFlag);
        updateViewCountBombs();
    }

    public void middleClickOnMouse(Position position){
        if (modelGame.getStateGame() == null){
            firstMove (position);
        }

        if (modelGame.getStateGame() == StateGame.PLAYED){
            modelGame.openCellsIfNumberOfCellEqualsCountFlags(position);
            modelGame.openCells(position);
            sendViewListCells();
            sendViewNewStateGame();
        }
    }

    private void sendViewListCells(){
        List<Cell> cells = modelGame.getCellsList();
        if (!cells.isEmpty()) {
            updatePlayingField(cells);
            cells.clear();
        }
    }

    private void updateViewCountBombs(){
        int bombs = modelGame.calcRemainedBombs();
        mainWindow.setBombsCount(bombs);
    }

    private void sendViewNewStateGame(){
        if (modelGame.isWin()){
            timerGame.stopTimer();
            Gamer newGamer = new Gamer(gameType, timerGame.getTimeSec());

            if (records.isBestGamer(newGamer)) {
                new RecordsWindow(mainWindow, name -> {
                    newGamer.setName(name);
                    records.addBestGamer(newGamer);
                    records.writeJsonInFile();

                    log.info("Получено имя победителя: {}", name);
                });
            }


            new WinWindow(mainWindow, e -> startNewGame(), exit -> mainWindow.dispose());
        }

        if (modelGame.isGameOver()){
            timerGame.stopTimer();
            new LoseWindow(mainWindow, e -> startNewGame(), exit -> mainWindow.dispose());
        }
    }

    public void startNewGame(){
        this.modelGame = new ModelGame(gameType);
        timerGame.stopTimer();
        loadTableRecords();
        createPlayingField(SettingsField.getWeight(), SettingsField.getHeight(), SettingsField.getListPositions());
        mainWindow.setBombsCount(SettingsField.getCountBombs());
        //mainWindow.setTimerValue(0);

        //loadTableRecords();

    }

    private void loadTableRecords(){
        records.readRecordsFromFile();
        List<Gamer> bestGamers = records.getBestGamers();

        //if (bestGamers != null) {
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
       // }
    }

    private void createPlayingField(int weight, int height, List<Position> allPositions){
        mainWindow.createGameField(height, weight);

        for (Position position : allPositions){
            mainWindow.setCellImage(position.getX(), position.getY(), GameImage.CLOSED);
        }
    }

    private void updatePlayingField(List<Cell> cells){

        for (Cell cell : cells){
            log.info("lst: {}", cell.isFlagged());
            updatePlayingField(cell);
        }
    }

    private void updatePlayingField(Cell cell){
        Position position = cell.getPosition();
        String state = cell.getStateCell().name();
        GameImage image = GameImage.valueOf(state);
        mainWindow.setCellImage(position.getX(), position.getY(), image);
    }

    private void updateFlag(Cell cell){
        Position position = cell.getPosition();
        GameImage image = cell.isFlagged() && cell.isClosed() ? GameImage.MARKED : GameImage.CLOSED;
        mainWindow.setCellImage(position.getX(), position.getY(), image);
    }


    private void firstMove (Position firstPos){
        //if (timerGame.getTimer() == null){
            timerGame.setMainWindow(mainWindow);
            timerGame.createAndStartTimer();
            timerGame.resetTimer();
       // }

        //timerGame.resetTimer();
        modelGame.setStateGame(StateGame.PLAYED);
        modelGame.init(firstPos);
    }

//    private void loadTableRecords(){
//        List<String[]> bestGamers = records.readRecordsFromFile();
//        System.out.println(bestGamers);
//
//        if (!bestGamers.isEmpty()) {
//            for (String[] gamer : bestGamers) {
//                GameType type = GameType.valueOf(gamer[0]);
//                System.out.println(gamer[1] +" "+ gamer[2]);
//                switch (type) {
//                    case NOVICE -> highScoresWindow.setNoviceRecord(gamer[1], Integer.parseInt(gamer[2]));
//                    case MEDIUM -> highScoresWindow.setMediumRecord(gamer[1], Integer.parseInt(gamer[2]));
//                    case EXPERT -> highScoresWindow.setExpertRecord(gamer[1], Integer.parseInt(gamer[2]));
//                    default -> {
//                        return;
//                    }
//                }
//            }
//        }
//    }





}
