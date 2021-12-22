package ru.cft.focusstart.task3.minesweeper.view;

import ru.cft.focusstart.task3.minesweeper.controller.Controller;
import ru.cft.focusstart.task3.minesweeper.model.field.Cell;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.records.Gamer;
import ru.cft.focusstart.task3.minesweeper.view.windows.*;

import java.util.List;

public class SwingView implements View {
   private final MainWindow mainWindow = new MainWindow();
   private final SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
   private final HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);
   private Controller controller;

    public SwingView() {
        mainWindow.setVisible(true);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public GameType getGameType(){
        return settingsWindow.getGameType();
    }

    public void notifyControllerAboutEventsView(){
        settingsWindow.setGameTypeListener(type -> {
            settingsWindow.setGameType(type);
            controller.notifyAboutChangeOfGameType(type);
        });

        mainWindow.setNewGameMenuAction(e -> controller.notifyAboutNewGame());
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());

        mainWindow.setCellListener((x, y, buttonType) -> {
            switch (buttonType) {
                case LEFT_BUTTON -> controller.leftOrMiddleClickOnMouse(false, new Position(x, y));
                case RIGHT_BUTTON -> controller.rightClickOnMouse(new Position(x, y));
                case MIDDLE_BUTTON -> controller.leftOrMiddleClickOnMouse(true, new Position(x, y));
            }
        });
    }

    @Override
    public void updateFlag(Cell cell) {
        Position position = cell.getPosition();
        GameImage image = cell.isFlagged() && cell.isClosed() ? GameImage.MARKED : GameImage.CLOSED;
        mainWindow.setCellImage(position.getX(), position.getY(), image);
    }

    @Override
    public void updateViewCountBombs(int bombs) {
        mainWindow.setBombsCount(bombs);
    }

    @Override
    public void updatePlayingField(Cell cell) {
        Position position = cell.getPosition();
        String state = cell.getStateCell().name();
        GameImage image = GameImage.valueOf(state);
        mainWindow.setCellImage(position.getX(), position.getY(), image);
    }

    @Override
    public void createRecordsWindow() {
        new RecordsWindow(mainWindow, name -> {
            controller.reportBestPlayer(name);
        });
    }

    @Override
    public void createWinWindow() {
        new WinWindow(mainWindow, e -> controller.notifyAboutNewGame(), exit -> mainWindow.dispose());
    }

    @Override
    public void createLoseWindow() {
        new LoseWindow(mainWindow, e -> controller.notifyAboutNewGame(), exit -> mainWindow.dispose());
    }

    @Override
    public void displayTableRecords(List<Gamer> bestGamers) {
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

    @Override
    public void createNewPlayingField(int weight, int height, List<Position> allPositions) {
        mainWindow.createGameField(height, weight);

        for (Position position : allPositions) {
            mainWindow.setCellImage(position.getX(), position.getY(), GameImage.CLOSED);
        }
    }

    @Override
    public void setTimerValue(int timeSeconds) {
        mainWindow.setTimerValue(timeSeconds);
    }

    @Override
    public void setBombsCount(int bombs) {
        mainWindow.setBombsCount(bombs);
    }
}
