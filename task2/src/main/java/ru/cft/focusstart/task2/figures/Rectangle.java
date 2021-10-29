package ru.cft.focusstart.task2.figures;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.figures.enum_figures.Figures;
import ru.cft.focusstart.task2.figures.round_result.RoundNum;

@Slf4j
@Getter
@EqualsAndHashCode(callSuper = true)
public final class Rectangle extends Figure {
    private final double width;
    private final double height;

    public Rectangle(double width, double height){
        super(Figures.RECTANGLE.getRusName());

        this.width = width;
        this.height = height;
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
        return RoundNum.round(diagonal);
    }
}
