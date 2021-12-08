package com.group3;

import com.group3.models.User;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayGame {
    /**
     * The PlayGame Starts The Game Called **** RIDDLARK ****
     * */
    static ArrayList<Group> groupList = new ArrayList<>();
    static Socket connection;
    /**
     * @param user to be willing to participate in the game
     * */
    static void playGame(User user) {
        if (groupList.isEmpty()){
            Group group = new Group();
            group.addPlayer(user);
            groupList.add(group);
            System.out.println("Empty GroupList, New Group Created : " + group.toString());
//            GroupSocketTask groupTask = new GroupSocketTask(connection, group.toString()); // create a new group socket task
//            groupTask.run(); // Run Task
        } else {
            Iterator<Group> iter = groupList.iterator();
            try {
                while (iter.hasNext()){
                    Group prevGroup = iter.next();
                    if(!prevGroup.isPlayer(user) && prevGroup.getTotalPlayers() < 4) {
                        prevGroup.addPlayer(user);
                        System.out.println("Previous Group : " + prevGroup.toString());
                    } else if (!iter.hasNext()) {
                        Group newGroup = new Group();
                        newGroup.addPlayer(user);
                        groupList.add(newGroup);
                        System.out.println("New Group Created : " + newGroup.toString());
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
