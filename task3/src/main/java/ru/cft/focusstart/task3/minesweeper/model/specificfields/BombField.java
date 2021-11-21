package ru.cft.focusstart.task3.minesweeper.model.specificfields;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.model.field.Cell;
import ru.cft.focusstart.task3.minesweeper.model.field.Field;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.model.field.StateCell;
import ru.cft.focusstart.task3.minesweeper.model.settings.SettingsField;
import java.util.Optional;
import java.util.Random;

@Slf4j
public class BombField{
    private static final Random random = new Random();
    private Field bombsField = new Field();
    private final int countBombs;
    private int countClosedCells;

    public BombField(int countBombs) {
        this.countBombs = countBombs;
        countClosedCells = SettingsField.getWeight() * SettingsField.getHeight();
    }


    public void init(Position firstPos) {

        for (int i = 0; i < countBombs; i++){
            placeBombsOnField(firstPos);
        }
        log.info("Все бомбы были размещены на игровое поле...");
    }


    public Cell getCell(Position position) {
        return bombsField.getCell(position);
    }


    public StateCell getStateCell(Position position) {
        return bombsField.getCell(position).getStateCell();
    }


    public int getCountBombs() {
            return countBombs;
    }

    public int getCountClosedCells() {
        return countClosedCells;
    }

    private void placeBombsOnField(Position firstPos){

        while (true){
            Position position = getRandomPosition();
            if (StateCell.BOMB == getStateCell(position) || (position.equals(firstPos))){ //если на данной координате уже установлена бомба, то пропустить
                continue;
            }
            bombsField.setStateCell(position, StateCell.BOMB);
            increaseNumbersAroundBombs(position);
            break;
        }
    }

    private  Position getRandomPosition(){
        return new Position(random.nextInt(SettingsField.getWeight()), random.nextInt(SettingsField.getHeight()));
    }

    private void increaseNumbersAroundBombs(Position position){
        for (Position posNeighbor : Field.calcPositionsNeighbors(position)){
            Cell neighbor = bombsField.getCell(posNeighbor);
            if (StateCell.BOMB != neighbor.getStateCell()){ // если пробегая вокруг ячейки, не нашли бомб, -> "next number"
                StateCell neighborNewState = bombsField.getStateCell(posNeighbor).getNextNumber();
                bombsField.setStateCell(posNeighbor, neighborNewState);
            }
        }
    }

    public Optional<Cell> openCell(Position position){
        Cell cell = bombsField.getCell(position);
        if (cell.isClosed() && cell.getStateCell() != StateCell.BOMB) {  ////**************
            cell.setClosed(false);
            subtractAnOpenCell();
            return Optional.of(cell);
        }
        return Optional.empty();
    }

    public void subtractAnOpenCell(){
        countClosedCells--;
    }

    public Cell openBombThatExploded(Position position) {//устанавливаем открытую клетку, на закрытой бомбе
        Cell cell = bombsField.getCell(position);
        cell.setClosed(false);
        return cell;
    }

    public Optional<Cell> setStateNobombWhenFlagIsWrong(Position position) {//если стоит флажок, а бомбы нет
        Cell cell = bombsField.getCell(position);
        if (cell.isFlagged()){
            cell.setStateCell(StateCell.NO_BOMB);
            return Optional.of(cell);
        }
        return Optional.empty();
    }

        public int calcNumberOfFlagsAroundCell(Position position){
        int countFlags = 0;
        for (Position posNeighbor : Field.calcPositionsNeighbors(position))
            if (bombsField.getCell(posNeighbor).isFlagged())
                countFlags++;
        return countFlags;
    }
}
