package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import java.io.*;
import java.net.Socket;

import static com.group3.Server.loggedInUserList;

public class ServerSocketManager extends Thread {

    static Socket connection;
    static ObjectOutputStream oos ;
    static ObjectInputStream ois ;
    ObjectInputStream out = null;
    static Request request;
    static Response response = new Response();
    static User currentUser = new User();
    static String UserName;
    static Server server;
    static BufferedWriter sockwriter;
    static BufferedReader socekReader;
/*    ServerSocketManager(Socket s,Server server) {
        this.server = server;
        this.connection = s;
    }*/

/*    public ServerSocketManager(Socket connection) {
        connection = connection;
        try {
            oos = new ObjectOutputStream(connection.getOutputStream());
            ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
public ServerSocketManager(Socket connection,ObjectOutputStream oos,ObjectInputStream ois) {
    this.connection = connection;
    this.oos = oos;
    this.ois = ois;
}

    @Override
    public synchronized void run() {
        try {

//            oos = new ObjectOutputStream(connection.getOutputStream());
//            ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));

            {
                request = (Request) ois.readObject();

//                  notify();
                switch (request.getSelectedOption()) {
                    case 1 -> Login.login();
                    case 2 -> Register.register();
                    case 3 -> PlayGame.playGame();
                    case 4 -> removeUserFromLoggedInList(request.getUser());
                    case 5 -> {/*Result.result();*/}
                    case 6 -> System.out.println("exiting");
                    case 7 -> {/*PlayGame.playGame();*/}
                    case 8 -> {/*CheckIfRecord.checkIfRecord();*/}
                    default -> System.out.println("WRONG CHOICE");
                }
                oos.writeUnshared(response);

               /*   ois.close();
                  oos.close();
                  connection.close();*/
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static synchronized void removeUserFromLoggedInList(User user) {
        loggedInUserList.removeIf(i -> i.getName().equals(user.getName()));
        System.out.println(user.getName() + " Requesting log out! , No. of Logged In Users now " + loggedInUserList.size());

    }
}
