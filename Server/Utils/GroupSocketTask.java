package com.group3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;
public class GroupSocketTask implements Runnable{

    private Socket connection;  // Create Socket
    private String serverReply;
    public GroupSocketTask(Socket s, String serverReply) {
        this.connection = s;
        this.serverReply = serverReply;
    }


    @Override
    public void run() {
        try {
            /***
             *  Setting up input stream */
            BufferedReader clientRequest = new BufferedReader(new InputStreamReader(connection.getInputStream())); //Create a Request Buffer
            String requestString = clientRequest.readLine(); //Read Client request, Convert it to String
            System.out.println("Client sent : " + requestString); //Print the client request
            try {
                Thread.sleep(requestString.length()*5000); // delay the thread. Time delay = size of request string in seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /***
             *  Setting up output stream */
            DataOutputStream serverReply = new DataOutputStream(connection.getOutputStream()); //Create a Reply Buffer
            serverReply.writeBytes("Reply : " + this.serverReply); //write "Reply" in the outputStream
            serverReply.writeBytes("\n");
            serverReply.flush(); //Send written content to client
            /***
             /* Close streams*/
            serverReply.close(); //close Request Buffer
            clientRequest.close(); //close Reply Buffer

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
