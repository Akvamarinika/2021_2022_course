package ru.cft.focusstart.task2.figures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.figures.factory.exception.CreateFigureException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    /*отрицательные стороны не проверяются.
    Т.к. Проверка осуществлена в классе FigureFactory*/

class TriangleTest {
    Triangle triangle;

    @BeforeEach
    void setUp() {
        List<Double> params = Arrays.asList(3., 4.5, 7.);
        triangle = new Triangle(params);
    }


    @Test
    void calcArea_whenPositiveSides_returnArea() {
        assertEquals(4.6, triangle.calcArea());
    }

    @Test
    void calcPerimeter_whenPositiveSides_returnPerimeter() {
        assertEquals(14.5, triangle.calcPerimeter());
    }

    @Test
    void calcAngleAlpha_whenPositiveSides_returnAngleAlpha() {
        assertEquals(16.99, triangle.calcAngleAlpha());
    }

    @Test
    void calcAngleBetta_whenPositiveSides_returnAngleBetta() {
        assertEquals(26, triangle.calcAngleBetta());
    }

    @Test
    void calcAngleGamma_whenPositiveSides_returnAngleGamma() {
        assertEquals(137.01, triangle.calcAngleGamma());
    }

    @Test
    void triangle_whenNoCorrectTriangleOneSideMoreThanSumOfTwo_throwCreateFigureException() {
        List<Double> paramsFigure =  Arrays.asList(35., 5., 2.);
        assertThrows(CreateFigureException.class, () -> new Triangle(paramsFigure));

        assertThrows(CreateFigureException.class, () -> new Triangle(Arrays.asList(35., 65., 2.)));

        assertThrows(CreateFigureException.class, () -> new Triangle(Arrays.asList(35., 5., 102.)));
    }
}