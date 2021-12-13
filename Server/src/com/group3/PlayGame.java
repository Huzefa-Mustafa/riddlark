package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.group3.ServerSocketManager.request;
import static com.group3.ServerSocketManager.response;

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

        //workerList.add(serverTask);
        if ((request.getUserReply() != null && request.getUserReply().equalsIgnoreCase("y")) && !groupList.isEmpty()) {
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
                    } else if (!iter.hasNext()) {
                        Group newGroup = new Group();
                        newGroup.addPlayer(user);
                        groupList.add(newGroup);
                        System.out.println("New Group Created : " + newGroup.toString());
                        response = new Response();
                        response.setMessage(user.getName() + " added to Group with ID: " + newGroup.getGroupID());
                        break;
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in PlayGame class. \nMessage: " + e.getMessage() + "\n Stacktrace: " + e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        List<ServerSocketManager> workerList = ServerSocketManager.server.getWorkerList();
        List<User> players = group.getPlayers(user.getName());
        for (ServerSocketManager worker : workerList) {
            for (User player : players) {
                /*System.out.println(player.getName() + " user name");
                System.out.println(worker + " workerList name" + player.getName() + " user name");*/
//                    worker.response.setMessage(player.getName() + " Added in Group");
                System.out.println(player);
            }

        }
        for (Group group : groupList) {
            System.out.println(group.getPlayers(user.getName()));
        }

    }

}
