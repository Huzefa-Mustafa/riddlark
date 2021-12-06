package com.group3;

import com.group3.models.User;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayGame {
    /**
     * The PlayGame Starts The Game Called **** RIDDLARK ****
     * */
    ArrayList<Group> groupList = new ArrayList<Group>();
    /**
     * @param user to be willing to participate in the game
     * */
    void playGame(User user) {
        if (groupList.isEmpty()){
            Group group = new Group();
            group.addPlayer(user);
        } else {
            Iterator<Group> iter = groupList.iterator();
            try {
                while (iter.hasNext()){
                    Group group = iter.next();
                    if(!group.isPlayer(user) && group.getTotalPlayers() < 4) {
                        group.addPlayer(user);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error in PlayGame class. \nMessage: " + e.getMessage() + "\n Stacktrace: " + e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }
}
