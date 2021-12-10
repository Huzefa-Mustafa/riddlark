package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketManager implements Runnable {

    static Socket connection;
    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    static Request request;
    static Response response = new Response();
    static User currentUser = new User();
    static String UserName;
    ServerSocketManager(Socket s) {
        this.connection = s;
    }
    @Override
    public void run() {
        try {

            oos = new ObjectOutputStream(connection.getOutputStream());
            ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));


            request = (Request) ois.readObject();
            switch (request.getSelectedOption()) {
                case 1 -> Login.login();
                case 2 -> Register.register(request.getUser());
                case 3 -> PlayGame.playGame(request.getUser());
                case 4 -> {/*JoinRoom.joinRoom();*/}
                case 5 -> {/*Result.result();*/}
                case 6 -> System.out.println("exiting");
                case 7 -> {/*PlayGame.playGame();*/}
                case 8 -> {/*CheckIfRecord.checkIfRecord();*/}
                default -> System.out.println("WRONG CHOICE");
            }
            oos.writeUnshared(response);

            ois.close();
            oos.close();
            connection.close();

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
