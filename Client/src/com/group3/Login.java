package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import static com.group3.App.*;

interface Login {
    static void Login() {
        if (loggedIn) {
            System.out.println("\n\t********* you are already logged in ********* ");
            System.out.println("\tenter \"y\" to logout and Login as a new user");
            System.out.print("\tyour choice: ");
            String choice = scanner.nextLine();

            if (choice.toLowerCase().equals("y")) {
                currentUser = null;
                loggedIn = false;
                Login();
            } else {
                System.out.println("\n\n\t\t\t*********** Welcome back " + currentUser.getName() + " ************");
            }
        } else {

            do {
                System.out.print("\n\tEnter your email address : ");
                user.setEmail(scanner.nextLine());
                System.out.print("\tEnter your password : ");
                user.setPassword(scanner.nextLine());

                Client client = new Client(new Request(choice, user), port);
                Response response = client.sendRequest();

                if (response.getErrorCode() == 0) {
                    System.out.println("\n\n\t**************** logged in successfully ****************");
                    currentUser = response.getUser();
                    System.out.println("\n\n\t\t\t*********** Welcome " + currentUser.getName() + " ************");
                    loggedIn = true;
                } else if (response.getErrorCode() == 2) {
                    System.out.println("\n\t********* log in failed ( Password incorrect ) **********");
                } else if (response.getErrorCode() == 3) {
                    System.out.println("\n\t********* log in failed ( email not found ) **********");
                    break;
                } else if (response.getErrorCode() == 4) {
                    System.out.println("\n\t**************** No registered user ****************");
                    break;
                }


            } while (!loggedIn);

        }
    }
}
