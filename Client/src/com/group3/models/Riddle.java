package com.group3.models;

import java.io.Serializable;

public class Riddle implements Serializable {


    //    @SerializedName("id")
//    @Expose
    public String id;
    //    @SerializedName("riddle")
//    @Expose
    public String riddle;
    //    @SerializedName("category")
//    @Expose
    public String category;
    //    @SerializedName("ans")
//    @Expose
    public String ans;
    //    @SerializedName("options")
//    @Expose
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

}
