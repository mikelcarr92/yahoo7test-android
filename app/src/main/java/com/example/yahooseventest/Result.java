package com.example.yahooseventest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**********************************
 * Created by Michael Carr on 06-Feb-16.
 *********************************/
public class Result {

    @SerializedName("results")
    public ArrayList<Program> results;

    @SerializedName("count")
    public int count;

}
