package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static com.group3.ClientSocketManager.*;
import static com.group3.ClientSocketManager.port;
import static com.group3.WelcomePage.*;

public class  JoinGame {

    static ClientSocketManager clientSocketManager;
    static Response response;
    public static void joinGame() throws IOException, ClassNotFoundException {
        /*ClientSocketManager clientSocketManager = new ClientSocketManager(new Request(choice, user), port);
        response = clientSocketManager.sendRequest();

        System.out.println("Server Reply >> " + response.getMessage() );*/
//
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("INFO: Enter ´q´ to stop session\n");
            System.out.print("Please Enter `y` to get ready for game.   \n");

            String clientReply = scanner.nextLine(); // Waiting for keyboard input
            if ("q".equalsIgnoreCase(clientReply)) {
                System.out.println("Exit!"); // if keyboard input equal to ´q´ close client process
                break;
            } else if ("y".equalsIgnoreCase(clientReply)) {
                Request request = new Request(choice, clientReply, user); //Create a Request
                System.out.println("Client reply: " + request.getUserReply());

                ClientSocketManager clientSocket = new ClientSocketManager(request,port); // create a new socket task
                clientSocket.sendRequest2(); //Run Task
                 // print response from server



/*                while (true) {
                    response = (Response) ois.readUnshared();
                    System.out.println(response.getMessage());
                }*/


            }

        }
    }


    private static void commmunicationLoop() throws IOException {
        sockReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        printWriter = new PrintWriter(connection.getOutputStream(), true);
        boolean keepRunning = true;
        while (keepRunning) {
            String input = sockReader.readLine();
            if (input == null) {
                keepRunning = false;
            }
            System.out.println(input);
        }
    }


}
