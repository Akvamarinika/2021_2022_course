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
public final class Rectangle extends Figure {
    private final double width;
    private final double height;

    public Rectangle(List<Double> paramsFigure) {
        super(Figures.RECTANGLE.getRusName());

        if (isExistFigure(paramsFigure)){
            this.width = paramsFigure.get(0);
            this.height = paramsFigure.get(1);
        } else {
            throw new CreateFigureException("Невозможно создать фигуру", Figures.CIRCLE.name(), paramsFigure.toString());
        }
    }

    @Override
    protected boolean isExistFigure(List<Double> paramsFigure) {
        int countParams = paramsFigure.size();
        int expectedCountParams = Figures.RECTANGLE.getNumberOfParams();

        if (countParams != expectedCountParams){
            log.warn("Несоответствие кол-ва параметров для фигуры прямоугольник. Ожидалось {}, получено {}.", expectedCountParams, countParams);
            return false;
        }

        double sideA = paramsFigure.get(0);
        double sideB = paramsFigure.get(1);

        if (isSquare(sideA, sideB)){
            log.error("извините, данная фигура \"SQUARE\" не поддерживается.");
            return false;
        }

        return true;
    }

    private static boolean isSquare(double sideA, double sideB){
        double eps = 0.0001;
        return Math.abs(sideA - sideB) < eps;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public double calcPerimeter() {
        return 2 * (width + height);
    }

    public final double calcDiagonal(){
        double diagonal = Math.sqrt((width * width) + (height * height));
        return Figure.round(diagonal);
    }

    @Override
    public String figureDescription() {
        String generalCharacteristic = super.figureDescription();
        StringJoiner joiner = new StringJoiner("\n");

        String diagonal = formatNum(this.calcDiagonal());
        String height = formatNum(this.height);
        String weigh = formatNum(this.width);

        joiner.add(generalCharacteristic)
                .add(RectangleParams.DIAGONAL_LENGTH.getRus() + diagonal + Units.CM.getRus())
                .add(RectangleParams.HEIGHT.getRus() + height + Units.CM.getRus())
                .add(RectangleParams.WEIGHT.getRus() + weigh + Units.CM.getRus());

        return joiner.toString();
    }
}
