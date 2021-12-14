package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import java.net.*;

import java.io.*;
import java.io.IOException;

import static com.group3.WelcomePage.*;

class Login {

    static Request request = new Request();
    static Response response = new Response();

    static void Login() throws IOException, ClassNotFoundException {


        InetAddress ip = InetAddress.getByName("localhost");

        Socket s = new Socket(ip, port);


        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(new DataInputStream(s.getInputStream()));

        while (true) {
            System.out.print("INFO: Enter ´Exit´ to stop session\n");
            System.out.print("\n\tEnter your User name : ");
            user.setName(scanner.nextLine());
            System.out.print("\tEnter your password : ");
            user.setPassword(scanner.nextLine());
            if ("Exit".equals(user.getName())) {
                System.out.println("Closing this connection : " + s);
                System.out.println("Connection closed");
                System.out.println("Exit!"); // if keyboard input equal to ´q´ close client process
                s.close();
                break;
            }
            oos.writeUnshared(request);

            response = (Response) ois.readUnshared();
            if (response.getErrorCode() == 0) {
                System.out.println("\n\n\t**************** logged in successfully ****************");
//                currentUser = response.getUser();
                System.out.println("\n\n\t\t\t*********** Welcome " + user.getName() + " ************");
                loggedIn = true;
//                user.setName(currentUser.getName());
                SuccessfulLoginPage.SuccessfulLoginPage();
            } else if (response.getErrorCode() == 2) {
                System.out.println("\n\t********* log in failed ( Password incorrect ) **********");
            } else if (response.getErrorCode() == 3) {
                System.out.println("\n\t********* log in failed ( user name not found ) **********");
                break;
            } else if (response.getErrorCode() == 4) {
                System.out.println("\n\t**************** No Registered User ****************");
                break;
            } else if (response.getErrorCode() == 5) {
                System.out.println("\n\t**************** User Already logged In ( Use different username ) ****************");
                break;
            }

        }
        scanner.close();
        oos.close();
        ois.close();

//            do {
//                System.out.print("\n\tEnter your User name : ");
//                user.setName(scanner.nextLine());
//                System.out.print("\tEnter your password : ");
//                user.setPassword(scanner.nextLine());
//
//                ClientSocketManager client = new ClientSocketManager(new Request(choice, user), port);
//                Response response = client.sendRequest();
//
//                if (response.getErrorCode() == 0) {
//                    System.out.println("\n\n\t**************** logged in successfully ****************");
//                    currentUser = response.getUser();
////                    System.out.println("\n\n\t\t\t*********** Welcome " + currentUser.getName() + " ************");
//                    loggedIn = true;
//                    user.setName(currentUser.getName());
//                } else if (response.getErrorCode() == 2) {
//                    System.out.println("\n\t********* log in failed ( Password incorrect ) **********");
//                } else if (response.getErrorCode() == 3) {
//                    System.out.println("\n\t********* log in failed ( user name not found ) **********");
//                    break;
//                } else if (response.getErrorCode() == 4) {
//                    System.out.println("\n\t**************** No Registered User ****************");
//                    break;
//                } else if (response.getErrorCode() == 5) {
//                    System.out.println("\n\t**************** User Already logged In ( Use different username ) ****************");
//                    break;
//                }
//            } while (!loggedIn);
//            if (loggedIn) {
//                SuccessfulLoginPage.SuccessfulLoginPage();
//            }
//
        }
    }
