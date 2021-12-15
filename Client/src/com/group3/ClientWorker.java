/*
package com.group3;

import java.io.IOException;

import static com.group3.Client.bufferedIn;
import static com.group3.Client.serverOut;

public class ClientWorker {
    private static String serverName;
    private static int serverPort;
    private Client client;

    public void Client(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public static void clientWorker() throws IOException {
        String welcome = """
                \r
                \t **** WELCOME TO RIDDLARK ****\r
                """;
        String menu = """
                \t
                \r            ****  MENU  ****           \t
                \r              PLEASE Write         \t
                \r          **** 1 : Login    ****   \t
                \r          **** 2 : Register ****   \t
                \r          **** 3 : Quit     ****    \r
                """;
        System.out.println(welcome + "\n" + menu);
        Client client = new Client(serverName, serverPort);

        if(login("123","123")){
            System.out.println("Login successful");
        }

    }


    static boolean login(String login, String password) throws IOException {
        String cmd = "1 " + login + " " + password + "\n";
        serverOut.write(cmd.getBytes());

        String response = bufferedIn.readLine();
        System.out.println("Response Line:" + response);

        if ("ok login".equalsIgnoreCase(response)) {
            startMessageReader();
            return true;
        } else {
            return false;
        }
    }

    static void startMessageReader() {
    }
}
*/
