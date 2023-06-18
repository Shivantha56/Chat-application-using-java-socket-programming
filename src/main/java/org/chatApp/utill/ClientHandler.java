package org.chatApp.utill;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {


        try {

            String msg;
            while ((msg = bufferedReader.readLine()) != null) {


                System.out.println(msg);


                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();


            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

