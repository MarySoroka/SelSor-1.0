package connection;

import model.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;


    Client(String address, int port) throws IOException {
        socket = new Socket(address, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendData(Object data) throws IOException {
        outputStream.writeUnshared(data);
        outputStream.reset();
        outputStream.flush();
    }

    public ServerMessage getData() throws SocketException {
        try {
            return (ServerMessage) inputStream.readUnshared();
        } catch (Exception e) {
            return null;
        }
    }
}

