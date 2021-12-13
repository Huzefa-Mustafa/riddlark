package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import static com.group3.Server.loggedInUserList;
import static com.group3.Server.usersList;
import static com.group3.ServerSocketManager.*;

class Login{

    static synchronized void login() {

        int status = 3;
        User user = request.getUser();

        if (checkIfLoggedIn(user)){
            System.out.println("Incoming user trying to attempt multiple access!");
            response.setErrorCode(5);
        } else {


            for (User u : usersList) {
                if (u.getName().equals(user.getName())) {
                    if (u.getPassword().equals(user.getPassword())) {
                        status = 1;
                        currentUser = u;
                    } else {
                        status = 2;
                    }
                    break;
                }
            }

            response = new Response();

            if (usersList.isEmpty()) {
                response.setErrorCode(4);
            } else if (status == 1) {
                System.out.println(user.toString());

                if (loggedInUserList.isEmpty()) {
                    loggedInUserList.add(user);
                } else if (checkIfLoggedIn(user)) {
                    System.out.println("Incoming user trying to attempt multiple access!");
                    response.setErrorCode(5);
                } else {
                    loggedInUserList.add(user);
                }
                System.out.println("New User Logged in, No. of Logged In Users now " + loggedInUserList.size());
                response.setErrorCode(0);
                response.setUser(currentUser);
            } else if (status == 2) {
                response.setErrorCode(2);
            } else {
                response.setErrorCode(3);
            }
        }

    }

    private static synchronized Boolean checkIfLoggedIn(User requestingUser) {
        for(User user : loggedInUserList){
            if(user.getName().equals(requestingUser.getName())) { return true; }
        }
        return false;
    }


}
