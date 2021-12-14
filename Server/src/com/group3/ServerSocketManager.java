package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import java.io.*;
import java.net.Socket;

import static com.group3.Server.loggedInUserList;

public class ServerSocketManager extends Thread {

    static Request request;
    static Response response = new Response();
    static User currentUser = new User();
    static String UserName;
    static Server server;
    static BufferedWriter sockwriter;
    static BufferedReader socekReader;

    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    static Socket connection;


    ServerSocketManager(Socket s,ObjectInputStream ois, ObjectOutputStream oos) {
        this.connection = s;
        this.ois = ois;
        this.oos = oos;
    }
    @Override
    public void run() {
        while (!this.connection.isClosed()) {

            try {

//            oos = new ObjectOutputStream(connection.getOutputStream());
//            ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));


//                request =  (Request) this.ois.readObject();
                readObject(this.ois);
//                User user = request.getUser();
//                System.out.println(user.toString());

                if (request.getUserReply() != null && request.getUserReply().equalsIgnoreCase("closeConnection")) {
//                    removeUserFromLoggedInList(request.getUser());
                    System.out.println("Client " + this.connection + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.connection.close();
                    System.out.println("Connection closed");
                    break;
                }

//                if(request.getSelectedOption() == 4) {
//
//                }
                switch (request.getSelectedOption()) {
                    case 1 -> Login.login();
                    case 2 -> Register.register();
                    case 3 -> PlayGame.playGame();
//                case 4 -> {/**/;}
//                case 5 -> {/*Result.result();*/}
//                case 6 -> System.out.println("exiting");
//                case 7 -> {/*PlayGame.playGame();*/}
//                case 8 -> {/*CheckIfRecord.checkIfRecord();*/}
                    default -> {
                        System.out.println(
                            "{"+
                                "\n\tSocket Info: " + this.connection +
                                "\n\tConnection State : " +  this.connection.isConnected() +
                                "\n\tUser Selection : " + request.getSelectedOption() +
                                "\n\tUser Reply >> " + request.getUserReply() +
                            "\n}"
                        );
                        response.setMessage("Invalid Command");
                    }
                }
                this.oos.writeUnshared(response);



            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try
        {
            // closing resources
            this.ois.close();
            this.oos.close();
//            this.connection.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static synchronized void removeUserFromLoggedInList(User user) {
        loggedInUserList.removeIf(i -> i.getName().equals(user.getName()));
        System.out.println(user.getName() + " Requesting log out! , No. of Logged In Users now " + loggedInUserList.size());

    }
    private synchronized void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        synchronized(objectInputStream) {
            request = (Request) objectInputStream.readObject();
        }
    }
}
