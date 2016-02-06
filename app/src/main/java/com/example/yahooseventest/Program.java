package com.example.yahooseventest;

import com.google.gson.annotations.SerializedName;

/**********************************
 * Created by Michael Carr on 06-Feb-16.
 *********************************/
public class Program {

    @SerializedName("name")
    public String name;

    @SerializedName("start_time")
    public String startTime;

    @SerializedName("end_time")
    public String endTime;

    @SerializedName("channel")
    public String channel;

    @SerializedName("rating")
    public String rating;

}
