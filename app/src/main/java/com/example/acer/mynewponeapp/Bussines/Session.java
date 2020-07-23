package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub

        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserName(String userName) {
        prefs.edit().putString("usenameSession", userName).commit();
    }
    public void setPassword(String password) {
        prefs.edit().putString("passwordSession", password).commit();
    }

    public String getUserName() {
        String userNameSession = prefs.getString("userNameSession","");
        return userNameSession;
    }

    public String getUserPassword() {
        String passwordSession = prefs.getString("passwordSession","");
        return passwordSession;
    }
}
