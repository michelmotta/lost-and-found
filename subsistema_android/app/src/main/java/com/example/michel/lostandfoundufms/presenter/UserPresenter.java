package com.example.michel.lostandfoundufms.presenter;

import com.example.michel.lostandfoundufms.interfaces.UserInterface;
import com.example.michel.lostandfoundufms.model.UserModel;
import com.example.michel.lostandfoundufms.utils.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

public class UserPresenter implements UserInterface.Presenter{

    private UserInterface.View view;
    private UserInterface.Model model;
    private UserSession userSession;

    public UserPresenter(UserInterface.View view) {
        this.view = view;
        model = new UserModel(this);
        userSession = new UserSession(view.getContext());
    }

    @Override
    public void requestEditUser(int id, String userName, String email, String password) {
        model.requestEditUserApi(id ,userName, email, password);
    }

    @Override
    public void requestEditUserApiResponse(JSONObject response) {
        try {
            String status = response.getString("status");
            String message = response.getString("message");

            if(status.equals("success")){

                JSONObject userInfo = response.getJSONObject("data");
                int id = userInfo.getInt("id");
                String username = userInfo.getString("username");
                String email = userInfo.getString("email");
                userSession.setUserSessionId(id);
                userSession.setUserSessionName(username);
                userSession.setUserSessionEmail(email);

                view.userEditSuccess(message);
            }else{
                view.userEditError(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
