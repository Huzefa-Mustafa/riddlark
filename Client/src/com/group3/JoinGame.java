package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;

import java.io.*;

import static com.group3.ClientSocketManager.connection;
import static com.group3.WelcomePage.*;

public class  JoinGame {
    public static void joinGame() throws IOException {
        ClientSocketManager client = new ClientSocketManager(new Request(choice, user), port);
        Response response = client.sendRequest2();

        InputStream serverIn = connection.getInputStream();
        OutputStream outputStream = connection.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(serverIn));

        String msg;
        do {
            System.out.println(reader.readLine());
            msg = scanner.nextLine();
            outputStream.write(msg.getBytes());
            outputStream.flush();
        } while (!"quit".equalsIgnoreCase(msg));

/*        while (true) {
            System.out.println(reader.readLine());
        }*/
    }

    }


 /*   public static void joinGame() throws IOException {
        ClientSocketManager client = new ClientSocketManager(new Request(choice, user), port);
        Response response = client.sendRequest2();
        InputStream serverIn = connection.getInputStream();
        OutputStream outputStream = connection.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(serverIn));
        String line = "";
        while (true) {
            line= reader.readLine();
            if (line == null) {
                break;
            }
        }
        boolean loopBreak = true;
        line = reader.readLine();
        System.out.println("You get: "+line);
        while ((line = reader.readLine()) != null) {
            line = reader.readLine();
            System.out.println("Response is: " + line);
            line = scanner.nextLine();
            outputStream.write(line.getBytes());
            if ("quit".equalsIgnoreCase(line)) {
                break;
            }
        }
    }
}*/
