package com.group3;

import com.group3.models.User;

import static com.group3.ServerSocketManager.request;
import static java.lang.constant.ConstantDescs.DEFAULT_NAME;

public class HostRoom {
    private Grouping grouping;
    boolean groupPlaying = false;
    private User currentUser;
    private String name = DEFAULT_NAME;
    private int id = 0;

    public HostRoom() {
    }

    public void HostRoom() {

        if (groupPlaying || grouping.getplayerReady() > 4) {
            grouping = new Grouping();
        }
        grouping = addPlayerToGroup(request.getUser());
        name = currentUser.getName();
        id =grouping.getId(name);
    }
    private  Grouping addPlayerToGroup(User user) {
        if (groupPlaying || grouping.getplayerReady() > 4) {
            grouping = new Grouping();
        }
        if (!grouping.addPlayer(name)) {
            return null;
        }
        return grouping;
    }
}
