package com.group3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static Socket socket;
    static OutputStream serverOut;
    static InputStream serverIn;
    static BufferedReader bufferedIn;
    static Client client;

    static String SERVERNAME;
    static int PORT, choice;
    static Scanner scanner = new Scanner(System.in);

    public Client(String serverName, int serverPort) {
        this.SERVERNAME = serverName;
        this.PORT = serverPort;
    }

    public boolean connect() {
        try {
            socket = new Socket(SERVERNAME, PORT);
            System.out.println("Client port is " + socket.getLocalPort());
            serverOut = socket.getOutputStream();
            serverIn = socket.getInputStream();
            bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void welcomePage() throws IOException {
         ClientWorker.clientWorker();
    }

}
