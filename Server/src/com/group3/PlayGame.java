package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.group3.ClientSocketManager.port;
import static com.group3.Server.server;
import static com.group3.Server.usersList;
import static com.group3.ServerSocketManager.*;

public class PlayGame {
    /**
     * The PlayGame Starts The Game Called **** RIDDLARK ****
     * */
    static ArrayList<Group> groupList = new ArrayList<>();
    static User userName;
    static Group group;
    /**
     * @param user to be willing to participate in the game
     * */
/*    public List<Group> getgroupList() {
        return groupList;
    }*/
    static void playGame(User user) throws IOException {
//        setUserName();
   //     System.out.println(request.getUserReply());
       // Request request = new Request();
        boolean keepRunning = true;
        while (keepRunning) {

            if ((request.getUserReply() != null && request.getUserReply().equalsIgnoreCase("y")) && !groupList.isEmpty()) {

                List<User> players = group.getPlayers();
                List<ServerSocketManager> workerList = ServerSocketManager.server.getWorkerList();
                for (ServerSocketManager worker : workerList) {
                    for (User player : players) {
                        System.out.println(player.getName() + " user name");
                        System.out.println(worker + " workerList name");
                        worker.response.setMessage(player.getName() + " Added in Group");
                        oos.writeUnshared(response);
                    }
                }
                /*response.setMessage("break")*/
                ;
                System.out.println(user.getName() + "Reply >>" + request.getUserReply());
            } else if (groupList.isEmpty()) {
                group = new Group();
                group.addPlayer(user);
                groupList.add(group);
                System.out.println("Empty GroupList, New Group Created : " + group.toString());
                response = new Response();
                response.setMessage(user.getName() + " added to Group with ID: " + group.getGroupID());
                oos.writeUnshared(response);

            } else {
                Iterator<Group> iter = groupList.iterator();
                try {
                    while (iter.hasNext()) {
                        Group prevGroup = iter.next();
                        if (!prevGroup.isPlayer(user) && prevGroup.getTotalPlayers() < 4) {
                            prevGroup.addPlayer(user);
                            System.out.println("Previous Group : " + prevGroup.toString());
                            response = new Response();
                            response.setMessage(user.getName() + " added to Group with ID: " + prevGroup.getGroupID());
                            oos.writeUnshared(response);
                        } else if (!iter.hasNext()) {
                            Group newGroup = new Group();
                            newGroup.addPlayer(user);
                            groupList.add(newGroup);
                            System.out.println("New Group Created : " + newGroup.toString());
                            response = new Response();
                            response.setMessage(user.getName() + " added to Group with ID: " + newGroup.getGroupID());
                            oos.writeUnshared(response);
                            break;
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error in PlayGame class. \nMessage: " + e.getMessage() + "\n Stacktrace: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }

            try {
                request = (Request) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
       }

}
