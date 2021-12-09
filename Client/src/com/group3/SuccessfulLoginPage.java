package com.group3;

import java.io.IOException;

import static com.group3.WelcomePage.*;

public class SuccessfulLoginPage {

    static void SuccessfulLoginPage() throws IOException {

        System.out.println("\n\n\t\t\t*********** Welcome " + currentUser.getName() + " ************");

        do {
            System.out.println("\n\t╷–––––––––––––––––––––––––––––╷");
            System.out.println("\t│            MENU             │");
            System.out.println("\t│–––––––––––––––––––––––––––––│");
            System.out.println("\t│   3. Play Game              │");
            System.out.println("\t│   4. Logout                   │");
            System.out.println("\t│_____________________________│");
            System.out.println("\n  please enter your choice");
            System.out.print("  Your choice : ");

            String input = scanner.nextLine();
            if (checkIfDigit(input)) choice = Integer.parseInt(input);
            else choice = 10;

            switch (choice) {
                case 3 -> JoinGame.joinGame();
                case 4 -> logout();
                default -> System.out.println("\n\t************** please enter correct choice **************");
            }
        } while (choice != 4);
    }

    private static void logout() {
        currentUser = null;
        loggedIn = false;
    }


}
