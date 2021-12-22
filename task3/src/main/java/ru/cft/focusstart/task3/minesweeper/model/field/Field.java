package ru.cft.focusstart.task3.minesweeper.model.field;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.model.settings.SettingsField;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Field {
    private final Cell[][] fieldGame;

    public Field() {
        this.fieldGame = new Cell[SettingsField.getHeight()][SettingsField.getWeight()];

        for (Position position : SettingsField.getListPositions()){
            fieldGame[position.getY()][position.getX()] = new Cell(position);
        }

        log.info("Создано поле {} X {} с значениями по умолчанию", SettingsField.getWeight(), SettingsField.getHeight());
    }

    public Cell getCell(Position position){
        if (Field.isCellInField(position)){
            return fieldGame[position.getY()][position.getX()];
        }

        Cell cell = new Cell(position);
        cell.setStateCell(StateCell.OFF_THE_FIELD);
        cell.setClosed(false);
        return cell;
    }

    public void setStateCell(Position position, StateCell state){
        if (Field.isCellInField(position)){
            Cell cell = fieldGame[position.getY()][position.getX()];
            cell.setStateCell(state);
        }
    }

    public StateCell getStateCell(Position position) {
        if (Field.isCellInField(position)){
            return fieldGame[position.getY()][position.getX()].getStateCell();
        }
        return StateCell.OFF_THE_FIELD;
    }

    public static List<Position> calcPositionsNeighbors(Position position){
        Position posNeighbor;
        List<Position> positionsAroundList = new ArrayList<>();

        for(int x = position.getX() - 1; x <= position.getX() + 1; x++){
            for (int y = position.getY() - 1; y <= position.getY() + 1; y++){
                posNeighbor = new Position(x, y);
                if (isCellInField(posNeighbor) && !posNeighbor.equals(position)){
                    positionsAroundList.add(posNeighbor);
                    log.info("Найдены соседи ячейки({},{}): коорд-ты({},{})",position.getX(), position.getY(), posNeighbor.getX(), posNeighbor.getY());
                }
            }
        }
        return positionsAroundList;
    }

    public static boolean isCellInField(Position position){
        return (position.getX() >= 0 && position.getX() < SettingsField.getWeight()) &&
                (position.getY() >= 0 && position.getY() < SettingsField.getHeight());
    }

}
