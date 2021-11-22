package ru.cft.focusstart.task2.figures;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Getter
@EqualsAndHashCode
public abstract class Figure {
    private final String name;

    protected Figure(String name) {
        this.name = name;
    }

    public abstract double calcArea();

    public abstract double calcPerimeter();

    protected abstract boolean isExistFigure(List<Double> paramsFigure);

    public String figureDescription(){
        StringJoiner joiner = new StringJoiner("\n");
        String area = formatNum(this.calcArea());
        String perimeter = formatNum(this.calcPerimeter());

        joiner.add(BasicParams.TYPE_FIGURE.getRus() + this.name)
                .add(BasicParams.AREA.getRus() + area + Units.CM_SQUARE.getRus())
                .add(BasicParams.PERIMETER.getRus() + perimeter + Units.CM.getRus());
        return joiner.toString();
    }

    public static String formatNum(double num){
        return new DecimalFormat("#.##").format(num);
    }

    public static double round(double number){
        return Math.round(number * 100.0) / 100.0;
    }
}
