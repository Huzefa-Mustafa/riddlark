package com.group3;


import java.io.IOException;
public class AppServer {

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.createServer();

    }
}
