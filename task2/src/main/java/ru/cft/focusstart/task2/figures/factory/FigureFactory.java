package ru.cft.focusstart.task2.figures.factory;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.figures.Circle;
import ru.cft.focusstart.task2.figures.Figure;
import ru.cft.focusstart.task2.figures.Rectangle;
import ru.cft.focusstart.task2.figures.Triangle;
import ru.cft.focusstart.task2.figures.enumfigures.Figures;
import ru.cft.focusstart.task2.figures.factory.exception.CreateFigureException;
import ru.cft.focusstart.task2.parserfile.dto.DataFigure;
import ru.cft.focusstart.task2.validators.FigureValidator;
import java.util.Collections;
import java.util.List;

@Slf4j
public class FigureFactory {

    public static Figure createFigure(DataFigure dataFigure) {
        List<Double> paramsFigure = dataFigure.getParamsFigure();
        Figures typeFigure = dataFigure.getTypeOfFigure();

        if ((typeFigure == Figures.UNKNOWN) || !FigureValidator.isPositiveParamsFigure(paramsFigure)){
            log.error("Некорректные параметры: тип фигуры {}, параметры {}", typeFigure, paramsFigure );
            throw new CreateFigureException("Невозможно создать фигуру", typeFigure.name(), paramsFigure.toString());
        }

        Collections.sort(paramsFigure);

        return switch (typeFigure) {
            case CIRCLE -> new Circle(paramsFigure);
            case RECTANGLE -> new Rectangle(paramsFigure);
            default -> new Triangle(paramsFigure);
        };

    }

}
