package com.example.michel.lostandfoundufms.presenter;

import com.example.michel.lostandfoundufms.interfaces.MyObjectsInterface;
import com.example.michel.lostandfoundufms.model.MyObjectsModel;
import com.example.michel.lostandfoundufms.model.ObjectItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyObjectsPresenter implements MyObjectsInterface.Presenter{

    private MyObjectsInterface.View view;
    private MyObjectsInterface.Model model;

    public MyObjectsPresenter(MyObjectsInterface.View view) {
        this.view = view;
        model = new MyObjectsModel(this);
    }

    @Override
    public void requestMyObjects(int userId) {
        model.requestMyObjectsApi(userId);
    }

    @Override
    public void requestMyObjectsApiResponse(JSONObject response) {
        List<ObjectItem> objectList = new ArrayList<>();
        try {
            JSONArray objectsArray = response.getJSONArray("data");
            for (int i = 0; i < objectsArray.length(); i++){
                JSONObject jsonObject = objectsArray.getJSONObject(i);
                ObjectItem objectItem = new ObjectItem(
                        jsonObject.getInt("id"),
                        jsonObject.getInt("user_id"),
                        jsonObject.getString("title"),
                        jsonObject.getString("solved"),
                        jsonObject.getString("type"),
                        jsonObject.getString("date"),
                        jsonObject.getString("image"),
                        jsonObject.getString("description"),
                        jsonObject.getString("created"),
                        jsonObject.getString("modified")
                );
                objectList.add(objectItem);
            }
            view.loadMyObjects(objectList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
