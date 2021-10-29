package ru.cft.focusstart.task2.command_line;

import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.command_line.dto.UserOption;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class UserOptionTest {

    @Test
    void testEquals_returnTrue() {
        UserOption expected = new UserOption();
        expected.setInputFile(new File("8.txt"));
        expected.setOutputFile(new File("out.txt"));

        UserOption userOption = new UserOption();
        userOption.setInputFile(new File("8.txt"));
        userOption.setOutputFile(new File("out.txt"));

        assertEquals(expected, userOption);
    }
}