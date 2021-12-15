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
    static InetAddress ip;
    static Socket s;
    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    WelcomePage() {

    }

    public void mainMenu() throws IOException, ClassNotFoundException {
        ip = InetAddress.getByName("localhost");

        s = new Socket(ip, port);


        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(new DataInputStream(s.getInputStream()));

        System.out.println("\n\t************** WELCOME TO RIDDLARK **************");

        do {
            System.out.println("\n\t╷–––––––––––––––––––––––––––––╷");
            System.out.println("\t│            MENU             │");
            System.out.println("\t│–––––––––––––––––––––––––––––│");
            System.out.println("\t│        PLEASE ENTER         │");
            System.out.println("\t│    **** 0: About    ****    │");
            System.out.println("\t│    **** 1: Login    ****    │");
            System.out.println("\t│    **** 2: Register ****    │");
//            System.out.println("\t│   3. Host a room            │");
//            System.out.println("\t│   4. Join a room            │");
//            System.out.println("\t│   5. Hall Of Fame           │");
            System.out.println("\t│    **** 4: Quit     ****    │");
            System.out.println("\t│_____________________________│");
            System.out.println("\n  please enter your choice");
            System.out.print("  Your choice : ");

            String input = scanner.nextLine();
            if (checkIfDigit(input)) choice = Integer.parseInt(input);
            else choice = 10;

            switch (choice) {
                case 0 -> About.about();
                case 1 -> Login.Login();
                case 2 -> Register.register();
                case 3 -> GetRecords.getRecords();
                case 4 -> System.out.println("\n\t************** GOOD BYE **************");
                default -> System.out.println("\n\t************** please enter correct choice **************");
            }
        } while (choice != 6);


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
