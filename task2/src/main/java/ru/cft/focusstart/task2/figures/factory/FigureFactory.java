package ru.cft.focusstart.task2.figures.factory;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.figures.Circle;
import ru.cft.focusstart.task2.figures.Figure;
import ru.cft.focusstart.task2.figures.Rectangle;
import ru.cft.focusstart.task2.figures.Triangle;
import ru.cft.focusstart.task2.figures.enum_figures.Figures;
import ru.cft.focusstart.task2.figures.factory.exception.CreateFigureException;
import ru.cft.focusstart.task2.parser_file.dto.DataFigure;
import ru.cft.focusstart.task2.validators.FigureValidator;
import java.util.Collections;
import java.util.List;

@Slf4j
public class FigureFactory {

    public static Figure createFigure(DataFigure dataFigure) throws CreateFigureException {
        List<Double> paramsFigure = dataFigure.getParamsFigure();
        Figures typeFigure = dataFigure.getTypeOfFigure();

        if ((typeFigure == Figures.UNKNOWN) || !FigureValidator.isMatchCountParamsFigureType(typeFigure, paramsFigure)
                || !FigureValidator.isPositiveParamsFigure(paramsFigure)){
            log.error("Некорректные параметры: тип фигуры {}, параметры {}", typeFigure, paramsFigure );
            throw new CreateFigureException("Невозможно создать фигуру", typeFigure.name(), paramsFigure.toString());
        }

        Collections.sort(paramsFigure);

        if (FigureValidator.isCircle(dataFigure)){
            return new Circle(paramsFigure.get(0));
        } else if (FigureValidator.isRectangle(dataFigure)){
            return new Rectangle(paramsFigure.get(0), paramsFigure.get(1));
        } else if (FigureValidator.isTriangle(dataFigure)){
            return new Triangle(paramsFigure.get(0), paramsFigure.get(1), paramsFigure.get(2));
        } else {
            throw new CreateFigureException("Невозможно создать фигуру", typeFigure.name(), paramsFigure.toString());
        }

    }

}
