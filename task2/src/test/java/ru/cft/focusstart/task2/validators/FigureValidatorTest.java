package ru.cft.focusstart.task2.validators;

import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.figures.enumfigures.Figures;
import ru.cft.focusstart.task2.parserfile.dto.DataFigure;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FigureValidatorTest {

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
}