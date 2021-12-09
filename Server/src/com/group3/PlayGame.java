package com.group3;

import com.group3.models.User;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import static com.group3.ServerSocketManager.currentUser;
import static com.group3.ServerSocketManager.response;

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
        setUserName();
        if (groupList.isEmpty()){
            Group group = new Group();
            group.addPlayer(user);
            groupList.add(group);
            PlayGameWorker worker = new PlayGameWorker(groupList);
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

    private static void setUserName() {
        userName = response.getUser();
        currentUser.setName(userName.getName());
    }
}
