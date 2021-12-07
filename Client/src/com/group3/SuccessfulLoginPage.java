package com.group3;

import static com.group3.WelcomePage.*;

public class SuccessfulLoginPage {

    static void SuccessfulLoginPage() {

        System.out.println("\n\n\t\t\t*********** Welcome " + currentUser.getName() + " ************");

        int choice;
        do {
            System.out.println("\n\t╷–––––––––––––––––––––––––––––╷");
            System.out.println("\t│            MENU             │");
            System.out.println("\t│–––––––––––––––––––––––––––––│");
            System.out.println("\t│   0. Play Game              │");
            System.out.println("\t│   1. Logout                   │");
            System.out.println("\t│_____________________________│");
            System.out.println("\n  please enter your choice");
            System.out.print("  Your choice : ");

            String input = scanner.nextLine();
            if (checkIfDigit(input)) choice = Integer.parseInt(input);
            else choice = 10;

            switch (choice) {
                case 0 -> JoinGame.joinGame();
                case 1 -> logout();
                default -> System.out.println("\n\t************** please enter correct choice **************");
            }
        } while (choice != 1);
    }

    private static void logout() {
        currentUser = null;
        loggedIn = false;
    }


}
