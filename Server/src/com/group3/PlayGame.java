package com.group3;

import com.group3.models.Request;
import com.group3.models.Response;
import com.group3.models.User;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import static com.group3.ServerSocketManager.*;

public class PlayGame {
    /**
     * The PlayGame Starts The Game Called **** RIDDLARK ****
     * */
    static ArrayList<Group> groupList = new ArrayList<>();
    static Socket connection;
    static User userName;
    /**
     * @param user to be willing to participate in the game
     * */
/*    public List<Group> getgroupList() {
        return groupList;
    }*/
    static void playGame(User user) throws IOException, InterruptedException {
//        setUserName();
   //     System.out.println(request.getUserReply());
       // Request request = new Request();
        if((request.getUserReply() != null && request.getUserReply().equalsIgnoreCase("y"))) {
            System.out.println(user.getName() + "Reply >>" + request.getUserReply());
//            runMsgThread();
        } else {
            if (groupList.isEmpty()){
                Group group = new Group();
                group.addPlayer(user);
                groupList.add(group);
                System.out.println("Empty GroupList, New Group Created : " + group.toString());
                response = new Response();
                response.setMessage( user.getName() + " added to Group with ID: " + group.getGroupID() );

            } else {
                Iterator<Group> iter = groupList.iterator();
                try {
                    while (iter.hasNext()){
                        Group prevGroup = iter.next();
                        if(!prevGroup.isPlayer(user) && prevGroup.getTotalPlayers() < 4) {
                            prevGroup.addPlayer(user);
                            System.out.println("Previous Group : " + prevGroup.toString());
                            response = new Response();
                            response.setMessage( user.getName() + " added to Group with ID: " + prevGroup.getGroupID() );
                        } else if (!iter.hasNext()) {
                            Group newGroup = new Group();
                            newGroup.addPlayer(user);
                            groupList.add(newGroup);
                            System.out.println("New Group Created : " + newGroup.toString());
                            response = new Response();
                            response.setMessage( user.getName() + " added to Group with ID: " + newGroup.getGroupID() );
                            break;
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error in PlayGame class. \nMessage: " + e.getMessage() + "\n Stacktrace: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
       }


    }
//
//    private static void runMsgThread(Group group) {
//        for (int i = 0; i < 10; i++) {
//            Response response = new Response();
//            response.setMessage("You are in group "+ group.getGroupID() + ". Current number of player in group are " + group.getTotalPlayers() + ". Please wait for other players to join.");
//            Thread.sleep(1000);
//        }
//    }

}
