package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import static com.group3.Server.*;
import static com.group3.ServerSocketManager.*;

class Login {
    static synchronized void login() {

        int status = 3;
        for (User u : usersList) {
            if (u.equals(request.getUser())) {
                if (u.getPassword().equals(request.getUser().getPassword())) {
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
            response.setErrorCode(0);
            response.setUser(currentUser);
        } else if (status == 2) {
            response.setErrorCode(2);
        } else {
            response.setErrorCode(3);
        }
    }
}
