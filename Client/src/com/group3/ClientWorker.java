package com.group3;

import java.io.IOException;

import static com.group3.Client.*;

public class ClientWorker {

    public static void clientWorker() throws IOException {
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
                password=scanner.nextLine();
                if (login(userName, password)) {
                    System.out.println("Login successful");
                }else {
                    System.err.println("Login failed");
                }
            }else if (choice == 2) {
                System.out.print("\n\tEnter your User name : ");
                userName = scanner.nextLine();
                System.out.print("\tEnter your password : ");
                password=scanner.nextLine();
                if (registration(userName, password)) {
                    System.out.println("Registration successful");
                }else {
                    System.err.println("Registration failed try with new user name");
                }
            }

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
        System.out.println("Response Line:" + response);

        if ("ok login".equalsIgnoreCase(response)) {
            startMessageReader();
            return true;
        } else {
            return false;
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

    static void startMessageReader() {
    }
}
