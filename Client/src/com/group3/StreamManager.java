package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static com.group3.App.socket;

public class StreamManager {
    static ObjectInputStream ois;
    static ObjectOutputStream oos;
    static Request request = new Request();
    static Response response = new Response();
    public StreamManager(Request request) {
        StreamManager.request = request;
    }

    public Response sendRequest() {
        try{
            ois = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
            oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeUnshared(request);
            oos.flush();

            response = (Response) ois.readUnshared();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response sendRequestLoop() {
        try{
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeUnshared(request);
                ois = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
                response = (Response) ois.readUnshared();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }
}
