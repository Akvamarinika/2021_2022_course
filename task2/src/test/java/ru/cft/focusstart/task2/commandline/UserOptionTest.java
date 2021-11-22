package ru.cft.focusstart.task2.commandline;

import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.commandline.dto.UserOption;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class UserOptionTest {

    @Test
    void testEquals_returnTrue() {
        UserOption expected = new UserOption();
        expected.setInputFile(new File("forDev.txt"));
        expected.setOutputFile(new File("out.txt"));

        UserOption userOption = new UserOption();
        userOption.setInputFile(new File("forDev.txt"));
        userOption.setOutputFile(new File("out.txt"));

        assertEquals(expected, userOption);
    }
}