package com.group3;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.ConnectException;

import static com.group3.Client.*;

public class ClientWorker {

    public static void clientWorker() throws IOException {
        try {
            String welcome = """
                    \r
                    \t **** WELCOME TO RIDDLARK ****\r
                    """;
            System.out.println(welcome);
            while (!socket.isClosed()) {

                String menu = """
                        \t
                        \r            ****  MENU  ****           \t
                        \r              PLEASE Write         \t
                        \r          **** 1 : Login    ****   \t
                        \r          **** 2 : Register ****   \t
                        \r          **** 3 : Quit     ****    \r
                        """;
                System.out.println(menu);
                System.out.println("\n  Please enter your choice");
                System.out.print("  Your Choice : ");
                String input = scanner.nextLine();
                if (checkIfDigit(input)) choice = Integer.parseInt(input);
                else choice = 10;

                String userName = null;
                String password = null;
                if (choice == 1) {
                    System.out.print("\n\tEnter your User name : ");
                    userName = scanner.nextLine();
                    System.out.print("\tEnter your password : ");
                    password = scanner.nextLine();
                    if (login(userName, password)) {
                        System.out.println("\t\t\nLogin successful");
                    } else {
                        System.out.println("\t\t\nLogin failed");
                    }
                } else if (choice == 2) {
                    System.out.print("\n\tEnter your User name : ");
                    userName = scanner.nextLine();
                    System.out.print("\tEnter your password : ");
                    password = scanner.nextLine();
                    if (registration(userName, password)) {
                        System.out.println("Registration successful");
                    } else {
                        System.err.println("Registration failed try with new user name");
                    }
                } else if (choice == 3) {
                    System.out.println("Thank you for Playing byeeee!");
                    close();
                } else System.out.println("\n\t************** Please Enter Correct Choice! **************");

            }
        }
         catch (ConnectException e) {
            System.out.println("The server is not running." + e);
        }catch (IOException e) {
            System.out.println("The connection to the server was lost.");
        } finally {
            close();
        }

    }

    static boolean registration(String login, String password) throws IOException {
        String cmd = "registration " + login + " " + password + "\n";
        serverOut.write(cmd.getBytes());

        String response = bufferedIn.readLine();
        System.out.println("Response Line:" + response);
        return "ok registration".equalsIgnoreCase(response);
    }


    static boolean login(String login, String password) throws IOException {
        String cmd = "login " + login + " " + password + "\n";
        serverOut.write(cmd.getBytes());

        String response = bufferedIn.readLine();
        System.out.println("\t\nResponse Line: " + response);

        if ("ok login".equalsIgnoreCase(response)) {
            startPlayGame();
            return true;
        } else {
            return false;
        }
    }

    private static void startPlayGame() throws IOException {

        String write = null;
        while (!"q".equalsIgnoreCase(write)) {
            String menu = """
                    \t
                    \r            ****   START RIDDLARK   ****         \t
                    \r             Please Enter '1' to Play Game       \t
                    \r                                                 \r
                    \rINFO: Enter 'q' to stop session                  \r
                    \rPlease enter your choice                         \r
                    \rYour Choice :                                    \r
                    """;
            System.out.println(menu);
            write = scanner.nextLine();
            if ("1".equalsIgnoreCase(write)) {
                serverOut.write((write+"\n").getBytes());
                String response = bufferedIn.readLine();
                System.out.println(response);
                while (!"q".equalsIgnoreCase(write)) {

                    System.out.println("\t\t\nPress y to get Ready!!!");
                    System.out.println("\t\tINFO: Enter 'q' to stop session");
                    write = scanner.nextLine();
                    if ("y".equalsIgnoreCase(write)) {
                        serverOut.write((write+"\n").getBytes());
                        startMessageReader();
                        break;
                    }else if ("q".equalsIgnoreCase(write)) {
                        break;
                    }else System.out.println("\n\t************** Please Enter Correct option! **************");
                }
            } else if ("q".equalsIgnoreCase(write)) {
                break;
            }else System.out.println("\n\t************** Please Enter Correct option! **************");
        }
    }

    private static void  startMessageReader() {
        try {
            String line;
            while (true) {
                line = bufferedIn.readLine();
                String[] tokens = StringUtils.split(line);
                if (tokens != null && tokens.length > 0) {
                    String cmd = tokens[0];
                    System.out.println(line);
                    if ("Server Reply>>".equalsIgnoreCase(cmd)) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean checkIfDigit(String input) {
        if (input.length() == 0) return false;
        for (int i = 0; i < input.length(); i++) {
            if (!(input.charAt(i) >= '0' && input.charAt(i) <= '9')) {
                return false;
            }
        }
        return true;
    }

}
