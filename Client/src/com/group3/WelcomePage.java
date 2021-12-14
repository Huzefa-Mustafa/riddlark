package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class WelcomePage  {

    static Scanner scanner = new Scanner(System.in);
    static Boolean loggedIn = false;
    static User user = new User();
    static User currentUser;
    static int port = 1234, choice;

    static Request request = new Request();
    static Response response = new Response();
    InetAddress ip;
    Socket s;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    WelcomePage() {

    }

    public void mainMenu() throws IOException, ClassNotFoundException {

        try {
            ip = InetAddress.getByName("localhost");

            s = new Socket(ip, port);


            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(new DataInputStream(s.getInputStream()));


            System.out.println("\n\t************** WELCOME TO RIDDLARK **************");

            while (true) {

                oos.writeUnshared(request);

                response = (Response) ois.readUnshared();

//                if(response.getMessage() != null){
//                    System.out.println("Server Reply >> " + response.getMessage() );
//                }

                System.out.println("\n\t\t╷–––––––––––––––––––––––––––––╷");
                System.out.println("\t\t│            MENU             │");
                System.out.println("\t\t│–––––––––––––––––––––––––––––│");
                System.out.println("\t\t│        PLEASE ENTER         │");
//                System.out.println("\t\t│    **** 0: About    ****    │");
                System.out.println("\t\t│    **** 1: Login    ****    │");
                System.out.println("\t\t│    **** 2: Register ****    │");
//            System.out.println("\t│   3. Host a room            │");
//            System.out.println("\t│   4. Join a room            │");
//            System.out.println("\t│   5. Hall Of Fame           │");
//            System.out.println("\t│    **** 4: Quit     ****    │");
                System.out.println("\t\t│_____________________________│");
                System.out.println("\n  Please enter your choice");
                System.out.print("  Your Choice : ");

                System.out.print("INFO: Enter ´q´ to stop session\n");
                String input = scanner.nextLine();
                if ("q".equals(input)) {

                    System.out.println("Exit!"); // if keyboard input equal to ´q´ close client process
                    System.out.println("\n\t************** GOOD BYE **************");
                    request.setUserReply("closeConnection");
                    oos.writeUnshared(request);
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }
                if (checkIfDigit(input)) choice = Integer.parseInt(input);
                else choice = 10;

                switch (choice) {
//                    case 0 -> About.about();
                    case 1 -> Login.Login(s);
                    case 2 -> Register.register();
                    default -> System.out.println("\n\t************** Please Enter Correct Choice! **************");
                }
            }
            // closing resources
            scanner.close();
            ois.close();
            oos.close();
//            s.close();
        } catch( Exception e ){
            e.printStackTrace();
        }


    }

    static boolean checkIfDigit(String input) {
        if (input.length() == 0) return false;
        for (int i = 0; i < input.length(); i++) {
            if (!(input.charAt(i) >= '0' && input.charAt(i) <= '9')) {
                return false;
            }
        }
        return true;
    }
}
