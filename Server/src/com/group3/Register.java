package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import static com.group3.ServerSocketManager.*;
import static com.group3.Server.usersList;
import static com.group3.Server.saveData;

interface Register {
    static void Register() {

        User user = request.getUser();
        System.out.println(user.getName());
        if (verifyEmail(user.getEmail()) && verifyPassword(user.getPassword())) {
            response = new Response(0);
            usersList.add(user);
//            usersList.get(0).display();
            saveData.saveUserData(usersList);
            System.out.println("\n************** new user ****************");
            user.display();
            System.out.println("****************************************\n");

        } else {
            response = new Response(1);
        }
    }
    private static boolean verifyEmail(String email) {
        return email.contains("@") && email.contains(".") ;
    }
    private static boolean verifyPassword(String password) {

        return password.length() >= 4;
    }

}
