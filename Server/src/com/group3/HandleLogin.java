package com.group3;

import java.io.*;
import java.util.Properties;

import static com.group3.ServerWorker.*;


public class HandleLogin {
    public void handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 3) {
            String login = tokens[1];
            String password = tokens[2];

            if ((login.equals("123") && password.equals("123"))) {
                String msg = "ok login\n";
                outputStream.write(msg.getBytes());
                System.out.println("User logged in succesfully: " + login);

            }
        }
    }
    public void loginHandler(OutputStream outputStream,InputStream inputStream, String[] tokens) throws IOException {
        if (tokens.length == 3) {
            String userName = tokens[1];
            String password = tokens[2];

            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            if (properties.containsKey(userName)) {
                if (password.equals(properties.getProperty(userName))) {
                    String msg = "ok login\n";
                    outputStream.write(msg.getBytes());
                    System.out.println("Login successful.");
                    user = new User(userName, password);
//                    this.login = userName;

                    if (loggedInUserList.isEmpty()) {
                        loggedInUserList.add(user);
                    } else if (checkIfLoggedIn(user)) {
                        System.out.println("Incoming user trying to attempt multiple access!");
                    } else {
                        loggedInUserList.add(user);
                    }
                    System.out.println("New User Logged in, No. of Logged In Users now " + loggedInUserList.size());
//                    List<ServerWorker> workerList = server.getWorkerList();
//                    // send current user all other online logins
//                    for(ServerWorker worker : workerList) {
//                        if (worker.getLogin() != null) {
//                            if (!login.equals(worker.getLogin())) {
//                                String msg2 = "online " + worker.getLogin() + "\n";
//                                send(msg2);
//                            }
//                        }
//                    }

                    // send other online users current user's status
//                    String onlineMsg = "online " + login + "\n";
//                    for(ServerWorker worker : workerList) {
//                        if (!login.equals(worker.getLogin())) {
//                            worker.send(onlineMsg);
//                        }
//                    }
                    /*
                    * play Game code
                    * */

                    if(user != null){
                        String line;
                        String menu = """
                                        \t
                                        \r            ****   START RIDDLARK   ****         \t
                                        \r             Please Enter 'y' to continue        \t
                                        \r                                                 \r
                                        \rINFO: Enter 'q' to stop session                  \r
                                        \rPlease enter your choice                         \r
                                        \rYour Choice :                                    \r
                                        """;
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        outputStream.write(menu.getBytes());
                        line = reader.readLine();
                        String clientReply = line;
                        System.out.println(clientReply);
                        user.setUserReply(clientReply);

                        if(clientReply.equals("y")) new PlayGame().playGameHandler(outputStream, inputStream, user);
                    }



                } else {
                    outputStream.write("\rWrong password,,,\r\n".getBytes());
                }
            } else {
                outputStream.write("\rUsername does not exist,,,\r\n".getBytes());
            }

        }
    }
    private Boolean checkIfLoggedIn(User requestingUser) {
        for(User user : loggedInUserList){
            if(user.getName().equals(requestingUser.getName())) { return true; }
        }
        return false;
    }
}
