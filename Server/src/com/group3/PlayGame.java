package com.group3;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
//import static com.group3.ServerWorker.*;
public class PlayGame {
    /**
     * The PlayGame Starts The Game Called **** RIDDLARK ****
     * */
    private static ArrayList<Group> groupList = new ArrayList<>();
    static User userName;
    /**
     //     * @param user to be willing to participate in the game
     * */
    public void playGameHandler(OutputStream outputStream, InputStream inputStream, User user) throws IOException {

        if (groupList.isEmpty()) {
            String groupID = user.getGroupID();
            System.out.println(
                    "Client User : " + user.getName() + "" +
                    "\nGroup ID : " + groupID +
                    "\nReply >> " + user.getUserReply()
            );
//            Group group = getGroupById(groupID);
//            System.out.println("Current groupID: " + group.getGroupID());


            Group group = new Group();
            group.addPlayer(user);
            groupList.add(group);
            System.out.println("Empty GroupList, New Group Created : " + group.getGroupID());
            user.setGroupID(group.getGroupID());
            String serverReply ="\rServer Reply>> "+ user.getName() + " added to Group with ID: " + user.getGroupID();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            outputStream.write(serverReply.getBytes());
//            line = reader.readLine();
//            String clientReply = line;

        } else {
            Iterator<Group> iter = groupList.iterator();
            try {
                while (iter.hasNext()) {
                    Group prevGroup = iter.next();
                    if (!prevGroup.isPlayer(user) && prevGroup.getTotalPlayers() < 4) {
                        prevGroup.addPlayer(user);
                        System.out.println("Previous Group : " + prevGroup.toString());
                        user.setGroupID(prevGroup.getGroupID());
                        String serverReply ="\nServer Reply>> "+ user.getName() + " added to Group with ID: " + user.getGroupID();
                        outputStream.write(serverReply.getBytes());
                    } else if (!iter.hasNext()) {
                        Group newGroup = new Group();
                        newGroup.addPlayer(user);
                        groupList.add(newGroup);
                        System.out.println("New Group Created : " + newGroup.toString());
                        user.setGroupID(newGroup.getGroupID());
                        String serverReply ="\nServer Reply>> "+ user.getName() + " added to Group with ID: " + user.getGroupID();
                        outputStream.write(serverReply.getBytes());

                    }
                }

            } catch (Exception e) {
                System.out.println("Error in PlayGame class. \nMessage: " + e.getMessage() + "\n Stacktrace: " + e.getLocalizedMessage());
                e.printStackTrace();
            }


        }
    }

    private static Group getGroupById(String groupID){
        for (Group group : groupList){
            if (group.getGroupID().equals(groupID)) { return group; }
        }
        return null;
    }

}