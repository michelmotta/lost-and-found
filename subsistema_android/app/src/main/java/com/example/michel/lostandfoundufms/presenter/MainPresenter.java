package com.example.michel.lostandfoundufms.presenter;

import com.example.michel.lostandfoundufms.interfaces.MainInterface;
import com.example.michel.lostandfoundufms.model.MainModel;
import com.example.michel.lostandfoundufms.model.ObjectItem;
import com.example.michel.lostandfoundufms.utils.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainInterface.Presenter{

    private MainInterface.View view;
    private MainInterface.Model model;

    private UserSession userSession;

    public MainPresenter(MainInterface.View view) {
        this.view = view;
        model = new MainModel(this);
        userSession = new UserSession(view.getContext());
    }

    @Override
    public void logoutUser() {
        userSession.setLoggedIn(false, -1, null, null);
    }

    @Override
    public void requestAllObjects() {
        model.requestAllObjectsApi();
    }

    @Override
    public void requestAllObjectsApiResponse(JSONObject response) {
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
            view.loadAllObjects(objectList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
