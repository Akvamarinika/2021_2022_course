package ru.cft.focusstart.task2.validators;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.figures.enumfigures.Figures;
import ru.cft.focusstart.task2.parserfile.dto.DataFigure;

import java.util.List;

@Slf4j
public class FigureValidator {
    public static boolean isFigure(String typeFigure){
        return (Figures.CIRCLE.name().equalsIgnoreCase(typeFigure))
                || (Figures.RECTANGLE.name().equalsIgnoreCase(typeFigure))
                || (Figures.TRIANGLE.name().equalsIgnoreCase(typeFigure));
    }

    public static boolean isPositiveParamsFigure(List<Double> params){
        for (double param : params){
            if (param <= 0){
                log.warn("В параметрах фигуры найдены отрицательные или нулевые значения: {}", param);
                return false;
            }
        }
        return true;
    }
}
