package com.group3;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class ServerWorker extends Thread {
    private final Server server;
    private final Socket clientSocket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private BufferedReader reader;
    private String login = null;
    static ArrayList<User> usersList = new ArrayList<>();
    static ArrayList<User> loggedInUserList = new ArrayList<>();
    static User user = null;
    static File file = new File("userdata.txt");

    public ServerWorker(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket=clientSocket;
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
            this. reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = StringUtils.split(line);
                if (tokens != null && tokens.length > 0) {
                    String cmd = tokens[0];
                    if ("q".equalsIgnoreCase(cmd)) {
                        break;
                    } else if ("login".equalsIgnoreCase(cmd)) {
                        new HandleLogin().loginHandler(this.outputStream, this.inputStream, tokens,this.reader);
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

}
