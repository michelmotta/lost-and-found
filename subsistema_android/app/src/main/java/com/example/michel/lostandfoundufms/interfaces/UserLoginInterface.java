package com.example.michel.lostandfoundufms.interfaces;

import android.content.Context;

import org.json.JSONObject;

public interface UserLoginInterface {
    interface View{
        void loginSuccess(String message);
        void loginError(String message);
        Context getContext();
    }
    interface Presenter{
        void loginUser(String email, String password);
        void loginRequestApiResponse(JSONObject response);
    }
    interface Model{
        void loginRequestApi(String email, String password);
    }
}
