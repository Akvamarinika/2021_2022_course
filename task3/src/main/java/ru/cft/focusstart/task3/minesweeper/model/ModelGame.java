package ru.cft.focusstart.task3.minesweeper.model;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.model.field.Cell;
import ru.cft.focusstart.task3.minesweeper.model.field.Field;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.model.field.StateCell;
import ru.cft.focusstart.task3.minesweeper.model.settings.SettingsField;
import ru.cft.focusstart.task3.minesweeper.model.specificfields.BombField;
import ru.cft.focusstart.task3.minesweeper.view.GameType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ModelGame implements IGame{
    private StateGame stateGame;
    private final List<Cell> cellList = new ArrayList<>();
    private final BombField bombField;
    private int countFlags;

    public ModelGame(GameType gameType) {
        SettingsField.init(gameType);
        bombField = new BombField(SettingsField.getCountBombs());
    }

    public void init(Position firstPos) {
        bombField.init(firstPos);
        //stateGame = StateGame.PLAYED;
    }

    public StateGame getStateGame() {
        return stateGame;
    }

    public void setStateGame(StateGame stateGame) {
        this.stateGame = stateGame;
    }

    public List<Cell> getCellsList() {
        return cellList;
    }

    public int calcRemainedBombs() {
        return bombField.getCountBombs() - countFlags;
    }

    public Optional<Cell> toggleFlag(Position position) {
        Cell cell = bombField.getCell(position);
        if (isGameOver() || !cell.isClosed()){
            return Optional.empty();
        }

        if (cell.isFlagged()) {
            cell.setFlagged(false);
            countFlags--;
            return Optional.of(cell);
        }

        cell.setFlagged(true);
        countFlags++;
        log.info("Flags: {}", cell);
        return Optional.of(cell);
    }

    @Override
    public boolean isWin() { // если играем && кол-во закрытых клеток == кол-ву бомб
        if ((stateGame == StateGame.PLAYED) && (bombField.getCountBombs() == bombField.getCountClosedCells())){
            stateGame = StateGame.WINED;
            return true;
        }
        log.info("closed cells: {}", bombField.getCountClosedCells());
        return false;
    }

    public void openCells(Position position) {  //**********************
        Cell cell = bombField.getCell(position);
        if (cell.isFlagged() || !cell.isClosed()){
            return;
        }

        switch (cell.getStateCell()) {
            case EMPTY -> openCellEmptyNeighbors(position);
            //case BOMB -> openBombs(position);
            default -> openCell(position);
        }


    }

    public void openCellsIfNumberOfCellEqualsCountFlags(Position position) {
        Cell cell = bombField.getCell(position);

        int countFlags = bombField.calcNumberOfFlagsAroundCell(position);
        if ((bombField.getStateCell(position) != StateCell.BOMB) && (countFlags == bombField.getStateCell(position).getValueCell()) && !cell.isClosed()) {
            cellList.add(cell);
            log.info("CellEqualsCountFlags: {}", cell);

            for (Position posNeighbor : Field.calcPositionsNeighbors(position)){
                //if (bombField.getCell(posNeighbor).isClosed() && (bombField.getStateCell(posNeighbor) != StateCell.BOMB)) {
                //if (!bombField.getCell(posNeighbor).isFlagged()) {
                openCells(posNeighbor);


                //bombField.subtractAnOpenCell();
                // }
                //}
            }
        }
    }

    private void openCellEmptyNeighbors(Position position) {
        Optional<Cell> cell = bombField.openCell(position);
        cell.ifPresent(cellList::add);
        log.info("Self EmptyNeighbors: {}", cell);
        //bombField.subtractAnOpenCell();

        for (Position posNeighbor : Field.calcPositionsNeighbors(position)) { // открыть пустых соседей клетки
            Cell neighbor = bombField.getCell(posNeighbor);
            if (neighbor.isFlagged()){
                neighbor.setFlagged(false);
                countFlags--;
                //bombField.subtractAnOpenCell();
            }
            cellList.add(neighbor);

            log.info("openCellEmptyNeighbors neighbor: {}", neighbor);///////////////////////////////////////////////////////////////////////////////////////////////////////


            openCells(posNeighbor);
        }
    }



    private void openBombs(Position posBomb) {
        stateGame = StateGame.LOST;
        Cell bomb = bombField.getCell(posBomb);
        bomb.setStateCell(StateCell.BOMBED);
        cellList.add(bomb);
        //flagField.setBombedToBox(posBomb);

        for (Position position : SettingsField.getListPositions()) {
            StateCell stateCell = bombField.getStateCell(position);

            if (stateCell == StateCell.BOMB) { // в какой-то ячейке есть бомба
                Cell cell = bombField.openBombThatExploded(position); //устанавливаем открытую ячейку, на закрытой бомбе
                cellList.add(cell);
            } else {
                Optional<Cell> cell = bombField.setStateNobombWhenFlagIsWrong(position); //если стоит флажок, а бомбы нет
                cell.ifPresent(cellList::add);
            }
        }
    }


    public void openCell(Position position){
        Optional<Cell> cellOpt = bombField.openCell(position);
        if (cellOpt.isPresent()){
            cellList.add(cellOpt.get());
            log.info("OpenCell: {}", cellOpt.get());
            //bombField.subtractAnOpenCell();
        }
        //cell.ifPresent(cellList::add);
    }

    @Override
    public boolean isGameOver() {
        return (stateGame != StateGame.PLAYED) && (stateGame != null) && (stateGame != StateGame.WINED);
        //start();
    }
}
