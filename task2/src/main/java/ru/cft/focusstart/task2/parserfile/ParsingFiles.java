package ru.cft.focusstart.task2.parserfile;

import ru.cft.focusstart.task2.parserfile.dto.DataFigure;

import java.io.File;
import java.io.IOException;

public interface ParsingFiles {
    DataFigure parse(File inputFile) throws IOException;
}
