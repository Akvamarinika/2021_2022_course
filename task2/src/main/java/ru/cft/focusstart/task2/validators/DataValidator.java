package ru.cft.focusstart.task2.validators;

import lombok.extern.slf4j.Slf4j;
import java.io.File;

@Slf4j
public class DataValidator {

    public static boolean isEmptyLine(String line, File file){
        if (line.isEmpty() ) {
            log.warn("Пустая строка в файле {} будет пропущена.", file.getName());
            return true;
        }

        return false;
    }


}
