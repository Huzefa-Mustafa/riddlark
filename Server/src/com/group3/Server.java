package com.group3;

import com.group3.models.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    static ServerSocket serverSocket;
    static Socket connection;

    static SaveData saveData = new SaveData();
    static ArrayList<User> usersList = new ArrayList<>();

    static ArrayList<User> loggedInUserList = new ArrayList<>();
    private ArrayList<ServerSocketManager> workerList = new ArrayList<>();
    Server(){
        this.usersList = saveData.loadUserData();
    }
    public List<ServerSocketManager> getWorkerList() {
        return workerList;
    }

    public synchronized void createServer() throws IOException {

            int port = 1234;
            this.serverSocket = new ServerSocket(port);
            while (true){

                Socket s = null;
                try {
                    /***
                     *  Connection with client */
                    System.out.println("wait for connections");
                    s = serverSocket.accept(); // Wait and create new connection if a client request arrives
                    System.out.println("A new client is connected : " + s);
                    // obtaining input and out stream
                    ObjectInputStream ois = new ObjectInputStream(new DataInputStream(s.getInputStream()));
                    ObjectOutputStream oos = new ObjectOutputStream(new DataOutputStream(s.getOutputStream()));
                    System.out.println("Assigning new thread for this client");
                    // create a new thread object
                    Thread clientThread = new ServerSocketManager(s, ois, oos); // create a new socket task
                    // Invoking the start() method
                    clientThread.start();

                    /***
                     /* Close socket */
                } catch (Exception e) {
                    //TODO: handle exception
                    s.close();

                    System.out.println("Could not connect to client");
                    System.out.println(e.toString());
                    e.printStackTrace();
                }
            }

    }
}


