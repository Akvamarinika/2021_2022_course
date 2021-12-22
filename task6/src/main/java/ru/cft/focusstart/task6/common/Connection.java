package ru.cft.focusstart.task6.common;

import ru.cft.focusstart.task6.common.dto.Message;
import java.io.*;
import java.net.Socket;

public class Connection implements Closeable {
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendMessage(Message message) throws IOException {
        synchronized (this.outputStream) {
            outputStream.writeObject(message);
        }
    }

    public Message receiveMessage() throws IOException, ClassNotFoundException {
        synchronized (this.inputStream) {
            Message message = (Message) inputStream.readObject();
            return message;
        }
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
