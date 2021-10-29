package ru.cft.focusstart.task2.figures;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.figures.enum_figures.Figures;
import ru.cft.focusstart.task2.figures.round_result.RoundNum;

@Slf4j
@Getter
@EqualsAndHashCode(callSuper = true)
public final class Circle extends Figure {
    private final double radius;

    public Circle(double radius){
        super(Figures.CIRCLE.getRusName());
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        double area = Math.PI * Math.pow(radius, 2);
        return RoundNum.round(area);
    }

    @Override
    public double calcPerimeter() {
        double perimeter = 2 * Math.PI * radius;
        return RoundNum.round(perimeter);
    }

    public double calcDiameter() {
        return 2 * radius;
    }

}
