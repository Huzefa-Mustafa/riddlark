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
    private BufferedReader inputStream;
    private PrintWriter outoutStream;
    private OutputStream outputStream;
    private String login = null;
    static ArrayList<String> usersList = new ArrayList<String>();
    static File file = new File("userdata.txt");

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
            InputStream inputStream = clientSocket.getInputStream();
            this.outputStream = clientSocket.getOutputStream();

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
            outputStream.write(welcome.getBytes());
            outputStream.write(menu.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = StringUtils.split(line);
                if (tokens != null && tokens.length > 0) {
                    String cmd = tokens[0];
                    if ("3".equalsIgnoreCase(cmd)) {
                        break;
                    } else if ("1".equalsIgnoreCase(cmd)) {
                        String l = """

                            \rTo login Write username space and your password
                            \r            For example
                            \rguest 1234
                            \r""";
                        outputStream.write(l.getBytes());
                        line = reader.readLine();
                        tokens = StringUtils.split(line);
                        handleLogin(outputStream, tokens);
                    } else if ("2".equalsIgnoreCase(cmd)) {
                        String l = """

                            \rTo register Write username space and your password
                            \r            For example
                            \rguest 1234
                            \r""";
                        outputStream.write(l.getBytes());
                        line = reader.readLine();
                        tokens = StringUtils.split(line);
                        handleRegistration(outputStream, tokens);
                        outputStream.write(menu.getBytes());
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRegistration(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 2) {
            String user = tokens[0];
            String password = tokens[1];
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            if (!properties.containsKey(user)) {
                properties.setProperty(user, password);
                properties.store(new FileOutputStream(file), "TCP");
                String msg = "\r Register Successful\n\r";
                outputStream.write(msg.getBytes());
                System.out.println("User Registeration in succesfully: " + login);
            }
        }
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 2) {
            String login = tokens[0];
            String password = tokens[1];
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            if (properties.containsKey(login)) {
                if (password.equals(properties.getProperty(login))) {
                    outputStream.write("\rLogin successful,,,\r\n".getBytes());
                    System.out.println("Login successful.");
                    this.login = login;
                    List<ServerWorker> workerList = server.getWorkerList();
                    // send current user all other online logins
                    for(ServerWorker worker : workerList) {
                        if (worker.getLogin() != null) {
                            if (!login.equals(worker.getLogin())) {
                                String msg2 = "online " + worker.getLogin() + "\n";
                                send(msg2);
                            }
                        }
                    }

                    // send other online users current user's status
                    String onlineMsg = "online " + login + "\n";
                    for(ServerWorker worker : workerList) {
                        if (!login.equals(worker.getLogin())) {
                            worker.send(onlineMsg);
                        }
                    }
                } else {
                    outputStream.write("\rWrong password,,,\r\n".getBytes());
                }
            } else {
                outputStream.write("\rUsername does not exist,,,\r\n".getBytes());
            }
/*            for (String u : usersList) {
                String[] userArray= StringUtils.split(";");
                System.out.println(u);
                if ((u.equals(userArray[0])) && u.equals(userArray[1])) {

                        String msg = "ok login\n\r";
                        outputStream.write(msg.getBytes());
                        this.login = login;
                        System.out.println("User logged in succesfully: " + login);

                } else {
                    String msg = "error login\n\r";
                    outputStream.write(msg.getBytes());
                    System.err.println("Login failed for " + login);
                }
            }*/
        }
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
