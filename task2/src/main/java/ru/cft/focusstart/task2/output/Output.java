package ru.cft.focusstart.task2.output;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.commandline.dto.UserOption;
import ru.cft.focusstart.task2.figures.Figure;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class Output {

    public static void outputIn(UserOption userOption, Figure figure){
        File outFile = userOption.getOutputFile();

        if (outFile == null){
            Output.outputInCMD(figure);
        } else {
            Output.outputInFile(figure, outFile);
        }
    }

    private static void outputInCMD(Figure figure){
        System.out.println(figure.figureDescription());

    }

    private static void outputInFile(Figure figure, File file){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(figure.figureDescription());
        } catch (IOException ex){
            log.error("Ошибка при записи в файл {}", file.getName());
        }
    }
}
