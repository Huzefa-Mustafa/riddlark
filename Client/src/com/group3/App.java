package com.group3;

import java.io.*;
import java.net.Socket;

public class App {
    private final String serverName;
    private final int serverPort;
    static Socket socket;
    static ObjectOutputStream serverOut;
    static InputStream serverIn;
    static DataInputStream dataInputStream;

    public App(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        App client = new App("localhost", 1234);

        if (!client.connect()) {
            System.err.println("Connect failed.");
        }else{
            System.out.println("Connect successful");
            startClientCommunicationThread();
        }

/*        WelcomePage welcomePage = new WelcomePage();
        welcomePage.mainMenu();*/
    }

    private static void startClientCommunicationThread() throws IOException, ClassNotFoundException {

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    new WelcomePage();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    private boolean connect() {
        try{
            this.socket = new Socket(serverName, serverPort);
            System.out.println("Client port is " + socket.getLocalPort());
//            serverOut = new ObjectOutputStream(socket.getOutputStream());
//            serverIn = socket.getInputStream();
//            dataInputStream = new DataInputStream(new ObjectInputStream(serverIn));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
