package com.group3;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServerWorker extends Thread {
    private final Server server;
    private final Socket clientSocket;
//    private BufferedReader inputStream;
    private PrintWriter outoutStream;
    private OutputStream outputStream;
    private InputStream inputStream;
    private String login = null;
    static ArrayList<User> usersList = new ArrayList<>();
    static ArrayList<User> loggedInUserList = new ArrayList<>();
    static User user = null;
    static File file = new File("userdata.txt");
    private Properties properties;

    public ServerWorker(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket=clientSocket;
//        new Thread(this).start();
    }

    @Override
    public void run() {

        handleClientSocket();

    }
    static{

        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void handleClientSocket() {
        try {
            this.inputStream = clientSocket.getInputStream();
            this.outputStream = clientSocket.getOutputStream();

            String welcome = """
                \r
                \t **** WELCOME TO RIDDLARK ****\r
                """;
            String menu = """
                \t
                \r            ****  MENU  ****           \t
                \r              PLEASE SELECT         \t
                \r          **** 1 : Login    ****   \t
                \r          **** 2 : Register ****   \t
                \r                                   \r
                \rINFO: Enter 'q' to stop session    \r
                \rPlease enter your choice           \r
                \rYour Choice :                      \r
                """;
//          outputStream.write(welcome.getBytes());
//            outputStream.write(menu.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = StringUtils.split(line);
                if (tokens != null && tokens.length > 0) {
                    String cmd = tokens[0];
                    if ("q".equalsIgnoreCase(cmd)) {
                        break;
                    } else if ("login".equalsIgnoreCase(cmd)) {
                        new HandleLogin().loginHandler(this.outputStream, this.inputStream, tokens);

                    } else if ("registration".equalsIgnoreCase(cmd)) {
                        handleRegistration(outputStream, tokens);
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRegistration(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 3) {
            String user = tokens[1];
            String password = tokens[2];
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            if (!properties.containsKey(user)) {
                properties.setProperty(user, password);
                properties.store(new FileOutputStream(file), "TCP");
                String msg = "ok registration\n";
                outputStream.write(msg.getBytes());
                System.out.println("User Registeration in succesfully: " + login);
            }
        }
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException {

    }

    private void handleLogoff() throws IOException {
        server.removeWorker(this);
        List<ServerWorker> workerList = server.getWorkerList();

        // send other online users current user's status
        String onlineMsg = "offline " + login + "\n";
        for(ServerWorker worker : workerList) {
            if (!login.equals(worker.getLogin())) {
                worker.send(onlineMsg);
            }
        }
        clientSocket.close();
    }

    private void send(String msg) {
        if (login != null) {
            try {
                outputStream.write(msg.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private String getLogin() {
        return login;
    }
}
