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
public final class Triangle extends Figure {
    private final double sideA;
    private final double sideB;
    private final double hypotC;

    public Triangle(List<Double> paramsFigure) {
        super(Figures.TRIANGLE.getRusName());

        if (isExistFigure(paramsFigure)){
            this.sideA = paramsFigure.get(0);
            this.sideB = paramsFigure.get(1);
            this.hypotC = paramsFigure.get(2);
        } else {
            throw new CreateFigureException("Невозможно создать фигуру", Figures.TRIANGLE.name(), paramsFigure.toString());
        }
    }

    @Override
    protected boolean isExistFigure(List<Double> paramsFigure) {
        int countParams = paramsFigure.size();
        int expectedCountParams = Figures.TRIANGLE.getNumberOfParams();

        if (countParams != expectedCountParams){
            log.warn("Несоответствие кол-ва параметров для фигуры треугольник Ожидалось {}, получено {}.", expectedCountParams, countParams);
            return false;
        }

        double sideA = paramsFigure.get(0);
        double sideB = paramsFigure.get(1);
        double hypotC = paramsFigure.get(2);

        if (!isExistTriangle(sideA, sideB, hypotC)){
            log.error("Треугольник не существует. Сумма длин двух его сторон, должна быть больше длины третьей стороны.");
            return false;
        }

        return true;
    }

    private static boolean isExistTriangle(double sideA, double sideB, double hypotC){
        return (sideA + sideB > hypotC) && (sideA + hypotC > sideB) && (sideB + hypotC > sideA);
    }

    @Override
    public double calcArea() {
        double semiPerimeter = this.calcPerimeter() / 2;
        double area = Math.sqrt(semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - hypotC));
        return round(area);
    }

    @Override
    public double calcPerimeter() {
        return sideA + sideB + hypotC;
    }

    public double calcAngleAlpha() {
        double angleA = ( sideB * sideB + hypotC * hypotC - sideA * sideA) / (2.0 * sideB * hypotC);
        double angleAlpha = Math.toDegrees(Math.acos(angleA));
        return round(angleAlpha);
    }

    public double calcAngleBetta() {
        double angleB = (sideA * sideA + hypotC * hypotC - sideB * sideB) / (2.0 * sideA * hypotC);
        double angleBetta = Math.toDegrees(Math.acos(angleB));
        return round(angleBetta);
    }

    public double calcAngleGamma() {
        double angleG = (sideA * sideA + sideB * sideB - hypotC * hypotC) / (2.0 * sideA * sideB);
        double angleGamma = Math.toDegrees(Math.acos(angleG));
        return round(angleGamma);
    }

    @Override
    public String figureDescription() {
        String generalCharacteristic = super.figureDescription();
        StringJoiner joiner = new StringJoiner("\n");

        String sideA = formatNum(this.sideA);
        String sideB = formatNum(this.sideB);
        String sideC = formatNum(this.hypotC);

        String alpha = formatNum(this.calcAngleAlpha());
        String betta = formatNum(this.calcAngleBetta());
        String gamma = formatNum(this.calcAngleGamma());

        joiner.add(generalCharacteristic)
                .add(TriangleParams.A_LENGTH.getRus() + sideA + Units.CM.getRus())
                .add(TriangleParams.ALPHA_ANGLE.getRus() + alpha + Units.DEGREE.getRus())
                .add(TriangleParams.B_LENGTH.getRus() + sideB + Units.CM.getRus())
                .add(TriangleParams.BETTA_ANGLE.getRus() + betta + Units.DEGREE.getRus())
                .add(TriangleParams.C_LENGTH.getRus() + sideC + Units.CM.getRus())
                .add(TriangleParams.GAMMA_ANGLE.getRus() + gamma + Units.DEGREE.getRus());

        return joiner.toString();
    }
}
