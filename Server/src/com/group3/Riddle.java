package com.group3;


public class Riddle  {


    public String id;
    public String riddle;
    public String category;
    public String ans;
    public String[] options;

    public int userAns;

    public Riddle(){

    }

    public int getUserAns() {
        return userAns;
    }

    public void setUserAns(int userAns) {
        this.userAns = userAns;
    }

    public void setId(String id) { this.id = id; }

    public void setRiddle(String riddle) { this.riddle = riddle; }

    public void setCategory(String category) { this.category = category; }

    public void setAns(String ans) { this.ans = ans; }

    public void setOptions(String[] options) { this.options = options; }

    public String getId() { return this.id; }

    public String getRiddle() { return this.riddle; }

    public String getCategory() { return this.category; }

    public String getAns() { return this.ans; }

    public String[] getOptions() { return this.options; }
    public void loadRiddlesData(){
//        Gson gson = new Gson();
//        BufferedReader bufferedReader = null;
//        try {
//            bufferedReader = new BufferedReader(new FileReader("riddles.json"));
//            List Riddle[] riddleData = gson.fromJson(bufferedReader, Riddle[].class);
//            System.out.println(riddleData);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bufferedReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

    public static void main(String[] args) {
        Riddle riddle = new Riddle();
        riddle.loadRiddlesData();
    }
}
