package ru.cft.focusstart.task2.figures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    Rectangle rectangle;

    /*отрицательные стороны не проверяются.
    Т.к. Проверка осуществлена в классе FigureFactory*/

    @Test
    void calcArea_whenPositiveSides_returnArea() {
        rectangle = new Rectangle(5.2, 10);
        assertEquals(52, rectangle.calcArea());
    }

    @Test
    void calcPerimeter_whenPositiveSides_returnPerimeter() {
        rectangle = new Rectangle(5.2, 10);
        assertEquals(30.4, rectangle.calcPerimeter(), 0.001);
    }

    @Test
    void calcDiagonal_whenPositiveSides_returnLengthDiagonal() {
        rectangle = new Rectangle(5.2, 10);
        assertEquals(11.27, rectangle.calcDiagonal(), 0.001);
    }

}