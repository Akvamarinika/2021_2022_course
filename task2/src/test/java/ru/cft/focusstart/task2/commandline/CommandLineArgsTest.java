package ru.cft.focusstart.task2.commandline;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.commandline.dto.UserOption;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class CommandLineArgsTest {
    private CommandLineArgs commandLineArgs;
    private File resourcesDirectory;

    @BeforeEach
    void setUp() {
        commandLineArgs = new CommandLineArgs();
        resourcesDirectory = new File("/src/test/resources/correct_Circle.txt");
        String path = resourcesDirectory.getAbsolutePath();
        System.out.println(path);
    }

    @Test
    void parse_notArgOutput_throwParseException() {
        String[] args = {"-i", "forDev.txt"};
        assertThrows(ParseException.class, () -> commandLineArgs.parse(args));
    }

    @Test
    void parse_notArgInput_throwParseException() {
        String[] args = {"-c"};
        assertThrows(ParseException.class, () -> commandLineArgs.parse(args));
    }

    @Test
    void parse_notArgs_throwParseException() {
        String[] args = {};
        assertThrows(ParseException.class, () -> commandLineArgs.parse(args));
    }

    @Test
    void parse_thereAreExtraArgs_throwParseException() {
        String[] args = {"-i", "forDev.txt", "-abc", "123", "-f", "out.txt"};
        UserOption expected = new UserOption();
        expected.setInputFile(new File("forDev.txt"));
        expected.setOutputFile(new File("out.txt"));

        try {
            assertEquals(expected, commandLineArgs.parse(args));
        } catch (ParseException e) {
            e.getMessage();
        }
    }

    @Test
    void parse_whenSimultaneousCallOutputArgs_throwParseException() {
        String[] args = {"-i", "forDev.txt", "-f", "out.txt", "-c"};
        assertThrows(ParseException.class, () -> commandLineArgs.parse(args));
    }

    @Test
    void parse_onlyOutputInFileWithFileName_throwParseException() {
        String[] args = {"-f", "out.txt"};
        assertThrows(ParseException.class, () -> commandLineArgs.parse(args));
    }

    @Test
    void parse_whenArgInputWithoutFile_throwParseException() {
        String[] args = {"-i", "-c"};
        assertThrows(ParseException.class, () -> commandLineArgs.parse(args));
    }

    @Test
    void parse_whenOutputInFileWithoutFileName_throwParseException() {
        String[] args = {"-i", "forDev.txt", "-f"};
        assertThrows(ParseException.class, () -> commandLineArgs.parse(args));
    }

    @Test
    void parse_whenInputAndOutputInFileTrue_returnFilesNameInputAndOut() {
        String[] args = {"-i", "forDev.txt", "-f", "out.txt"};
        UserOption expected = new UserOption();
        expected.setInputFile(new File("forDev.txt"));
        expected.setOutputFile(new File("out.txt"));

        try {
            assertEquals(expected, commandLineArgs.parse(args));
        } catch (ParseException e) {
            e.getMessage();
        }

    }
}