package com.group3;

import com.group3.models.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server extends Thread {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    static ServerSocket server;
//    static Socket connection;
    private final int serverPort;

    static SaveData saveData = new SaveData();
    static ArrayList<User> usersList = new ArrayList<>();
    static ArrayList<User> loggedInUserList = new ArrayList<>();
    static ArrayList<ServerSocketManager> workerList = new ArrayList<>();
    static ServerSocketManager serverTask;
    Server(int serverPort){
        this.usersList = saveData.loadUserData();
        this.serverPort =serverPort;
    }
    public List<ServerSocketManager> getWorkerList() {
        return workerList;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("About to accept client connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                serverTask = new ServerSocketManager(this, clientSocket     );
//                workerList.add(serverTask);     // Have to work here
                serverTask.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  /*  public synchronized void createServer(){
        try {
            int port = 1234;
            this.server = new ServerSocket(port);
            while (true){
                *//***
                 *  Connection with client *//*
                System.out.println("wait for connections");
                this.connection = server.accept(); // Wait and create new connection if a client request arrives
                ServerSocketManager serverTask = new ServerSocketManager(this.connection, this); // create a new socket task
                workerList.add(serverTask);

                serverTask.run(); // Run Task
                *//***
                 /* Close socket *//*
                this.connection.close(); // close Socket connection
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Could not connect to client");
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }*/
   /* public void createServer2(){
        try {
//            ServerSocket serverSocket = new ServerSocket(1234);
            int port = 1234;
            this.server = new ServerSocket(port);

            while (true) {

                System.out.println("wait for connections");
                this.connection = this.server.accept();
                executorService.execute(new ServerSocketManager(this.connection,this));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/
}


