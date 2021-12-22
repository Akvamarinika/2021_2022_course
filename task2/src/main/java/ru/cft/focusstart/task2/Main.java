package ru.cft.focusstart.task2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.ParseException;
import ru.cft.focusstart.task2.commandline.CommandLineArgs;
import ru.cft.focusstart.task2.commandline.dto.UserOption;
import ru.cft.focusstart.task2.figures.Figure;
import ru.cft.focusstart.task2.figures.factory.FigureFactory;
import ru.cft.focusstart.task2.figures.factory.exception.CreateFigureException;
import ru.cft.focusstart.task2.output.Output;
import ru.cft.focusstart.task2.parserfile.ParsingFiles;
import ru.cft.focusstart.task2.parserfile.dto.DataFigure;
import ru.cft.focusstart.task2.parserfile.ParserFile;
import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            CommandLineArgs commandLineArgs = new CommandLineArgs();
            UserOption userOption = commandLineArgs.parse(args);
            ParsingFiles parsingFiles = new ParserFile();
            DataFigure dataFigure = parsingFiles.parse(userOption.getInputFile());
            Figure figure = FigureFactory.createFigure(dataFigure);
            Output.outputIn(userOption, figure);
        } catch (IOException | ParseException ex) {
            log.error("Невозможно продолжить выполнение приложения. {}", ex.getMessage());
        } catch (CreateFigureException ex){
            log.error("{} \"{}\",  с параметрами: {}", ex.getMessage(), ex.getFigureName(), ex.getFigureParams());
        }
    }
}
