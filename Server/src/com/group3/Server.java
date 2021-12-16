package com.group3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Server  extends Thread{
    private final int serverPort;
    static ArrayList<ServerWorker> workerList = new ArrayList<>();

    static Vector<ServerWorker> ar = new Vector<>();

    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while(true) {
                System.out.println("About to accept client connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                ServerWorker worker = new ServerWorker(this, clientSocket);
                workerList.add(worker);
                worker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeWorker(ServerWorker serverWorker) {
        workerList.remove(serverWorker);
    }

    public List<ServerWorker> getWorkerList() {
        return workerList;
    }
}
