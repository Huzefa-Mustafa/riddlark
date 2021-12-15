package com.group3;


import java.io.IOException;

import static com.group3.Client.client;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        client = new Client("localhost", 5555);
        client.addUserStatusListener(new UserStatusListener() {
            @Override
            public void online(String login) {
                System.out.println("ONLINE" + login);
            }

            @Override
            public void offline(String login) {
                System.out.println("OFFLINE" + login);
            }
        });
        if (!client.connect()) {
            System.err.println("Connect failed.");
        } else {
            System.out.println("Connect successful");
            client.welcomePage();
        }
    }


}
