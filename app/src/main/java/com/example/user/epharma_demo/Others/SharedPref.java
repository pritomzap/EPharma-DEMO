package com.example.user.epharma_demo.Others;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by user on 12/15/2017.
 */

public class SharedPref {

    SharedPreferences sharedPreference;
    Context c;
    String sess;

    public SharedPref(Context c) {
        this.c = c;
    }

    public void saveInfo(String email, String password, String session) {
        sharedPreference = (SharedPreferences) c.getSharedPreferences("userInfo", 0);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("session", session);
        editor.apply();
    }

    public ArrayList<String> retriveData() {
        ArrayList<String> info = new ArrayList<>();
        sharedPreference = (SharedPreferences) c.getSharedPreferences("userInfo", 0);
        info.add(sharedPreference.getString("email", "null"));
        info.add(sharedPreference.getString("password", "null"));
        sess = sharedPreference.getString("session", "null");
        info.add(sess);
        return info;
    }

    public String getSess() {
        retriveData();
        return sess;
    }
}
