package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import java.util.Iterator;

import static com.group3.ServerSocketManager.*;
import static com.group3.Server.usersList;
import static com.group3.Server.saveData;

class Register {
    public synchronized static void register() {

        User userDetails = request.getUser();
        System.out.println("Has user: " + usersList.contains(userDetails));

        if (usersList.isEmpty()) {
            System.out.println("UserList is added");
            usersList.add(userDetails);
            saveData.saveUserData(usersList);
            System.out.println("\n************** new user ****************");
            userDetails.display();
            System.out.println("****************************************\n");
        } else {
            Iterator<User> iter = usersList.iterator();
            while(iter.hasNext()) {
                User user = iter.next();
                if (!user.getName().equals(userDetails.getName())) {
                    response = new Response(0);
                    usersList.add(user);
                    saveData.saveUserData(usersList);
                    System.out.println("\n************** new user ****************");
                    userDetails .display();
                    System.out.println("****************************************\n");
                } else {
                    response = new Response(1);
                }
            }
        }
    }
}
