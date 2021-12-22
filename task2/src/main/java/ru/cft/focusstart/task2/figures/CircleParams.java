package ru.cft.focusstart.task2.figures;

enum CircleParams {
    RADIUS("Радиус: "),
    DIAMETER("Диаметр: ");

    private final String rusName;

    CircleParams(String rusName){
        this.rusName = rusName;
    }

    public String getRus() {
        return rusName;
    }
}
