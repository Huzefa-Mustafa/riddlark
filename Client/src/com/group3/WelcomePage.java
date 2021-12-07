package com.group3;

import com.group3.models.User;

import java.util.Scanner;


public class WelcomePage  {

    static Scanner scanner = new Scanner(System.in);
    static Boolean loggedIn = false;
    static User user = new User(), currentUser;
    static int port = 1234, choice;

    WelcomePage() {

    }

    public void mainMenu() {

        System.out.println("\n\t************** WELCOME TO RIDDLARK **************");

        do {
            System.out.println("\n\t╷–––––––––––––––––––––––––––––╷");
            System.out.println("\t│            MENU             │");
            System.out.println("\t│–––––––––––––––––––––––––––––│");
            System.out.println("\t│        PLEASE ENTER         │");
            System.out.println("\t│    **** 0: Login    ****    │");
            System.out.println("\t│    **** 1: Register ****    │");
            System.out.println("\t│    **** 2: About    ****    │");
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
                case 0 -> Login.Login();
                case 1 -> Register.register();
                case 2 -> About.about();
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
