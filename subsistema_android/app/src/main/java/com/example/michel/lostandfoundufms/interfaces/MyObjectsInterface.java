package com.example.michel.lostandfoundufms.interfaces;

import com.example.michel.lostandfoundufms.model.ObjectItem;

import org.json.JSONObject;

import java.util.List;

public interface MyObjectsInterface {
    interface View{
        void loadMyObjects(List<ObjectItem> objectList);
    }
    interface Presenter{
        void requestMyObjects(int userId);
        void requestMyObjectsApiResponse(JSONObject response);
    }
    interface Model{
        void requestMyObjectsApi(int userId);
    }
}
