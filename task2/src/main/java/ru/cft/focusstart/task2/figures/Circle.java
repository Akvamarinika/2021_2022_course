package ru.cft.focusstart.task2.figures;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.figures.enumfigures.Figures;
import ru.cft.focusstart.task2.figures.factory.exception.CreateFigureException;

import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Getter
@EqualsAndHashCode(callSuper = true)
public final class Circle extends Figure {
    private final double radius;

    public Circle( List<Double> paramsFigure) {
        super(Figures.CIRCLE.getRusName());

        if (isExistFigure(paramsFigure)){
          this.radius = paramsFigure.get(0);
        } else {
            throw new CreateFigureException("Невозможно создать фигуру", Figures.CIRCLE.name(), paramsFigure.toString());
        }
    }

    @Override
    protected boolean isExistFigure(List<Double> paramsFigure) {
        int countParams = paramsFigure.size();
        int expectedCountParams = Figures.CIRCLE.getNumberOfParams();

        if (countParams != expectedCountParams){
            log.warn("Несоответствие количества параметров для фигуры круг. Ожидалось {}, получено {}.", expectedCountParams, countParams);
            return false;
        }
        return true;
    }

    @Override
    public double calcArea() {
        double area = Math.PI * Math.pow(radius, 2);
        return round(area);
    }

    @Override
    public double calcPerimeter() {
        double perimeter = 2 * Math.PI * radius;
        return round(perimeter);
    }

    public double calcDiameter() {
        return 2 * radius;
    }

    @Override
    public String figureDescription() {
        String generalCharacteristic = super.figureDescription();
        StringJoiner joiner = new StringJoiner("\n");

        String radius = formatNum(this.radius);
        String diameter = formatNum(this.calcDiameter());

        joiner.add(generalCharacteristic)
                .add(CircleParams.RADIUS.getRus() + radius + Units.CM.getRus())
                .add(CircleParams.DIAMETER.getRus() + diameter + Units.CM.getRus());

        return joiner.toString();
    }
}
