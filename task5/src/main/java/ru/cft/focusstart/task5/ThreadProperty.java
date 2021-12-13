package ru.cft.focusstart.task5;

import ru.cft.focusstart.task5.exeption.PropertyException;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ThreadProperty {
    public static final String FORMAT_ERROR = "Не удалось преобразовать к числу свойство: ";
    public static final String PROPERTY_ERROR = "Ошибка: Значение свойства меньше или равно 0. ";
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
                log.warn("Ошибка: Значение свойства {} меньше или равно 0.", propertyName);
                throw new PropertyException(PROPERTY_ERROR, propertyName);
            }

            return intValue;

        } catch (NumberFormatException ex) {
            log.warn("Не удалось преобразовать свойство {} к числу. Ошибка: {}", propertyName, ex.getMessage());
            throw new PropertyException(FORMAT_ERROR, propertyName);
        }
    }
}
