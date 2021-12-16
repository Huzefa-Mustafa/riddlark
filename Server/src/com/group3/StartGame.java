package com.group3;

import java.io.*;
import java.util.Properties;
import static com.group3.ServerWorker.*;
public class StartGame {
    public synchronized void startGameHandler() throws IOException {


        Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        System.out.println(properties.getProperty("riddle1"));
        properties.setProperty("riddle2","What has 4 eyes but can't see.");
//
//        if (properties.containsKey(userName)) {
//            if (password.equals(properties.getProperty(userName))) {
//
//
//            }
//        }

    }

    public static void main(String[] args) throws IOException {
        StartGame sg = new StartGame();
        sg.startGameHandler();
    }
}

