package com.group3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class PlayGame {
    /**
     * The PlayGame Starts The Game Called **** RIDDLARK ****
     * */
    private static ArrayList<Group> groupList = new ArrayList<>();
    static User userName;
    static Group lobbyGroup = new Group();
    static ArrayList<User> isReadyPlayerList = new ArrayList<>();
    static ArrayList<ServerWorker> workerList = new ArrayList<>();
    private static ServerWorker worker;

    public synchronized Boolean playGameHandler(OutputStream outputStream, InputStream inputStream, BufferedReader reader, User user,Server server, String workerName) throws IOException {

        lobbyGroup.addPlayer(user);

        System.out.println("User is added in lobby ");

        String serverReply ="Server Reply>> Hello " + user.getName() + "! Welcome to Riddlark.";
        outputStream.write((serverReply + "\n").getBytes());
        String line = reader.readLine();

        if(line.equalsIgnoreCase("y")) {
            user.setUserReply(line);
            user.setIsReadyState(true);
            System.out.println(
                    "Client User : " + user.getName() +
                            "User Ready to Play : " + user.getIsReadyState() +

                            "Reply >> " + user.getUserReply()
            );
            worker = getCurrentWorker(user, server);

            String waitMsg ="Server Reply>>  No. of ready players in lobby are "+ lobbyGroup.getTotalReadyPlayer().size() +
                    "/" + lobbyGroup.getTotalPlayers() + ". Please wait for other players to join.";

            worker.outputStream.write((waitMsg + "\n").getBytes());
        }
        try {
            splitLobbyPlayersInGroup(outputStream, server);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static ServerWorker getCurrentWorker(User user, Server server) {
        for (ServerWorker w: server.getWorkerList()) {
            if (user.getWorkerName().equals(w.getName())) {
                System.out.println("Worker Name: " + w.getName());
                return w;
            }
        }
        return null;
    }

    private static void splitLobbyPlayersInGroup(OutputStream outputStream, Server server) throws InterruptedException, IOException {
        /**
         * if total ready players = 2 , 3 and 4 : Create 1 group
         * if total ready players = 5 : Create 1 groups for 4 players and
         *                              tell last one ready player to join game again
         * if total ready players = 6 : Create 2 groups of 3 players each
         * if total ready players = 7 : Create 2 groups of 3 and 2 players
         * if total ready players = 8 : Create 2 groups of 4 players each
         * */

            Thread.sleep(50000);
            String waitMsg;
            isReadyPlayerList = lobbyGroup.getTotalReadyPlayer();
            if (isReadyPlayerList.size() == 2 || isReadyPlayerList.size() == 3 || isReadyPlayerList.size() == 4){
                Group group1 = new Group();
                for (User readyPlayer: isReadyPlayerList){
                    group1.addPlayer(readyPlayer);
                    lobbyGroup.removePlayer(readyPlayer);
                }
                System.out.println("New Group Created : " + group1.toString());

                for(User player : group1.getPlayers()) {
                    worker = getCurrentWorker(player, server);
                    waitMsg ="Server Reply>> You belong to group with id " + group1.getGroupID() +
                            ". No of players in group are " + group1.getTotalPlayers();
                    worker.outputStream.write((waitMsg + "\n").getBytes());
                }
//                new PlayGame();
            }

    }
    private void runGroupMsgThread(OutputStream outputStream,String message, Group group) {
        new Thread(() -> {
            try {
                while (!group.getIsPlayingState()) {
                    if(group.getIsPlayingState()) { break; }

                    String waitMsg = message;
                    outputStream.write((waitMsg + "\n").getBytes());

                    Thread.sleep(10000);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void readyPlayerListener(User user ,Group group, BufferedReader reader, OutputStream outputStream) throws IOException {
        String line = reader.readLine();
        if(line.equalsIgnoreCase("y")){
            user.setUserReply(line);
            user.setIsReadyState(true);
            System.out.println(
                    "\rClient User : " + user.getName() +
                            "\rGroup ID : " + user.getGroupID() +

                            "\rReply >> " + user.getUserReply()
            );
        }
    }
    private static void runMgsThread(Group group, User user, OutputStream outputStream){
        new Thread(() -> {
            try {
                int i = 1;
                while (i <= 5) {
                    if(group.getIsPlayingState()){break;}
                    String waitMsg ="Server Reply>> Your Group ID: "+ group.getGroupID() +
                            ". No. of ready players in group are "+ group.getTotalReadyPlayer() +
                            "/" + group.getTotalPlayers() + ". Game starts in T - " + i;
                    outputStream.write((waitMsg + "\n").getBytes());

                    Thread.sleep(5000);
                    i++;
                }
                if(group.getTotalPlayers() < 1 && group.getTotalReadyPlayer().size() > 1){
                    group.setIsPlayingState(true);
                    System.out.println("Starting game for current group "+ group.getGroupID() +"!");

                } else {
                    group.removePlayer(user);
                    /*
                     *  'E' is for termination
                     * */
                    String serverReply ="E Server Reply>>" + user.getName() + " remove from group with ID: " + user.getGroupID();
                    outputStream.write((serverReply + "\n").getBytes());
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    private static Group getGroupById(String groupID){
        for (Group group : groupList){
            if (group.getGroupID().equals(groupID)) { return group; }
        }
        return null;
    }

}