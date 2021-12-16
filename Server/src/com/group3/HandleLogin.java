package com.group3;

import java.io.*;
import java.util.Properties;

import static com.group3.ServerWorker.*;


public class HandleLogin {
    public void loginHandler(OutputStream outputStream, InputStream inputStream, String[] tokens, BufferedReader reader) throws IOException {
        if (tokens.length == 3) {
            String userName = tokens[1];
            String password = tokens[2];
            user = new User(userName, password);
            if (checkIfLoggedIn(user)) {
                String msg = "User is already logged in\n";
                outputStream.write(msg.getBytes());
            }else {
                Properties properties = new Properties();
                properties.load(new FileInputStream(file));
                if (properties.containsKey(userName)) {
                    if (password.equals(properties.getProperty(userName))) {
                        String msg = "ok login\n";
                        outputStream.write(msg.getBytes());
                        System.out.println("Login successful.");
                        loggedInUserList.add(user);

                        System.out.println("New User Logged in, No. of Logged In Users now " + loggedInUserList.size());
                        String line = null;
                        while (!"q".equalsIgnoreCase(line)) {
                            line = reader.readLine();
                            if (line.equalsIgnoreCase("1")) {
                                boolean playGame = new PlayGame().playGameHandler(outputStream, inputStream, reader, user);
                            } else if(user.getUserReply().equalsIgnoreCase("q")) {
                                break;
                            }
                        }
                        removeLoggedIn(user);
                    } else {
                        String msg = "Wrong password,,,\n";
                        outputStream.write(msg.getBytes());
                    }
                } else {
                    String msg = "Username does not exist,,,\n";
                    outputStream.write(msg.getBytes());
                }
            }

        }
    }
    private Boolean checkIfLoggedIn(User requestingUser) {
        if (loggedInUserList != null) {
            for(User user : loggedInUserList){
                if(user.getName().equals(requestingUser.getName())) { return true; }
            }
        }
        return false;
    }
    private void removeLoggedIn(User requestingUser) {
        loggedInUserList.removeIf(user -> user.getName().equals(requestingUser.getName()));
    }
}
