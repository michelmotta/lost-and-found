package com.example.michel.lostandfoundufms.presenter;

import com.example.michel.lostandfoundufms.interfaces.ObjectRegisterInterface;
import com.example.michel.lostandfoundufms.model.ObjectRegisterModel;
import com.example.michel.lostandfoundufms.utils.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

public class ObjectRegisterPresenter implements ObjectRegisterInterface.Presenter{

    private ObjectRegisterInterface.View view;
    private ObjectRegisterInterface.Model model;
    private UserSession userSession;

    public ObjectRegisterPresenter(ObjectRegisterInterface.View view) {
        this.view = view;
        model = new ObjectRegisterModel(this);
        userSession = new UserSession(view.getContext());
    }

    @Override
    public void requestSaveObject(String id,String title, String date, String situation, String classification, String image, String description) {
        if(id.equals("")){
            model.requestSaveObjectApi(userSession.getUserSessionId(), title, date, situation, classification, image, description);
        }else{
            model.requestEditObjectApi(userSession.getUserSessionId(), id, title, date, situation, classification, image, description);
        }
    }

    @Override
    public void requestSaveObjectApiResponse(JSONObject response) {
        try {
            String status = response.getString("status");
            String message = response.getString("message");

            if(status.equals("success")){
                view.objectSaveSuccess(message);
            }else{
                view.objectSaveError(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
