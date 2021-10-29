package ru.cft.focusstart.task2.figures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    private Circle circle;

    /*отрицательный радиус не проверяется.
    Т.к. Проверка осуществлена в классе FigureFactory*/

    @Test
    void calcArea_whenRadiusPositive_returnArea() {
        circle = new Circle(5);
        assertEquals(78.54, circle.calcArea(), 0.001);
    }

    @Test
    void calcPerimeter_whenRadiusPositive_returnPerimeter() {
        circle = new Circle(5);
        assertEquals(31.42, circle.calcPerimeter(), 0.001);
    }

    @Test
    void calcDiameter_whenRadiusPositive_returnDiameter() {
        circle = new Circle(5);
        assertEquals(10, circle.calcDiameter(), 0.001);
    }


}