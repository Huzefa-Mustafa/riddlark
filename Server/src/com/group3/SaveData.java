package com.group3;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class SaveData {
    ArrayList<Object> list = new ArrayList<Object>();
    SaveData(){}
    public Boolean saveDataInJSON(String fileName, ArrayList arrayList){
        try {
            String jsonList = new Gson().toJson(arrayList);
            writeFile(fileName,jsonList);
            return true;
        } catch (Exception e) {
            System.out.println("Impossible to create the file " + fileName);
            System.out.println("Program terminated");
            return false;
        }

    }

    public ArrayList readDataFromJson(String filePath) {
        try {
            return readFile(filePath);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Impossible to open the selected file " + filePath);
            System.out.println("Program terminated");
            e.printStackTrace();
            return null;
        }
    }
    private ArrayList<Object> readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String jsonList = br.readLine();

        while (jsonList != null) {
            list.add(jsonList);
            jsonList = br.readLine();

        }
        br.close();
        return list;
    }
    private void writeFile(String filePath, String data) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
        while (data.length() > 0) {
            bw.write(data);
            bw.newLine();
        }
        bw.close();
    }
}
