package com.example.michel.lostandfoundufms.interfaces;

import android.content.Context;

import org.json.JSONObject;

public interface UserInterface {
    interface View{
        Context getContext();
        void userEditSuccess(String message);
        void userEditError(String message);
    }
    interface Presenter{
        void requestEditUser(int id, String userName, String email, String password);
        void requestEditUserApiResponse(JSONObject response);
    }
    interface Model{
        void requestEditUserApi(int id, String userName, String email, String password);
    }
}
