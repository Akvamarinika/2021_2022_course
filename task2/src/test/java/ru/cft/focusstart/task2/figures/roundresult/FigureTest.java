package ru.cft.focusstart.task2.figures.roundresult;

import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.figures.Figure;

import static org.junit.jupiter.api.Assertions.*;

class FigureTest {

    @Test
    void round_whenDoubleNumbers_ReturnDoubleRoundingToTwoDigits() {
        double num = Figure.round(121.12345);
        assertEquals(121.12, num, 0.001);
    }

    @Test
    void formatNum_whenDoubleNumbersAndMantissaZero_ReturnWholePartString() {
        String num = Figure.formatNum(121.0);
        assertEquals("121", num);
    }
}