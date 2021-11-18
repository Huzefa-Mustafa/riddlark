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

public class ServerSocketManager implements Runnable, Login, Register, CheckIfRecord {

    static Socket connection;
    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    static Request request;
    static Response response = new Response();
    ServerSocketManager(Socket s) {
        this.connection = s;
    }
    @Override
    public void run() {
        try {

            oos = new ObjectOutputStream(connection.getOutputStream());
            ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));


            request = (Request) ois.readObject();
            switch (request.getType()) {
                case 1 -> Login.Login();
                case 2 -> Register.Register();
                case 3 -> { /* HostRoom.hostRoom();  */}
                case 4 -> {/*JoinRoom.joinRoom();*/}
                case 5 -> {/*Result.result();*/}
                case 6 -> System.out.println("exiting");
                case 7 -> {/*PlayGame.playGame();*/}
                case 8 -> CheckIfRecord.checkIfRecord();
                default -> System.out.println("WRONG CHOICE");
            }
            oos.writeUnshared(response);

            ois.close();
            oos.close();
            connection.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
