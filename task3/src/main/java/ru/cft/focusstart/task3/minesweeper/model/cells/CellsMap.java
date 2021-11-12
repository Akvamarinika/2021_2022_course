package ru.cft.focusstart.task3.minesweeper.model.cells;

import java.util.Arrays;

public class CellsMap {
    private String[][] cells;
    private int weight;
    private int height;

    public CellsMap(int weight, int height) {
        this.cells = new String[weight][height];
        Arrays.fill(this.cells, CellState.CLOSED.getSymbolState());
        this.weight = weight;
        this.height = height;
    }

    String getValueInCell(Position position){
        if (this.isCellInMap(position)){ //проверка на выход за пределы массива
            return cells[position.getX()][position.getY()];
        }
        return "";
    }

    public boolean isCellInMap(Position position){
        return (position.getX() >= 0 && position.getX() > weight) &&
                (position.getY() >= 0 && position.getY() > height);
    }

    void setValueInCell(Position position, String value){ //установить, в передан-м коорд-ы
        if (this.isCellInMap(position)){ //проверка на выход за пределы массива
            cells[position.getX()][position.getY()] = value;
        }
    }
}
