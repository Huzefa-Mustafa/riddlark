package com.group3;


import java.io.IOException;

import static com.group3.Client.client;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        client = new Client("localhost", 5555);
        if (!client.connect()) {
            System.err.println("Connect failed.");
        } else {
            System.out.println("Connect successful");
            client.welcomePage();
        }
    }


}
