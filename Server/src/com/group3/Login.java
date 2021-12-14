package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import static com.group3.Server.loggedInUserList;
import static com.group3.Server.usersList;
import static com.group3.ServerSocketManager.*;
import static com.group3.ServerSocketManager.response;

class Login{

    static synchronized void login() {

        while (true){
            int status = 3;
            User user = request.getUser();

            if (checkIfLoggedIn(user)){
                System.out.println("Incoming user trying to attempt multiple access!");
                response.setErrorCode(5);
                break;
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
                    break;
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
                    break;
                } else if (status == 2) {
                    response.setErrorCode(2);
                    break;
                } else {
                    response.setErrorCode(3);
                    break;
                }
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
