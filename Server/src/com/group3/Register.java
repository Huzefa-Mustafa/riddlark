package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import static com.group3.Server.saveData;
import static com.group3.Server.usersList;
import static com.group3.ServerSocketManager.request;
import static com.group3.ServerSocketManager.response;

class Register {


    public synchronized static void register() {

        User userDetails = request.getUser();
        System.out.println("Username : " + userDetails.getName());

        if (usersList.isEmpty()) {
            addUser(userDetails);
        } else if (userExist(userDetails)) {
            response = new Response(1);
        } else {
            addUser(userDetails);
        }
    }
    private static void addUser(User userDetails) {
        usersList.add(userDetails);
        response = new Response(0);
        saveData.saveUserData(usersList);
        System.out.println("\n************** new user ****************");
        userDetails.display();
        System.out.println("****************************************\n");
    }
    static boolean userExist(User clientUserDetails) {
        for (User user : usersList) {
            if (user.getName().equals(clientUserDetails.getName())) {
                return true;
            }
        }
        return false;
    }
}
