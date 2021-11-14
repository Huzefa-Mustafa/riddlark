package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import static com.group3.Server.*;

interface Login {
    static void Login() {

        int status = 3;
        User currentUser = new User();
        for (User u : usersList) {
            if (u.getEmail().equals(request.getUser().getEmail())) {
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
