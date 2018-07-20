package com.example.michel.lostandfoundufms.interfaces;

import android.content.Context;

import org.json.JSONObject;

public interface ObjectRegisterInterface {
    interface View{
        Context getContext();
        void objectSaveSuccess(String message);
        void objectSaveError(String message);
    }
    interface Presenter{
        void requestSaveObject(String id, String title, String date, String situation, String classification, String image, String description);
        void requestSaveObjectApiResponse(JSONObject response);
    }
    interface Model{
        void requestSaveObjectApi(int userSessionId, String title, String date, String situation, String classification, String image, String description);
        void requestEditObjectApi(int userSessionId, String id, String title, String date, String situation, String classification, String image, String description);
    }
}
