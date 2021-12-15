package com.group3;

public class ServerMain {
    public static void main(String[] args) {
        int port = 5555;
        Server server = new Server(port);
        server.start();
    }
}
