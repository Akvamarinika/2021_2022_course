package ru.cft.focusstart.task2.validators;

import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.figures.enum_figures.Figures;
import ru.cft.focusstart.task2.parser_file.dto.DataFigure;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FigureValidatorTest {


    @Test
    void isCircle_whenParamCircleEqualsOne_returnTrue() {
        DataFigure dataFigure = new DataFigure(Figures.CIRCLE, Collections.singletonList(5.));
        boolean result = FigureValidator.isCircle(dataFigure);

        assertTrue(result);
    }

    @Test
    void isCircle_whenParamCircleNotOne_returnFalse() {
        DataFigure dataFigure = new DataFigure(Figures.CIRCLE, Arrays.asList(10., 5.));
        boolean result = FigureValidator.isCircle(dataFigure);
        assertFalse(result);

        dataFigure = new DataFigure(Figures.CIRCLE, Arrays.asList(10., 5., 7.));
        result = FigureValidator.isCircle(dataFigure);
        assertFalse(result);
    }

    @Test
    void isFigure_whenTypeCircle_returnTrue() {
        boolean result = FigureValidator.isFigure(Figures.CIRCLE.name());
        assertTrue(result);
    }

    @Test
    void isFigure_whenTypeRectangle_returnTrue() {
        boolean result = FigureValidator.isFigure(Figures.RECTANGLE.name());
        assertTrue(result);
    }

    @Test
    void isFigure_whenTypeTriangle_returnTrue() {
        boolean result = FigureValidator.isFigure(Figures.TRIANGLE.name());
        assertTrue(result);

    }

    @Test
    void isFigure_whenTypeOther_returnFalse() {
        boolean result = FigureValidator.isFigure(Figures.UNKNOWN.name());
        assertFalse(result);

        result = FigureValidator.isFigure("abc");
        assertFalse(result);
    }

    @Test
    void isRectangle_whenCorrectRectangle_returnTrue() {
        DataFigure dataFigure = new DataFigure(Figures.RECTANGLE, Arrays.asList(15., 7.));
        boolean result = FigureValidator.isRectangle(dataFigure);
        assertTrue(result);
    }

    @Test
    void isRectangle_whenSidesRectangleEquals_returnFalse() {
        DataFigure dataFigure = new DataFigure(Figures.RECTANGLE, Arrays.asList(15., 15.));
        boolean result = FigureValidator.isRectangle(dataFigure);
        assertFalse(result);
    }

    @Test
    void isTriangle_whenCorrectTriangle_returnTrue() {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(14., 15., 2.));
        boolean result = FigureValidator.isTriangle(dataFigure);
        assertTrue(result);
    }

    @Test
    void isTriangle_whenNoCorrectTriangleOneSideMoreThanSumOfTwo_returnFalse() {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(35., 5., 2.));
        boolean result = FigureValidator.isTriangle(dataFigure);
        assertFalse(result);

        dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(35., 65., 2.));
        result = FigureValidator.isTriangle(dataFigure);
        assertFalse(result);

        dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(35., 5., 102.));
        result = FigureValidator.isTriangle(dataFigure);
        assertFalse(result);
    }
}