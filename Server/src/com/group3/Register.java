package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import java.util.Iterator;

import static com.group3.ServerSocketManager.*;
import static com.group3.Server.usersList;
import static com.group3.Server.saveData;

class Register {


    public synchronized static void register(User userDetails) {

//        User userDetails = request.getUser();
        System.out.println("Has user: " + usersList.contains(userDetails));

        if (usersList.isEmpty()) {

            usersList.add(userDetails);
            saveData.saveUserData(usersList);
            System.out.println("\n************** new user ****************");
            userDetails.display();
            System.out.println("****************************************\n");
        } else {
            if (!userExist(userDetails)) {
                response = new Response(1);
            };
        }
    }

    private static synchronized Boolean userExist(User clientUserDetails) {
        Iterator<User> iter = usersList.iterator();
        while(iter.hasNext()) {
            User user = iter.next();
            if (!user.getName().equals(clientUserDetails.getName())) {
                response = new Response(0);
                usersList.add(user);
                saveData.saveUserData(usersList);
                System.out.println("\n************** new user ****************");
                clientUserDetails.display();
                System.out.println("****************************************\n");
                return true;
            } else if(!iter.hasNext()) {
                return false;
            }
        }
        return false;
    }
}
