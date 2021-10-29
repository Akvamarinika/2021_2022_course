package ru.cft.focusstart.task2.validators;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class DataValidatorTest {

    @Test
    void isEmptyLine_WhenEmptyLine_returnTrue() {
        boolean result = DataValidator.isEmptyLine("", new File("test.txt"));
        assertTrue(result);

    }

    @Test
    void isEmptyLine_WhenNotEmptyLine_returnFalse() {
        boolean result = DataValidator.isEmptyLine("abc", new File("test.txt"));
        assertFalse(result);

    }
}