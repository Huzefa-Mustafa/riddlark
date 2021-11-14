package com.group3;


import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    static ServerSocket server;
    static Socket connection;
    static ArrayList<User> usersList = new ArrayList<>();
//    static ObjectOutputStream oos;
//    static ObjectInputStream ois;
    static Request request;
    static Response response = new Response();

    Server(){}

    public void createServer(){
        try {
            int port = 1234;
            this.server = new ServerSocket(port);
            while (true){
                /***
                 *  Connection with client */
                System.out.println("wait for connections");
                this.connection = server.accept(); // Wait and create new connection if a client request arrives
                ServerSocketManager serverTask = new ServerSocketManager(this.connection); // create a new socket task
                serverTask.run(); // Run Task
                /***
                 /* Close socket */
                this.connection.close(); // close Socket connection
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Could not connect to client");
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    public void createServer2(){
        try {
//            ServerSocket serverSocket = new ServerSocket(1234);
            int port = 1234;
            this.server = new ServerSocket(port);

            while (true) {

                System.out.println("wait for connections");
                this.connection = this.server.accept();
                executorService.execute(new ServerSocketManager(this.connection));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
//    public Server(Socket connection) {
//        Server.connection = connection;
//    }

//    @Override
//    public void run() {
//
//        try {
//
//            oos = new ObjectOutputStream(connection.getOutputStream());
//            ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));
//
//
//            request = (Request) ois.readObject();
//            switch (request.getType()) {
//                case 1 -> Login.login();
//                case 2 -> Register.register();
//                case 3 -> { /* HostRoom.hostRoom();  */}
//                case 4 -> JoinRoom.joinRoom();
//                case 5 -> Result.result();
//                case 6 -> System.out.println("exiting");
//                case 7 -> PlayGame.playGame();
//                case 8 -> CheckIfRecord.checkIfRecord();
//                default -> System.out.println("WRONG CHOICE");
//            }
//            oos.writeUnshared(response);
//
//            ois.close();
//            oos.close();
//            connection.close();
//
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

}


