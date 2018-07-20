package com.example.michel.lostandfoundufms.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public UserSession(Context ctx) {
        prefs = ctx.getSharedPreferences("lostFoundUser", Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.apply();
    }

    public void setLoggedIn(boolean loggedIn, int id, String name, String email){
        editor.putBoolean("loggedInMode", loggedIn);
        editor.putInt("userId", id);
        editor.putString("userName", name);
        editor.putString("userEmail", email);
        editor.commit();
    }

    public int getUserSessionId() {
        return prefs.getInt("userId", -1);
    }

    public void setUserSessionId(int id) {
        editor.putInt("userId", id);
        editor.commit();
    }

    public String getUserSessionName() {
        return prefs.getString("userName", "");
    }

    public void setUserSessionName(String username) {
        editor.putString("userName", username);
        editor.commit();
    }

    public String getUserSessionEmail() {
        return prefs.getString("userEmail", "");
    }

    public void setUserSessionEmail(String email) {
        editor.putString("userEmail", email);
        editor.commit();
    }

    public boolean loggedIn(){
        return prefs.getBoolean("loggedInMode", false);
    }
}
