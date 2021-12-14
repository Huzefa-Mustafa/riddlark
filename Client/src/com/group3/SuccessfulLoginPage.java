package com.group3;

import com.group3.models.Request;

import java.io.IOException;

import static com.group3.ClientSocketManager.port;
import static com.group3.WelcomePage.*;

public class SuccessfulLoginPage {

    static void SuccessfulLoginPage() throws IOException, ClassNotFoundException {

        System.out.println("\n\n\t\t\t*********** Welcome " + user.getName() + " ************");

        do {
            System.out.println("\n\t╷–––––––––––––––––––––––––––––╷");
            System.out.println("\t│            MENU             │");
            System.out.println("\t│–––––––––––––––––––––––––––––│");
            System.out.println("\t│   3. Play Game              │");
            System.out.println("\t│   4. Logout                 │");
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
        // User requesting logout
        System.out.println("Requesting log out!");
        Request request = new Request(choice, "logOut", user); //Create a Request
        ClientSocketManager clientSocket = new ClientSocketManager(request,port); // create a new socket task
        clientSocket.sendRequest(); //Run Task
        currentUser = null;
        loggedIn = false;
    }


}
