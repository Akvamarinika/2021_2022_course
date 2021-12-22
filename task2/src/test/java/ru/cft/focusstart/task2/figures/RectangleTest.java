package ru.cft.focusstart.task2.figures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.figures.factory.exception.CreateFigureException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    Rectangle rectangle;

    /*отрицательные стороны не проверяются.
    Т.к. Проверка осуществлена в классе FigureFactory*/
    @BeforeEach
    void setUp() {
        List<Double> params = Arrays.asList(5.2, 10.);
        rectangle = new Rectangle(params);
    }

    @Test
    void calcArea_whenPositiveSides_returnArea() {
        assertEquals(52, rectangle.calcArea());
    }

    @Test
    void calcPerimeter_whenPositiveSides_returnPerimeter() {
        assertEquals(30.4, rectangle.calcPerimeter(), 0.001);
    }

    @Test
    void calcDiagonal_whenPositiveSides_returnLengthDiagonal() {
        assertEquals(11.27, rectangle.calcDiagonal(), 0.001);
    }

    @Test
    void circle_whenThereAreMoreThanOneParams_throwCreateFigureException() {
        List<Double> paramsFigure =  Arrays.asList(12., 5.);
        assertThrows(CreateFigureException.class, () -> new Circle(paramsFigure));
    }

    @Test
    void rectangle_whenSidesRectangleEquals_throwCreateFigureException() {
        List<Double> paramsFigure =  Arrays.asList(12., 12.);
        assertThrows(CreateFigureException.class, () -> new Rectangle(paramsFigure));
    }

    @Test
    void rectangle_whenNotTwoParams_throwCreateFigureException() {
        List<Double> paramsFigure =  Arrays.asList(12., 15., 5.);
        assertThrows(CreateFigureException.class, () -> new Rectangle(paramsFigure));

        assertThrows(CreateFigureException.class, () -> new Rectangle(Collections.singletonList(12.)));
    }

}