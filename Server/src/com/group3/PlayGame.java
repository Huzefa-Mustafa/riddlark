package com.group3;

import com.group3.models.Response;
import com.group3.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static com.group3.ServerSocketManager.request;
import static com.group3.ServerSocketManager.response;

public class PlayGame {
    /**
     * The PlayGame Starts The Game Called **** RIDDLARK ****
     * */
    private static ArrayList<Group> groupList = new ArrayList<>();
    static User userName;
    static Group group;
    /**
//     * @param user to be willing to participate in the game
     * */
/*    public List<Group> getgroupList() {
        return groupList;
    }*/
    static void playGame() throws IOException {
//        setUserName();
   //     System.out.println(request.getUserReply());
       // Request request = new Request();
        User user = request.getUser();
        if((request.getUserReply() != null && request.getUserReply().equalsIgnoreCase("y"))) {
//            user = request.getUser();
            String groupID = user.getGroupID() ;
            System.out.println(
                "Client User : " + user.getName() + "" +
                "\nGroup ID : " +  groupID +
                "\nReply >> " + request.getUserReply()
            );
            response = new Response();
            response.setMessage(user.getName() + " belongs to group with ID : " + user.getGroupID());
//            System.out.println("");
//            Group group = getGroupById(groupID);

//            runMsgThread();
        }
//        boolean keepRunning = true;
//        while (keepRunning) {

//            if ((request.getUserReply() != null && request.getUserReply().equalsIgnoreCase("y")) && !groupList.isEmpty()) {
//
//                List<User> players = group.getPlayers();
//                List<ServerSocketManager> workerList = ServerSocketManager.server.getWorkerList();
//                for (ServerSocketManager worker : workerList) {
//                    for (User player : players) {
//                        System.out.println(player.getName() + " user name");
//                        System.out.println(worker + " workerList name");
//                        worker.response.setMessage(player.getName() + " Added in Group");
//                        oos.writeUnshared(response);
//                    }
//                }
//                /*response.setMessage("break")*/
//                ;
//                System.out.println(user.getName() + "Reply >>" + request.getUserReply());
//            }
            else if (groupList.isEmpty()) {
                group = new Group();
                group.addPlayer(user);
                groupList.add(group);
                System.out.println("Empty GroupList, New Group Created : " + group.getGroupID());
                response = new Response();
                user.setGroupID(group.getGroupID());
//                User nUser = new User(group.getGroupID(), user);
                response.setUser(user);
                request.setUser(user);
                response.setMessage(user.getName() + " added to Group with ID: " + user.getGroupID());
//                oos.writeUnshared(response);

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
//                            oos.writeUnshared(response);
                        } else if (!iter.hasNext()) {
                            Group newGroup = new Group();
                            newGroup.addPlayer(user);
                            groupList.add(newGroup);
                            System.out.println("New Group Created : " + newGroup.toString());
                            response = new Response();
                            response.setMessage(user.getName() + " added to Group with ID: " + newGroup.getGroupID());
//                            oos.writeUnshared(response);//no need of that
                            break;
                        }
                    }

            } catch (Exception e) {
                System.out.println("Error in PlayGame class. \nMessage: " + e.getMessage() + "\n Stacktrace: " + e.getLocalizedMessage());
                e.printStackTrace();
            }
        }/*
        workerList.add(serverTask); // adding running thread to array to send data all user at once
        List<ServerSocketManager> workerList = ServerSocketManager.server.getWorkerList(); //Have to seee
        List<User> players = group.getPlayers(user.getName());
        for (ServerSocketManager worker : workerList) {
            for (User player : players) {
                *//*System.out.println(player.getName() + " user name");
                System.out.println(worker + " workerList name" + player.getName() + " user name");*//*
//                    worker.response.setMessage(player.getName() + " Added in Group");
                System.out.println(player);
            }
        }*/
    }

    private static void runMsgThread(Group group) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Response response = new Response();
            response.setMessage("You are in group "+ group.getGroupID() + ". Current number of player in group are " + group.getTotalPlayers() + ". Please wait for other players to join.");
            Thread.sleep(1000);
        }
    }

    private static Group getGroupById(String groupID){
        for (Group group : groupList){
            if (group.getGroupID() == groupID) { return group; }
        }
        return null;
    }
}
