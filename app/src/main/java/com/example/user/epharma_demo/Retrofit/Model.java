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
    @SerializedName("username")
    public String username;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    boolean success;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

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
