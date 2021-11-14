package ru.cft.focusstart.task3.minesweeper.model.specificfields;

import ru.cft.focusstart.task3.minesweeper.model.field.Field;
import ru.cft.focusstart.task3.minesweeper.model.field.Position;
import ru.cft.focusstart.task3.minesweeper.model.field.StateCell;
import ru.cft.focusstart.task3.minesweeper.model.settings.SettingsField;

import java.util.Random;

public class BombField implements GenerateField {
    private static final Random random = new Random();
    private Field bombs;
    private final int countBombs;

    public BombField(int countBombs) {
        this.countBombs = SettingsField.getCountBombs();
    }

    public int getCountBombs() {
        return countBombs;
    }

    @Override
    public void init() {
        bombs = new Field(StateCell.EMPTY);

        for (int i = 0; i < countBombs; i++){
            placeBombsOnField();
        }
    }

    @Override
    public int getValueCell(Position position) {
        return 0;
    }

    @Override
    public int getCountItems() {
        return 0;
    }

    public void placeBombsOnField(){

    }

    private  Position getRandomPosition(){    //вернуть случ. позицию из карты
        return new Position(random.nextInt(SettingsField.getWeight()), random.nextInt(SettingsField.getHeight()));
    }

    private void increaseNumbersAroundBombs(Position position){
        for (Position posNeighbor : Field.calcPositionsNeighbors(position)){
            if (StateCell.BOMB != bombs.getValue(posNeighbor)){ // если пробегая вокруг ячейки, не нашли бомб, -> "next state"
                StateCell neighborNewState = bombs.getValue(posNeighbor).getNextState();
                bombs.setValue(posNeighbor, neighborNewState);
            }
        }
    }


}
