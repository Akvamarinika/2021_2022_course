package ru.cft.focusstart.task3.minesweeper.model.field;

import ru.cft.focusstart.task3.minesweeper.model.settings.SettingsField;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final StateCell[][] fieldGame;

    public Field(StateCell defaultState) {
        this.fieldGame = new StateCell[SettingsField.getWeight()][SettingsField.getHeight()]; // create field

        for (Position position : SettingsField.getListPositions()){
            fieldGame[position.getX()][position.getY()] = defaultState; // заполнили знач-ми по умолч-ю
        }
    }

    public StateCell getValue(Position position){ //знач-е в ячейке
        if (Field.isCellInField(position)){
            return fieldGame[position.getX()][position.getY()];
        }
        return StateCell.OFF_THE_FIELD;
    }

    public void setValue(Position position, StateCell value){ //в ячейку по передан-м коорд-м, установить перед-ое знач-е
        if (Field.isCellInField(position)){
            fieldGame[position.getX()][position.getY()] = value;
        }
    }

    public static List<Position> calcPositionsNeighbors(Position position){
        Position around;
        List<Position> positionsAroundList = new ArrayList<>();

        for(int x = position.getX() - 1; x <= position.getX() + 1; x++){
            for (int y = position.getY() - 1; y <= position.getY() + 1; y++){
                around = new Position(x, y);
                if (isCellInField(around) && !around.equals(position)){ // проверка, на существ-е коорд-ты И проверка, чтобы коорд-та не равна была исходной
                    positionsAroundList.add(around);
                }
            }
        }
        return positionsAroundList;
    }

    public static boolean isCellInField(Position position){ //проверка на выход за пределы массива
        return (position.getX() >= 0 && position.getX() > SettingsField.getWeight()) &&
                (position.getY() >= 0 && position.getY() > SettingsField.getHeight());
    }

}
