package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import static com.group3.ClientSocketManager.*;
import static com.group3.WelcomePage.*;
import static com.group3.WelcomePage.ois;
import static com.group3.WelcomePage.oos;
import static com.group3.WelcomePage.request;

public class  JoinGame {

    static ClientSocketManager clientSocketManager;
    static Response response;
    public static void joinGame() throws IOException, ClassNotFoundException {
        /*ClientSocketManager clientSocketManager = new ClientSocketManager(new Request(choice, user), port);
        response = clientSocketManager.sendRequest();*/
        request = new Request(choice, user);
        oos.writeUnshared(request);

        response = (Response) ois.readUnshared();
        user = response.getUser();
        System.out.println("Server Reply >> " + response.getMessage() );
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
                request = new Request(choice, user);
                oos.writeUnshared(request);

                response = (Response) ois.readUnshared();
//                System.out.println("Client reply: " + request.getUserReply());

/*                ClientSocketManager clientSocket = new ClientSocketManager(request,port); // create a new socket task
                response = clientSocket.sendRequest(); //Run Task*/
                if (response.getUser() != null) {
//                    User user = response.getUser();
                    System.out.println("Server Reply >> " + user.toString() );
                }


                System.out.println("Server Reply >> " + response.getMessage() );



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
