package ru.cft.focusstart.task2.output.characteristics_of_figures;

public enum TriangleParams {
    A_LENGTH("Длина стороны А: "),
    ALPHA_ANGLE("Угол Альфа: "),
    B_LENGTH("Длина стороны В: "),
    BETTA_ANGLE("Угол Бетта: "),
    C_LENGTH("Длина стороны С: "),
    GAMMA_ANGLE("Угол Гамма: ");

    private final String rusName;

    TriangleParams(String rusName){
        this.rusName = rusName;
    }

    public String getRus() {
        return rusName;
    }
}
