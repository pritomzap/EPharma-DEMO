package com.example.user.epharma_demo.Retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/14/2017.
 */

public class Model {

    @SerializedName("message")
    public String message;
    @SerializedName("user_id")
    public String userID;
    boolean success;

    public String getUserID() {
        return userID;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
