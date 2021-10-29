package ru.cft.focusstart.task2.output.characteristics_of_figures;

public enum Units {
    MM(" мм"),
    CM(" cм"),
    M(" м"),
    MM_SQUARE(" кв. мм"),
    CM_SQUARE(" кв. cм"),
    M_SQUARE(" кв. м"),
    DEGREE("\u00B0");

    private final String rusName;

    Units(String rusName){
        this.rusName = rusName;
    }

    public String getRus() {
        return rusName;
    }

}
