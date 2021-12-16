package com.group3;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class ServerWorker extends Thread {
    private final Server server;
    private final Socket clientSocket;
    public OutputStream outputStream;
    private InputStream inputStream;
    private BufferedReader reader;
    static String login = null;
    static ArrayList<User> usersList = new ArrayList<>();
    static ArrayList<User> loggedInUserList = new ArrayList<>();
    static User user = null;
    private ServerWorker serverWorker;
    static File file = new File("userdata.txt");

    public ServerWorker(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket=clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred when connecting to new client or communication with that client.");
        }
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

    private void handleClientSocket() throws IOException, InterruptedException {
        try {
            this.inputStream = clientSocket.getInputStream();
            this.outputStream = clientSocket.getOutputStream();
            this.reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = StringUtils.split(line);
                if (tokens != null && tokens.length > 0) {
                    String cmd = tokens[0];
                    if ("q".equalsIgnoreCase(cmd)) {
                        break;
                    } else if ("login".equalsIgnoreCase(cmd)) {
                        new HandleLogin().loginHandler(this.outputStream, this.inputStream, tokens, this.reader,this.server);
                    } else if ("registration".equalsIgnoreCase(cmd)) {
                        handleRegistration(outputStream, tokens);
                    }else if ("quit".equalsIgnoreCase(cmd)) {
                        break;
                    }
                }
            }
        } finally {
            quit();

        }
    }

    private void quit() {
        if (login == null) {
            login = " Unknown user";
        }
        System.out.println("The connection ended for" + login);
        try {
            inputStream.close();
            outputStream.close();
            clientSocket.close();
            } catch (IOException e) {
            System.out.println("Exception in getName : " + e.getMessage());
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

}
