package org.chatApp;

import org.chatApp.utill.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(3000);

            while (true){
                System.out.println("Waiting for a client");
                Socket socket = serverSocket.accept();
                System.out.println("client is accept");
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.run();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
