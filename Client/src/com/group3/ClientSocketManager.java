package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import java.io.*;
import java.net.Socket;

import static com.group3.App.*;
import static com.group3.WelcomePage.choice;
import static com.group3.WelcomePage.user;

public class ClientSocketManager implements Runnable{

    static  int port;
    static ObjectInputStream ois;
    static ObjectOutputStream oos;
    static Request request = new Request();
    static Response response = new Response();
    static Socket connection;
    static BufferedWriter sockWriter;
    static BufferedReader sockReader;
    static PrintWriter printWriter;


    public ClientSocketManager(Request request, int port) {
        ClientSocketManager.request = request;
        ClientSocketManager.port = port;
    }

    public Response sendRequest() {


        try {
            connection = new Socket("localhost", port);
            oos = new ObjectOutputStream(connection.getOutputStream());
            ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));

            oos.writeUnshared(request);

            response = (Response) ois.readUnshared();
            oos.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public Response sendRequest2() {

        try {

            connection = new Socket("localhost", port);
            oos = new ObjectOutputStream(connection.getOutputStream());
            ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));

            oos.writeUnshared(request);

            response = (Response) ois.readUnshared();
            oos.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    static void playerGameWorker() throws IOException {



    }

    @Override
    public void run() {
        try {
            boolean keepRunnung = true;
            while (keepRunnung) {

                connection = new Socket("localhost", port);
                oos = new ObjectOutputStream(connection.getOutputStream());
                ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));

                oos.writeUnshared(request);

                response = (Response) ois.readUnshared();
                System.out.println(response.getMessage());
/*                if (response.getMessage().equalsIgnoreCase("break")) {
                    System.out.println("break");
                    break;
                }*/

            }
            oos.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Response sendRequest3() throws IOException {


        try {

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(new DataInputStream(socket.getInputStream()));

            oos.writeUnshared(request);
            oos.flush();
            response = (Response) ois.readUnshared();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }
}
