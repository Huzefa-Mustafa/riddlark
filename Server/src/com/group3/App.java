package com.group3;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class App {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
//        try {
//            ServerSocket serverSocket = new ServerSocket(1234);
//
//            while (true) {
//
//                System.out.println("wait for connections");
//                Socket connection = serverSocket.accept();
//                executorService.execute(new Server(connection));
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
        Server server = new Server();
        server.createServer2();

    }
}
