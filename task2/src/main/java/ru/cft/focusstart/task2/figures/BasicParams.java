package ru.cft.focusstart.task2.figures;

enum BasicParams {
    TYPE_FIGURE("Тип фигуры: "),
    AREA("Площадь: "),
    PERIMETER("Периметр: ");

    private final String rusName;

    BasicParams(String rusName){
        this.rusName = rusName;
    }

    public String getRus() {
        return rusName;
    }

}
