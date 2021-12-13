package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import java.io.*;
import java.net.Socket;

public class ServerSocketManager  extends Thread {

    static Socket clientSocket;
    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    static Request request;
    static Response response = new Response();
    static User currentUser = new User();
    static String UserName;
    static Server server;
    static BufferedWriter sockwriter;
    static BufferedReader socekReader;
    ServerSocketManager(Server server,Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        try {

            handleClientSocket();
/*
            boolean keepRunning = true;
            while (keepRunning) {

                oos = new ObjectOutputStream(connection.getOutputStream());
                ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));


                request = (Request) ois.readObject();
                switch (request.getSelectedOption()) {
                    case 1 -> Login.login();
                    case 2 -> Register.register();
                    case 3 -> PlayGame.playGame(request.getUser());
                    case 4 -> {*/
/*JoinRoom.joinRoom();*//*
}
                    case 5 -> {*/
/*Result.result();*//*
}
                    case 6 -> {
                        System.out.println("exiting");
                        break;
                    }
                    case 7 -> {*/
/*PlayGame.playGame();*//*
}
                    case 8 -> {*/
/*CheckIfRecord.checkIfRecord();*//*
}
                    default -> System.out.println("WRONG CHOICE");
                }
                oos.writeUnshared(response);
                oos.flush();

            }
            ois.close();
            oos.close();
            connection.close();
*/

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException, ClassNotFoundException {

        boolean keepRunning = true;


        while (keepRunning) {

            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(new DataInputStream(clientSocket.getInputStream()));
            request = (Request) ois.readObject();
            switch (request.getSelectedOption()) {
                case 1 -> Login.login();
                case 2 -> Register.register();
                case 3 -> PlayGame.playGame(request.getUser());
                case 4 -> {/*JoinRoom.joinRoom();*/}
                case 5 -> {/*Result.result();*/}
                case 6 -> {
                    System.out.println("exiting");
                    break;
                }
                case 7 -> {/*PlayGame.playGame();*/}
                case 8 -> {/*CheckIfRecord.checkIfRecord();*/}
                default -> System.out.println("WRONG CHOICE");
            }
            oos.writeUnshared(response);

          /*  ois.close();
            oos.close();
            clientSocket.close();*/

        }
    }
}
