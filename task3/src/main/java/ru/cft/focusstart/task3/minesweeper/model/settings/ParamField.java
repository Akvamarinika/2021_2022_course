package ru.cft.focusstart.task3.minesweeper.model.settings;

public enum ParamField {
    NOVICE(9,9,10),
    MEDIUM(16,16,40),
    EXPERT(30,16,99);   //y = 16, x = 30

    private final int weight;
    private final int height;
    private final int countBombs;

    ParamField(int weight, int height, int countBombs) {
        this.weight = weight;
        this.height = height;
        this.countBombs = countBombs;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getCountBombs() {
        return countBombs;
    }
}
