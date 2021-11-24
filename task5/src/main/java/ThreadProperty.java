import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ThreadProperty {
    private static final String FILE_NAME = "threads.properties";
    private static Properties prop;

    public static void loadProperties(){
        prop = new Properties();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (inputStream == null) {
                log.warn("Не удалось найти threads.properties");
                return;
            }

            prop.load(inputStream);

        } catch (IOException ex) {
            log.warn("Ошибка при чтении threads.properties");
        }
    }

    public static int getProperty(String propertyName){
        String strValue = prop.getProperty(propertyName);
        int intValue;

        try {

            intValue = Integer.parseInt(strValue);

            if (intValue < 1){
                intValue = 0;
                log.warn("Значение свойства {} <= 0.", propertyName);
            }

            return intValue;

        } catch (NumberFormatException ex) {
            intValue = 0;
            log.warn("Не удалось преобразовать свойство {} к числу. Установлено значение по умолчанию: 0", propertyName);
        }

        return intValue;
    }
}
