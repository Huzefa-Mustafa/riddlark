package com.group3;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
//    private final String serverName;
//    private final int serverPort;

    private ArrayList<UserStatusListener> userStatusListeners = new ArrayList<>();
    static Socket socket;
    static OutputStream serverOut;
    static InputStream serverIn;
    static BufferedReader bufferedIn;
    static Client client;

    static String SERVERNAME;
    static int PORT;

    public Client(String serverName, int serverPort) {
        this.SERVERNAME = serverName;
        this.PORT = serverPort;
    }

    public boolean connect() {
        try {
            socket = new Socket(SERVERNAME, PORT);
            System.out.println("Client port is " + socket.getLocalPort());
            serverOut = socket.getOutputStream();
            serverIn = socket.getInputStream();
            bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addUserStatusListener(UserStatusListener listener) {
        userStatusListeners.add(listener);
    }

    public void removeUserStatusListener(UserStatusListener listener) {
        userStatusListeners.remove(listener);
    }

    public void welcomePage() throws IOException {
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
/*        if(client.login("123","123")){
            System.out.println("Login successful");
        }*/
    }

    public boolean login(String login, String password) throws IOException {
        String cmd = "login " + login + " " + password + "\n";
        serverOut.write(cmd.getBytes());
        bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
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
