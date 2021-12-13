package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import java.util.Scanner;

import static com.group3.WelcomePage.*;
import static com.group3.WelcomePage.user;

class Register {
     static void register() {
//         clientSocketManager = new ClientSocketManager(new Request(choice, user), port);
//         Response response = clientSocketManager.sendRequest();
//         System.out.println("Server Reply >> " + response.getMessage() );
//
         Scanner scanner = new Scanner(System.in);
         while (true) {
             System.out.print("INFO: Enter ´q´ to stop session\n");
             System.out.print("\tEnter your display name : ");

             String name = scanner.nextLine();
             if ("q".equals(name)) {
                 System.out.println("Exit!"); // if keyboard input equal to ´q´ close client process
                 break;
             }
             System.out.print("\tEnter your password : ");
             String password = scanner.nextLine();
             if ("q".equals(password) ) {
                 System.out.println("Exit!"); // if keyboard input equal to ´q´ close client process
                 break;
             }
             User user = new User(name, password);

             StreamManager client = new StreamManager(new Request(choice, user));
             Response response = client.sendRequest();
             if (response.getErrorCode() == 0) {
                 System.out.println("\n\t******** User created successfully **********");
                 break;
             } else {
                 System.out.println("\n\t******** Failed to create user (user name already taken!) **********");
             }
         }

//         boolean flag;
//         do {
//             System.out.print("\tEnter your display name : ");
//             String name = scanner.nextLine();
//
//             System.out.print("\tEnter your password : ");
//             String password = scanner.nextLine();
//
//             User user = new User(name, password);
//
//             ClientSocketManager client = new ClientSocketManager(new Request(choice, user), port);
//             Response response = client.sendRequest();
//
//             if (response.getErrorCode() == 0) {
//                 System.out.println("\n\t******** User created successfully **********");
//                 flag = false;
//             } else {
//                 System.out.println("\n\t******** Failed to create user (user name already taken!) **********");
//                 flag = true;
//             }
//         } while (flag);

     }
}
