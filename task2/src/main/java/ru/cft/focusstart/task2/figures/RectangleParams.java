package ru.cft.focusstart.task2.figures;

enum RectangleParams {
    DIAGONAL_LENGTH("Длина диагонали: "),
    HEIGHT("Длина: "),
    WEIGHT("Ширина: ");

    private final String rusName;

    RectangleParams(String rusName){
        this.rusName = rusName;
    }

    public String getRus() {
        return rusName;
    }
}
