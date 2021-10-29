package ru.cft.focusstart.task2.figures.round_result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundNumTest {

    @Test
    void round_whenDoubleNumbers_ReturnDoubleRoundingToTwoDigits() {
        double num = RoundNum.round(121.12345);
        assertEquals(121.12, num, 0.001);
    }
}