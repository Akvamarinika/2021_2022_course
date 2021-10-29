package ru.cft.focusstart.task2.parser_file;

import ru.cft.focusstart.task2.parser_file.dto.DataFigure;

import java.io.File;
import java.io.IOException;

public interface ParsingFiles {
    DataFigure parse(File inputFile) throws IOException;
}
