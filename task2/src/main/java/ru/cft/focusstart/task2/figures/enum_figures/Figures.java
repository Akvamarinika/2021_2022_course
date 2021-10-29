package ru.cft.focusstart.task2.figures.enum_figures;

public enum Figures {
    CIRCLE("Круг", 1),
    RECTANGLE("Прямоугольник", 2),
    TRIANGLE("Треугольник", 3),
    UNKNOWN("Неизвестная_Фигура", 0);

    private final String rusName;
    private final int numberOfParams;

    Figures(String rusName, int numberOfParams){
        this.rusName = rusName;
        this.numberOfParams = numberOfParams;
    }

    @Override
    public String toString() {
        return rusName;
    }

    public String getRusName() {
        return rusName;
    }

    public int getNumberOfParams(){
        return numberOfParams;
    }
}
