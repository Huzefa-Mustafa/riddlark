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
    private InputStream serverIn;


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
            oos.writeUnshared(request);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    static void playerGameWorker() throws IOException {



    }
}
