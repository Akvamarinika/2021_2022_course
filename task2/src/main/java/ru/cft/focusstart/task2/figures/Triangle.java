package ru.cft.focusstart.task2.figures;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.cft.focusstart.task2.figures.enum_figures.Figures;
import ru.cft.focusstart.task2.figures.round_result.RoundNum;

@Getter
@EqualsAndHashCode(callSuper = true)
public final class Triangle extends Figure {
    private final double sideA;
    private final double sideB;
    private final double hypotC;

    public Triangle(double sideA, double sideB, double hypotC) {
        super(Figures.TRIANGLE.getRusName());

        this.sideA = sideA;
        this.sideB = sideB;
        this.hypotC = hypotC;
    }

    @Override
    public double calcArea() {
        double semiPerimeter = this.calcPerimeter() / 2;
        double area = Math.sqrt(semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - hypotC));
        return RoundNum.round(area);
    }

    @Override
    public double calcPerimeter() {
        return sideA + sideB + hypotC;
    }

    public double calcAngleAlpha() {
        double angleA = ( sideB * sideB + hypotC * hypotC - sideA * sideA) / (2.0 * sideB * hypotC);
        double angleAlpha = Math.toDegrees(Math.acos(angleA));
        return RoundNum.round(angleAlpha);
    }

    public double calcAngleBetta() {
        double angleB = (sideA * sideA + hypotC * hypotC - sideB * sideB) / (2.0 * sideA * hypotC);
        double angleBetta = Math.toDegrees(Math.acos(angleB));
        return RoundNum.round(angleBetta);
    }

    public double calcAngleGamma() {
        double angleG = (sideA * sideA + sideB * sideB - hypotC * hypotC) / (2.0 * sideA * sideB);
        double angleGamma = Math.toDegrees(Math.acos(angleG));
        return RoundNum.round(angleGamma);
    }
}
