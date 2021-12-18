package ru.cft.focusstart.task6.server;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task6.server.handler.ClientHandler;
import ru.cft.focusstart.task6.server.handler.Handler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ChatServer implements Server, ModelServer {
    //private ServerSocket server;
    private final int port;
    private final List<Socket> clientsSockets;
    private Thread clientHandlerThread;

    public ChatServer(int port) {
        this.clientsSockets = new ArrayList<>();
        this.port = port;
    }

    @Override
    public void startServer() {
        clientHandlerThread = new Thread(this::handlerClients);
        clientHandlerThread.setDaemon(true);

        try(ServerSocket server = new ServerSocket(port)){
            clientHandlerThread.start();
            log.info("Сервер запущен...");

            while (true){
                Socket clientSocket = server.accept();

                synchronized (clientsSockets){
                    clientsSockets.add(clientSocket);
                    log.info("Подключился Inet-адрес: {} Номер порта: {}", clientSocket.getInetAddress(), clientSocket.getLocalPort());
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handlerClients(){
        while (true){
            List<Socket> copyClientsSockets;

            synchronized (this.clientsSockets){
                copyClientsSockets = new ArrayList<>(clientsSockets);

                Handler clientsHandler = new ClientHandler(copyClientsSockets);
            }



        }
    }

    @Override
    public void nameRequest() {

    }

    @Override
    public void getNameFromClient(String name) {

    }

    @Override
    public void chatMessagesResponse() {

    }
}
