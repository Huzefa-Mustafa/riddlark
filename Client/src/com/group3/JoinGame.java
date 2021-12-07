package com.group3;

import com.group3.ClientSocketManager;
import com.group3.models.Request;
import com.group3.models.Response;

import static com.group3.WelcomePage.*;

public class JoinGame {
    public static void joinGame() {
        ClientSocketManager client = new ClientSocketManager(new Request(choice, user), port);
        Response response = client.sendRequest();
    }
}
