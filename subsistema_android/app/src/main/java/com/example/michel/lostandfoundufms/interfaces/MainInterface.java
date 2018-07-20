package com.example.michel.lostandfoundufms.interfaces;

import android.content.Context;

import com.example.michel.lostandfoundufms.model.ObjectItem;

import org.json.JSONObject;

import java.util.List;

public interface MainInterface {
    interface View{
        Context getContext();
        void loadAllObjects(List<ObjectItem> objectList);
    }
    interface Presenter{
        void logoutUser();
        void requestAllObjects();
        void requestAllObjectsApiResponse(JSONObject response);
    }
    interface Model{
        void requestAllObjectsApi();
    }
}
