package ru.cft.focusstart.task2.figures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.figures.factory.exception.CreateFigureException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    private Circle circle;

    /*отрицательный радиус не проверяется.
    Т.к. Проверка осуществлена в классе FigureFactory*/
    @BeforeEach
    void setUp() {
        List<Double> params = Collections.singletonList(5.);
        circle = new Circle(params);
    }

    @Test
    void calcArea_whenRadiusPositive_returnArea() {
        assertEquals(78.54, circle.calcArea(), 0.001);
    }

    @Test
    void calcPerimeter_whenRadiusPositive_returnPerimeter() {
        assertEquals(31.42, circle.calcPerimeter(), 0.001);
    }

    @Test
    void calcDiameter_whenRadiusPositive_returnDiameter() {
        assertEquals(10, circle.calcDiameter(), 0.001);
    }

    @Test
    void circle_whenThereAreMoreThanOneParams_throwCreateFigureException() {
        List<Double> paramsFigure =  Arrays.asList(12., 5.);
        assertThrows(CreateFigureException.class, () -> new Circle(paramsFigure));
    }
}