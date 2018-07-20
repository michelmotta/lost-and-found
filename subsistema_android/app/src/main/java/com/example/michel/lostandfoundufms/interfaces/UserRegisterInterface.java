package com.example.michel.lostandfoundufms.interfaces;

import org.json.JSONObject;

public interface UserRegisterInterface {
    interface View{
        void registerSuccess(String message);
        void registerError(String message);
    }
    interface Presenter{
        void registerUser(String username, String email, String password);
        void registerRequestApiResponse(JSONObject response);
    }
    interface Model{
        void registerRequestApi(String username, String email, String password);
    }
}
