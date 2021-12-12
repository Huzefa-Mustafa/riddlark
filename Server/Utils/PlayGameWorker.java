/*
package com.group3;


import com.group3.models.Request;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static com.group3.PlayGame.userName;
import static com.group3.ServerSocketManager.*;

public class PlayGameWorker implements Runnable {

    private String currentUser;


    PlayGameWorker(ArrayList<Group> groupList) throws IOException, InterruptedException {
        handleClientSocket();
        // handleClientSocket1();
    }

    private void handleClientSocket() throws IOException, InterruptedException {
        OutputStream outputStream = connection.getOutputStream();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        outputStream.write(("Hi There: ").getBytes());
        outputStream.write(("Time is now " + new Date() + "\n").getBytes());
        String line ;
*/
/*        do {
            line = reader.readLine();
            System.out.println("From Client: " + line);
            String msg = "You typed :" + line + "\n";
            outputStream.write(msg.getBytes());
            outputStream.flush();
        }while(!"quit".equalsIgnoreCase(line));*//*


       */
/* while ((line = reader.readLine())) {
            if ("quit".equalsIgnoreCase(line)) {
                break;
            }
            String msg = "You typed :" + line + "\n";
            outputStream.write(msg.getBytes());
        }*//*




        for (int i = 0; i < 10; i++) {
            outputStream.write(("Time is now " + new Date() + "\n").getBytes());
            Thread.sleep(1000);
        }
    }

    public void handleClientSocket1() throws IOException, InterruptedException {
        OutputStream outputStream = connection.getOutputStream();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        //outputStream.write(("Welcome :").getBytes());

        for (int i = 0; i < 10; i++) {
            outputStream.write(("Time is now " + new Date() + "\n").getBytes());
            Thread.sleep(1000);
        }

        while ((line = reader.readLine())!=null) {

            System.out.println(line);
            if ("quit".equalsIgnoreCase(line)) {
                break;
            }
            String msg = "you typed " + line +"\n";
            outputStream.write(msg.getBytes());
        }
    }

    @Override
    public void run() {

    }
}
*/
