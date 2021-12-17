package ru.cft.focusstart.task6.server;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task6.server.exeption.PropertyException;
import ru.cft.focusstart.task6.server.property.PropertyReader;

@Slf4j
public class Main {
    public static final String PROPERTY_NAME_SERVER_PORT = "server.port";

    public static void main(String[] args) {

        try {

            PropertyReader.loadProperties();
            int serverPort = PropertyReader.getProperty(PROPERTY_NAME_SERVER_PORT);
            Server server =  new ChatServer(serverPort);
            server.startServer();

        } catch (PropertyException ex) {
            log.error("{} {}", ex.getMessage(), ex.getPropertyName());
        }
    }

    private void readServerPort(){
//        try {
//            PropertyReader.loadProperties();
//            int serverPort = PropertyReader.getProperty(PROPERTY_NAME_SERVER_PORT);
//            return serverPort;
//
//        } catch (PropertyException ex) {
//            log.error("{} {}", ex.getMessage(), ex.getPropertyName());
//        } catch (Exception ex){
//            log.error("Неизвестная ошибка в процессе выполнения программы: {}", ex.getMessage());
//        }
    }


}
