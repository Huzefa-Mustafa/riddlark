package com.group3;

import com.google.gson.Gson;
import com.group3.models.User;
//import com.group3.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import static com.group3.ServerSocketManager.*;
public class SaveData {
    ArrayList<User> userList = new ArrayList<>();
    String[] fileNames = {"Users.txt"};
    SaveData(){}
    public void saveUserData(ArrayList arrayList){

        Iterator<User> iter = arrayList.iterator();
        try {
            FileOutputStream fos = new FileOutputStream(fileNames[0]);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            while (iter.hasNext()){
                User user = iter.next();
                oos.writeObject(user);
            }
            oos.close();
            System.out.println("The User was successfully written to a file");

        } catch (Exception e) {
            System.out.println("Error writing object. \nMessage: " + e.getMessage() + "\n Stacktrace: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public ArrayList loadUserData() {
        this.userList = new ArrayList<>();
        try {

            FileInputStream fis = new FileInputStream(new File(fileNames[0]));
            ObjectInputStream ois = new ObjectInputStream(fis);
            User user;
            while (fis.available() > 0) {
//              while ((user = (User) ois.readObject()) != null){
                user = (User) ois.readObject();
                System.out.println("\n************ Loaded user *************");
                user.display();
                System.out.println("**************************************");
                this.userList.add(user);
            }
            ois.close();
            System.out.println("The Users are successfully loaded from a file");
            return this.userList;
        }catch (EOFException e) {
            System.out.println("File ended");
            e.printStackTrace();
            return this.userList;
        } catch (IOException e) {
            System.out.println("File Not Found. \nMessage: " + e.getMessage() + "\nStacktrace: " + e.toString());
//            e.printStackTrace();
            return this.userList;
        }  catch(ClassNotFoundException e){
            System.out.println("Error class. \nMessage: " + e.getMessage() + "\nStacktrace: " + e.getLocalizedMessage());
            return this.userList;
        } catch(Exception e){
            System.out.println("Error : \nMessage: " + e.getMessage() + "\nStacktrace: " + e.getLocalizedMessage());
            return this.userList;
        }
    }
    public void loadRiddlesData(){
        Gson gson = new Gson();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("riddles.json"));
            Riddle[] riddleData = gson.fromJson(bufferedReader, Riddle[].class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    public ArrayList readDataFromJson(String filePath) {
//        try {
//            return readFile(filePath);
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            System.out.println("Impossible to open the selected file " + filePath);
//            System.out.println("Program terminated");
//            e.printStackTrace();
//            return null;
//        }
//    }
//    private ArrayList<Object> readFile(String filePath) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(filePath));
//        String jsonList = br.readLine();
//
//        while (jsonList != null) {
//            list.add(jsonList);
//            jsonList = br.readLine();
//
//        }
//        br.close();
//        return list;
//    }
//    private void writeFile(String filePath, String data) throws IOException {
//        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
//        while (data.length() > 0) {
//            bw.write(data);
//            bw.newLine();
//        }
//        bw.close();
//    }
}
