package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import java.io.IOException;

import static com.group3.WelcomePage.*;

class Login {
    static void Login() throws IOException, ClassNotFoundException {

            do {
                System.out.print("\n\tEnter your User name : ");
                user.setName(scanner.nextLine());
                System.out.print("\tEnter your password : ");
                user.setPassword(scanner.nextLine());

               /* ClientSocketManager client = new ClientSocketManager(new Request(choice, user), port);
                Response response = client.sendRequest();*/
                request = new Request(choice, user);
                oos.writeUnshared(request);
                oos.flush();

                response = (Response) ois.readUnshared();
                if (response.getErrorCode() == 0) {
                    System.out.println("\n\n\t**************** logged in successfully ****************");
                    currentUser = response.getUser();
//                    System.out.println("\n\n\t\t\t*********** Welcome " + currentUser.getName() + " ************");
                    loggedIn = true;
                    user.setName(currentUser.getName());
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
            } while (!loggedIn);
            if (loggedIn) {
                SuccessfulLoginPage.SuccessfulLoginPage();
            }

        }
    }
