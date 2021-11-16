package com.group3;

import com.group3.models.User;

import java.util.Scanner;


public class App  {

    static Scanner scanner = new Scanner(System.in);
    static Boolean loggedIn = false;
    static User user = new User(), currentUser;
    static int port = 1234, choice;

    public static void main(String[] args) {

        System.out.println("\n\t************** WELCOME TO RIDDLARK **************");

        do {
            System.out.println("\n\t╷–––––––––––––––––––––––––––––╷");
            System.out.println("\t│            MENU             │");
            System.out.println("\t│–––––––––––––––––––––––––––––│");
            System.out.println("\t│   0. About                  │");
            System.out.println("\t│   1. Login                  │");
            System.out.println("\t│   2. Register               │");
            System.out.println("\t│   3. Host a room            │");
            System.out.println("\t│   4. Join a room            │");
            System.out.println("\t│   5. Hall Of Fame           │");
            System.out.println("\t│   6. Exit                   │");
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
                case 3 -> HostRoom.hostRoom();
                case 4 -> JoinRoom.joinRoom();
                case 5 -> GetRecords.getRecords();
                case 6 -> System.out.println("\n\t************** GOOD BYE **************");
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
