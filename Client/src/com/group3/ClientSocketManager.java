package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import java.io.*;
import java.net.Socket;

public class ClientSocketManager{

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
            boolean keepRunnung = true;
            while (keepRunnung) {

                connection = new Socket("localhost", port);
                oos = new ObjectOutputStream(connection.getOutputStream());
                ois = new ObjectInputStream(new DataInputStream(connection.getInputStream()));

                oos.writeUnshared(request);

                response = (Response) ois.readUnshared();
                System.out.println(response.getMessage());
                if (response.getMessage().equalsIgnoreCase("break")) {
                    System.out.println("break");
                    break;
                }
                return response;
            }
            oos.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    static void playerGameWorker() throws IOException {



    }
}
