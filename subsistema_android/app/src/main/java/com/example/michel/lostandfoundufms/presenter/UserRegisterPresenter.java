package com.example.michel.lostandfoundufms.presenter;

import com.example.michel.lostandfoundufms.interfaces.UserRegisterInterface;
import com.example.michel.lostandfoundufms.model.UserRegisterModel;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRegisterPresenter implements UserRegisterInterface.Presenter{

    private UserRegisterInterface.View view;
    private UserRegisterInterface.Model model;

    public UserRegisterPresenter(UserRegisterInterface.View view) {
        this.view = view;
        model = new UserRegisterModel(this);
    }

    @Override
    public void registerUser(String username, String email, String password) {
        model.registerRequestApi(username, email, password);
    }

    @Override
    public void registerRequestApiResponse(JSONObject response) {
        try {
            String status = response.getString("status");
            String message = response.getString("message");

            if(status.equals("success")){
                view.registerSuccess(message);
            }else{
                view.registerError(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
