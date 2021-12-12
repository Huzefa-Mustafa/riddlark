package com.group3;

import com.group3.models.User;

import java.util.ArrayList;
import java.util.List;

public class Group {
    /**
     * The Group class creates group of participating players.
     * */
    private static int groupID = 10000;
    private int numberOfGroups;
    private ArrayList<User> players = new ArrayList<>();
    private Boolean isPlaying;

    Group(){
        groupID++;
        this.isPlaying = Boolean.FALSE;
    }
    /**
     * @return the current group id.
     * */
    public String getGroupID(){ return String.valueOf(this.groupID); }
    /**
     * @param user The user object to be added in the list of players.
     * */
    public void addPlayer(User user){
        if (!this.players.contains(user)) { this.players.add(user); }
    }
    /**
     * @return total player in the group list.
     * */
    public int getTotalPlayers() { return this.players.size(); }
    /**
     * @param user the user object to be checked if exists in the list of players.
     * @return true or false base on result.
     * */
    public boolean isPlayer(User user) { return this.players.contains(user); }
    /**
     * @param isPlaying sets the status of group playing or not.
     * */
    public void setIsPlayingState(boolean isPlaying) { this.isPlaying = isPlaying; }
    /**
     * @return the status of the group if playing or not.
     * */
    public boolean getIsPlayingState() { return this.isPlaying; }
    /**
     * @param user the user object to be removed from the list of players.
     * */
    public void removePlayer(User user) { this.players.remove(user); }
    /**
     * remove all players from the list of players.
     * */
    public void removeAllPlayers() { this.players.removeAll(this.players); }

    public List<User> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Group{" +
                "ID = " + this.getGroupID() +
                ",No. of players=" + this.getTotalPlayers() +
                '}';
    }


}
