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


public class Server implements Runnable,login,hostRoom,joinRoom,register,result,playGame,checkIfRecord {

    static Socket connection;
    static ArrayList<User> usersList = new ArrayList<>();
    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    static Request request;
    static Response response = new Response();


    public Server(Socket connection) {
        Server.connection = connection;
    }

    @Override
    public void run() {

        try {

            oos = new ObjectOutputStream(connection.getOutputStream());
            ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));


            request = (Request) ois.readObject();
            switch (request.getType()) {
                case 1 ->  login.login();
                case 2 -> register.register();
                case 3 -> hostRoom.hostRoom();
                case 4 -> joinRoom.joinRoom();
                case 5 -> result.result();
                case 6 -> System.out.println("exiting");
                case 7 -> playGame.playGame();
                case 8 -> checkIfRecord.checkIfRecord();
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


