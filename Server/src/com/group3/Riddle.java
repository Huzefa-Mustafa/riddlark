package com.group3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Riddle {
    Riddle(){}

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("riddle")
    @Expose
    private String riddle;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("ans")
    @Expose
    private String ans;
    @SerializedName("options")
    @Expose
    private String[] options;

    public String getId() { return this.id; }

    public String getRiddle() { return this.riddle; }

    public String getCategory() { return this.category; }

    public String getAns() { return this.ans; }

    public String[] getOptions() { return this.options; }

}
