package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketManager{

    static  int port;
    static ObjectInputStream ois;
    static ObjectOutputStream oos;
    static Request request = new Request();
    static Response response = new Response();

    public ClientSocketManager(Request request, int port) {
        ClientSocketManager.request = request;
        ClientSocketManager.port = port;
    }

    public Response sendRequest() {


        try {
            Socket connection = new Socket("localhost", port);
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
}
