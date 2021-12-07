package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import static com.group3.WelcomePage.*;

class Login {
    static void Login() {

            do {
                System.out.print("\n\tEnter your email address : ");
                user.setEmail(scanner.nextLine());
                System.out.print("\tEnter your password : ");
                user.setPassword(scanner.nextLine());

                ClientSocketManager client = new ClientSocketManager(new Request(choice, user), port);
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
            if (loggedIn) {
                SuccessfulLoginPage.SuccessfulLoginPage();
            }

        }
    }
