package com.group3;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);

        while (true) {
            Socket connection = serverSocket.accept();
            executorService.execute(new Server(connection));
        }
    }
}
