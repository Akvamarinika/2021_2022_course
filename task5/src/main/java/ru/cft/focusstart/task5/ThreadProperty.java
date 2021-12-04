package ru.cft.focusstart.task5;

import ru.cft.focusstart.task5.exeption.PropertyException;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ThreadProperty {
    private static final String PROPERTIES_FILE_NAME = "threads.properties";
    private static Properties prop;

    public static void loadProperties(){
        prop = new Properties();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            if (inputStream == null) {
                log.warn("Не удалось найти {}", PROPERTIES_FILE_NAME);
                return;
            }

            prop.load(inputStream);

        } catch (IOException ex) {
            log.warn("Ошибка при чтении {}", PROPERTIES_FILE_NAME);
        }
    }

    public static int getProperty(String propertyName){
        String strValue = prop.getProperty(propertyName);

        try {
            int intValue = Integer.parseInt(strValue);

            if (intValue < 1){
                intValue = 0;
                log.warn("Значение свойства {} <= 0.", propertyName);
            }

            return intValue;

        } catch (NumberFormatException ex) {
            log.error("Не удалось преобразовать свойство {} к числу. Ошибка: {}", propertyName, ex.getMessage());
            throw new PropertyException("Не удалось преобразовать к числу свойство: ", propertyName);
        }
    }
}
