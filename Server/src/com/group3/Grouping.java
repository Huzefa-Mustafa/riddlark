package com.group3;

public class Grouping {
    private int playerReady = 0;
    private String[] names;
    private static HostRoom[] hostRoom = new HostRoom[4];
    public int getplayerReady() {
        return playerReady;
    }

    public int getId(String name) {
        int id = -1;
        if (name.equals(names[0])) {
            id = 0;
        } else if (name.equals(names[1])) {
            id = 1;
        } else if (name.equals(names[2])) {
            id = 2;
        } else if (name.equals(names[3])) {
            id = 3;
        }
        return id;
    }

    public boolean addPlayer(String name) {
        boolean added = false;
        synchronized (names) {
            if (!name.equals(names)) {
            }
        }
        return added;
    }
}
