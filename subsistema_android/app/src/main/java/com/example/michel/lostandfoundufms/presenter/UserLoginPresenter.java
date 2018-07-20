package com.example.michel.lostandfoundufms.presenter;

import com.example.michel.lostandfoundufms.interfaces.UserLoginInterface;
import com.example.michel.lostandfoundufms.model.UserLoginModel;
import com.example.michel.lostandfoundufms.utils.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

public class UserLoginPresenter implements UserLoginInterface.Presenter{

    private UserLoginInterface.View view;
    private UserLoginInterface.Model model;
    private UserSession userSession;

    public UserLoginPresenter(UserLoginInterface.View view) {
        this.view = view;
        model = new UserLoginModel(this);
        userSession = new UserSession(view.getContext());
    }

    @Override
    public void loginUser(String email, String password) {
        model.loginRequestApi(email, password);
    }

    @Override
    public void loginRequestApiResponse(JSONObject response) {
        try {
            String status = response.getString("status");
            String message = response.getString("message");

            if(status.equals("success")){

                JSONObject userData = response.getJSONObject("data");
                int userId = userData.getInt("id");
                String userName = userData.getString("username");
                String userEmail = userData.getString("email");

                userSession.setLoggedIn(true, userId, userName, userEmail);

                view.loginSuccess(message);
            }else{
                view.loginError(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
