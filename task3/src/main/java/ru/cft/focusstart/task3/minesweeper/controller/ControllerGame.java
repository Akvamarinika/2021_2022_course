package ru.cft.focusstart.task3.minesweeper.controller;

import ru.cft.focusstart.task3.minesweeper.model.ModelGame;
import ru.cft.focusstart.task3.minesweeper.model.StateGame;
import ru.cft.focusstart.task3.minesweeper.model.field.Cell;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.model.settings.SettingsField;
import ru.cft.focusstart.task3.minesweeper.view.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class ControllerGame {
    MainWindow mainWindow;
    SettingsWindow settingsWindow;
    ModelGame modelGame;
    //GameType gameType;

    public ControllerGame(MainWindow mainWindow, SettingsWindow settingsWindow) {
        this.mainWindow = mainWindow;
        this.settingsWindow = settingsWindow;

    }

//    public GameType getGameType() {
//        return gameType;
//    }
//
//    public void setGameType(GameType gameType) {
//        this.gameType = gameType;
//    }
//
//    public void setMainWindow(MainWindow mainWindow) {
//        this.mainWindow = mainWindow;
//
//    }
//
//    public void setModelGame(ModelGame modelGame) {
//        this.modelGame = modelGame;
//    }

    public void leftClickOnMouse(Position position){
        //System.out.println(position.getX() + " " + position.getY());
        if (modelGame.getStateGame() == null) {
            System.out.println(position.getX() + " startLeft " + position.getY());
            firstMove (position);
        }
        modelGame.openCells(position);
        sendViewListCells();
        sendViewNewStateGame();

//        modelGame.isWin();
       // mainWindow.updatePlayingField(List<Position> positions);
    }
    public void rightClickOnMouse(Position position){
        //System.out.println(position.getX() + " " + position.getY());
        Optional<Cell> cellOpt = modelGame.toggleFlag(position);
        cellOpt.ifPresent(this::updateFlag);
    }
    public void middleClickOnMouse(Position position){
        if (modelGame.getStateGame() == null) {
            System.out.println(position.getX() + " startRight " + position.getY());
            firstMove (position);
        }

        modelGame.setOpenedToClosedBoxesAroundNumber(position);
        modelGame.openCells(position);
        sendViewListCells();
        sendViewNewStateGame();
    }

    private void sendViewListCells(){
        List<Cell> cells = modelGame.getCellsList();
        if (!cells.isEmpty()) {
            updatePlayingField(cells);
            cells.clear();
        }
    }

    private void sendViewNewStateGame(){
        if (modelGame.isWin()){
            WinWindow winWindow = new WinWindow(mainWindow);
            mainWindow.setSettingsMenuAction(e -> winWindow.setVisible(true));
            winWindow.setNewGameListener(e -> startNewGame());
            winWindow.setExitListener(e -> {
                winWindow.dispose();
                mainWindow.dispose();
            });
        }

        if (modelGame.isGameOver()){
            LoseWindow loseWindow = new LoseWindow(mainWindow);
            mainWindow.setSettingsMenuAction(e -> loseWindow.setVisible(true));
            loseWindow.setNewGameListener(e -> startNewGame());
            loseWindow.setExitListener(e -> mainWindow.dispose());
            loseWindow.setExitListener(e -> {
                loseWindow.dispose();
                mainWindow.dispose();
            });
        }
    }

    public void leftAndRightClickOnMouse(){}

    public void startNewGame(){
        this.modelGame = new ModelGame(settingsWindow.getGameType());

        createPlayingField(SettingsField.getWeight(), SettingsField.getHeight(), SettingsField.getListPositions());
        mainWindow.setBombsCount(SettingsField.getCountBombs());
        mainWindow.setTimerValue(0);

//        mainWindow.createGameField(SettingsField.getWeight(), SettingsField.getHeight());
//        mainWindow.setBombsCount(SettingsField.getCountBombs());
//
//        for (Position position : SettingsField.getListPositions()){
//            mainWindow.setCellImage(position.getX(), position.getY(), GameImage.CLOSED);
//        }







        // TODO: There is a sample code below, remove it after try

//        mainWindow.setTimerValue(145);
//
//        mainWindow.setCellImage(0, 0, GameImage.EMPTY);
//        mainWindow.setCellImage(0, 1, GameImage.CLOSED);
//        mainWindow.setCellImage(0, 2, GameImage.MARKED);
//        mainWindow.setCellImage(0, 3, GameImage.BOMB);
//        mainWindow.setCellImage(1, 0, GameImage.NUM_1);
//        mainWindow.setCellImage(1, 1, GameImage.NUM_2);
//        mainWindow.setCellImage(1, 2, GameImage.NUM_3);
//        mainWindow.setCellImage(1, 3, GameImage.NUM_4);
//        mainWindow.setCellImage(1, 4, GameImage.NUM_5);
//        mainWindow.setCellImage(1, 5, GameImage.NUM_6);
//        mainWindow.setCellImage(1, 6, GameImage.NUM_7);
//        mainWindow.setCellImage(1, 7, GameImage.NUM_8);
    }

    private void createPlayingField(int weight, int height, List<Position> allPositions){
        mainWindow.createGameField(weight, height);
        //System.out.println(weight + " " + height);

        for (Position position : allPositions){
            mainWindow.setCellImage(position.getY(), position.getX(), GameImage.CLOSED);
        }
    }

    private void updatePlayingField(List<Cell> cells){
        System.out.println(cells);
        for (Cell cell : cells){
            updatePlayingField(cell);
        }
    }

    private void updatePlayingField(Cell cell){
        Position position = cell.getPosition();
//        System.out.println(cell.getStateCell());
//        System.out.println(cell.isClosed());
        //if (!cell.isClosed()){
        //this.setCellImage(position.getX(), position.getY(), GameImage.MARKED);


        String state = cell.getStateCell().name();
        GameImage image = GameImage.valueOf(state);
        mainWindow.setCellImage(position.getX(), position.getY(), image);

//        switch (cell.getStateCell()) {
//            case NUM_1 ->  this.setCellImage(position.getX(), position.getY(), GameImage.NUM_1);
//            case NUM_2 ->  this.setCellImage(position.getX(), position.getY(), GameImage.NUM_2);
//            case NUM_3 ->  this.setCellImage(position.getX(), position.getY(), GameImage.NUM_3);
//            case NUM_4 ->  this.setCellImage(position.getX(), position.getY(), GameImage.NUM_4);
//            case NUM_5 ->  this.setCellImage(position.getX(), position.getY(), GameImage.NUM_5);
//            case NUM_6 ->  this.setCellImage(position.getX(), position.getY(), GameImage.NUM_6);
//            case NUM_7 ->  this.setCellImage(position.getX(), position.getY(), GameImage.NUM_7);
//            case NUM_8 ->  this.setCellImage(position.getX(), position.getY(), GameImage.NUM_8);
//            case BOMB ->   this.setCellImage(position.getX(), position.getY(), GameImage.BOMB);
//            case BOMBED ->   this.setCellImage(position.getX(), position.getY(), GameImage.BOMBED);
//            case NO_BOMB -> this.setCellImage(position.getX(), position.getY(), GameImage.NO_BOMB);
//            default -> this.setCellImage(position.getX(), position.getY(), GameImage.EMPTY);
//        }


        // }
    }

    private void updateFlag(Cell cell){
        Position position = cell.getPosition();
        GameImage image = cell.isFlagged() && cell.isClosed() ? GameImage.MARKED : GameImage.CLOSED;
        mainWindow.setCellImage(position.getX(), position.getY(), image);
    }


    private void firstMove (Position firstPos){
        modelGame.setStateGame(StateGame.PLAYED);
        modelGame.init(firstPos);
    }





}
