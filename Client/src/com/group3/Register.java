package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import static com.group3.App.*;

 class Register {
     static void register() {

         boolean flag;
         do {
             System.out.print("\n\tEnter your email address : ");
             String email = scanner.nextLine();

             System.out.print("\tEnter your display name : ");
             String name = scanner.nextLine();

             System.out.print("\tEnter your password : ");
             String password = scanner.nextLine();

             User user = new User(email, name, password);

             Client client = new Client(new Request(choice, user), port);
             Response response = client.sendRequest();

             if (response.getErrorCode() == 0) {
                 System.out.println("\n\t******** User created successfully **********");
                 flag = false;
             } else {
                 System.out.println("\n\t******** Failed to create user (email/password incorrect) **********");
                 flag = true;
             }
         } while (flag);

     }
}
