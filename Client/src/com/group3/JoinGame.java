package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import java.io.*;
import java.util.Scanner;

import static com.group3.ClientSocketManager.connection;
import static com.group3.WelcomePage.*;

public class  JoinGame {

    static ClientSocketManager clientSocketManager;
    
    public static void joinGame() throws IOException {




        clientSocketManager = new ClientSocketManager(new Request(choice, user), port);
        Response response = clientSocketManager.sendRequest();
        System.out.println("Server Reply >> " + response.getMessage() );
//
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("INFO: Enter ´q´ to stop session\n");
            System.out.print("Please Enter `y` to get ready for game.   \n");
            String clientReply = scanner.nextLine(); // Waiting for keyboard input
            Request request = new Request(choice, clientReply, user); //Create a Request
            System.out.println("Client reply: " + request.getUserReply());
            if ("q".equals(clientReply)) {
                System.out.println("Exit!"); // if keyboard input equal to ´q´ close client process
                break;
            }
            clientSocketManager = new ClientSocketManager(request,port); // create a new socket task
            clientSocketManager.sendRequest(); //Run Task
        }

//        InputStream serverIn = connection.getInputStream();
//        OutputStream outputStream = connection.getOutputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(serverIn));
//
//        String msg;
//        do {
//            System.out.println(reader.readLine());
//            msg = scanner.nextLine();
//            outputStream.write(msg.getBytes());
//            outputStream.flush();
//        } while (!"quit".equalsIgnoreCase(msg));

/*        while (true) {
            System.out.println(reader.readLine());
        }*/
    }

    }


 /*   public static void joinGame() throws IOException {
        ClientSocketManager client = new ClientSocketManager(new Request(choice, user), port);
        Response response = client.sendRequest2();
        InputStream serverIn = connection.getInputStream();
        OutputStream outputStream = connection.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(serverIn));
        String line = "";
        while (true) {
            line= reader.readLine();
            if (line == null) {
                break;
            }
        }
        boolean loopBreak = true;
        line = reader.readLine();
        System.out.println("You get: "+line);
        while ((line = reader.readLine()) != null) {
            line = reader.readLine();
            System.out.println("Response is: " + line);
            line = scanner.nextLine();
            outputStream.write(line.getBytes());
            if ("quit".equalsIgnoreCase(line)) {
                break;
            }
        }
    }
}*/
